package com.volkan.repository.entity;

import com.volkan.repository.enums.ECity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblauthorization")
public class Authorization extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorizationId;
    @Enumerated (EnumType.STRING)
    private ECity eCity;
    private Long zoneId;
    private Long sectorId;
    @ElementCollection
    @Builder.Default
    Set<Long> allowedList = new HashSet<Long>();

}