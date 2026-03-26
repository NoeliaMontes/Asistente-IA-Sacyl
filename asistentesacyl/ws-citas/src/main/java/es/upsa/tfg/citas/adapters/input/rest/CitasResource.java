package es.upsa.tfg.citas.adapters.input.rest;

import es.upsa.tfg.citas.application.usecases.GetCitasByPacienteIdUseCase;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/citas")
public class CitasResource {

    @Inject
    GetCitasByPacienteIdUseCase getCitasByPacienteId;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getMedicoById(@PathParam("id") String id)
    {
        List<Cita> listaCitas = getCitasByPacienteId.execute(id);
        return Response.ok().entity(listaCitas).build();
    }
}
