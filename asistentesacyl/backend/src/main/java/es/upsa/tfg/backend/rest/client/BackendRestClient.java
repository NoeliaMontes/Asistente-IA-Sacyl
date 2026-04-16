package es.upsa.tfg.backend.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8080")
public interface BackendRestClient
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citas/{id}")
    Response getCitas(@PathParam("id") String id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/posologia/{id}")
    Response getPosologia(@PathParam("id") String id);

    
}
