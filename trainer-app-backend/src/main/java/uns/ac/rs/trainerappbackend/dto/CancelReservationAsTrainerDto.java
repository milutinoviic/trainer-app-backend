package uns.ac.rs.trainerappbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancelReservationAsTrainerDto {

    @NotNull
    private String emailUser;

    @NotNull
    private Long trainingId;

    @NotNull
    private String accessCode;
}
