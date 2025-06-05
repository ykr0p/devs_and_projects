package automarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "features")
public class Feature extends AbstractEntity {
    @NotBlank(message = "Тип характеристики не может быть пустым")
    @Size(max = 30, message = "Тип характеристики не может быть длиннее 30 символов")
    @Column(nullable = false)
    private String featureType;

    @NotBlank(message = "Значение характеристики не может быть пустым")
    @Size(max = 50, message = "Значение характеристики не может быть длиннее 50 символов")
    @Column(nullable = false)
    private String featureValue;

    @ManyToMany(mappedBy = "features", fetch = FetchType.EAGER) // Изменили с tags на features
    @JsonIgnoreProperties("features")
    private List<Car> cars = new ArrayList<>();


    public String getFeatureType() { return featureType; }
    public void setFeatureType(String featureType) { this.featureType = featureType; }
    public String getFeatureValue() { return featureValue; }
    public void setFeatureValue(String featureValue) { this.featureValue = featureValue; }
    public List<Car> getCars() { return cars; }
    public void setCars(List<Car> cars) { this.cars = cars; }
}