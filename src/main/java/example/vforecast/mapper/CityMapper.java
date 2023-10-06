package example.vforecast.mapper;

import example.vforecast.dto.city.CityAverageTempGetDto;
import example.vforecast.dto.city.CityGetDto;
import example.vforecast.model.City;

import java.time.LocalDateTime;

public class CityMapper {

    public static CityGetDto toDto(City city) {
        return new CityGetDto(city.getId(), city.getName(), city.getCountryCode());
    }

    public static City toEntity(CityGetDto city) {
        return new City(city.id(), city.name(), city.countryCode());
    }

    public static CityAverageTempGetDto toAverageTempDto(City city, double averageTemperature, LocalDateTime from, LocalDateTime to) {
        return new CityAverageTempGetDto(
                toDto(city),
                averageTemperature,
                from,
                to
        );
    }

}
