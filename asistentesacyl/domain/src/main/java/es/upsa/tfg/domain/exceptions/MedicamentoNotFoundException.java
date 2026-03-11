package es.upsa.tfg.domain.exceptions;

public class MedicamentoNotFoundException extends RuntimeException
{

    public MedicamentoNotFoundException()
    {
        super("No poseemos ningún medicamento que concuerde con lo que está consultando.");
    }
}
