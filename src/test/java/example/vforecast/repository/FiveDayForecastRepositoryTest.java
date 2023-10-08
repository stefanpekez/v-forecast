package example.vforecast.repository;

import example.vforecast.model.City;
import example.vforecast.model.FiveDayForecast;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FiveDayForecastRepositoryTest {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private FiveDayForecastRepository fiveDayForecastRepository;

    @Test
    void findByCityId_givenCityId_whenFiveDayForecastExists_thenReturnFiveDayForecast() {
        City city = new City(1L, "Beograd", "RS");
        City savedCity = cityRepository.save(city);
        FiveDayForecast forecast = new FiveDayForecast(savedCity);
        fiveDayForecastRepository.save(forecast);

        Optional<FiveDayForecast> foundForecastOpt = fiveDayForecastRepository.findByCityId(savedCity.getId());

        assertThat(foundForecastOpt).isNotEmpty();
        assertThat(foundForecastOpt.get().getCity().getId()).isEqualTo(savedCity.getId());
    }

}