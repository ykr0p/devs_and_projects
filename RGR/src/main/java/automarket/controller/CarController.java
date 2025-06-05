package automarket.controller;

import automarket.entity.Car;
import automarket.service.CarService;
import automarket.validation.ValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "api/cars")
public class CarController extends AbstractController<Car> {

    @Autowired
    private CarService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Car>> get() {
        return super.get();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable long id) {
        return super.getById(id);
    }

    @GetMapping("/by-brand/{brand}")
    public ResponseEntity<List<Car>> getByBrand(@PathVariable String brand) {
        List<Car> cars = service.findByBrand(brand);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/by-price-range")
    public ResponseEntity<List<Car>> getByPriceRange(
            @RequestParam("min") BigDecimal minPrice,
            @RequestParam("max") BigDecimal maxPrice) {
        List<Car> cars = service.findByPriceBetween(minPrice, maxPrice);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/by-year/{year}")
    public ResponseEntity<List<Car>> getByYear(@PathVariable int year) {
        List<Car> cars = service.findByYear(year);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/by-feature")
    public ResponseEntity<List<Car>> getByFeature(
            @RequestParam String type,
            @RequestParam String value) {
        List<Car> cars = service.findByFeaturesFeatureTypeAndFeaturesFeatureValue(type, value);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return new ResponseEntity<>(service.count(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<String> post(@Validated(ValidationGroups.OnCreate.class) @RequestBody Car entity) {
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PutMapping
    public ResponseEntity<String> put(@Validated(ValidationGroups.OnUpdate.class) @RequestBody Car entity) {
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        getService().delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PostMapping("/{carId}/features/{featureId}")
    public ResponseEntity<String> addFeatureToCar(
            @PathVariable long carId,
            @PathVariable long featureId) {
        service.addFeatureToCar(carId, featureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @DeleteMapping("/{carId}/features/{featureId}")
    public ResponseEntity<String> removeFeatureFromCar(
            @PathVariable long carId,
            @PathVariable long featureId) {
        service.removeFeatureFromCar(carId, featureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public CarService getService() {
        return service;
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<Car>> getByStatus(@PathVariable String status) {
        List<Car> cars = service.findByStatus(status);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @PostMapping("/{id}/{status}")
    public ResponseEntity<String> updateStatus(
            @PathVariable long id,
            @PathVariable String status) {
        service.updateStatus(id, status);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}