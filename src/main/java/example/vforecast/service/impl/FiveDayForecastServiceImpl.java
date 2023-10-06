package example.vforecast.service.impl;

import example.vforecast.dto.FiveDayForecastCreateDto;
import example.vforecast.dto.FiveDayForecastGetDto;
import example.vforecast.mapper.FiveDayForecastMapper;
import example.vforecast.model.FiveDayForecast;
import example.vforecast.repository.FiveDayForecastRepository;
import example.vforecast.service.FiveDayForecastService;
import org.springframework.stereotype.Service;

@Service
public class FiveDayForecastServiceImpl implements FiveDayForecastService {

    private final FiveDayForecastRepository fiveDayForecastRepository;

    public FiveDayForecastServiceImpl(FiveDayForecastRepository fiveDayForecastRepository) {
        this.fiveDayForecastRepository = fiveDayForecastRepository;
    }

    @Override
    public FiveDayForecastGetDto save(FiveDayForecastCreateDto createDto) {
        FiveDayForecast fiveDayForecast = FiveDayForecastMapper.toEntity(createDto);

        return FiveDayForecastMapper.toDto(this.fiveDayForecastRepository.save(fiveDayForecast));
    }

}
