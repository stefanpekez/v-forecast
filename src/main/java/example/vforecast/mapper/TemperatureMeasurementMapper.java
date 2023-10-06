package example.vforecast.mapper;

import example.vforecast.dto.TemperatureMeasurementCreateDto;
import example.vforecast.model.TemperatureMeasurement;

public class TemperatureMeasurementMapper {

    public static TemperatureMeasurement toEntity(TemperatureMeasurementCreateDto createDto) {
        return new TemperatureMeasurement(createDto.temperature(), createDto.measuredAt());
    }

}
