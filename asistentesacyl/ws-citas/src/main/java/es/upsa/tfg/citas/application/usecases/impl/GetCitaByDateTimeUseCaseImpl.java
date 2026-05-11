package es.upsa.tfg.citas.application.usecases.impl;

import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.citas.application.usecases.GetCitaByDateTimeUseCase;
import es.upsa.tfg.citas.application.usecases.GetCitaByIdUseCase;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@ApplicationScoped
public class GetCitaByDateTimeUseCaseImpl implements GetCitaByDateTimeUseCase
{
    @Inject
    Repository repository;

    @Override
    public Optional<Cita> execute(LocalDate fecha, LocalTime hora) {
        return repository.getByDate(fecha,hora);
    }
}
