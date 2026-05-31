package es.upsa.tfg.posologias.adapters.output.persistence;


import es.upsa.tfg.domain.entities.Posologia;

import java.util.List;
import java.util.Optional;

public interface Dao
{
    List<Posologia> getById(String id);
}
