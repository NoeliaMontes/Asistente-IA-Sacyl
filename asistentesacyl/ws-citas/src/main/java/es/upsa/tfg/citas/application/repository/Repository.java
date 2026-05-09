package es.upsa.tfg.citas.application.repository;



import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    Optional<Cita> getById(String id);

    List<Cita> getByIdPaciente(String id);

    void deleteCita(String id, String idPaciente);

    Cita postCita(CitaDto citaDto);
}
