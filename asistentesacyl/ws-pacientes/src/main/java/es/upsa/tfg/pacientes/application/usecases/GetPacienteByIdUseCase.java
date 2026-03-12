package es.upsa.tfg.pacientes.application.usecases;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Paciente;

import java.util.Optional;

public interface GetPacienteByIdUseCase
{
    Optional<Paciente> execute(String id);
}
