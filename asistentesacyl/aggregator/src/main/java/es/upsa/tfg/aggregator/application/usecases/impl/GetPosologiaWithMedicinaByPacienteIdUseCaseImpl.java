package es.upsa.tfg.aggregator.application.usecases.impl;


import es.upsa.tfg.aggregator.application.repository.Repository;
import es.upsa.tfg.aggregator.application.usecases.GetPosologiaWithMedicinaByPacienteIdUseCase;
import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;
import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Posologia;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@ApplicationScoped
public class GetPosologiaWithMedicinaByPacienteIdUseCaseImpl implements GetPosologiaWithMedicinaByPacienteIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<PosologiaWMedicina> execute(String id)
    {
        List<PosologiaWMedicina> listaPyM = new ArrayList<>();
        List<Posologia> listPosologias = repository.getPosologiasById(id);
        if(listPosologias.isEmpty())
        {
            System.out.println("No hay posologia");
            return listaPyM;
        }
        else
        {
            for (Posologia posologia : listPosologias) {
                Optional<Medicamento> medicamento = repository.getMedicamentoById(posologia.getId_medicina());
                if (medicamento.isPresent())
                {
                    System.out.println("Hay posologia y medicamento");
                    listaPyM.add(PosologiaWMedicina.builder()
                            .id(posologia.getId())
                            .dosis(posologia.getDosis())
                            .frecuencia(posologia.getFrecuencia())
                            .unidad(posologia.getUnidad())
                            .medicamento(medicamento.get())
                            .build());
                }
                System.out.println("No hay medicamento");
            }
            return listaPyM;
        }
    }
}
