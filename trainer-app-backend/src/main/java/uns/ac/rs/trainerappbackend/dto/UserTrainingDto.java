package uns.ac.rs.trainerappbackend.dto;

import lombok.Data;

@Data
public class UserTrainingDto {
    private Long id;

    private String name;

    private String phone;

    private String email;
}
