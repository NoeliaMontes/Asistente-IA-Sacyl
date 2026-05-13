package es.upsa.tfg.backend.application.ai;

import dev.langchain4j.agent.tool.Tool;
import es.upsa.tfg.backend.application.utils.UserContext;
import es.upsa.tfg.backend.rest.client.AggregatorRestClient;
import es.upsa.tfg.backend.rest.client.CitasRestClient;
import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;
import es.upsa.tfg.domain.dtos.CitaDto;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.domain.enums.Lugar;
import es.upsa.tfg.domain.enums.Tipo;
import es.upsa.tfg.domain.exceptions.CitaNotFoundException;
import es.upsa.tfg.domain.exceptions.SacylException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

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

    @Tool("Consultar citas por tipo de consulta/medico")
    @Transactional
    public List<Cita> getCitasByType(String tipo)
    {
        List<Cita> citasPaciente = citas.getCitas(context.getUserId());
        return citasPaciente.stream().filter(cita -> cita.getTipo().equals(tipo)).toList();

    }


    @Tool("Pedir o sacar una cita")
    @Transactional
    public String postCitas(String lugar, String tipo, String motivo, String fecha, String hora)
    {
        LocalDate fechaCita = LocalDate.parse(fecha);
        LocalTime horaCita = LocalTime.parse(hora);
        Lugar place;
        if(lugar.contains("virtual") || lugar.contains("no presencial") || lugar.contains("telefonica"))
        {
             place = Lugar.NO_PRESENCIAL;
        }
        else
        {
             place = Lugar.PRESENCIAL;
        }

        Tipo type;
        switch (tipo.toLowerCase())
        {
            case "matrona" -> {
                type = Tipo.MATRONA;
            }

            case "medicina" -> {
                type = Tipo.MEDICINA;
            }

            case "enfermeria" -> {
                type = Tipo.ENFERMERIA;
            }

            case "analisis" -> {
                type = Tipo.ANALISIS;
            }

            case "trabajador social" -> {
                type = Tipo.TRABAJADOR_SOCIAL;
            }
            case "vacunas adultos" -> {
                type = Tipo.VACUNAS_ADULTOS;
            }
            case "vacunas pediatria" -> {
                type = Tipo.VACUNAS_PEDIATRIA;
            }

            default -> {
                return "No se permite este tipo de cita";
            }
        }
        try {
            Medico medico = aggregator.getMedicosDisponibles(fechaCita, horaCita);
            CitaDto nuevaCita = CitaDto.builder()
                    .id_medico(medico.getId())
                    .id_paciente(context.getUserId())
                    .lugar(place.toString())
                    .tipo(type.toString())
                    .motivo(motivo)
                    .fecha(fechaCita)
                    .hora(horaCita)
                    .build();

            citas.postCita(nuevaCita);
            return "La cita ha sido confirmada";
        }
        catch (SacylException exception)
        {
            return "ERROR: No se pudo insertar la cita debido a: " + exception.getMessage();
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

