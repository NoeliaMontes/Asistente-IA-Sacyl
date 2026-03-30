package es.upsa.tfg.medicos.adapters.output.persistence;

import es.upsa.tfg.domain.entities.Medico;

import java.util.Optional;

public interface Dao
{
    Optional<Medico> getById(String id);
}
