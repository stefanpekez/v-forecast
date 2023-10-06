package example.vforecast.service;

import example.vforecast.dto.FiveDayForecastCreateDto;
import example.vforecast.dto.FiveDayForecastGetDto;

public interface FiveDayForecastService {

    FiveDayForecastGetDto save(FiveDayForecastCreateDto createDto);

}
