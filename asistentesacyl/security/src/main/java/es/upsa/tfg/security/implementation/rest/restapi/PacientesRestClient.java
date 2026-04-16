package es.upsa.tfg.security.implementation.rest.restapi;


import es.upsa.tfg.domain.dtos.PacienteDto;
import es.upsa.tfg.domain.entities.Paciente;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import java.util.Optional;

@RegisterRestClient(baseUri = "http://localhost:80")
@Path("/pacientes")
public interface PacientesRestClient
{

    @POST
    @Path("/animales")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Paciente getPaciente(PacienteDto pacienteDto);

}
