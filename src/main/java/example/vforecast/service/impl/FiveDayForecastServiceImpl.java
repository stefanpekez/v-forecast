package example.vforecast.service.impl;

import example.vforecast.dto.FiveDayForecastCreateDto;
import example.vforecast.dto.FiveDayForecastGetDto;
import example.vforecast.dto.TemperatureMeasurementCreateDto;
import example.vforecast.mapper.FiveDayForecastMapper;
import example.vforecast.mapper.TemperatureMeasurementMapper;
import example.vforecast.model.FiveDayForecast;
import example.vforecast.model.TemperatureMeasurement;
import example.vforecast.repository.FiveDayForecastRepository;
import example.vforecast.service.FiveDayForecastService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public FiveDayForecastGetDto saveMeasurements(Long forecastId, List<TemperatureMeasurementCreateDto> temperatureMeasurements) {
        FiveDayForecast fiveDayForecast = this.fiveDayForecastRepository.findById(forecastId).orElseThrow(() ->
                new NoSuchElementException("Forecast not found")
        );

        List<TemperatureMeasurement> tempMeasurements = temperatureMeasurements
                .stream()
                .map(TemperatureMeasurementMapper::toEntity)
                .toList();

        tempMeasurements.forEach(tm -> tm.setFiveDayForecast(fiveDayForecast));
        fiveDayForecast.setTemperatureMeasurements(tempMeasurements);

        return FiveDayForecastMapper.toDto(this.fiveDayForecastRepository.save(fiveDayForecast));
    }

}
