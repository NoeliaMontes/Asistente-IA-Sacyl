package es.upsa.tfg.citas.application.usecases;

import es.upsa.tfg.domain.entities.Cita;

import java.util.Optional;

public interface GetCitaByIdUseCase
{

    Optional<Cita> execute(String idCita);
}
