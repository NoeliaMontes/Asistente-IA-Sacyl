package es.upsa.tfg.medicos.application.repository.impl;


import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.medicos.adapters.output.persistence.Dao;
import es.upsa.tfg.medicos.application.repository.Repository;
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
    public List<Medico> getMedicos()
    {
        return dao.getMedicos();
    }
}
