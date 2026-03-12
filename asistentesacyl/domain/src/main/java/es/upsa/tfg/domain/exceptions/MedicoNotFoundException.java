package es.upsa.tfg.domain.exceptions;

public class MedicoNotFoundException extends RuntimeException {
    public MedicoNotFoundException()
    {
        super("No poseemos ningun médico que concuerde con lo que está consultando.");
    }
}
