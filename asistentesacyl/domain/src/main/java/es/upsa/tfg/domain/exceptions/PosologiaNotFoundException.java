package es.upsa.tfg.domain.exceptions;

public class PosologiaNotFoundException extends SacylException
{
    public PosologiaNotFoundException()
    {
        super("Este paciente no existe, revisa tus credenciales.");
    }
}
