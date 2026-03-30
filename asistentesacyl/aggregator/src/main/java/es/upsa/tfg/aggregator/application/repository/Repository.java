package es.upsa.tfg.aggregator.application.repository;


import es.upsa.tfg.domain.entities.Posologia;

import java.util.List;

public interface Repository
{
    List<Posologia> getPosologiasById(String id);
}
