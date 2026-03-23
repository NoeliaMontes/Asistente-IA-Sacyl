package es.upsa.tfg.posologias.application.usecases;

import es.upsa.tfg.domain.entities.Posologia;

import java.util.Optional;

public interface GetPosologiaByPacienteIdUseCase
{
    Optional<Posologia> execute(String id);
}
