package es.upsa.tfg.aggregator.implementation.rest;

import es.upsa.tfg.aggregator.adapters.rest.MedicoDao;
import es.upsa.tfg.aggregator.implementation.rest.restapi.CitasRestClient;
import es.upsa.tfg.aggregator.implementation.rest.restapi.MedicoRestClient;
import es.upsa.tfg.domain.entities.Medico;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class MedicoDaoImpl implements MedicoDao
{
    @Inject
    @RestClient
    MedicoRestClient restClient;

    @Override
    public List<Medico> getAll() {
        return restClient.getMedicos();
    }
}
