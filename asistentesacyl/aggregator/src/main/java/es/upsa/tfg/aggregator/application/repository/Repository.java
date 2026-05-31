package es.upsa.tfg.aggregator.application.repository;


import es.upsa.tfg.domain.entities.Cita;
import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.entities.Medico;
import es.upsa.tfg.domain.entities.Posologia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface Repository
{
    List<Posologia> getPosologiasById(String id);
    Optional<Medicamento> getMedicamentoById(String id);
    List<Medico> getMedicos();
    List<Cita> getCitas(LocalDate fecha, LocalTime hora);
}
