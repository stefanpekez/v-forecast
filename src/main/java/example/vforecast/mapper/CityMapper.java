package example.vforecast.mapper;

import example.vforecast.dto.CityGetDto;
import example.vforecast.model.City;

public class CityMapper {

    public static CityGetDto toDto(City city) {
        return new CityGetDto(city.getId(), city.getName(), city.getCountryCode());
    }

}
