package my.exam.catalog.apiserver.libapiserver.service;

import io.jsonwebtoken.Claims;
import my.exam.catalog.apiserver.libapiserver.entity.Jwt;
import org.springframework.security.core.Authentication;

public interface JwtService {

    String generatedJwt(Authentication authentication);

    Claims getClaims(String jwt);

    boolean isValidJwt(Jwt jwt);
}
