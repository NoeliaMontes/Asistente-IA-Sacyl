package es.upsa.tfg.backend.rest.client;

import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.entities.Medico;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RegisterRestClient(baseUri = "http://ngnix:80/aggregator")
public interface AggregatorRestClient
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/posologias/{id}")
    List<PosologiaWMedicina> getPosologia(@PathParam("id") String id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/medicos/{fecha}/{hora}")
    Medico getMedicosDisponibles(@PathParam("fecha") LocalDate fecha, @PathParam("hora") LocalTime hora);

}
