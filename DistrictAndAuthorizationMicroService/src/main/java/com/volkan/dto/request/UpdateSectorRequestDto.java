package com.volkan.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSectorRequestDto {
    @NotBlank(message = "İsim alanı girmek zorunludur")
    @Column(unique = true)
    private String name;
}
