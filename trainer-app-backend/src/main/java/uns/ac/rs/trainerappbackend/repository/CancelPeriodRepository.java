package uns.ac.rs.trainerappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.trainerappbackend.model.CancelPeriod;

public interface CancelPeriodRepository extends JpaRepository<CancelPeriod, Long> {
}
