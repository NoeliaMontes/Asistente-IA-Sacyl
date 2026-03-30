package es.upsa.tfg.aggregator.application.usecases.impl;

import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.medicos.application.repository.Repository;
import es.upsa.tfg.medicos.application.usecases.GetMedicoByIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class GetMedicoByIdUseCaseImpl implements GetMedicoByIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public Optional<Medico> execute(String id) {
        return repository.getById(id);
    }
}
