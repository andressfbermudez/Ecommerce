package com.api.ecommerce.service;

import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.api.ecommerce.web.dto.VehicleCreateDTO;
import com.api.ecommerce.web.dto.VehicleUpdatedDTO;
import com.api.ecommerce.web.dto.VehicleResponseDTO;
import com.api.ecommerce.persistence.entity.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.ecommerce.persistence.repository.VehicleRepository;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Long createVehicle(VehicleCreateDTO vehicleCreateDTO) {
        Vehicle newVehicle = new Vehicle(vehicleCreateDTO);
        return vehicleRepository.save(newVehicle).getId();
    }

    public List<VehicleResponseDTO> findAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(VehicleResponseDTO::convertToVehicleResponseDAO)
                .toList();
    }

    public VehicleResponseDTO findById(Long id) {
        if (vehicleRepository.existsById(id)) {
            Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
            return VehicleResponseDTO.convertToVehicleResponseDAO(optionalVehicle.get());
        }
        return null;
    }

    @Transactional
    public Long updateVehicle(Long id, VehicleUpdatedDTO v) {
        return vehicleRepository.findById(id).map(vehicle -> {
            if (v.name() != null) vehicle.setName(v.name().trim());
            if (v.description() != null) vehicle.setDescription(v.description().trim());
            if (v.price() != null) vehicle.setPrice(v.price());
            if (v.stock() != null) vehicle.setStock(v.stock());
            if (v.brand() != null) vehicle.setBrand(v.brand().trim());
            if (v.model() != null) vehicle.setModel(v.model().trim());
            if (v.year() != null) vehicle.setYear(v.year());
            if (v.mileage() != null) vehicle.setMileage(v.mileage());
            if (v.engineCapacity() != null) vehicle.setEngineCapacity(v.engineCapacity());
            if (v.transmission() != null) vehicle.setTransmission(v.transmission());
            if (v.doors() != null) vehicle.setDoors(v.doors());
            if (v.color() != null) vehicle.setColor(v.color().trim());
            if (v.location() != null) vehicle.setLocation(v.location().trim());

            return vehicle.getId();
        }).orElse(null);
    }
}
