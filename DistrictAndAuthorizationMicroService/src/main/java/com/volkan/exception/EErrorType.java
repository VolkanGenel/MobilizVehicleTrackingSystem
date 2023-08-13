package com.volkan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum EErrorType {
    INTERNAL_ERROR(5200,"Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4200,"Parametre Hatası", HttpStatus.BAD_REQUEST),
    ZONE_DUPLICATE(4210,"Böyle bir bölge mevcut",HttpStatus.BAD_REQUEST),
    ZONE_NOT_FOUND(4211,"Böyle bir bölge bulunamadı",HttpStatus.NOT_FOUND),
    ZONE_NOT_CREATED(4212,"Bölge oluşturulamadı",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4213,"Geçersiz Token",HttpStatus.BAD_REQUEST),
    SECTOR_DUPLICATE(4214,"Böyle bir sektör mevcut",HttpStatus.BAD_REQUEST),
    SECTOR_NOT_FOUND(4215,"Böyle bir sektör bulunamadı",HttpStatus.NOT_FOUND),
    SECTOR_NOT_CREATED(4216,"Sektör oluşturulamadı",HttpStatus.BAD_REQUEST),
    AUTHORIZATION_DUPLICATE(4217,"Böyle bir yetki alanı mevcut",HttpStatus.BAD_REQUEST),
    AUTHORIZATION_NOT_FOUND(4218,"Böyle bir yetki alanı bulunamadı",HttpStatus.NOT_FOUND),
    AUTHORIZATION_NOT_CREATED(4219,"Yetki alanı oluşturulamadı",HttpStatus.BAD_REQUEST),
    ZONE_NOT_FOUND_CREATE_ONE(4220,"Böyle bir bölge bulunamadı. Önce Bölgeyi Kaydedin!",HttpStatus.NOT_FOUND),
    SECTOR_NOT_FOUND_CREATE_ONE(4221,"Böyle bir sektör bulunamadı. Önce Sektörü kaydedin!",HttpStatus.NOT_FOUND),
    TOKEN_CREATION_ERROR(4222,"Token oluşturulamadı",HttpStatus.BAD_REQUEST),
    CREATE_ERROR(1010, "Kullanici olusturulamadi",HttpStatus.BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(2002,"Giriş yaptığınız değer, istenilen değerle uyuşmamaktadır",HttpStatus.BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(2003,"URL içinde eksik parametre gönderimi",HttpStatus.BAD_REQUEST),
    INVALID_PARAMETER(3001,"Geçersiz parametre girişi yaptınız", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
