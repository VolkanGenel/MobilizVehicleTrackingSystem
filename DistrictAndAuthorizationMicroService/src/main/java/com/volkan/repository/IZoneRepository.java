package com.volkan.repository;

import com.volkan.repository.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IZoneRepository extends JpaRepository<Zone,Long> {
    @Query(value = "select COUNT(z)>0 from Zone z where upper(z.name)=upper(?1)")
    boolean isName(String name);
}
