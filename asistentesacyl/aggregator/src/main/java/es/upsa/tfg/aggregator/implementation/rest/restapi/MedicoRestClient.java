package es.upsa.tfg.aggregator.implementation.rest.restapi;

import es.upsa.tfg.domain.entities.Medico;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://ngnix")
public interface MedicoRestClient
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/medicos")
    List<Medico> getMedicos();

}

