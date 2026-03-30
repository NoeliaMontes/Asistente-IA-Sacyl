package es.upsa.tfg.aggregator.implementation.rest;

import es.upsa.tfg.aggregator.adapters.rest.MedicamentoDao;
import es.upsa.tfg.aggregator.implementation.rest.restapi.MedicamentosRestClient;
import es.upsa.tfg.aggregator.implementation.rest.restapi.PosologiasRestClient;
import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.exceptions.MedicamentoNotFoundException;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

public class MedicamentosDaoImpl implements MedicamentoDao
{
    @Inject
    @RestClient
    MedicamentosRestClient restClient;


    @Override
    public Optional<Medicamento> getById(String id)
    {
        try {
            return Optional.of(restClient.getMedicamentoById(id));
        } catch (MedicamentoNotFoundException e) {
            return Optional.empty();
        }

    }
}
