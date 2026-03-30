package es.upsa.tfg.aggregator.application.usecases;

import es.upsa.tfg.domain.entities.Medico;

import java.util.Optional;

public interface GetMedicoByIdUseCase
{
    Optional<Medico> execute(String id);
}
