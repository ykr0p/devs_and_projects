package automarket.repository;

import automarket.entity.Car;
import automarket.entity.Feature;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByFeatureTypeAndFeatureValue(String featureType, String featureValue);

    @Query("SELECT f FROM Feature f LEFT JOIN f.cars c GROUP BY f ORDER BY COUNT(c) DESC")
    List<Feature> findPopularFeatures(Pageable pageable);

    @Query("SELECT c FROM Car c JOIN c.features f WHERE f.id = :featureId")
    List<Car> findCarsByFeatureId(long featureId);

    List<Feature> findByFeatureType(String featureType);
}