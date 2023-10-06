CREATE TABLE five_day_forecast (
    id IDENTITY NOT NULL PRIMARY KEY,
    city_id BIGINT NOT NULL
);

ALTER TABLE IF EXISTS five_day_forecast ADD CONSTRAINT fk_city_id FOREIGN KEY (city_id) REFERENCES city (id);
