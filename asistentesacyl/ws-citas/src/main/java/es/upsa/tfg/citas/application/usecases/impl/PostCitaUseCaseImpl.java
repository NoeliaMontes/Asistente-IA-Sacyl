package es.upsa.tfg.citas.application.usecases.impl;



import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.citas.application.usecases.PostCitaUseCase;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PostCitaUseCaseImpl implements PostCitaUseCase
{
    @Inject
    Repository repository;


    @Override
    public Cita execute(CitaDto citaDto) {
        return repository.postCita(citaDto);
    }
}
