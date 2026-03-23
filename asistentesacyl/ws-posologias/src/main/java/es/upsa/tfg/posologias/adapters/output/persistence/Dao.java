package es.upsa.tfg.posologias.adapters.output.persistence;


import es.upsa.tfg.domain.entities.Posologia;

import java.util.Optional;

public interface Dao
{
    Optional<Posologia> getById(String id);
}
