package uns.ac.rs.trainerappbackend.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import uns.ac.rs.trainerappbackend.enums.DurationTraining;

import java.time.LocalDateTime;

@Data
public class CreateTrainingDto {

    @NotNull
    private Long trainerId;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private DurationTraining duration;
}
