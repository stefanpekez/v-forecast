package example.vforecast.service;

import example.vforecast.dto.CityGetDto;

import java.util.List;

public interface CityService {

    List<CityGetDto> findAll();

}
