package example.vforecast.controller.openapi;

import example.vforecast.dto.city.CityAverageTempGetDto;
import example.vforecast.dto.city.CityGetDto;
import example.vforecast.dto.exception.RestApiExceptionGetDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CityControllerOpenApi {

    @Operation(
            summary = "Find all cities"
    )
    List<CityGetDto> findAll();

    @Operation(
            summary = "Find the average temperature of cities for a specific time period",
            description = "Specified time period must be in the scope of the next five days."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Average temperature calculated.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CityAverageTempGetDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "City not found.", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters", content = {
                    @Content(mediaType = "application/json")
            })
    })
    List<CityAverageTempGetDto> findAverageTemperatures(@Parameter(name = "from", description = "Start date time of time period. Use format: yyyy-MM-dd'T'HH:mm") LocalDateTime from,
                                                        @Parameter(name = "to", description = "End date time of time period. Use format: yyyy-MM-dd'T'HH:mm") LocalDateTime to,
                                                        @Parameter(name = "cities", description = "City names separated by a comma. Leave empty to calculate average temperature for all cities.", example = "Subotica,Beograd") Optional<String> cities,
                                                        @Parameter(name = "sort", description = "Sort cities by average temperature. Use 'asc' for ascending or 'desc' for descending sort") Optional<String> sort);

}
