package example.vforecast.controller;

import example.vforecast.dto.city.CityAverageTempGetDto;
import example.vforecast.dto.city.CityGetDto;
import example.vforecast.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<CityGetDto> findAll() {
        return this.cityService.findAll();
    }

    @GetMapping("/average-temperature")
    public List<CityAverageTempGetDto> findAverageTemperatures(@RequestParam LocalDateTime from,
                                                               @RequestParam LocalDateTime to,
                                                               @RequestParam Optional<String> cities) {
        if (cities.isPresent()) {
            return this.cityService.findAverageTemperatures(cities.get());
        }

        return this.cityService.findAverageTemperatures();
    }

}
