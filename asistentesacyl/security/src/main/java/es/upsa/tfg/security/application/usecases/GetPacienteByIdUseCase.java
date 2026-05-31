package es.upsa.tfg.security.application.usecases;

import es.upsa.tfg.domain.dtos.PacienteDto;
import es.upsa.tfg.domain.entities.Paciente;

import java.util.Optional;

public interface GetPacienteByIdUseCase
{
    Optional<Paciente> execute(PacienteDto pacienteDto);
}
