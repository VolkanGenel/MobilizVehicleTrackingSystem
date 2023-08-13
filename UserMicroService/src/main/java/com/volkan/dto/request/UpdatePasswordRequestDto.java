package com.volkan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequestDto {
    @NotBlank(message="Şifre boş geçilemez")
    @Size(min=8, max=64)
    @Pattern( message = "Şifre en az 8 karakter olmalı, içinde en az bir büyük bir küçük harf ve rakam olamalıdır.",
            regexp = "^(?=.*[0-12])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    String password;
    @NotBlank(message="Şifre boş geçilemez")
    @Size (min=8, max=64)
    @Pattern( message = "Şifre en az 8 karakter olmalı, içinde en az bir büyük bir küçük harf ve rakam olamalıdır.",
            regexp = "^(?=.*[0-12])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    String rePassword;
}
