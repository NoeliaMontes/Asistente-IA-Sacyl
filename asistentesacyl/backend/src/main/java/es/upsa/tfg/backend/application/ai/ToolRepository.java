package es.upsa.tfg.backend.application.ai;

import dev.langchain4j.agent.tool.Tool;
import es.upsa.tfg.backend.application.utils.UserContext;
import es.upsa.tfg.backend.rest.client.AggregatorRestClient;
import es.upsa.tfg.backend.rest.client.CitasRestClient;
import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.enums.Lugar;
import es.upsa.tfg.domain.enums.Tipo;
import es.upsa.tfg.domain.exceptions.CitaNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
public class ToolRepository
{
    @Inject
    @RestClient
    AggregatorRestClient aggregator;

    @Inject
    @RestClient
    CitasRestClient citas;

    @Inject
    UserContext context;

    @Tool("Cancelar una cita cuando el usuario tiene el id de la cita")
    @Transactional
    public String cancelCitaById(String idCita)
    {
        try {
            citas.deleteCitaById(context.getUserId(),idCita);
            return "Cita eliminada con éxito";
        }catch (CitaNotFoundException exception)
        {
            return """
               ERROR:
               La cita que están intentando anular no existe o no pertenece a este paciente
               """;
        }
    }

    @Tool("Consultar una cita cuando el usuario tiene el id de la cita")
    @Transactional
    public void getCitaById(String idCita)
    {

    }

    /*
    @Tool("Consultar una cita cuando el usuario solo tiene fecha y hora de la cita")
    @Transactional
    public void getCitaByTime(LocalDate fecha,LocalTime hora)
    {

    }
     */


    @Tool("Pedir o sacar una cita")
    @Transactional
    public void postCitas(Lugar lugar, Tipo tipo, String motivo, LocalDate fecha, LocalTime hora)
    {
        CitaDto nuevaCita = CitaDto.builder()
                .id_medico("")
                .id_paciente(context.getUserId())
                .lugar(lugar.toString())
                .tipo(tipo.toString())
                .motivo(motivo)
                .fecha(fecha)
                .hora(hora)
                .build();

        try {
            citas.postCita(nuevaCita);
        }catch (RuntimeException exception)
        {
            throw new RuntimeException(exception);
        }

    }


    @Tool("Ver mis citas")
    @Transactional
    public List<Cita> getCitas()
    {
        return  citas.getCitas(context.getUserId());
    }

    @Tool("Ver mis medicamentos")
    @Transactional
    public List<PosologiaWMedicina> listaPosologias()
    {
        return aggregator.getPosologia(context.getUserId());
    }
}

