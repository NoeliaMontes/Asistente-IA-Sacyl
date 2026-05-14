package es.upsa.tfg.citas.application.usecases.impl;



import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.citas.application.usecases.DeleteCitaByIdUseCase;
import es.upsa.tfg.citas.application.usecases.GetCitasByPacienteIdUseCase;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.exceptions.CitaNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DeleteCitaByIdUseCaseImpl implements DeleteCitaByIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public void execute(String id, String idPaciente)
    {
        //Revisa si la cita existe y si pertenece al paciente antes de eliminar
        Optional<Cita> cita = repository.getById(id);
        if(cita.isPresent())
        {
            if(cita.get().getId_paciente().equals(idPaciente))
            {
                repository.deleteCita(id, idPaciente);
            }
            else
            {
                throw new CitaNotFoundException();
            }
        }
        else
        {
            throw new CitaNotFoundException();
        }

    }

}
