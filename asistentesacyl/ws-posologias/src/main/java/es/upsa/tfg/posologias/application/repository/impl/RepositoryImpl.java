package es.upsa.tfg.posologias.application.repository.impl;


import es.upsa.tfg.domain.entities.Posologia;
import es.upsa.tfg.posologias.adapters.output.persistence.Dao;
import es.upsa.tfg.posologias.application.repository.Repository;
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
    public Optional<Posologia> getById(String id)
    {
        return dao.getById(id);
    }
}
