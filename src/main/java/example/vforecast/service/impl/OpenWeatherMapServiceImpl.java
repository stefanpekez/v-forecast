package example.vforecast.service.impl;

import example.vforecast.dto.city.CityGetDto;
import example.vforecast.dto.open_weather_map.OpenWeatherMapForecastGetDto;
import example.vforecast.dto.temperature_measurement.TemperatureMeasurementCreateDto;
import example.vforecast.service.CityService;
import example.vforecast.service.FiveDayForecastService;
import example.vforecast.service.OpenWeatherMapService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class OpenWeatherMapServiceImpl implements OpenWeatherMapService {

    @Value("${open-weather-map.api-key}")
    private String apiKey;

    @Value("${open-weather-map.units}")
    private String units;

    private final CityService cityService;
    private final FiveDayForecastService forecastService;

    public OpenWeatherMapServiceImpl(CityService cityService, FiveDayForecastService forecastService) {
        this.cityService = cityService;
        this.forecastService = forecastService;
    }

    @Override
    @PostConstruct
    public void saveFiveDayForecastData() {
        for (CityGetDto city: this.cityService.findAll()) {
            OpenWeatherMapForecastGetDto openWeatherMapForecast = getOpenWeatherMapForecastForCity(city);

            List<TemperatureMeasurementCreateDto> tempMeasurements = extractTemperatureMeasurements(openWeatherMapForecast);

            this.forecastService.save(city, tempMeasurements);
        }
    }

    private OpenWeatherMapForecastGetDto getOpenWeatherMapForecastForCity(CityGetDto city) {
        String cityInfo = city.name() + "," + city.countryCode();

        WebClient webClient = WebClient.create();
        WebClient.ResponseSpec responseSpec = webClient
                .get()
                .uri("api.openweathermap.org/data/2.5/forecast?appid=" + apiKey + "&q=" + cityInfo + "&units=" + units)
                .retrieve();

        return responseSpec.bodyToMono(OpenWeatherMapForecastGetDto.class).block();
    }

    private List<TemperatureMeasurementCreateDto> extractTemperatureMeasurements(OpenWeatherMapForecastGetDto openWeatherMapForecast) {
        return openWeatherMapForecast.getMeasurements()
                .stream()
                .map(m -> new TemperatureMeasurementCreateDto(m.getTemp(), m.getMeasuredAt()))
                .toList();
    }

}
