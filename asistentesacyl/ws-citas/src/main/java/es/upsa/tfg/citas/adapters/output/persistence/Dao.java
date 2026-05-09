package es.upsa.tfg.citas.adapters.output.persistence;



import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;

import java.util.List;
import java.util.Optional;

public interface Dao
{
    Optional<Cita> getById(String id);

    List<Cita> getByIdPaciente(String id);

    void deleteById(String id, String idPaciente);

    Cita post(CitaDto citaDto);
}
