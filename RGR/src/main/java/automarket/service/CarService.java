package automarket.service;

import automarket.entity.Car;

import java.math.BigDecimal;
import java.util.List;

public interface CarService extends Service<Car> {
    List<Car> findByBrand(String brand);
    List<Car> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);    List<Car> findByYear(int year);
    List<Car> findByFeaturesFeatureTypeAndFeaturesFeatureValue(String featureType, String featureValue);
    long count();
    void addFeatureToCar(long carId, long featureId);
    void removeFeatureFromCar(long carId, long featureId);
    List<Car> findByStatus(String status);
    void updateStatus(Long carId, String status);
}