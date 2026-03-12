package es.upsa.tfg.medicos.application.usecases;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Medico;

import java.util.Optional;

public interface GetMedicoByIdUseCase
{
    Optional<Medico> execute(String id);
}
