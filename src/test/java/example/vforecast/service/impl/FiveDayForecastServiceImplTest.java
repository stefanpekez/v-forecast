package example.vforecast.service.impl;

import example.vforecast.dto.five_day_forecast.FiveDayForecastGetDto;
import example.vforecast.dto.five_day_forecast.FiveDayForecastTimeSpanGetDto;
import example.vforecast.model.City;
import example.vforecast.model.FiveDayForecast;
import example.vforecast.model.TemperatureMeasurement;
import example.vforecast.repository.FiveDayForecastRepository;
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
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FiveDayForecastServiceImplTest {
    @Mock
    private FiveDayForecastRepository forecastRepository;
    @InjectMocks
    private FiveDayForecastServiceImpl forecastService;
    private City beograd;
    private FiveDayForecast forecast;
    private List<TemperatureMeasurement> tempMeasurements = new ArrayList<>();

    @BeforeEach
    void setUp() {
        beograd = new City(1L, "Beograd", "RS");
        forecast = new FiveDayForecast(1L, beograd);

        for (int i = 0; i < 40; ++i) {
            TemperatureMeasurement tempMeasurement;
            if (i != 0) {
                tempMeasurement = new TemperatureMeasurement((long) i, i+1, tempMeasurements.get(i - 1).getMeasuredAt().plusHours(3));
            } else {
                tempMeasurement = new TemperatureMeasurement((long) i, i+1, LocalDateTime.parse("2023-10-10T18:00"));
            }

            tempMeasurements.add(tempMeasurement);
        }

        forecast.setTemperatureMeasurements(tempMeasurements);
    }

    @Test
    void findByCityId_givenCityId_whenFiveDayForecastExists_thenReturnFiveDayForecast() {
        given(forecastRepository.findByCityId(beograd.getId())).willReturn(Optional.ofNullable(forecast));

        FiveDayForecastGetDto forecastGetDto = forecastService.findByCityId(1L);

        assertThat(forecastGetDto.city().id()).isEqualTo(beograd.getId());
        assertThat(forecastGetDto.id()).isEqualTo(forecast.getId());
    }

    @Test
    void findFiveDayForecastTimeSpanByForecastId_givenFiveDayForecastId_whenFiveDayForecastExists_thenReturnFiveDayForecastTimeSpan() {
        given(forecastRepository.findById(1L)).willReturn(Optional.ofNullable(forecast));

        FiveDayForecastTimeSpanGetDto forecastTimeSpanGetDto = forecastService.findFiveDayForecastTimeSpanByForecastId(1L);
        LocalDateTime expectedStartTime = LocalDateTime.parse("2023-10-10T18:00");

        assertThat(forecastTimeSpanGetDto.from()).isEqualTo(expectedStartTime);
        assertThat(forecastTimeSpanGetDto.to()).isEqualTo(expectedStartTime.plusHours(39 * 3));
    }

}