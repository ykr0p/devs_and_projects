package automarket.service.impl;

import automarket.entity.Car;
import automarket.entity.Feature;
import automarket.repository.CarRepository;
import automarket.repository.FeatureRepository;
import automarket.service.CarService;
import automarket.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository repository;

    @Autowired  // Добавь это
    private FeatureService featureService;

    @Override
    public Car read(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Car> read() {
        return repository.findAll();
    }

    @Override
    public void save(Car entity) {
        if (entity.getFeatures() != null) {
            List<Feature> managedFeatures = new ArrayList<>();

            for (Feature feature : entity.getFeatures()) { // Ищем существующую характеристику
                List<Feature> existing = featureService.findByFeatureTypeAndFeatureValue(
                        feature.getFeatureType(),
                        feature.getFeatureValue()
                );

                if (!existing.isEmpty()) {                 // Используем существующую
                    managedFeatures.add(existing.get(0));
                } else {                                   // Или сохраняем новую
                    featureService.save(feature);
                    managedFeatures.add(feature);
                }
            }
            entity.setFeatures(managedFeatures);
        }
        repository.save(entity);
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Override
    public List<Car> findByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    @Override
    public List<Car> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Car> findByYear(int year) {
        return carRepository.findByYear(year);
    }

    @Override
    public List<Car> findByFeaturesFeatureTypeAndFeaturesFeatureValue(String featureType, String featureValue) {
        return carRepository.findByFeaturesFeatureTypeAndFeaturesFeatureValue(featureType, featureValue);
    }

    @Override
    public long count() {
        return carRepository.count();
    }

    @Override
    public void addFeatureToCar(long carId, long featureId) {
        Car car = carRepository.findById(carId).orElseThrow();
        Feature feature = featureRepository.findById(featureId).orElseThrow();
        car.getFeatures().add(feature);
        carRepository.save(car);
    }

    @Override
    public void removeFeatureFromCar(long carId, long featureId) {
        Car car = carRepository.findById(carId).orElseThrow();
        car.getFeatures().removeIf(feature -> feature.getId().equals(featureId));
        carRepository.save(car);
    }

    @Override
    public List<Car> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    @Override
    public void updateStatus(Long carId, String status) {
        Car car = repository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        car.setStatus(status);
        repository.save(car);
    }
}