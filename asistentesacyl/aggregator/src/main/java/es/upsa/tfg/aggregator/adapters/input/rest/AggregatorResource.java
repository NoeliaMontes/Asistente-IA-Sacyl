package es.upsa.tfg.aggregator.adapters.input.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/aggregator")
public class AggregatorResource {

    @Inject
    GetPosologiaWithMedicinaByPacienteIdUseCase getPosologiaWithMedicinaByPacienteId;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getPosologiasWithMedicinaByPacienteId(@PathParam("id") String id)
    {
        List<> listaPosologias = getPosologiaWithMedicinaByPacienteId.execute(id);
        return Response.ok().entity(listaPosologias).build();
    }
}
