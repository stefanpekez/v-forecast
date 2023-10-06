package example.vforecast.mapper;

import example.vforecast.dto.city.CityGetDto;
import example.vforecast.model.City;

public class CityMapper {

    public static CityGetDto toDto(City city) {
        return new CityGetDto(city.getId(), city.getName(), city.getCountryCode());
    }

    public static City toEntity(CityGetDto city) {
        return new City(city.id(), city.name(), city.countryCode());
    }

}
