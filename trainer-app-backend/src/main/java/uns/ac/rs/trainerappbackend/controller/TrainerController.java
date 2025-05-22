package uns.ac.rs.trainerappbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uns.ac.rs.trainerappbackend.dto.TrainerResponseVerifyDto;
import uns.ac.rs.trainerappbackend.dto.TrainerVerifyDto;
import uns.ac.rs.trainerappbackend.service.TrainerService;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    private TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    @PostMapping("/verify-access-code")
    public ResponseEntity<TrainerResponseVerifyDto> verifyAccessCode(@RequestBody TrainerVerifyDto trainerVerifyDto) {
        TrainerResponseVerifyDto response = trainerService.getTrainerID(trainerVerifyDto);
        return ResponseEntity.ok(response);
    }


}
