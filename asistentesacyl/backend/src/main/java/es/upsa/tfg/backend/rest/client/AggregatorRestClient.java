package es.upsa.tfg.backend.rest.client;

import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://ngnix:80")
public interface AggregatorRestClient
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/aggregator/posologias/{id}")
    List<PosologiaWMedicina> getPosologia(@PathParam("id") String id);

}
