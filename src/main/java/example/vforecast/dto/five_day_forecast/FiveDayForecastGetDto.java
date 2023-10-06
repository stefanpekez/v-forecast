package example.vforecast.dto.five_day_forecast;

import example.vforecast.dto.city.CityGetDto;
import example.vforecast.dto.temperature_measurement.TemperatureMeasurementGetDto;

import java.util.List;

public record FiveDayForecastGetDto(Long id, CityGetDto city, List<TemperatureMeasurementGetDto> temperatureMeasurements) {

}
