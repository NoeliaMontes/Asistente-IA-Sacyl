package es.upsa.tfg.aggregator.application.usecases;

import es.upsa.tfg.domain.entities.Medico;

import java.time.LocalDate;
import java.time.LocalTime;

public interface GetMedicosDisponiblesUseCase
{
    Medico execute(LocalDate date, LocalTime time);
}
