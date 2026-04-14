package es.upsa.tfg.security.adapters.output.persistence;

import es.upsa.tfg.domain.entities.Paciente;

import java.util.Optional;

public interface PacientesDao
{
    Optional<Paciente> getById(String id);
}
