package es.upsa.tfg.aggregator.application.repository;


import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Posologia;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    List<Posologia> getPosologiasById(String id);
    Optional<Medicamento> getMedicamentoById(String id);
}
