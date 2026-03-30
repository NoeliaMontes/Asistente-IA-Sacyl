package es.upsa.tfg.aggregator.adapters.rest;



import es.upsa.tfg.domain.entities.Posologia;

import java.util.List;

public interface PosologiaDao
{
    List<Posologia> getAllById(String id);
}
