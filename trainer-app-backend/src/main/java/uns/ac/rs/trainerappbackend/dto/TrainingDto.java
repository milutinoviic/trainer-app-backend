package uns.ac.rs.trainerappbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String duration;
    private LocalDateTime createdAt;
    private LocalDateTime canceledAt;
    private UserTrainingDto user;
    private TrainerTrainingDto trainer;

}
