package es.upsa.tfg.citas.application.usecases;

import es.upsa.tfg.domain.entities.Cita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface GetCitaByDateTimeUseCase
{

    List<Cita> execute(LocalDate fecha, LocalTime hora);
}
