package example.vforecast.service.impl;

import example.vforecast.dto.city.CityAverageTempGetDto;
import example.vforecast.dto.city.CityGetDto;
import example.vforecast.dto.five_day_forecast.FiveDayForecastGetDto;
import example.vforecast.mapper.CityMapper;
import example.vforecast.mapper.FiveDayForecastMapper;
import example.vforecast.model.City;
import example.vforecast.model.FiveDayForecast;
import example.vforecast.model.TemperatureMeasurement;
import example.vforecast.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private CityServiceImpl cityService;
    @Mock
    private FiveDayForecastServiceImpl forecastService;
    private List<City> cities;
    private City beograd;
    private FiveDayForecast forecast;
    private FiveDayForecastGetDto forecastGetDto;

    @BeforeEach
    void setUp() {
        beograd = new City(1L, "Beograd", "RS");
        City noviSad = new City(2L, "Novi Sad", "RS");
        City subotica = new City(3L, "Subotica", "RS");

        cities = new ArrayList<>();
        cities.add(beograd);
        cities.add(noviSad);
        cities.add(subotica);

        List<TemperatureMeasurement> tempMeasurements = new ArrayList<>();
        for (int i = 0; i < 40; ++i) {
            TemperatureMeasurement tempMeasurement;
            if (i != 0) {
                tempMeasurement = new TemperatureMeasurement((long) i, i+1, tempMeasurements.get(i - 1).getMeasuredAt().plusHours(3));
            } else {
                tempMeasurement = new TemperatureMeasurement((long) i, i+1, LocalDateTime.parse("2023-10-10T18:00"));
            }

            tempMeasurements.add(tempMeasurement);
        }

        forecast = new FiveDayForecast(beograd);
        forecast.setTemperatureMeasurements(tempMeasurements);
        forecastGetDto = FiveDayForecastMapper.toDto(forecast);
    }

    @Test
    void findAll_whenCitiesExists_thenReturnAllCities() {
        given(cityRepository.findAll()).willReturn(cities);

        List<CityGetDto> cityGetDtos = cityRepository.findAll()
                .stream()
                .map(CityMapper::toDto)
                .toList();

        assertThat(cityGetDtos).isNotEmpty();
        assertThat(cityGetDtos.size()).isEqualTo(3);
        assertThat(cityGetDtos.get(0).id()).isEqualTo(1L);
        assertThat(cityGetDtos.get(0).name()).isEqualTo("Beograd");
        assertThat(cityGetDtos.get(2).id()).isEqualTo(3L);
        assertThat(cityGetDtos.get(2).name()).isEqualTo("Subotica");
    }

    @Test
    void findAverageTemperatures_givenFromAndTo_whenTemperatureMeasurementsExist_thenReturnAverageTemperaturesForAllCities() {
        given(cityRepository.findAll()).willReturn(cities);
        given(forecastService.findByCityId(anyLong())).willReturn(forecastGetDto);
        LocalDateTime from = LocalDateTime.parse("2023-10-10T18:00");

        List<CityAverageTempGetDto> cityAverageTemps = cityService.findAverageTemperaturesForAllCities(from, from.plusHours(3 * 39), Optional.empty());

        assertThat(cityAverageTemps.size()).isEqualTo(3);
        assertThat(cityAverageTemps.get(0).city().id()).isEqualTo(beograd.getId());
        assertThat(cityAverageTemps.get(0).averageTemperature()).isEqualTo(20.5);
    }

}