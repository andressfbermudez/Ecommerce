package com.api.ecommerce.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.ecommerce.persistence.entity.vehicle.VehicleEntity;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    List<VehicleEntity> findByIsActiveTrue();
    Optional<VehicleEntity> findByIdAndIsActiveTrue(Long id);
    boolean existsByIdAndIsActiveTrue(Long id);

    @Query(value = """
        SELECT * FROM vehicles
        WHERE (:brand IS NULL OR LOWER(brand) LIKE CONCAT('%', LOWER(:brand), '%'))
          AND (:model IS NULL OR LOWER(model) LIKE CONCAT('%', LOWER(:model), '%'))
          AND (:year IS NULL OR year = :year)
          AND (:price IS NULL OR price = :price)
          AND (:doors IS NULL OR doors = :doors)
          AND (:color IS NULL OR LOWER(color) LIKE CONCAT('%', LOWER(:color), '%'))
          AND (:location IS NULL OR LOWER(location) LIKE CONCAT('%', LOWER(:location), '%'))
          AND is_active = TRUE
        """, nativeQuery = true)
    List<VehicleEntity> search(String brand, String model, Integer year,
                               Double price, Integer doors, String color, String location);
}
