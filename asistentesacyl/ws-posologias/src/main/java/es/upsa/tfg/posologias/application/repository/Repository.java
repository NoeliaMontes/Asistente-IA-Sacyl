package es.upsa.tfg.posologias.application.repository;


import es.upsa.tfg.domain.entities.Posologia;

import java.util.Optional;

public interface Repository
{
    Optional<Posologia> getById(String id);
}
