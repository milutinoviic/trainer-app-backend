package uns.ac.rs.trainerappbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainerVerifyDto {
    @NotNull
    private String accessCode;
}
