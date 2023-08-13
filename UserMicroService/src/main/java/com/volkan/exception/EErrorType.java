package com.volkan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum EErrorType {
    INTERNAL_ERROR(5200,"Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4200,"Parametre Hatası", HttpStatus.BAD_REQUEST),
    USER_DUPLICATE(4210,"Böyle bir kullanıcı mevcut",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4211,"Böyle bir kullanıcı bulunamadı",HttpStatus.NOT_FOUND),
    USER_NOT_CREATED(4212,"Kullanıcı oluşturulamadı",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4213,"Geçersiz Token",HttpStatus.BAD_REQUEST),
    FOLLOW_REQUEST_ALREADY_EXISTS(4214,"Böyle bir takip isteği daha önce oluşturulmuştur",HttpStatus.BAD_REQUEST),
    TOKEN_CREATION_ERROR(4215,"Token oluşturulamadı",HttpStatus.BAD_REQUEST),
    PASSWORD_UNMATCH_ERROR(1004, "Girilen parolalar uyuşmamaktadır",HttpStatus.BAD_REQUEST),
    REGISTER_ERROR_EMAIL(1005,"Bu email daha önce alınmıştır",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR_USERNAME_PASSWORD(1006,"Kullanıcı adı ya da şifre hatalıdır",HttpStatus.BAD_REQUEST),
    NOT_ACTIVE_ACCOUNT(1008,"Hesabınız şu anda aktif değildir", HttpStatus.BAD_REQUEST),
    LOGIN_ERROR_ADMIN(1009,"Admin girisi gereklidir !",HttpStatus.BAD_REQUEST),
    CREATE_ERROR(1010, "Kullanici olusturulamadi",HttpStatus.BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(2002,"Giriş yaptığınız değer, istenilen değerle uyuşmamaktadır",HttpStatus.BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(2003,"URL içinde eksik parametre gönderimi",HttpStatus.BAD_REQUEST),
    INVALID_PARAMETER(3001,"Geçersiz parametre girişi yaptınız", HttpStatus.BAD_REQUEST),
    ACTIVATE_CODE_ERROR(4113,"Aktivasyon kod hatası",HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
