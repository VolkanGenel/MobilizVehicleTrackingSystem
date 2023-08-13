package com.volkan.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.volkan.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {
    @NotBlank(message="İsim boş geçilemez")
    private String name;
    @NotBlank(message="Soyisim boş geçilemez")
    private String surname;
    @Email(message = "Lütfen geçerli bir email adresi giriniz")
    private String email;
    @NotBlank(message="Şifre boş geçilemez")
    @Size(min=8, max=64)
    @Pattern( message = "Şifre en az 8 karakter olmalı, içinde en az bir büyük bir küçük harf ve rakam olamalıdır.",
            regexp = "^(?=.*[0-12])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ERole role = ERole.STANDARD;
}
