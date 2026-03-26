package es.upsa.tfg.citas.application.repository;



import es.upsa.tfg.domain.entities.Cita;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    List<Cita> getById(String id);
}
