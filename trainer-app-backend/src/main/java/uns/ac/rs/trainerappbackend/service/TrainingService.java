package uns.ac.rs.trainerappbackend.service;

import uns.ac.rs.trainerappbackend.dto.*;

import java.util.List;

public interface TrainingService {

    TrainingDto createTraining(CreateTrainingDto dto);

    List<TrainingDto> getAllReservations();

    boolean reserveTraining(CreateReservationDto dto);

    boolean cancelTrainingByUser(CancelReservationDto dto);

    boolean cancelTrainingByTrainer(CancelReservationAsTrainerDto dto);

    List<TrainingDto> getTrainingsForTrainerForTodayFromNow(Long trainerId);

    List<TrainingDto> getTrainingsForTrainerForCurrentWeek(Long trainerId);

    List<TrainingDto> getTrainingsByUserEmail(GetUserTrainingsDto getUserTrainingsDto);


}
