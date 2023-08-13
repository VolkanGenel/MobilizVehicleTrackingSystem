package com.volkan.repository;

import com.volkan.repository.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle,Long> {
    @Query(value = "select COUNT(v)>0 from Vehicle v where upper(v.plate)=upper(?1)")
    boolean isPlate(String plate);

}
