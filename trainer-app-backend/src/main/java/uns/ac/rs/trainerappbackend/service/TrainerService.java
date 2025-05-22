package uns.ac.rs.trainerappbackend.service;

import uns.ac.rs.trainerappbackend.dto.TrainerResponseVerifyDto;
import uns.ac.rs.trainerappbackend.dto.TrainerVerifyDto;

public interface TrainerService {

    TrainerResponseVerifyDto getTrainerID(TrainerVerifyDto trainerVerifyDto);


}
