package es.upsa.tfg.pacientes.adapters.input.rest;

import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.dtos.PacienteDto;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Paciente;
import es.upsa.tfg.domain.exceptions.MedicamentoNotFoundException;
import es.upsa.tfg.domain.exceptions.PacienteNotFoundException;
import es.upsa.tfg.pacientes.application.usecases.GetPacienteByIdUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.Optional;

@Path("/pacientes")
public class PacientesResource
{
    @Inject
    GetPacienteByIdUseCase getPacienteById;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response getPaciente(PacienteDto pacienteDto)
    {
        Optional<Paciente> pacienteOpt = getPacienteById.execute(pacienteDto);
        if (pacienteOpt.isPresent()) return Response.ok().entity(pacienteOpt.get()).build();
        else throw new PacienteNotFoundException();
    }

}

