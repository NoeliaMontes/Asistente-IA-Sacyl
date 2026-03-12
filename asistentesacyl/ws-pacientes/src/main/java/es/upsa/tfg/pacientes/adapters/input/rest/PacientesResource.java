package es.upsa.tfg.pacientes.adapters.input.rest;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Paciente;
import es.upsa.tfg.domain.exceptions.MedicamentoNotFoundException;
import es.upsa.tfg.domain.exceptions.PacienteNotFoundException;
import es.upsa.tfg.pacientes.application.usecases.GetPacienteByIdUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/pacientes")
public class PacientesResource
{
    @Inject
    GetPacienteByIdUseCase getPacienteById;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getPacienteById(@PathParam("id") String id)
    {
        Optional<Paciente> pacienteOpt = getPacienteById.execute(id);
        if (pacienteOpt.isPresent()) return Response.ok().entity(pacienteOpt.get()).build();
        else throw new PacienteNotFoundException();
    }
}
