package es.upsa.tfg.backend.rest.client;

import es.upsa.tfg.domain.dtos.CitaDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8080")
public interface BackendRestClient
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citas/{id}")
    Response getCitas(@PathParam("id") String id);

    @DELETE
    @Path("/citas/{id}")
     Response deleteCitaById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citas")
     Response postCita(CitaDto citaDto, @Context UriInfo uriInfo);


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/aggregator/posologia/{id}")
    Response getPosologia(@PathParam("id") String id);

}
