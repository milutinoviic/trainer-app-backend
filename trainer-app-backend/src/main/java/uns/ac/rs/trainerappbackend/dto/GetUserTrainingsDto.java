package uns.ac.rs.trainerappbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetUserTrainingsDto {

    @NotNull
    String email;
}
