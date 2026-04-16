package es.upsa.tfg.citas.application.usecases;


import es.upsa.tfg.domain.entities.Cita;


public interface PostCitaUseCase
{
    Cita execute(CitaDto citaDto);
}
