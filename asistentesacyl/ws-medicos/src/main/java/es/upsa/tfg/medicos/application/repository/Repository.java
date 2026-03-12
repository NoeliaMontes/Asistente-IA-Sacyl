package es.upsa.tfg.medicos.application.repository;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Medico;

import java.util.Optional;

public interface Repository
{
    Optional<Medico> getById(String id);
}
