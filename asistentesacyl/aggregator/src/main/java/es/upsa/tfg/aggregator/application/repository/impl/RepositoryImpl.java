package es.upsa.tfg.aggregator.application.repository.impl;


import es.upsa.tfg.aggregator.adapters.rest.PosologiaDao;
import es.upsa.tfg.aggregator.application.repository.Repository;
import es.upsa.tfg.domain.entities.Posologia;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class RepositoryImpl implements Repository
{

    @Inject
    PosologiaDao posologiaDao;

    @Override
    public List<Posologia> getPosologiasById(String id)
    {
        return posologiaDao.getAllById(id);
    }
}
