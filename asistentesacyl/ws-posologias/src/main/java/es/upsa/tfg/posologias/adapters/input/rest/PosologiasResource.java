package es.upsa.tfg.posologias.adapters.input.rest;

import es.upsa.tfg.domain.entities.Posologia;
import es.upsa.tfg.domain.exceptions.PosologiaNotFoundException;
import es.upsa.tfg.posologias.application.usecases.GetPosologiaByPacienteIdUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/posologias")
public class PosologiasResource {

    @Inject
    GetPosologiaByPacienteIdUseCase getPosologiaByPacienteId;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getMedicoById(@PathParam("id") String id)
    {
        Optional<Posologia> posologiaOpt = getPosologiaByPacienteId.execute(id);
        if (posologiaOpt.isPresent()) return Response.ok().entity(posologiaOpt.get()).build();
        else throw new PosologiaNotFoundException();
    }
}
