package example.vforecast.dto.temperature_measurement;

import java.time.LocalDateTime;

public record TemperatureMeasurementGetDto(Long id, double temperature, LocalDateTime measuredAt) {

}
