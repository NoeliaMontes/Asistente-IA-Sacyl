package es.upsa.tfg.security.implementation.rest;

import es.upsa.tfg.domain.entities.Paciente;
import es.upsa.tfg.security.adapters.output.persistence.PacientesDao;
import es.upsa.tfg.security.implementation.rest.restapi.PacientesRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class PacientesDaoImpl implements PacientesDao
{
    @Inject
    @RestClient
    PacientesRestClient restClient;

    @Override
    public Optional<Paciente> getById(String id)
    {
        return Optional.of( restClient.getPacienteById(id));
    }
}
