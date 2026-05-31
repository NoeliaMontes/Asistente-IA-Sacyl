package es.upsa.tfg.aggregator.implementation.rest;

import es.upsa.tfg.aggregator.adapters.rest.CitasDao;
import es.upsa.tfg.aggregator.implementation.rest.restapi.CitasRestClient;
import es.upsa.tfg.aggregator.implementation.rest.restapi.MedicamentosRestClient;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
public class CitasDaoImpl implements CitasDao
{
    @Inject
    @RestClient
    CitasRestClient restClient;

    @Override
    public List<Cita> getAll(LocalDate fecha, LocalTime hora) {
        return restClient.getCitas(fecha,hora);
    }
}
