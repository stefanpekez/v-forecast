package example.vforecast.mapper;

import example.vforecast.dto.FiveDayForecastCreateDto;
import example.vforecast.dto.FiveDayForecastGetDto;
import example.vforecast.model.FiveDayForecast;

public class FiveDayForecastMapper {

    public static FiveDayForecastGetDto toDto(FiveDayForecast fiveDayForecast) {
        return new FiveDayForecastGetDto(fiveDayForecast.getId(), CityMapper.toDto(fiveDayForecast.getCity()));
    }

    public static FiveDayForecast toEntity(FiveDayForecastCreateDto createDto) {
        return new FiveDayForecast(CityMapper.toEntity(createDto.city()));
    }

}
