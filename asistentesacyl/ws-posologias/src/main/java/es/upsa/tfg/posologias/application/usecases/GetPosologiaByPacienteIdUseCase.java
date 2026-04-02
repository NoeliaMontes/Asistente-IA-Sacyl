package es.upsa.tfg.posologias.application.usecases;

import es.upsa.tfg.domain.entities.Posologia;

import java.util.List;
import java.util.Optional;

public interface GetPosologiaByPacienteIdUseCase
{
    List<Posologia> execute(String id);
}
