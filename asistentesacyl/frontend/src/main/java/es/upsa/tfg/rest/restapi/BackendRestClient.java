package es.upsa.tfg.rest.restapi;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.text.ParseException;

@RegisterRestClient(baseUri = "http://backend:8084")
@Path("/backend")
public interface BackendRestClient
{
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{token}")
    @POST
     String askQuestion(String question, @PathParam("token") String token) throws ParseException;

}
