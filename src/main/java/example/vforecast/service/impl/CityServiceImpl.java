package example.vforecast.service.impl;

import example.vforecast.dto.city.CityAverageTempGetDto;
import example.vforecast.dto.city.CityGetDto;
import example.vforecast.dto.five_day_forecast.FiveDayForecastGetDto;
import example.vforecast.dto.temperature_measurement.TemperatureMeasurementGetDto;
import example.vforecast.mapper.CityMapper;
import example.vforecast.model.City;
import example.vforecast.model.TemperatureMeasurement;
import example.vforecast.repository.CityRepository;
import example.vforecast.service.CityService;
import example.vforecast.service.FiveDayForecastService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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
    public List<CityAverageTempGetDto> findAverageTemperatures(LocalDateTime from, LocalDateTime to) {
        List<CityAverageTempGetDto> cities = new ArrayList<>();

        for (City city: this.cityRepository.findAll()) {
            CityAverageTempGetDto avgTemp = getAverageTemperatureForCity(from, to, city);
            cities.add(avgTemp);
        }

        return cities;
    }

    @Override
    public List<CityAverageTempGetDto> findAverageTemperatures(LocalDateTime from, LocalDateTime to, String cityNames) {
        List<CityAverageTempGetDto> cities = new ArrayList<>();

        for (City city: findCitiesByCityNames(cityNames)) {
            CityAverageTempGetDto avgTemp = getAverageTemperatureForCity(from, to, city);
            cities.add(avgTemp);
        }

        return cities;
    }

    private CityAverageTempGetDto getAverageTemperatureForCity(LocalDateTime from, LocalDateTime to, City city) {
        FiveDayForecastGetDto forecast = this.forecastService.findByCityId(city.getId());

        double averageTemperature = calculateAverageTemperature(forecast.temperatureMeasurements());

        return CityMapper.toAverageTempDto(city, averageTemperature, from, to);
    }

    private double calculateAverageTemperature(List<TemperatureMeasurementGetDto> tempMeasurements) {
        double totalTemperature = 0;
        double averageTemperature;

        for (TemperatureMeasurementGetDto tm: tempMeasurements) {
            totalTemperature += tm.temperature();
        }
        return totalTemperature / tempMeasurements.size();
    }

    private List<City> findCitiesByCityNames(String cityNames) {
        String[] cities = cityNames.split(",");

        List<City> foundCities = new ArrayList<>();
        for (String cityName: cities) {
            City city = this.cityRepository.findByName(cityName).orElseThrow(() ->
                    new NoSuchElementException("City not found")
            );

            foundCities.add(city);
        }

        return foundCities;
    }

}
