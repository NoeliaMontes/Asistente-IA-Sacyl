package es.upsa.tfg.posologias.application.usecases.impl;


import es.upsa.tfg.domain.entities.Posologia;
import es.upsa.tfg.posologias.application.repository.Repository;
import es.upsa.tfg.posologias.application.usecases.GetPosologiaByPacienteIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class GetPosologiaByPacienteIdUseCaseImpl implements GetPosologiaByPacienteIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<Posologia> execute(String id) {
        return repository.getById(id);
    }
}
