package es.upsa.tfg.aggregator.application.repository;

import es.upsa.tfg.domain.entities.Medico;

import java.util.Optional;

public interface Repository
{
    Optional<Medico> getById(String id);
}
