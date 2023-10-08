package example.vforecast.util;

import example.vforecast.dto.temperature_measurement.TemperatureMeasurementGetDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TemperatureMeasurementUtilTest {

    @Test
    void isMeasuredAtBetweenOrEqualToDateRange_givenMeasuredAtAndFromAndTo_whenMeasuredAtEqualFrom_thenReturnTrue() {
        LocalDateTime measuredAt = LocalDateTime.parse("2023-10-10T18:00");
        LocalDateTime from = LocalDateTime.parse("2023-10-10T18:00");
        LocalDateTime to = LocalDateTime.parse("2023-10-12T09:00");

        boolean isValid = TemperatureMeasurementUtil.isMeasuredAtBetweenOrEqualToDateRange(measuredAt, from, to);

        assertThat(isValid).isTrue();
    }

    @Test
    void isAfterOrEqual_givenDate1AndDate2_whenDate1AfterDate2_thenReturnTrue() {
        LocalDateTime date1 = LocalDateTime.parse("2023-10-16T09:00");
        LocalDateTime date2 = LocalDateTime.parse("2023-10-12T18:00");

        boolean isAfterOrEqual = TemperatureMeasurementUtil.isAfterOrEqual(date1, date2);

        assertThat(isAfterOrEqual).isTrue();
    }

    @Test
    void isBeforeOrEqual_givenDate1AndDate2_whenDate1BeforeDate2_thenReturnTrue() {
        LocalDateTime date1 = LocalDateTime.parse("2023-10-10T18:00");
        LocalDateTime date2 = LocalDateTime.parse("2023-10-12T18:00");

        boolean isBeforeOrEqual = TemperatureMeasurementUtil.isBeforeOrEqual(date1, date2);

        assertThat(isBeforeOrEqual).isTrue();
    }

    @Test
    void isRequestedTimePeriodValid_givenFromAndToAndTempMeasurements_whenFromOutOfRange_thenReturnFalse() {
        List<TemperatureMeasurementGetDto> tempMeasurements = createTemperatureMeasurements();
        LocalDateTime from = LocalDateTime.parse("2023-10-10T15:00");
        LocalDateTime to = LocalDateTime.parse("2023-10-12T18:00");

        boolean isValid = TemperatureMeasurementUtil.isRequestedTimePeriodValid(from, to, tempMeasurements);

        assertThat(isValid).isFalse();
    }

    @Test
    void isRequestedHourAndMinutesValid_givenDate_whenDateIsNotInAnIntervalOfThreeHours_thenReturnFalse() {
        LocalDateTime date = LocalDateTime.parse("2023-10-10T17:00");

        boolean isValid = TemperatureMeasurementUtil.isRequestedHourAndMinutesValid(date);

        assertThat(isValid).isFalse();
    }

    private List<TemperatureMeasurementGetDto> createTemperatureMeasurements() {
        List<TemperatureMeasurementGetDto> tempMeasurements = new ArrayList<>();
        for (int i = 0; i < 40; ++i) {
            TemperatureMeasurementGetDto tempMeasurement;
            if (i != 0) {
                tempMeasurement = new TemperatureMeasurementGetDto((long) i, i+1, tempMeasurements.get(i - 1).measuredAt().plusHours(3));
            } else {
                tempMeasurement = new TemperatureMeasurementGetDto((long) i, i+1, LocalDateTime.parse("2023-10-10T18:00"));
            }

            tempMeasurements.add(tempMeasurement);
        }
        return tempMeasurements;
    }

}