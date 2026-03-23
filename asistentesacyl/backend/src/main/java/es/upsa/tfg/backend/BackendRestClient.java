package es.upsa.tfg.backend;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8080")
public interface BackendRestClient
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citas")
    Response getCitas();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/posologia")
    Response getPosologia();
}
