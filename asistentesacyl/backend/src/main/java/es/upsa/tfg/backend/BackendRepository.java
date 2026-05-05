package es.upsa.tfg.backend;

import dev.langchain4j.agent.tool.Tool;
import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;
import es.upsa.tfg.domain.entities.Cita;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

public class BackendRepository
{
    @Tool("Cancelar una cita")
    @Transactional
    public void cancelCita() {

    }


    @Tool("Ver una cita")
    @Transactional
    public Cita getCitaDetails() {
        return null;
    }

    @Tool("Ver mis medicamentos")
    @Transactional
    public List<PosologiaWMedicina> listaPosologias() {
        return null;
    }
}

