package com.volkan.repository.entity;

import com.volkan.repository.enums.ERole;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tbluser")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @ElementCollection
    @Builder.Default
    private Set<Long> authorizationIds = new HashSet<>();
    private String name;
    private String surname;
    private String email;
    @Enumerated(EnumType.STRING)
    private ERole role;
    private String password;
}
