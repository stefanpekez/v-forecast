package example.vforecast.service.impl;

import example.vforecast.dto.city.CityGetDto;
import example.vforecast.dto.five_day_forecast.FiveDayForecastGetDto;
import example.vforecast.dto.temperature_measurement.TemperatureMeasurementCreateDto;
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
import java.util.NoSuchElementException;

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
    public FiveDayForecastGetDto saveMeasurements(Long forecastId, List<TemperatureMeasurement> temperatureMeasurements) {
        FiveDayForecast fiveDayForecast = this.fiveDayForecastRepository.findById(forecastId).orElseThrow(() ->
                new NoSuchElementException("Forecast not found")
        );

        temperatureMeasurements.forEach(tm -> tm.setFiveDayForecast(fiveDayForecast));
        fiveDayForecast.setTemperatureMeasurements(temperatureMeasurements);

        return FiveDayForecastMapper.toDto(this.fiveDayForecastRepository.save(fiveDayForecast));
    }

}
