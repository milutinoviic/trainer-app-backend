package uns.ac.rs.trainerappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uns.ac.rs.trainerappbackend.model.Training;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByTrainerIdAndStartTimeBetween(Long trainerId, LocalDateTime start, LocalDateTime end);

    List<Training> findAllByUserEmailOrderByStartTimeAsc(String email);




}
