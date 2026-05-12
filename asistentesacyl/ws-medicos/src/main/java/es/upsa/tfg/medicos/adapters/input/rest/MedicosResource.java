package es.upsa.tfg.medicos.adapters.input.rest;

import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.medicos.application.usecases.GetMedicosUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/medicos")
public class MedicosResource
{
    @Inject
    GetMedicosUseCase getMedicos;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicos()
    {
        List<Medico> medicos = getMedicos.execute();
        return Response.ok().entity(medicos).build();
    }
}
