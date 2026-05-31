package es.upsa.tfg.medicos.application.usecases;

import es.upsa.tfg.domain.entities.Medico;

import java.util.List;
import java.util.Optional;

public interface GetMedicosUseCase
{
    List<Medico> execute();
}
