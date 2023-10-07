package example.vforecast.service;

import example.vforecast.dto.city.CityGetDto;
import example.vforecast.dto.five_day_forecast.FiveDayForecastGetDto;
import example.vforecast.dto.temperature_measurement.TemperatureMeasurementCreateDto;
import example.vforecast.model.TemperatureMeasurement;

import java.util.List;

public interface FiveDayForecastService {

    void save(CityGetDto city, List<TemperatureMeasurementCreateDto> temperatureMeasurementDtos);
    void saveMeasurements(Long forecastId, List<TemperatureMeasurement> temperatureMeasurements);
    FiveDayForecastGetDto findByCityId(Long cityId);

}
