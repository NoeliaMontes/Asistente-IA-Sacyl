package es.upsa.tfg.aggregator.adapters.input.rest;

import es.upsa.tfg.aggregator.application.usecases.GetMedicosDisponiblesUseCase;
import es.upsa.tfg.aggregator.application.usecases.GetPosologiaWithMedicinaByPacienteIdUseCase;
import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;
import es.upsa.tfg.domain.entities.Medico;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Path("/aggregator")
public class AggregatorResource {

    @Inject
    GetPosologiaWithMedicinaByPacienteIdUseCase getPosologiaWithMedicinaByPacienteId;

    @Inject
    GetMedicosDisponiblesUseCase getMedicosDisponibles;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/posologias/{id}")
    public Response getPosologiasWithMedicinaByPacienteId(@PathParam("id") String id)
    {
        List<PosologiaWMedicina> listaPosologias = getPosologiaWithMedicinaByPacienteId.execute(id);
        return Response.ok().entity(listaPosologias).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/medicos/{fecha}/{hora}")
    public Response getMedicosDisponibles(@PathParam("fecha") String fecha, @PathParam("hora") String hora)
    {
        LocalDate date = LocalDate.parse(fecha);
        LocalTime time = LocalTime.parse(hora);
        Medico medicoDisponible = getMedicosDisponibles.execute(date, time);
        return Response.ok().entity(medicoDisponible).build();
    }
}
