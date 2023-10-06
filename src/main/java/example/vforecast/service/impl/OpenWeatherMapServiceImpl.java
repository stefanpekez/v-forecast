package example.vforecast.service.impl;

import example.vforecast.dto.OpenWeatherMapForecastGetDto;
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

    @Override
    @PostConstruct
    public void findFiveDayForecast() {
        WebClient webClient = WebClient.create();
        WebClient.ResponseSpec responseSpec = webClient
                .get()
                .uri("api.openweathermap.org/data/2.5/forecast?appid=" + apiKey + "&q=Beograd,RS&units=" + units)
                .retrieve();

        OpenWeatherMapForecastGetDto openWeatherMapForecastGetDto = responseSpec.bodyToMono(OpenWeatherMapForecastGetDto.class).block();
    }

}
