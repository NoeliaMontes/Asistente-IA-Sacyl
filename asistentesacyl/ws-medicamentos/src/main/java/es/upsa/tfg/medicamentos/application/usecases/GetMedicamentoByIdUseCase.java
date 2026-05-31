package es.upsa.tfg.medicamentos.application.usecases;

import es.upsa.tfg.domain.entities.Medicamento;

import java.util.Optional;

public interface GetMedicamentoByIdUseCase
{
    Optional<Medicamento> execute(String id);
}
