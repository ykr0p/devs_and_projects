package automarket.repository;

import automarket.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrand(String brand);
    List<Car> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);    List<Car> findByYear(int year);
    List<Car> findByFeaturesFeatureTypeAndFeaturesFeatureValue(String featureType, String featureValue);
    List<Car> findByStatus(String status);

    @Query("SELECT COUNT(c) FROM Car c")
    long count();
}