package es.upsa.tfg.domain.exceptions;

public class CitaNotFoundException extends SacylException
{
    public CitaNotFoundException()
    {
        super("Esta cita no existe, porfavor vuelva a intentarlo más tarde.");
    }
}
