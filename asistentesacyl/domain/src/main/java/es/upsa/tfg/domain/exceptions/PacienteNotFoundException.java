package es.upsa.tfg.domain.exceptions;

public class PacienteNotFoundException extends SacylException {
    public PacienteNotFoundException()
    {
        super("Este paciente no existe, revisa tus credenciales.");
    }
}
