package uns.ac.rs.trainerappbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancelReservationDto {

    @NotNull
    private String email;

    @NotNull
    private Long trainingId;
}
