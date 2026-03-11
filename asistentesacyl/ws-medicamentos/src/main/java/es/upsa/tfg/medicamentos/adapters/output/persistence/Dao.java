package es.upsa.tfg.medicamentos.adapters.output.persistence;

import es.upsa.tfg.domain.entities.Medicamento;

import java.util.Optional;

public interface Dao {
    Optional<Medicamento> getById(String id);
}
