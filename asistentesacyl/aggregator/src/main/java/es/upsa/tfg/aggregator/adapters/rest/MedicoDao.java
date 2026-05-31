package es.upsa.tfg.aggregator.adapters.rest;

import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.domain.entities.Posologia;

import java.util.List;

public interface MedicoDao
{
    List<Medico> getAll();
}
