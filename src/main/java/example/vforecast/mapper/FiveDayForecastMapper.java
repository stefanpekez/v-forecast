package example.vforecast.mapper;

import example.vforecast.dto.five_day_forecast.FiveDayForecastCreateDto;
import example.vforecast.dto.five_day_forecast.FiveDayForecastGetDto;
import example.vforecast.model.FiveDayForecast;

public class FiveDayForecastMapper {

    public static FiveDayForecastGetDto toDto(FiveDayForecast fiveDayForecast) {
        return new FiveDayForecastGetDto(
                fiveDayForecast.getId(),
                CityMapper.toDto(fiveDayForecast.getCity()),
                fiveDayForecast.getTemperatureMeasurements()
                        .stream()
                        .map(TemperatureMeasurementMapper::toDto)
                        .toList()
        );
    }

    public static FiveDayForecast toEntity(FiveDayForecastCreateDto createDto) {
        return new FiveDayForecast(CityMapper.toEntity(createDto.city()));
    }

}
