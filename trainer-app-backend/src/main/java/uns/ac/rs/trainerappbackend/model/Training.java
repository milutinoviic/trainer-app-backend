package uns.ac.rs.trainerappbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import uns.ac.rs.trainerappbackend.enums.DurationTraining;
import uns.ac.rs.trainerappbackend.enums.ReservationStatus;

import java.time.LocalDateTime;

@Data
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DurationTraining durationTraining;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createdAt;

    private LocalDateTime canceledAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Trainer trainer;
}
