package uns.ac.rs.trainerappbackend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TrainerProfilDto {

    private Long id;

    private String name;

    private String phone;

    private String email;

    private LocalDateTime createdAt;

    private List<TrainingDto> trainings;
}
