package example.vforecast.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class TemperatureMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double temperature;
    @Column(nullable = false)
    private LocalDateTime measuredAt;
    @ManyToOne
    @JoinColumn(name = "five_day_forecast_id", nullable = false)
    private FiveDayForecast fiveDayForecast;

    public TemperatureMeasurement() {
    }

    public TemperatureMeasurement(double temperature, LocalDateTime measuredAt) {
        this.temperature = temperature;
        this.measuredAt = measuredAt;
    }

    public TemperatureMeasurement(Long id, double temperature, LocalDateTime measuredAt) {
        this.id = id;
        this.temperature = temperature;
        this.measuredAt = measuredAt;
    }

    public TemperatureMeasurement(double temperature, LocalDateTime measuredAt, FiveDayForecast fiveDayForecast) {
        this.temperature = temperature;
        this.measuredAt = measuredAt;
        this.fiveDayForecast = fiveDayForecast;
    }

    public Long getId() {
        return id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    public void setMeasuredAt(LocalDateTime measuredAt) {
        this.measuredAt = measuredAt;
    }

    public FiveDayForecast getFiveDayForecast() {
        return fiveDayForecast;
    }

    public void setFiveDayForecast(FiveDayForecast fiveDayForecast) {
        this.fiveDayForecast = fiveDayForecast;
    }

}
