package example.vforecast.dto.five_day_forecast;

import java.time.LocalDateTime;

public record FiveDayForecastTimeSpanGetDto(LocalDateTime from, LocalDateTime to) {

}
