package es.upsa.tfg.citas.application.usecases.impl;

import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.citas.application.usecases.GetCitasUseCase;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetCitasUseCaseImpl implements GetCitasUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<Cita> execute() {
        return repository.getCitas();
    }
}
