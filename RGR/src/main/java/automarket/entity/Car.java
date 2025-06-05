package automarket.entity;

import automarket.validation.UniqueFeatureTypes;
import automarket.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends AbstractEntity {
    @NotBlank(message = "Бренд не может быть пустым", groups = ValidationGroups.OnCreate.class)
    @Size(max = 50, message = "Бренд не может быть длиннее 50 символов", groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    private String brand;

    @NotBlank(message = "Модель не может быть пустой", groups = ValidationGroups.OnCreate.class)
    @Size(max = 50, message = "Модель не может быть длиннее 50 символов", groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    private String model;

    @NotNull(message = "Год не может быть пустым", groups = ValidationGroups.OnCreate.class)
    @Min(value = 1900, message = "Год должен быть не меньше 1900", groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    @Max(value = 2023, message = "Год должен быть не больше 2023", groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    private Integer year;

    @NotNull(message = "Цена не может быть пустой", groups = ValidationGroups.OnCreate.class)
    @DecimalMin(value = "0.0", message = "Цена не может быть отрицательной", groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    private BigDecimal price;


    @NotNull(message = "Статус не может быть пустым", groups = ValidationGroups.OnCreate.class)
    private String status = "ON_SALE";

    @AssertTrue(message = "Недопустимый статус. Допустимые значения: ON_SALE, BOOKED, SOLD", groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    @JsonIgnore
    public boolean isValidStatus() {
        return status != null && (status.equals("ON_SALE") || status.equals("BOOKED") || status.equals("SOLD"));
    }

    @UniqueFeatureTypes(groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "car_features",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id"))
    @JsonIgnoreProperties("cars")
    private List<Feature> features = new ArrayList<>();


    public java.lang.String getBrand() { return brand; }
    public void setBrand(java.lang.String brand) { this.brand = brand; }
    public java.lang.String getModel() { return model; }
    public void setModel(java.lang.String model) { this.model = model; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public List<Feature> getFeatures() { return features; }
    public void setFeatures(List<Feature> features) { this.features = features; }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}