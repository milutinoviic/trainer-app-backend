package uns.ac.rs.trainerappbackend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ac.rs.trainerappbackend.dto.*;
import uns.ac.rs.trainerappbackend.enums.DurationTraining;
import uns.ac.rs.trainerappbackend.enums.ReservationStatus;
import uns.ac.rs.trainerappbackend.exception.BadRequestException;
import uns.ac.rs.trainerappbackend.exception.ResourceNotFoundException;
import uns.ac.rs.trainerappbackend.model.CancelPeriod;
import uns.ac.rs.trainerappbackend.model.Trainer;
import uns.ac.rs.trainerappbackend.model.Training;
import uns.ac.rs.trainerappbackend.model.User;
import uns.ac.rs.trainerappbackend.repository.CancelPeriodRepository;
import uns.ac.rs.trainerappbackend.repository.TrainerRepository;
import uns.ac.rs.trainerappbackend.repository.TrainingRepository;
import uns.ac.rs.trainerappbackend.repository.UserRepository;
import uns.ac.rs.trainerappbackend.service.EmailService;
import uns.ac.rs.trainerappbackend.service.TrainingService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingServiceimpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final TrainerRepository trainerRepository;
    private final CancelPeriodRepository cancelPeriodRepository;
    private final EmailService emailService;

    @Autowired
    public TrainingServiceimpl(TrainingRepository trainingRepository,UserRepository userRepository,TrainerRepository trainerRepository,CancelPeriodRepository cancelPeriodRepository,EmailService emailService) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.trainerRepository = trainerRepository;
        this.cancelPeriodRepository = cancelPeriodRepository;
        this.emailService=emailService;
    }


    @Override
    public TrainingDto createTraining(CreateTrainingDto dto) {

        int startMinute = dto.getStartTime().getMinute();
        if (startMinute != 0 && startMinute != 30) {
            throw new IllegalArgumentException("Start time must be on the hour or half hour (minutes must be 0 or 30).");
        }

        User user = null;
        Trainer trainer = trainerRepository.findById(dto.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + dto.getTrainerId()));


        Training training = new Training();
        training.setUser(user);
        training.setTrainer(trainer);
        training.setStartTime(dto.getStartTime());
        LocalDateTime endTime;
        if (dto.getDuration() == DurationTraining.HALF_HOUR) {
            endTime = dto.getStartTime().plusMinutes(30);
            training.setEndTime(endTime);
        } else {
            endTime = dto.getStartTime().plusMinutes(60);
            training.setEndTime(endTime);
        }
        training.setDurationTraining(dto.getDuration());
        training.setStatus(ReservationStatus.ACTIVE);
        training.setCreatedAt(LocalDateTime.now());

        trainingRepository.save(training);

        return convertToDto(training);
    }


    public TrainingDto convertToDto(Training training) {
        TrainingDto dto = new TrainingDto();
        dto.setId(training.getId());
        dto.setStartTime(training.getStartTime());
        dto.setEndTime(training.getEndTime());
        dto.setStatus(training.getStatus().name());
        dto.setDuration(training.getDurationTraining().name());
        dto.setCreatedAt(training.getCreatedAt());
        dto.setCanceledAt(training.getCanceledAt());

        UserTrainingDto userDto = null;
        if(training.getUser() != null)

        {

            userDto = new UserTrainingDto();
            userDto.setId(training.getUser().getId());
            userDto.setName(training.getUser().getName());
            userDto.setEmail(training.getUser().getEmail());
            userDto.setPhone(training.getUser().getPhone());

        }

        TrainerTrainingDto trainerDto = new TrainerTrainingDto();
        trainerDto.setId(training.getTrainer().getId());
        trainerDto.setName(training.getTrainer().getName());
        trainerDto.setEmail(training.getTrainer().getEmail());

        dto.setUser(userDto);
        dto.setTrainer(trainerDto);
        return dto;
    }

    @Override
    public List<TrainingDto> getAllReservations() {
        List<Training> trainings = trainingRepository.findAll();
        List<TrainingDto> activeReservations = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Training training : trainings) {
           if(training.getStatus().equals(ReservationStatus.ACTIVE)&&
                   training.getStartTime().isAfter(now)){
               activeReservations.add(convertToDto(training));
           }
        }

        return activeReservations;
    }


    @Override
    public boolean reserveTraining(CreateReservationDto dto){
        User user = userRepository.findUserByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + dto.getEmail()));

        Training training = trainingRepository.findById(dto.getTrainingId())
                .orElseThrow(() -> new ResourceNotFoundException("Training not found with id: " + dto.getTrainingId()));

        if (training.getStartTime().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("The training has already started or passed and cannot be reserved.");
        }

        if(training.getStatus().equals(ReservationStatus.RESERVED)){

            throw new BadRequestException("Training is already reserved");

        }
        training.setUser(user);
        training.setStatus(ReservationStatus.RESERVED);
        trainingRepository.save(training);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. 'at' HH:mm");
        String formattedDateTime = training.getStartTime().format(formatter);

        String subject = "Training successfully reserved";
        String body = "Dear " + user.getName() + ",\n\n"
                + "Your training has been successfully reserved for  " + formattedDateTime + "\n";


        emailService.sendEmail(user.getEmail(), subject, body);

        String subject1 = "New Training Reservation";
        String body1 = "Dear " + training.getTrainer().getName() + ",\n\n"
                + "User " + user.getName() + " has successfully reserved a training session scheduled for " + formattedDateTime + ".\n\n"
                + "Best regards,\n"
                + "Your Training App Team";

        emailService.sendEmail(training.getTrainer().getEmail(), subject1, body1);

        return true;


    }

    @Override
    public boolean cancelTrainingByUser(CancelReservationDto dto) {
        Training training = trainingRepository.findById(dto.getTrainingId())
                .orElseThrow(() -> new RuntimeException("Training not found"));

        User user = userRepository.findUserByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + dto.getEmail()));

        if(training.getStatus().equals(ReservationStatus.CANCELED)){
            throw new BadRequestException("Training is already canceled");
        }

        if(!training.getUser().getId().equals(user.getId())){
            throw new BadRequestException("You are not you");
        }

        CancelPeriod cancelPeriod = cancelPeriodRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Cancel period config missing"));


        LocalDateTime cancelDeadline = training.getStartTime().minusHours(cancelPeriod.getCancelLimitHours());
        if (LocalDateTime.now().isAfter(cancelDeadline)) {
            throw new IllegalArgumentException("Ne može se otkazati trening manje  sati pre termina");
        }

        training.setStatus(ReservationStatus.ACTIVE);
        training.setUser(null);
        training.setCanceledAt(LocalDateTime.now());
        trainingRepository.save(training);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. 'at' HH:mm");
        String formattedDateTime = training.getStartTime().format(formatter);

        String subject = "Training successfully canceled";
        String body = "Dear " + user.getName() + ",\n\n"
                + "Your training scheduled for " + formattedDateTime + " has been successfully canceled.\n";

        emailService.sendEmail(user.getEmail(), subject, body);

        return true;
    }

    @Override
    public boolean cancelTrainingByTrainer(CancelReservationAsTrainerDto dto) {
        Training training = trainingRepository.findById(dto.getTrainingId())
                .orElseThrow(() -> new RuntimeException("Training not found"));

        User user = userRepository.findUserByEmail(dto.getEmailUser())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + dto.getEmailUser()));

        if(training.getStatus().equals(ReservationStatus.CANCELED)){
            throw new BadRequestException("Training is already canceled");
        }

        Trainer trainer = trainerRepository.findTrainerByAccessCode(dto.getAccessCode())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));

        if(!training.getTrainer().getId().equals(trainer.getId())){
            throw new BadRequestException("You are not that trainer");

        }

        training.setStatus(ReservationStatus.ACTIVE);
        training.setUser(null);
        training.setCanceledAt(LocalDateTime.now());
        trainingRepository.save(training);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. 'at' HH:mm");
        String formattedDateTime = training.getStartTime().format(formatter);

        String subject = "Training Canceled";
        String body = "Dear " + user.getName() + ",\n\n"
                + "Your training scheduled for " + formattedDateTime + " has been canceled.\n"
                + "For more info, contact your trainer " + trainer.getName() + ".\n\n";

        emailService.sendEmail(user.getEmail(), subject, body);

        return true;
    }

    @Override
    public List<TrainingDto> getTrainingsForTrainerForTodayFromNow(Long trainerId) {

        List<TrainingDto> trainingDtos = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfDay = now.toLocalDate().atTime(23, 59, 59);

        List<Training> trainings = trainingRepository.findByTrainerIdAndStartTimeBetween(trainerId, now, endOfDay);

        for (Training training : trainings) {
            trainingDtos.add(convertToDto(training));
        }

        return trainingDtos;
    }

    @Override
    public List<TrainingDto> getTrainingsForTrainerForCurrentWeek(Long trainerId) {

        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDate sunday = today.with(DayOfWeek.SUNDAY);
        LocalDateTime startOfWeek = monday.atStartOfDay();
        LocalDateTime endOfWeek = sunday.atTime(LocalTime.MAX);


        List<Training> trainings = trainingRepository.findByTrainerIdAndStartTimeBetween(trainerId, startOfWeek, endOfWeek);

        List<TrainingDto> trainingDtos = new ArrayList<>();
        for (Training training : trainings) {
            trainingDtos.add(convertToDto(training));
        }
        return trainingDtos;
    }


    @Override
    public List<TrainingDto> getTrainingsByUserEmail(GetUserTrainingsDto getUserTrainingsDto) {

        List<TrainingDto> trainingDtos = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfDay = now.toLocalDate().atTime(23, 59, 59);

        List<Training> trainings = trainingRepository.findAllByUserEmailOrderByStartTimeAsc(getUserTrainingsDto.getEmail());

        for (Training training : trainings) {
            trainingDtos.add(convertToDto(training));
        }

        return trainingDtos;
    }


}
