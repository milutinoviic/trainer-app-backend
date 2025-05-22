package uns.ac.rs.trainerappbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CancelPeriod {

    @Id
    private Long id = 1L;

    private Integer cancelLimitHours;
}
