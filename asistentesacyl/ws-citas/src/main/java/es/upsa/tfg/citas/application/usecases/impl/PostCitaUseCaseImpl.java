package es.upsa.tfg.citas.application.usecases.impl;



import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.citas.application.usecases.PostCitaUseCase;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

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
