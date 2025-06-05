package automarket.controller;

import automarket.entity.Car;
import automarket.entity.Feature;
import automarket.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/features")
public class FeatureController extends AbstractController<Feature> {

    @Autowired
    private FeatureService service;

    @Override
    @GetMapping
    public ResponseEntity<List<Feature>> get() {
        return super.get();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Feature> getById(@PathVariable long id) {
        return super.getById(id);
    }

    @GetMapping("/by-type-and-value")
    public ResponseEntity<List<Feature>> getByTypeAndValue(
            @RequestParam String type,
            @RequestParam String value) {
        List<Feature> features = service.findByFeatureTypeAndFeatureValue(type, value);
        if (features.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(features, HttpStatus.OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Feature>> getPopularFeatures(
            @RequestParam(defaultValue = "5") int limit) {
        return new ResponseEntity<>(service.findPopularFeatures(limit), HttpStatus.OK);
    }

    @GetMapping("/{id}/cars")
    public ResponseEntity<List<Car>> getCarsByFeatureId(@PathVariable long id) {
        return new ResponseEntity<>(service.findCarsByFeatureId(id), HttpStatus.OK);
    }

    @GetMapping("/by-type/{type}")
    public ResponseEntity<List<Feature>> getByType(@PathVariable String type) {
        return new ResponseEntity<>(service.findByFeatureType(type), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> post(@Valid @RequestBody Feature entity) {
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<String> put(@Valid @RequestBody Feature entity) {
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        getService().delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public FeatureService getService() {
        return service;
    }
}