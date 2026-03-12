package es.upsa.tfg.pacientes.application.repository;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Paciente;

import java.util.Optional;

public interface Repository
{
    Optional<Paciente> getById(String id);
}
