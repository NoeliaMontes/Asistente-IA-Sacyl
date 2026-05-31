package es.upsa.tfg.backend.rest.client;

import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://ngnix:80")
public interface CitasRestClient
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citas/{id}")
    List<Cita> getCitas(@PathParam("id") String id);

    @DELETE
    @Path("/citas/{id}/{idCitas}")
     void deleteCitaById(@PathParam("id") String id,@PathParam("idCitas") String idCitas);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citas")
     Response postCita(CitaDto citaDto);
}
