package com.volkan.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.volkan.exception.EErrorType;
import com.volkan.exception.VehicleManagerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtTokenManager {
    @Value("${jwt.secretKey}")
    String secretKey;
    @Value("${jwt.issuer}")
    String issuer;
    @Value("${jwt.audience}")
    String audience;

    public Optional<String> createToken(Long id) {
        String token = null;
        Date date = new Date(System.currentTimeMillis() + (1000 * 60 * 5));
        try {
            token = JWT.create().withAudience(audience).withIssuer(issuer).withIssuedAt(new Date()).withExpiresAt(date).withClaim("id", id).sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
    public Boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT==null) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
        }
        return true;
    }

    public Optional<Long> getIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
            }
            Long id = decodedJWT.getClaim("id").asLong();
            return Optional.of(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
        }
    }

    public Optional<String> getRoleFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
            }
            String role = decodedJWT.getClaim("role").asString();
            return Optional.of(role);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
        }
    }
    public List<Long> getAllowedListFromToken (String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
            }
            List<Long> allowedList = decodedJWT.getClaim("allowedList").asList(Long.class);
            return allowedList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
        }
    }
}
