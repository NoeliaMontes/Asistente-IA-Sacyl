package es.upsa.tfg.aggregator.implementation.rest.restapi;

import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RegisterRestClient(baseUri = "http://ngnix")
public interface CitasRestClient
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/citas/{fecha}/{hora}")
    List<Cita> getCitas(@PathParam("fecha") LocalDate fecha, @PathParam("hora") LocalTime hora);
}
