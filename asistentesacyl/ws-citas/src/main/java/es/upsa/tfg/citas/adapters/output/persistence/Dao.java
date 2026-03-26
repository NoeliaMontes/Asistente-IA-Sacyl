package es.upsa.tfg.citas.adapters.output.persistence;



import es.upsa.tfg.domain.entities.Cita;

import java.util.List;
import java.util.Optional;

public interface Dao
{
    List<Cita> getById(String id);
}
