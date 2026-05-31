package es.upsa.tfg.citas.application.usecases.impl;



import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.citas.application.usecases.GetCitasByPacienteIdUseCase;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class GetCitasByPacienteIdUseCaseImpl implements GetCitasByPacienteIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<Cita> execute(String id) {
        return repository.getByIdPaciente(id);
    }
}
