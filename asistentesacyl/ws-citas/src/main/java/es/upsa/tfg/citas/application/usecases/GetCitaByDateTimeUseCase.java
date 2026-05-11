package es.upsa.tfg.citas.application.usecases;

import es.upsa.tfg.domain.entities.Cita;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;


public interface GetCitaByDateTimeUseCase
{

    Optional<Cita> execute(LocalDate fecha, LocalTime hora);
}
