package es.upsa.tfg.medicos.adapters.input.rest;

import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.medicos.application.usecases.GetMedicoByIdUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/medicos")
public class MedicosResource
{
    @Inject
    GetMedicoByIdUseCase getMedicoById;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getMedicamentoById(@PathParam("id") String id)
    {
        Optional<Medico> medicoOpt = getMedicoById.execute(id);
        if (medicoOpt.isPresent()) return Response.ok().entity(medicoOpt.get()).build();
        else throw new MedicoNotFoundException();
    }
}
