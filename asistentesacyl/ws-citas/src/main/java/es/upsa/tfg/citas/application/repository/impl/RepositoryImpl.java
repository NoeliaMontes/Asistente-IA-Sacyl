package es.upsa.tfg.citas.application.repository.impl;



import es.upsa.tfg.citas.adapters.output.persistence.Dao;
import es.upsa.tfg.citas.application.repository.Repository;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository
{
    //Injectamos el Dao
    @Inject
    Dao dao;

    @Override
    public List<Cita> getById(String id)
    {
        return dao.getById(id);
    }

    @Override
    public void deleteCita(String id)
    {
        dao.deleteById(id);
    }

    @Override
    public Cita postCita(CitaDto citaDto)
    {
        return dao.post(citaDto);
    }
}
