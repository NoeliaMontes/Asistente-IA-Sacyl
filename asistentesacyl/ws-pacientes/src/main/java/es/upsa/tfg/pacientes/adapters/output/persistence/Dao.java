package es.upsa.tfg.pacientes.adapters.output.persistence;

import es.upsa.tfg.domain.dtos.PacienteDto;
import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Paciente;

import java.util.Optional;

public interface Dao
{
    Optional<Paciente> getById(PacienteDto pacienteDto);
}
