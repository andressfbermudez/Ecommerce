package com.api.ecommerce.persistence.entity.vehicle;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import com.api.ecommerce.web.dao.VehicleCreateDAO;
import com.api.ecommerce.persistence.entity.Category;
import com.api.ecommerce.persistence.entity.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Vehicle extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, length = 100)
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

//    @CreatedBy
//    protected String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    protected Category category;

    public Vehicle(VehicleCreateDAO vehicleCreateDAO) {
        this.name = vehicleCreateDAO.name();
        this.description = vehicleCreateDAO.description();
        this.price = vehicleCreateDAO.price();
        this.stock = vehicleCreateDAO.stock();
        this.brand = vehicleCreateDAO.brand();
        this.model = vehicleCreateDAO.model();
        this.year = vehicleCreateDAO.year();
        this.mileage = vehicleCreateDAO.mileage();
        this.engineCapacity = vehicleCreateDAO.engineCapacity();
        this.fuelType = vehicleCreateDAO.fuelType();
        this.transmission = vehicleCreateDAO.transmission();
        this.doors = vehicleCreateDAO.doors();
        this.color = vehicleCreateDAO.color();
        this.location = vehicleCreateDAO.location();
        this.category = vehicleCreateDAO.category();
    }
}
