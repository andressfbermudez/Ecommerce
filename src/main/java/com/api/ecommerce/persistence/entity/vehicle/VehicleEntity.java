package com.api.ecommerce.persistence.entity.vehicle;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import com.api.ecommerce.persistence.entity.Category;
import com.api.ecommerce.persistence.entity.Auditable;
import com.api.ecommerce.web.dto.vehicledto.VehicleCreateDTO;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class VehicleEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, length = 100, unique = true)
    protected String name;

    @Column(columnDefinition = "TEXT")
    protected String description;

    @Column(nullable = false)
    protected Double price;

    @Column(nullable = false)
    protected Integer stock;

    @Column(nullable = false, length = 100)
    private String brand;

    @Column(nullable = false, length = 100)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer mileage;

    @Column(name = "engine_capacity", nullable = false)
    private Integer engineCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transmission transmission;

    @Column(nullable = false)
    private Integer doors;

    @Column(nullable = false, length = 100)
    private String color;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

//    @CreatedBy
//    protected String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    protected Category category = new Category(1L, "vehicle");

    public VehicleEntity(VehicleCreateDTO vehicleCreateDTO) {
        this.name = vehicleCreateDTO.name().trim();
        this.description = vehicleCreateDTO.description().trim();
        this.price = vehicleCreateDTO.price();
        this.stock = vehicleCreateDTO.stock();
        this.brand = vehicleCreateDTO.brand().trim();
        this.model = vehicleCreateDTO.model().trim();
        this.year = vehicleCreateDTO.year();
        this.mileage = vehicleCreateDTO.mileage();
        this.engineCapacity = vehicleCreateDTO.engineCapacity();
        this.fuelType = vehicleCreateDTO.fuelType();
        this.transmission = vehicleCreateDTO.transmission();
        this.doors = vehicleCreateDTO.doors();
        this.color = vehicleCreateDTO.color().trim();
        this.location = vehicleCreateDTO.location().trim();
    }
}
