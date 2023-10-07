package example.vforecast.controller;

import example.vforecast.controller.openapi.CityControllerOpenApi;
import example.vforecast.dto.city.CityAverageTempGetDto;
import example.vforecast.dto.city.CityGetDto;
import example.vforecast.service.CityService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController implements CityControllerOpenApi {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<CityGetDto> findAll() {
        return this.cityService.findAll();
    }

    @GetMapping("/average-temperature")
    public List<CityAverageTempGetDto> findAverageTemperatures(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime from,
                                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime to,
                                                               @RequestParam Optional<String> cities,
                                                               @RequestParam Optional<String> sort) {
        if (cities.isPresent()) {
            return this.cityService.findAverageTemperatures(from, to, cities.get(), sort);
        }

        return this.cityService.findAverageTemperatures(from, to, sort);
    }

}
