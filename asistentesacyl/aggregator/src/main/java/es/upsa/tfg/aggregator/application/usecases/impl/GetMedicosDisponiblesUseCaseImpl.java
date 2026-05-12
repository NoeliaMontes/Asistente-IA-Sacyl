package es.upsa.tfg.aggregator.application.usecases.impl;

import es.upsa.tfg.aggregator.application.repository.Repository;
import es.upsa.tfg.aggregator.application.usecases.GetMedicosDisponiblesUseCase;
import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.entities.Medico;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetMedicosDisponiblesUseCaseImpl implements GetMedicosDisponiblesUseCase
{
    @Inject
    Repository repository;

    @Override
    public Medico execute(LocalDate date, LocalTime time)
    {
        List<Medico> medicos = repository.getMedicos();
        List<String> ocupados = new ArrayList<>();
        List<Cita> citas = repository.getCitas(date, time);
        for (Cita cita : citas)
        {
            ocupados.add(cita.getId_medico());
        }
        for (Medico medico: medicos)
        {
            if(!ocupados.contains(medico.getId()))
            {
                return medico;
            }
        }
        throw new RuntimeException("No hay medicos disponibles en este momento");
    }
}
