package com.volkan.repository.entity;

import com.volkan.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {
      Long createat;
      Long updateat;
      @Enumerated(EnumType.STRING)
      @Builder.Default
      private EStatus status=EStatus.ACTIVE;
}
