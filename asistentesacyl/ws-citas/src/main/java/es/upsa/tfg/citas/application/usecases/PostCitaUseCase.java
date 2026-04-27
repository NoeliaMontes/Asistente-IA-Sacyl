package es.upsa.tfg.citas.application.usecases;


import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;

import java.util.Optional;


public interface PostCitaUseCase
{
    Cita execute(CitaDto citaDto);
}
