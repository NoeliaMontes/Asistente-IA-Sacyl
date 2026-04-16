package es.upsa.tfg.security.application.usecases.impl;

import es.upsa.tfg.domain.dtos.PacienteDto;
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
    public Optional<Paciente> execute(PacienteDto pacienteDto) {
        return repository.getPaciente(pacienteDto);
    }
}
