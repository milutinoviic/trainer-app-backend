package uns.ac.rs.trainerappbackend.dto;

import lombok.Data;

@Data
public class CancelReservationDto {

    private String email;

    private Long trainingId;
}
