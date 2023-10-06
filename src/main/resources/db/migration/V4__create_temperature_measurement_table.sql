CREATE TABLE temperature_measurement (
    id IDENTITY NOT NULL PRIMARY KEY,
    temperature DOUBLE PRECISION NOT NULL,
    measured_at TIMESTAMP NOT NULL,
    five_day_forecast_id BIGINT NOT NULL
);

ALTER TABLE IF EXISTS temperature_measurement ADD CONSTRAINT fk_five_day_forecast_id FOREIGN KEY (five_day_forecast_id) REFERENCES five_day_forecast (id);
