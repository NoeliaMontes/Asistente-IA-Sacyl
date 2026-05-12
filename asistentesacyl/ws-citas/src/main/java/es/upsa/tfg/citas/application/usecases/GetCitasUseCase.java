package es.upsa.tfg.citas.application.usecases;

import es.upsa.tfg.domain.entities.Cita;

import java.util.List;

public interface GetCitasUseCase
{
    List<Cita> execute();

}
