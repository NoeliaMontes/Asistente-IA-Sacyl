package es.upsa.tfg.security.application.security;

import es.upsa.tfg.domain.entities.Paciente;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.jwt.Claims;

@ApplicationScoped
public class GenerateTokenImpl implements GenerateToken
{
    public String generate(Paciente paciente)
    {
        String token = Jwt.claim(Claims.sub.name(),paciente.getId())
                            .claim(Claims.given_name.name(), paciente.getNombre())
                            .sign();
        return token;
    }
}
