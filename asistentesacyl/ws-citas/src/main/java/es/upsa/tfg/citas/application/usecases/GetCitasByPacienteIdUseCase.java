package es.upsa.tfg.citas.application.usecases;


import es.upsa.tfg.domain.entities.Cita;

import java.util.List;
import java.util.Optional;

public interface GetCitasByPacienteIdUseCase
{
    List<Cita> execute(String id);
}
