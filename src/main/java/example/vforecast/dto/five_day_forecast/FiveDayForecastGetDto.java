package example.vforecast.dto.five_day_forecast;

import example.vforecast.dto.city.CityGetDto;

public record FiveDayForecastGetDto(Long id, CityGetDto city) {

}
