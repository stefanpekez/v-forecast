package example.vforecast.service;

import example.vforecast.dto.city.CityAverageTempGetDto;
import example.vforecast.dto.city.CityGetDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CityService {

    List<CityGetDto> findAll();
    List<CityAverageTempGetDto> findAverageTemperatures(LocalDateTime from, LocalDateTime to);
    List<CityAverageTempGetDto> findAverageTemperatures(LocalDateTime from, LocalDateTime to, String cityNames);

}
