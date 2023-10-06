package example.vforecast.service;

import example.vforecast.dto.CityGetDto;
import example.vforecast.dto.FiveDayForecastCreateDto;
import example.vforecast.dto.FiveDayForecastGetDto;
import example.vforecast.dto.TemperatureMeasurementCreateDto;
import example.vforecast.model.TemperatureMeasurement;

import java.util.List;

public interface FiveDayForecastService {

    void save(CityGetDto city, List<TemperatureMeasurementCreateDto> temperatureMeasurementDtos);
    FiveDayForecastGetDto saveMeasurements(Long forecastId, List<TemperatureMeasurement> temperatureMeasurements);

}
