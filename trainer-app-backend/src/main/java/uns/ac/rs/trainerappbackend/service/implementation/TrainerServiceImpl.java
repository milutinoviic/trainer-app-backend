package uns.ac.rs.trainerappbackend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ac.rs.trainerappbackend.dto.TrainerResponseVerifyDto;
import uns.ac.rs.trainerappbackend.dto.TrainerVerifyDto;
import uns.ac.rs.trainerappbackend.exception.ResourceNotFoundException;
import uns.ac.rs.trainerappbackend.model.Trainer;
import uns.ac.rs.trainerappbackend.repository.TrainerRepository;
import uns.ac.rs.trainerappbackend.repository.TrainingRepository;
import uns.ac.rs.trainerappbackend.repository.UserRepository;
import uns.ac.rs.trainerappbackend.service.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository, UserRepository userRepository,TrainingRepository trainingRepository) {
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public TrainerResponseVerifyDto getTrainerID(TrainerVerifyDto trainerVerifyDto) {

        Trainer trainer = trainerRepository.findTrainerByAccessCode(trainerVerifyDto.getAccessCode())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));

        TrainerResponseVerifyDto trainerResponseVerifyDto = new TrainerResponseVerifyDto();
        trainerResponseVerifyDto.setId(trainer.getId());

        return trainerResponseVerifyDto;

    }





}
