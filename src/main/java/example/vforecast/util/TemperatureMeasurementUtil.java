package example.vforecast.util;

import example.vforecast.dto.temperature_measurement.TemperatureMeasurementGetDto;

import java.time.LocalDateTime;
import java.util.List;

public class TemperatureMeasurementUtil {

    public static boolean isMeasuredAtBetweenOrEqualToDateRange(LocalDateTime measuredAt, LocalDateTime from, LocalDateTime to) {
        return isAfterOrEqual(measuredAt, from) && isBeforeOrEqual(measuredAt, to);
    }

    public static boolean isAfterOrEqual(LocalDateTime date1, LocalDateTime date2) {
        return date1.isAfter(date2) || date1.isEqual(date2);
    }

    public static boolean isBeforeOrEqual(LocalDateTime date1, LocalDateTime date2) {
        return date1.isBefore(date2) || date1.isEqual(date2);
    }

    public static boolean isRequestedTimePeriodValid (LocalDateTime from, LocalDateTime to, List<TemperatureMeasurementGetDto> tempMeasurements) {
        LocalDateTime start = tempMeasurements.get(0).measuredAt();
        LocalDateTime end = tempMeasurements.get(39).measuredAt();

        if (from.isAfter(to) || to.isBefore(from)) {
            return false;
        }

        return isAfterOrEqual(from, start) && isBeforeOrEqual(to, end);
    }

}
