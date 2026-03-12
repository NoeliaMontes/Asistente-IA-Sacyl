package es.upsa.tfg.pacientes.application.repository.impl;

import es.upsa.tfg.domain.entities.Medicamento;

import es.upsa.tfg.domain.entities.Paciente;
import es.upsa.tfg.pacientes.adapters.output.persistence.Dao;
import es.upsa.tfg.pacientes.application.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository
{
    @Inject
    Dao dao;

    @Override
    public Optional<Paciente> getById(String id)
    {
        return dao.getById(id);
    }
}
