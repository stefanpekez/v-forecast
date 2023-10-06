package example.vforecast.dto.open_weather_map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenWeatherMapCityGetDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String countryCode;

    public OpenWeatherMapCityGetDto() {
    }

    public OpenWeatherMapCityGetDto(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
