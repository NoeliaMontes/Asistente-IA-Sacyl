package es.upsa.tfg.domain.exceptions;

public class MedicamentoNotFoundException extends SacylException
{
    //Esta excepción devuelve el mensaje indicado abajo como respuesta.
    public MedicamentoNotFoundException()
    {
        super("No poseemos ningún medicamento que concuerde con lo que está consultando.");
    }
}
