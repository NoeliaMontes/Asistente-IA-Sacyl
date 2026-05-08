package es.upsa.tfg.backend.application.ai;

import dev.langchain4j.agent.tool.Tool;
import es.upsa.tfg.backend.application.utils.UserContext;
import es.upsa.tfg.backend.rest.client.AggregatorRestClient;
import es.upsa.tfg.backend.rest.client.CitasRestClient;
import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

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

    @Tool("Cancelar una cita")
    @Transactional
    public void cancelCita()
    {

    }


    @Tool("Ver mis citas")
    @Transactional
    public List<Cita> getCitas()
    {
        System.out.println("USER ID TOOL: " + context.getUserId());
        return  citas.getCitas(context.getUserId());
    }

    @Tool("Ver mis medicamentos")
    @Transactional
    public List<PosologiaWMedicina> listaPosologias()
    {
        System.out.println("USER ID TOOL: " + context.getUserId());
        return aggregator.getPosologia(context.getUserId());
    }
}

