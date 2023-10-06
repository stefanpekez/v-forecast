package example.vforecast.service.impl;

import example.vforecast.dto.CityGetDto;
import example.vforecast.dto.OpenWeatherMapForecastGetDto;
import example.vforecast.mapper.CityMapper;
import example.vforecast.mapper.FiveDayForecast;
import example.vforecast.model.City;
import example.vforecast.service.CityService;
import example.vforecast.service.OpenWeatherMapService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OpenWeatherMapServiceImpl implements OpenWeatherMapService {

    @Value("${open-weather-map.api-key}")
    private String apiKey;

    @Value("${open-weather-map.units}")
    private String units;

    private final CityService cityService;

    public OpenWeatherMapServiceImpl(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    @PostConstruct
    public void findFiveDayForecast() {
        WebClient webClient = WebClient.create();

        for (CityGetDto cityGetDto: this.cityService.findAll()) {
            String cityInfo = cityGetDto.name() + "," + cityGetDto.countryCode();

            WebClient.ResponseSpec responseSpec = webClient
                    .get()
                    .uri("api.openweathermap.org/data/2.5/forecast?appid=" + apiKey + "&q=" + cityInfo + "&units=" + units)
                    .retrieve();

            OpenWeatherMapForecastGetDto openWeatherMapForecastGetDto = responseSpec.bodyToMono(OpenWeatherMapForecastGetDto.class).block();
        }

    }

}
