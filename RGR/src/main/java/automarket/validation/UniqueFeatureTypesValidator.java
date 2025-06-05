package automarket.validation;

import automarket.entity.Feature;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueFeatureTypesValidator implements ConstraintValidator<UniqueFeatureTypes, List<Feature>> {

    @Override
    public boolean isValid(List<Feature> features, ConstraintValidatorContext context) {
        if (features == null || features.isEmpty()) {
            return true;
        }

        Set<String> featureTypes = new HashSet<>();
        for (Feature feature : features) {
            if (!featureTypes.add(feature.getFeatureType())) {
                // Если не удалось добавить (уже существует), возвращаем ошибку
                return false;
            }
        }
        return true;
    }
}