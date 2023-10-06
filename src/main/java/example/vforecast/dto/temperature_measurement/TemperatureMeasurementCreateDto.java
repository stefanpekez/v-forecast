package example.vforecast.dto.temperature_measurement;

import java.time.LocalDateTime;

public record TemperatureMeasurementCreateDto(double temperature, LocalDateTime measuredAt) {

}
