package example.vforecast.service.impl;

import example.vforecast.dto.city.CityGetDto;
import example.vforecast.dto.five_day_forecast.FiveDayForecastGetDto;
import example.vforecast.dto.temperature_measurement.TemperatureMeasurementCreateDto;
import example.vforecast.exception.ResourceNotFoundException;
import example.vforecast.mapper.CityMapper;
import example.vforecast.mapper.FiveDayForecastMapper;
import example.vforecast.mapper.TemperatureMeasurementMapper;
import example.vforecast.model.City;
import example.vforecast.model.FiveDayForecast;
import example.vforecast.model.TemperatureMeasurement;
import example.vforecast.repository.FiveDayForecastRepository;
import example.vforecast.service.FiveDayForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiveDayForecastServiceImpl implements FiveDayForecastService {

    private final FiveDayForecastRepository fiveDayForecastRepository;

    public FiveDayForecastServiceImpl(FiveDayForecastRepository fiveDayForecastRepository) {
        this.fiveDayForecastRepository = fiveDayForecastRepository;
    }

    @Override
    public void save(CityGetDto cityGetDto, List<TemperatureMeasurementCreateDto> temperatureMeasurementDtos) {
        City city = CityMapper.toEntity(cityGetDto);
        FiveDayForecast newForecast = new FiveDayForecast(city);

        FiveDayForecast savedForecast = this.fiveDayForecastRepository.save(newForecast);

        List<TemperatureMeasurement> temperatureMeasurements = temperatureMeasurementDtos
                .stream()
                .map(TemperatureMeasurementMapper::toEntity)
                .toList();

        saveMeasurements(savedForecast.getId(), temperatureMeasurements);
    }

    @Override
    public void saveMeasurements(Long forecastId, List<TemperatureMeasurement> temperatureMeasurements) {
        FiveDayForecast fiveDayForecast = this.fiveDayForecastRepository.findById(forecastId).orElseThrow(() ->
                new ResourceNotFoundException("Forecast not found")
        );

        temperatureMeasurements.forEach(tm -> tm.setFiveDayForecast(fiveDayForecast));
        fiveDayForecast.setTemperatureMeasurements(temperatureMeasurements);

        FiveDayForecastMapper.toDto(this.fiveDayForecastRepository.save(fiveDayForecast));
    }

    @Override
    public FiveDayForecastGetDto findByCityId(Long cityId) {
        FiveDayForecast forecast = this.fiveDayForecastRepository.findByCityId(cityId).orElseThrow(() ->
                new ResourceNotFoundException("Forecast not found")
        );

        return FiveDayForecastMapper.toDto(forecast);
    }

}
