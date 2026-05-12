package es.upsa.tfg.medicos.application.usecases.impl;

import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.medicos.application.repository.Repository;
import es.upsa.tfg.medicos.application.usecases.GetMedicosUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class GetMedicosUseCaseImpl implements GetMedicosUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<Medico> execute() {
        return repository.getMedicos();
    }
}
