package example.vforecast.repository;

import example.vforecast.model.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void findByName_givenCityName_whenCityExists_thenReturnCity() {
        City city = new City(1L, "Beograd", "RS");
        cityRepository.save(city);

        Optional<City> foundCityOpt = cityRepository.findByName("Beograd");

        assertThat(foundCityOpt).isNotEmpty();
        assertThat(foundCityOpt.get().getId()).isEqualTo(1L);
        assertThat(foundCityOpt.get().getName()).isEqualTo("Beograd");
    }

}