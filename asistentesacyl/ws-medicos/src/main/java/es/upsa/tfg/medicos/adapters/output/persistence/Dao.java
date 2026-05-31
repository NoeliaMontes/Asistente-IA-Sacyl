package es.upsa.tfg.medicos.adapters.output.persistence;

import es.upsa.tfg.domain.entities.Medico;

import java.util.List;
import java.util.Optional;

public interface Dao
{
    List<Medico> getMedicos();
}
