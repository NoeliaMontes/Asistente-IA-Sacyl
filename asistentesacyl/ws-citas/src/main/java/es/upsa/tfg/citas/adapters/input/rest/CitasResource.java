package es.upsa.tfg.citas.adapters.input.rest;

import es.upsa.tfg.citas.application.usecases.*;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.exceptions.PacienteNotFoundException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Path("/citas")
public class CitasResource {

    @Inject
    GetCitaByDateTimeUseCase getCitas;

    @Inject
    GetCitasByPacienteIdUseCase getCitasByPacienteId;

    @Inject
    DeleteCitaByIdUseCase deleteCitaById;

    @Inject
    PostCitaUseCase postCitaUseCase;


    //Obtiene todas las citas de un paciente
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getCitasByPacienteId(@PathParam("id") String id)
    {
        List<Cita> listaCitas = getCitasByPacienteId.execute(id);
        return Response.ok().entity(listaCitas).build();
    }

    //Obtiene todas las citas en una fecha y hora concretas
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{fecha}/{hora}")
    public Response getCitas(@PathParam("fecha") LocalDate fecha, @PathParam("hora") LocalTime hora)
    {
        List<Cita> listaCitas = getCitas.execute(fecha, hora);
        return Response.ok().entity(listaCitas).build();
    }


    //Inserta una cita
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCita(CitaDto citaDto, @Context UriInfo uriInfo )
    {
        Cita citaInsertada = postCitaUseCase.execute(citaDto);
        return Response.created(createUri(uriInfo, citaInsertada)).entity(citaInsertada).build();
    }

    //Elimina una cita
    @DELETE
    @Path("/{id}/{idCita}")
    public Response deleteCitaById(@PathParam("id") String id, @PathParam("idCita") String idCita)
    {
        deleteCitaById.execute(idCita,id);
        return Response.noContent().build();
    }

    //Crea la URI de referencia de una cita
    private URI createUri(UriInfo uriInfo, Cita cita)
    {
        return uriInfo.getBaseUriBuilder()
                .path("/citas/{id}/{idCita}")
                .resolveTemplate("id", cita.getId_paciente())
                .resolveTemplate("idCita", cita.getId())
                .build();

    }
}
