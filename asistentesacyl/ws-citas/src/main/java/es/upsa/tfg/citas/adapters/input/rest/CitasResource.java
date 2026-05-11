package es.upsa.tfg.citas.adapters.input.rest;

import es.upsa.tfg.citas.application.usecases.GetCitaByIdUseCase;
import es.upsa.tfg.citas.application.usecases.GetCitasByPacienteIdUseCase;
import es.upsa.tfg.citas.application.usecases.DeleteCitaByIdUseCase;
import es.upsa.tfg.citas.application.usecases.PostCitaUseCase;
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
import java.util.List;
import java.util.Optional;

@Path("/citas")
public class CitasResource {

    @Inject
    GetCitasByPacienteIdUseCase getCitasByPacienteId;

    @Inject
    DeleteCitaByIdUseCase deleteCitaById;

    @Inject
    PostCitaUseCase postCitaUseCase;

    @Inject
    GetCitaByIdUseCase getCitaById;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getCitasByPacienteId(@PathParam("id") String id)
    {
        List<Cita> listaCitas = getCitasByPacienteId.execute(id);
        return Response.ok().entity(listaCitas).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/{idCita}")
    public Response getCitasById(@PathParam("id") String id,@PathParam("idCita") String idCita)
    {
        Optional<Cita> citaopt = getCitaById.execute(idCita);
        if(citaopt.isPresent())
        {
            Cita  cita = citaopt.get();
            if(cita.getId_paciente().equals(id) )
            {
                return Response.ok().entity(cita).build();
            }
            else
            {
                throw new PacienteNotFoundException();
            }
        }
        else
        {
            throw new PacienteNotFoundException();
        }
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
    @Path("/{id}/{idCita}")
    public Response deleteCitaById(@PathParam("id") String id, @PathParam("idCita") String idCita)
    {
        deleteCitaById.execute(idCita,id);
        return Response.noContent().build();
    }

    private URI createUri(UriInfo uriInfo, Cita cita)
    {
        return uriInfo.getBaseUriBuilder()
                .path("/citas/{id}/{idCita}")
                .resolveTemplate("id", cita.getId_paciente())
                .resolveTemplate("idCita", cita.getId())
                .build();

    }
}
