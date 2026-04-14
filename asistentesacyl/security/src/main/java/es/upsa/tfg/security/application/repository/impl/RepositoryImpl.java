package es.upsa.tfg.security.application.repository.impl;

import es.upsa.tfg.domain.entities.Paciente;

import es.upsa.tfg.security.adapters.output.persistence.PacientesDao;
import es.upsa.tfg.security.application.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository
{
    @Inject
    PacientesDao dao;

    @Override
    public Optional<Paciente> getById(String id)
    {
        return dao.getById(id);
    }
}
