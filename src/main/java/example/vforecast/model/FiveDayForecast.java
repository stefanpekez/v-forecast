package example.vforecast.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class FiveDayForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false)
    private City city;
    @OneToMany(mappedBy = "fiveDayForecast")
    private List<TemperatureMeasurement> temperatureMeasurements;

    public FiveDayForecast() {
    }

    public FiveDayForecast(City city) {
        this.city = city;
    }

    public FiveDayForecast(City city, List<TemperatureMeasurement> temperatureMeasurements) {
        this.city = city;
        this.temperatureMeasurements = temperatureMeasurements;
    }

    public Long getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<TemperatureMeasurement> getTemperatureMeasurements() {
        return temperatureMeasurements;
    }

    public void setTemperatureMeasurements(List<TemperatureMeasurement> temperatureMeasurements) {
        this.temperatureMeasurements = temperatureMeasurements;
    }

}
