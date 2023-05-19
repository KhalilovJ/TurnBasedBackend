package az.evilcastle.turnbased.services.interfaces;

import io.jsonwebtoken.Claims;

public interface JwtService {

    String extractUsername(String token);

    Claims extractAllClaims(String token);

}
