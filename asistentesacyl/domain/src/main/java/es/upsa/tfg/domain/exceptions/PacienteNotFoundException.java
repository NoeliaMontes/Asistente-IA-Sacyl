package es.upsa.tfg.domain.exceptions;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException()
    {
        super("Este paciente no existe, revisa tus credenciales.");
    }
}
