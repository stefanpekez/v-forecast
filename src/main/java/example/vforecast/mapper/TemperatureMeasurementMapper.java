package example.vforecast.mapper;

import example.vforecast.dto.temperature_measurement.TemperatureMeasurementCreateDto;
import example.vforecast.dto.temperature_measurement.TemperatureMeasurementGetDto;
import example.vforecast.model.TemperatureMeasurement;

public class TemperatureMeasurementMapper {

    public static TemperatureMeasurementGetDto toDto(TemperatureMeasurement temperatureMeasurement) {
        return new TemperatureMeasurementGetDto(
                temperatureMeasurement.getId(),
                temperatureMeasurement.getTemperature(),
                temperatureMeasurement.getMeasuredAt()
        );
    }

    public static TemperatureMeasurement toEntity(TemperatureMeasurementCreateDto createDto) {
        return new TemperatureMeasurement(createDto.temperature(), createDto.measuredAt());
    }

}
