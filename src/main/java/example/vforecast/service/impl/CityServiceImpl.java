package example.vforecast.service.impl;

import example.vforecast.dto.city.CityAverageTempGetDto;
import example.vforecast.dto.city.CityGetDto;
import example.vforecast.dto.five_day_forecast.FiveDayForecastGetDto;
import example.vforecast.dto.temperature_measurement.TemperatureMeasurementGetDto;
import example.vforecast.mapper.CityMapper;
import example.vforecast.model.City;
import example.vforecast.repository.CityRepository;
import example.vforecast.service.CityService;
import example.vforecast.service.FiveDayForecastService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final FiveDayForecastService forecastService;

    public CityServiceImpl(CityRepository cityRepository, FiveDayForecastService forecastService) {
        this.cityRepository = cityRepository;
        this.forecastService = forecastService;
    }

    @Override
    public List<CityGetDto> findAll() {
        return this.cityRepository.findAll()
                .stream()
                .map(CityMapper::toDto)
                .toList();
    }

    @Override
    public List<CityAverageTempGetDto> findAverageTemperatures() {
        List<CityAverageTempGetDto> cities = new ArrayList<>();

        double totalTemperature = 0;
        double averageTemperature;
        for (City city: this.cityRepository.findAll()) {
            FiveDayForecastGetDto forecast = this.forecastService.findByCityId(city.getId());
            for (TemperatureMeasurementGetDto tempMeasurement: forecast.temperatureMeasurements()) {
                totalTemperature += tempMeasurement.temperature();
            }
            averageTemperature = totalTemperature / forecast.temperatureMeasurements().size();


            CityAverageTempGetDto cityAverageTemp = CityMapper.toAverageTempDto(city, averageTemperature);
            cities.add(cityAverageTemp);
        }

        return cities;
    }

    @Override
    public List<CityAverageTempGetDto> findAverageTemperatures(String cities) {
        return null;
    }

}
