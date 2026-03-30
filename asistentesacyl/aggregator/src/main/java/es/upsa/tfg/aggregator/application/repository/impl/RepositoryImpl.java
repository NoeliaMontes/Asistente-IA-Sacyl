package es.upsa.tfg.aggregator.application.repository.impl;

import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.medicos.adapters.output.persistence.Dao;
import es.upsa.tfg.medicos.application.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository
{
    //Injectamos el Dao
    @Inject
    Dao dao;

    @Override
    public Optional<Medico> getById(String id)
    {
        return dao.getById(id);
    }
}
