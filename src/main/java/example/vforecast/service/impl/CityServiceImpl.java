package example.vforecast.service.impl;

import example.vforecast.dto.city.CityGetDto;
import example.vforecast.mapper.CityMapper;
import example.vforecast.repository.CityRepository;
import example.vforecast.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityGetDto> findAll() {
        return this.cityRepository.findAll()
                .stream()
                .map(CityMapper::toDto)
                .toList();
    }

}
