package example.vforecast.util;

import java.time.LocalDateTime;

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

}
