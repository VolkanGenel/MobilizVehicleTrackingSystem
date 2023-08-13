package com.volkan.repository;

import com.volkan.repository.entity.Sector;
import com.volkan.repository.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISectorRepository extends JpaRepository<Sector,Long> {
    @Query(value = "select COUNT(s)>0 from Sector s where upper(s.name)=upper(?1)")
    boolean isName (String name);
}
