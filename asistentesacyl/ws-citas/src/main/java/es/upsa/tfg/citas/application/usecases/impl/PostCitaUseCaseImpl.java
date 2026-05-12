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
        List<Cita> citasPosibles = getBydate.execute(citaDto.getFecha(), citaDto.getHora());
        for (Cita cita : citasPosibles)
        {
            if (cita.getId_paciente().equals(citaDto.getId_paciente())) {
                throw new CitaExistException();
            }
        }
            return repository.postCita(citaDto);

    }
}
