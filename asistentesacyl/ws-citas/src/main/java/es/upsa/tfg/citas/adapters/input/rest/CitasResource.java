package es.upsa.tfg.citas.adapters.input.rest;

import es.upsa.tfg.citas.application.usecases.GetCitasByPacienteIdUseCase;
import es.upsa.tfg.citas.application.usecases.DeleteCitaByIdUseCase;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/citas")
public class CitasResource {

    @Inject
    GetCitasByPacienteIdUseCase getCitasByPacienteId;

    @Inject
    DeleteCitaByIdUseCase deleteCitaById;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getCitasById(@PathParam("id") String id)
    {
        List<Cita> listaCitas = getCitasByPacienteId.execute(id);
        return Response.ok().entity(listaCitas).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteCitaById(@PathParam("id") String id)
    {
        deleteCitaById.execute(id);
        return Response.noContent().build();
    }
}
