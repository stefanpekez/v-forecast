package example.vforecast.controller.openapi;

import example.vforecast.dto.five_day_forecast.FiveDayForecastTimeSpanGetDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface FiveDayForecastControllerOpenApi {

    @Operation(
            summary = "Find the date time of the first and the last temperature measurement of a forecast"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Average temperature calculated.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FiveDayForecastTimeSpanGetDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Forecast not found.", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    FiveDayForecastTimeSpanGetDto findFiveDayForecastTimeSpanByForecastId(@Parameter(name = "forecastId", description = "Id of the five day forecast for which you want to retrieve the time span") Long forecastId);

}
