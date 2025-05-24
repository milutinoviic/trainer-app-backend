package uns.ac.rs.trainerappbackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uns.ac.rs.trainerappbackend.dto.*;
import uns.ac.rs.trainerappbackend.service.TrainingService;

import java.util.List;

@RestController
@RequestMapping("/api/trainings")
@CrossOrigin(origins = "http://localhost:4200")
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("/create")
    public ResponseEntity<TrainingDto> createTraining(@Valid @RequestBody CreateTrainingDto dto) {
        TrainingDto createdTraining = trainingService.createTraining(dto);
        return new ResponseEntity<>(createdTraining, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<TrainingDto>> getAllReservations() {
        List<TrainingDto> trainings = trainingService.getAllReservations();
        return ResponseEntity.ok(trainings);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Boolean> reserveTraining(@Valid @RequestBody CreateReservationDto dto) {
        boolean success = trainingService.reserveTraining(dto);
        return ResponseEntity.ok(success);
    }

    @PutMapping("/cancel-training-by-user")
    public ResponseEntity<Boolean> cancelTrainingByUser(@Valid @RequestBody CancelReservationDto dto) {
        boolean canceled = trainingService.cancelTrainingByUser(dto);
        return ResponseEntity.ok(canceled);

    }

    @PutMapping("/cancel-training-by-trainer")
    public ResponseEntity<Boolean> cancelTrainingByTrainer(@Valid @RequestBody CancelReservationAsTrainerDto dto) {
        boolean canceled = trainingService.cancelTrainingByTrainer(dto);
        return ResponseEntity.ok(canceled);

    }

    @GetMapping("/{trainerId}/today-from-now")
    public List<TrainingDto> getTrainingsForTrainerForTodayFromNow(@PathVariable Long trainerId) {
        return trainingService.getTrainingsForTrainerForTodayFromNow(trainerId);
    }

    @GetMapping("/{trainerId}/week")
    public ResponseEntity<List<TrainingDto>> getTrainingsForTrainerForWeek(@PathVariable Long trainerId) {
        List<TrainingDto> trainings = trainingService.getTrainingsForTrainerForCurrentWeek(trainerId);
        return ResponseEntity.ok(trainings);
    }

    @PostMapping("/user")
    public ResponseEntity<List<TrainingDto>> getTrainingsByUserEmail(@RequestBody GetUserTrainingsDto getUserTrainingsDto) {
        List<TrainingDto> trainings = trainingService.getTrainingsByUserEmail(getUserTrainingsDto);
        return ResponseEntity.ok(trainings);
    }

}
