package es.upsa.tfg.citas.application.usecases.impl;

import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.citas.application.usecases.GetCitaByIdUseCase;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class GetCitaByIdUseCaseImpl implements GetCitaByIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public Optional<Cita> execute(String idCita) {
        return repository.getById(idCita);
    }
}
