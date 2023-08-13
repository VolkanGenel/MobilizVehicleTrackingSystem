package com.volkan.repository;

import com.volkan.repository.entity.Authorization;
import com.volkan.repository.enums.ECity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorizationRepository extends JpaRepository<Authorization,Long> {
    @Query(value = "select COUNT(a)>0 from Authorization a where a.authorizationId=(?1)")
    boolean isId(String plate);
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Authorization a " +
            "WHERE a.zoneId = :zoneId AND a.sectorId = :sectorId AND a.eCity = :eCity")
    boolean existsByZoneIdAndSectorIdAndECity(Long zoneId, Long sectorId, ECity eCity);

}
