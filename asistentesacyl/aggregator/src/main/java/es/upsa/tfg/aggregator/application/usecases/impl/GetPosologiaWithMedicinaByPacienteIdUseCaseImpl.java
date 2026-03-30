package es.upsa.tfg.aggregator.application.usecases.impl;


import es.upsa.tfg.aggregator.application.repository.Repository;
import es.upsa.tfg.aggregator.application.usecases.GetPosologiaWithMedicinaByPacienteIdUseCase;
import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;
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
        List<Posologia> listPosologias = repository.getPosologiasById(id);
        if(listPosologias.isEmpty())
        {
            return new ArrayList<>();
        }

        listPosologias.forEach();


    }
}
