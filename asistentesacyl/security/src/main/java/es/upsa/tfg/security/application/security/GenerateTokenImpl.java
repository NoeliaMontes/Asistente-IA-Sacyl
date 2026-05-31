package es.upsa.tfg.security.application.security;

import es.upsa.tfg.domain.entities.Paciente;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Duration;

@ApplicationScoped
public class GenerateTokenImpl implements GenerateToken
{
    //Función para generar el token
    public String generate(Paciente paciente)
    {
        //El token tiene inyectado el el id y el nombre del paciente
        String token = Jwt.claim(Claims.sub.name(),paciente.getId())
                            .claim(Claims.given_name.name(), paciente.getNombre())
        //Definimos cuanto tarda en caducar el token
                            .expiresIn(Duration.ofHours(1))
                            .sign();
        return token;
    }
}
