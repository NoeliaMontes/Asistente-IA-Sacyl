package es.upsa.tfg.aggregator.adapters.rest;

import es.upsa.tfg.domain.entities.Medicamento;

import java.util.Optional;

public interface MedicamentoDao
{
    Optional<Medicamento> getById(String id);
}
