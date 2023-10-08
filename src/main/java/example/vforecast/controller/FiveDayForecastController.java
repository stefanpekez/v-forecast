package example.vforecast.controller;

import example.vforecast.controller.openapi.FiveDayForecastControllerOpenApi;
import example.vforecast.dto.five_day_forecast.FiveDayForecastTimeSpanGetDto;
import example.vforecast.service.FiveDayForecastService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/five-day-forecasts")
public class FiveDayForecastController implements FiveDayForecastControllerOpenApi {

    private final FiveDayForecastService fiveDayForecastService;

    public FiveDayForecastController(FiveDayForecastService fiveDayForecastService) {
        this.fiveDayForecastService = fiveDayForecastService;
    }

    @GetMapping("/time-span/{forecastId}")
    public FiveDayForecastTimeSpanGetDto findFiveDayForecastTimeSpanByForecastId(@PathVariable Long forecastId) {
        return this.fiveDayForecastService.findFiveDayForecastTimeSpanByForecastId(forecastId);
    }

}
