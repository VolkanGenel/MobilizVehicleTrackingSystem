package com.volkan.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.HashSet;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationUserModel implements Serializable {
    @ElementCollection
    @Builder.Default
    HashSet<Long> authorizationIds = new HashSet<>();
    private Long userId;
}
