package es.upsa.tfg.citas.application.repository.impl;



import es.upsa.tfg.citas.adapters.output.persistence.Dao;
import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository
{
    //Injectamos el Dao
    @Inject
    Dao dao;

    @Override
    public Optional<Cita> getById(String id) {
        return dao.getById(id);
    }

    @Override
    public List<Cita> getByIdPaciente(String id)
    {
        return dao.getByIdPaciente(id);
    }

    @Override
    public void deleteCita(String id, String idPaciente)
    {
        dao.deleteById(id, idPaciente);
    }

    @Override
    public Cita postCita(CitaDto citaDto)
    {
        return dao.post(citaDto);
    }

    @Override
    public Optional<Cita> getByDate(LocalDate fecha, LocalTime hora)
    {
        return dao.getByTime(fecha,hora);
    }
}
