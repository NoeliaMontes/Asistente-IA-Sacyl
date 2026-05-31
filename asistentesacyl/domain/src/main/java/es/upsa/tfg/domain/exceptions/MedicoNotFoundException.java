package es.upsa.tfg.domain.exceptions;

public class MedicoNotFoundException extends SacylException {
    public MedicoNotFoundException()
    {
        super("No poseemos ningun médico que concuerde con lo que está consultando.");
    }
}
