package example.vforecast.repository;

import example.vforecast.model.TemperatureMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureMeasurementRepository extends JpaRepository<TemperatureMeasurement, Long> {

}
