package es.upsa.tfg.posologias.application.repository;


import es.upsa.tfg.domain.entities.Posologia;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    List<Posologia> getById(String id);
}
