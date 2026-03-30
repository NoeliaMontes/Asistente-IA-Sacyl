package es.upsa.tfg.aggregator.application.usecases;


import es.upsa.tfg.domain.aggregator.PosologiaWMedicina;

import java.util.List;

public interface GetPosologiaWithMedicinaByPacienteIdUseCase
{
    List<PosologiaWMedicina> execute(String id);
}
