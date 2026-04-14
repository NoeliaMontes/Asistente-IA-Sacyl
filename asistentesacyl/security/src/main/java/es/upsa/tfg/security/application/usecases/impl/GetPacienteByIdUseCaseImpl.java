package es.upsa.tfg.security.application.usecases.impl;

import es.upsa.tfg.domain.entities.Paciente;
import es.upsa.tfg.security.application.repository.Repository;
import es.upsa.tfg.security.application.usecases.GetPacienteByIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class GetPacienteByIdUseCaseImpl implements GetPacienteByIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public Optional<Paciente> execute(String id) {
        return repository.getById(id);
    }
}
