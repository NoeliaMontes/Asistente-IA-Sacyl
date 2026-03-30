package es.upsa.tfg.aggregator.implementation.rest;

import es.upsa.tfg.aggregator.adapters.rest.PosologiaDao;
import es.upsa.tfg.aggregator.implementation.rest.restapi.PosologiasRestClient;
import es.upsa.tfg.domain.entities.Posologia;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

public class PosologiasDaoImpl implements PosologiaDao
{
    @Inject
    @RestClient
    PosologiasRestClient restClient;

    @Override
    public List<Posologia> getAllById(String id)
    {
        return restClient.getPosologiaByPacienteId(id);
    }
}
