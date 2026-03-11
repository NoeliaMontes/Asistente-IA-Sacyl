package es.upsa.tfg.medicamentos.application.repository;

import es.upsa.tfg.domain.entities.Medicamento;

import java.util.Optional;

public interface Repository
{
    Optional<Medicamento> getById(String id);
}
