package es.upsa.tfg.aggregator.adapters.rest;

import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.entities.Medico;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitasDao
{
    List<Cita> getAll(LocalDate fecha, LocalTime hora);
}
