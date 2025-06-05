package automarket.service.impl;

import automarket.entity.Car;
import automarket.entity.Feature;
import automarket.repository.FeatureRepository;
import automarket.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class FeatureServiceImpl implements FeatureService {
    @Autowired
    private FeatureRepository repository;

    @Override
    public Feature read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Feature> read() {
        return repository.findAll();
    }

    @Override
    public void save(Feature entity) {
        repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Feature> findByFeatureTypeAndFeatureValue(String featureType, String featureValue) {
        List<Feature> features = repository.findByFeatureTypeAndFeatureValue(featureType, featureValue);
        if (features.isEmpty()) {
            throw new EntityNotFoundException(
                    "Характеристика не найдена: тип='" + featureType + "', значение='" + featureValue + "'"
            );
        }
        return features;
    }

    @Override
    public List<Feature> findPopularFeatures(int limit) {
        return repository.findPopularFeatures(PageRequest.of(0, limit));
    }

    @Override
    public List<Car> findCarsByFeatureId(long featureId) {
        return repository.findCarsByFeatureId(featureId);
    }

    @Override
    public List<Feature> findByFeatureType(String featureType) {
        return repository.findByFeatureType(featureType);
    }
}