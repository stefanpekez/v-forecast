package example.vforecast.service;

import example.vforecast.dto.FiveDayForecastCreateDto;
import example.vforecast.dto.FiveDayForecastGetDto;
import example.vforecast.dto.TemperatureMeasurementCreateDto;
import example.vforecast.model.TemperatureMeasurement;

import java.util.List;

public interface FiveDayForecastService {

    FiveDayForecastGetDto save(FiveDayForecastCreateDto createDto);
    FiveDayForecastGetDto saveMeasurements(Long forecastId, List<TemperatureMeasurementCreateDto> temperatureMeasurements);

}
