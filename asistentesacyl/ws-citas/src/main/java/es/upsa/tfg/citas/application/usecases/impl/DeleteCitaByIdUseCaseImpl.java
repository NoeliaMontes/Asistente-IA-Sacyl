package es.upsa.tfg.citas.application.usecases.impl;



import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.citas.application.usecases.DeleteCitaByIdUseCase;
import es.upsa.tfg.citas.application.usecases.GetCitasByPacienteIdUseCase;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DeleteCitaByIdUseCaseImpl implements DeleteCitaByIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public void execute(String id)
    {
        repository.deleteCita(id);
    }

}
