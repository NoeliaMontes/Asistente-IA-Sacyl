package es.upsa.tfg.domain.exceptions;

public class CitaExistException extends RuntimeException {
    public CitaExistException() {
        super("Ya tienes una cita concertada a esta hora");
    }
}
