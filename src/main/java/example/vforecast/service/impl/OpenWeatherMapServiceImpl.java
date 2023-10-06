package example.vforecast.service.impl;

import example.vforecast.dto.CityGetDto;
import example.vforecast.dto.FiveDayForecastCreateDto;
import example.vforecast.dto.FiveDayForecastGetDto;
import example.vforecast.dto.OpenWeatherMapForecastGetDto;
import example.vforecast.dto.OpenWeatherMapMeasurementGetDto;
import example.vforecast.dto.TemperatureMeasurementCreateDto;
import example.vforecast.model.FiveDayForecast;
import example.vforecast.model.TemperatureMeasurement;
import example.vforecast.service.CityService;
import example.vforecast.service.FiveDayForecastService;
import example.vforecast.service.OpenWeatherMapService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
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
    public void findFiveDayForecast() {
        WebClient webClient = WebClient.create();

        for (CityGetDto city: this.cityService.findAll()) {
            String cityInfo = city.name() + "," + city.countryCode();

            WebClient.ResponseSpec responseSpec = webClient
                    .get()
                    .uri("api.openweathermap.org/data/2.5/forecast?appid=" + apiKey + "&q=" + cityInfo + "&units=" + units)
                    .retrieve();

            OpenWeatherMapForecastGetDto forecastGetDto = responseSpec.bodyToMono(OpenWeatherMapForecastGetDto.class).block();

            FiveDayForecastCreateDto forecastCreateDto = new FiveDayForecastCreateDto(city);
            FiveDayForecastGetDto fiveDayForecastGetDto = this.forecastService.save(forecastCreateDto);

            List<TemperatureMeasurementCreateDto> tempMeasurements = mapOpenWeatherMeasurementsToTemperatureMeasurements(forecastGetDto.getMeasurements());

            this.forecastService.saveMeasurements(fiveDayForecastGetDto.id(), tempMeasurements);
        }

    }

    private List<TemperatureMeasurementCreateDto> mapOpenWeatherMeasurementsToTemperatureMeasurements(List<OpenWeatherMapMeasurementGetDto> openWeatherMeasurements) {
        return openWeatherMeasurements
                .stream()
                .map(m -> new TemperatureMeasurementCreateDto(m.getTemp(), m.getMeasuredAt()))
                .toList();
    }

}
