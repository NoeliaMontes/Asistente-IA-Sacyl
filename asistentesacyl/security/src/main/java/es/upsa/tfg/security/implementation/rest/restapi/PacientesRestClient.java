package es.upsa.tfg.security.implementation.rest.restapi;


import es.upsa.tfg.domain.entities.Paciente;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import java.util.Optional;

@RegisterRestClient(baseUri = "http://localhost:80")
@Path("/pacientes")
public interface PacientesRestClient
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    Paciente getPacienteById(@PathParam("id") String id);

}
