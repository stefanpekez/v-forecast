package example.vforecast.repository;

import example.vforecast.model.FiveDayForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiveDayForecastRepository extends JpaRepository<FiveDayForecast, Long> {

    Optional<FiveDayForecast> findByCityId(Long cityId);

}
