package automarket.service;

import automarket.entity.Car;
import automarket.entity.Feature;
import java.util.List;

public interface FeatureService extends Service<Feature> {
    List<Feature> findByFeatureTypeAndFeatureValue(String featureType, String featureValue);
    List<Feature> findPopularFeatures(int limit);
    List<Car> findCarsByFeatureId(long featureId);
    List<Feature> findByFeatureType(String featureType);
}