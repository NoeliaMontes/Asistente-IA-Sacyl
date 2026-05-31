package es.upsa.tfg.citas.application.usecases.impl;

import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.citas.application.usecases.GetCitaByDateTimeUseCase;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
public class GetCitaByDateTimeUseCaseImpl implements GetCitaByDateTimeUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<Cita> execute(LocalDate fecha, LocalTime hora) {
        return repository.getByDate(fecha,hora);
    }
}
