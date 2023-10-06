package example.vforecast.dto.open_weather_map;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpenWeatherMapForecastGetDto {

    @JsonProperty("list")
    private List<OpenWeatherMapMeasurementGetDto> measurements;

    public List<OpenWeatherMapMeasurementGetDto> getMeasurements() {
        return measurements;
    }

}
