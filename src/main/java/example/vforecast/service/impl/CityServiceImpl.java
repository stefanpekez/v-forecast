package example.vforecast.service.impl;

import example.vforecast.dto.city.CityAverageTempGetDto;
import example.vforecast.dto.city.CityGetDto;
import example.vforecast.dto.five_day_forecast.FiveDayForecastGetDto;
import example.vforecast.dto.temperature_measurement.TemperatureMeasurementGetDto;
import example.vforecast.exception.InvalidRequestedTimePeriodException;
import example.vforecast.exception.InvalidSortOrderTypeException;
import example.vforecast.exception.ResourceNotFoundException;
import example.vforecast.mapper.CityMapper;
import example.vforecast.model.City;
import example.vforecast.repository.CityRepository;
import example.vforecast.service.CityService;
import example.vforecast.service.FiveDayForecastService;
import example.vforecast.util.TemperatureMeasurementUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    public List<CityAverageTempGetDto> findAverageTemperaturesForAllCities(LocalDateTime from, LocalDateTime to, Optional<String> sort) {
        List<CityAverageTempGetDto> cities = new ArrayList<>();

        for (City city: this.cityRepository.findAll()) {
            CityAverageTempGetDto avgTemp = getAverageTemperatureForCity(from, to, city);
            cities.add(avgTemp);
        }

        return sort
                .map(s -> sortCities(cities, s))
                .orElse(cities);
    }

    @Override
    public List<CityAverageTempGetDto> findAverageTemperaturesForSpecifiedCities(LocalDateTime from, LocalDateTime to, String cityNames, Optional<String> sort) {
        List<CityAverageTempGetDto> cities = new ArrayList<>();

        for (City city: findCitiesByCityNames(cityNames)) {
            CityAverageTempGetDto avgTemp = getAverageTemperatureForCity(from, to, city);
            cities.add(avgTemp);
        }
        
        return sort
                .map(s -> sortCities(cities, s))
                .orElse(cities);
    }

    private CityAverageTempGetDto getAverageTemperatureForCity(LocalDateTime from, LocalDateTime to, City city) {
        FiveDayForecastGetDto forecast = this.forecastService.findByCityId(city.getId());

        validateRequestedTimePeriod(from, to, forecast.temperatureMeasurements());

        double averageTemperature = calculateAverageTemperature(from, to, forecast.temperatureMeasurements());
        return CityMapper.toAverageTempDto(city, averageTemperature, from, to);
    }

    private double calculateAverageTemperature(LocalDateTime from, LocalDateTime to, List<TemperatureMeasurementGetDto> tempMeasurements) {
        double totalTemperature = 0;
        int counter = 0;

        for (TemperatureMeasurementGetDto tm: tempMeasurements) {
            if (TemperatureMeasurementUtil.isMeasuredAtBetweenOrEqualToDateRange(tm.measuredAt(), from, to)) {
                totalTemperature += tm.temperature();
                ++counter;
            }
        }
        return totalTemperature / counter;
    }

    private List<City> findCitiesByCityNames(String cityNames) {
        String[] cities = cityNames.split(",");

        List<City> foundCities = new ArrayList<>();
        for (String cityName: cities) {
            City city = this.cityRepository.findByName(cityName).orElseThrow(() ->
                    new ResourceNotFoundException("City with name: '" + cityName + "' not found")
            );

            foundCities.add(city);
        }

        return foundCities;
    }

    private List<CityAverageTempGetDto> sortCities(List<CityAverageTempGetDto> cities, String sort) {

        if (sort.equals("asc")) {
            cities.sort(Comparator.comparing(CityAverageTempGetDto::averageTemperature));
        } else if (sort.equals("desc")) {
            cities.sort(Comparator.comparing(CityAverageTempGetDto::averageTemperature).reversed());
        } else {
            throw new InvalidSortOrderTypeException();
        }

        return cities;
    }

    private void validateRequestedTimePeriod(LocalDateTime from, LocalDateTime to, List<TemperatureMeasurementGetDto> tempMeasurements) {
        if (!TemperatureMeasurementUtil.isRequestedTimePeriodValid(from, to, tempMeasurements)) {
            throw new InvalidRequestedTimePeriodException("Requested time period doesn't fit in forecast range");
        }

        if (!TemperatureMeasurementUtil.isRequestedHourAndMinutesValid(from) || !TemperatureMeasurementUtil.isRequestedHourAndMinutesValid(to)) {
            throw new InvalidRequestedTimePeriodException("Make sure that the hours are in a interval of three starting from 00 (12 AM). Make sure the minutes are set to 00");
        }
    }

}
