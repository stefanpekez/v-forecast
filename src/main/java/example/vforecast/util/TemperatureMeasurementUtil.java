package example.vforecast.util;

import example.vforecast.dto.temperature_measurement.TemperatureMeasurementGetDto;
import example.vforecast.exception.InvalidRequestedTimePeriodException;

import java.time.LocalDateTime;
import java.util.List;

public class TemperatureMeasurementUtil {

    private static final int[] SUPPORTED_HOURS = {0, 3, 6, 9, 12, 15, 18, 21};

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

        if (from.isAfter(to)) {
            throw new InvalidRequestedTimePeriodException("'from' and 'to' dates must be in chronological order or the same");
        }

        return isAfterOrEqual(from, start) && isBeforeOrEqual(to, end);
    }

    public static boolean isRequestedHourAndMinutesValid(LocalDateTime dateTime) {
        boolean isValid = false;
        for (int hour: SUPPORTED_HOURS) {
            if (dateTime.getHour() == hour && dateTime.getMinute() == 0) {
                isValid = true;
                break;
            }
        }

        return isValid;
    }

}
