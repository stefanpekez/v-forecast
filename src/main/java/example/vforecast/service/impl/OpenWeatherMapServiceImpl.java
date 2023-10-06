package example.vforecast.service.impl;

import example.vforecast.dto.CityGetDto;
import example.vforecast.dto.FiveDayForecastCreateDto;
import example.vforecast.dto.OpenWeatherMapForecastGetDto;
import example.vforecast.service.CityService;
import example.vforecast.service.FiveDayForecastService;
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
    private final FiveDayForecastService forecastService;

    public OpenWeatherMapServiceImpl(CityService cityService, FiveDayForecastService forecastService) {
        this.cityService = cityService;
        this.forecastService = forecastService;
    }

    @Override
    @PostConstruct
    public void findFiveDayForecast() {
        WebClient webClient = WebClient.create();

        for (CityGetDto city: this.cityService.findAll()) {
            String cityInfo = city.name() + "," + city.countryCode();

            WebClient.ResponseSpec responseSpec = webClient
                    .get()
                    .uri("api.openweathermap.org/data/2.5/forecast?appid=" + apiKey + "&q=" + cityInfo + "&units=" + units)
                    .retrieve();

            OpenWeatherMapForecastGetDto openWeatherMapForecastGetDto = responseSpec.bodyToMono(OpenWeatherMapForecastGetDto.class).block();

            FiveDayForecastCreateDto forecastCreateDto = new FiveDayForecastCreateDto(city);
            this.forecastService.save(forecastCreateDto);
        }

    }

}
