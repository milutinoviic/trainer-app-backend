package uns.ac.rs.trainerappbackend.dto;

import lombok.Data;

@Data
public class CreateReservationDto {

    private String email;

    private Long trainingId;
}
