package es.upsa.tfg.security.application.repository;

import es.upsa.tfg.domain.dtos.PacienteDto;
import es.upsa.tfg.domain.entities.Paciente;

import java.util.Optional;

public interface Repository
{
    Optional<Paciente> getPaciente(PacienteDto pacienteDto);
}
