package es.upsa.tfg.citas.adapters.input.rest;

import es.upsa.tfg.citas.application.usecases.GetCitasByPacienteIdUseCase;
import es.upsa.tfg.citas.application.usecases.DeleteCitaByIdUseCase;
import es.upsa.tfg.citas.application.usecases.PostCitaUseCase;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("/citas")
public class CitasResource {

    @Inject
    GetCitasByPacienteIdUseCase getCitasByPacienteId;

    @Inject
    DeleteCitaByIdUseCase deleteCitaById;

    @Inject
    PostCitaUseCase postCitaUseCase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getCitasById(@PathParam("id") String id)
    {
        List<Cita> listaCitas = getCitasByPacienteId.execute(id);
        return Response.ok().entity(listaCitas).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCita(CitaDto citaDto, @Context UriInfo uriInfo )
    {
        Cita citaInsertada = postCitaUseCase.execute(citaDto);
        return Response.created(createUri(uriInfo, citaInsertada)).entity(citaInsertada).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteCitaById(@PathParam("id") String id)
    {
        deleteCitaById.execute(id);
        return Response.noContent().build();
    }

    private URI createUri(UriInfo uriInfo, Cita cita)
    {
        return uriInfo.getBaseUriBuilder()
                .path("/citas/{id}")
                .resolveTemplate("id", cita.getId())
                .build();

    }
}
