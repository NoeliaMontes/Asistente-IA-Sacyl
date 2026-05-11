package es.upsa.tfg.citas.application.usecases.impl;



import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.citas.application.usecases.GetCitaByDateTimeUseCase;
import es.upsa.tfg.citas.application.usecases.PostCitaUseCase;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.exceptions.CitaExistException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PostCitaUseCaseImpl implements PostCitaUseCase
{
    @Inject
    GetCitaByDateTimeUseCase getBydate;

    @Inject
    Repository repository;


    @Override
    public Cita execute(CitaDto citaDto)
    {
        Optional<Cita> citaExiste = getBydate.execute(citaDto.getFecha(), citaDto.getHora());
        if(citaExiste.isPresent())
        {
            throw new CitaExistException();
        }
        else {
            return repository.postCita(citaDto);
        }
    }
}
