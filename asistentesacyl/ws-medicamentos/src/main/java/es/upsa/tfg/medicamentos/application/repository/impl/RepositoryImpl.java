package es.upsa.tfg.medicamentos.application.repository.impl;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.medicamentos.adapters.output.persistence.Dao;
import es.upsa.tfg.medicamentos.application.repository.Repository;
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
    public Optional<Medicamento> getById(String id)
    {
        return dao.getById(id);
    }
}
