package example.vforecast.dto.city;

import java.time.LocalDateTime;

public record CityAverageTempGetDto(CityGetDto city, double averageTemperature, LocalDateTime from, LocalDateTime to) {

}
