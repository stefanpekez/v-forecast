package example.vforecast.dto;

import java.time.LocalDateTime;

public record TemperatureMeasurementCreateDto(double temperature, LocalDateTime measuredAt) {

}
