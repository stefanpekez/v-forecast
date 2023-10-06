package example.vforecast.service;

import example.vforecast.dto.city.CityGetDto;

import java.util.List;

public interface CityService {

    List<CityGetDto> findAll();

}
