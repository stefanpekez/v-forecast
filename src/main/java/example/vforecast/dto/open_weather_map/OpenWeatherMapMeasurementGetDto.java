package example.vforecast.dto.open_weather_map;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class OpenWeatherMapMeasurementGetDto {

    private LocalDateTime measuredAt;
    private double temperature;

    @JsonProperty("main")
    private void extractTemperature(Map<String, String> tempMeasurements) {
        this.temperature = Double.parseDouble(tempMeasurements.get("temp"));
    }

    @JsonProperty("dt_txt")
    private void extractAndFormatDateOfMeasurement(String dtTxt) {
        this.measuredAt = LocalDateTime.parse(dtTxt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    public double getTemperature() {
        return temperature;
    }

}
