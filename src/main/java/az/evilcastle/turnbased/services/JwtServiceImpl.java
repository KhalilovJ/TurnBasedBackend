package az.evilcastle.turnbased.services;

import az.evilcastle.turnbased.services.interfaces.JwtService;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

    @Override
    public String extractUsername(String jwt) {
        return null;
    }
}
