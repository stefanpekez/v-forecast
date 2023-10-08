package example.vforecast.service;

import example.vforecast.dto.city.CityAverageTempGetDto;
import example.vforecast.dto.city.CityGetDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CityService {

    List<CityGetDto> findAll();
    List<CityAverageTempGetDto> findAverageTemperaturesForAllCities(LocalDateTime from, LocalDateTime to, Optional<String> sortOrder);
    List<CityAverageTempGetDto> findAverageTemperaturesForSpecifiedCities(LocalDateTime from, LocalDateTime to, String cityNames, Optional<String> sortOrder);

}
