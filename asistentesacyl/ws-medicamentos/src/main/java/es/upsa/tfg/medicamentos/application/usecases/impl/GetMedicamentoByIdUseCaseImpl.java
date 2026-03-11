package es.upsa.tfg.medicamentos.application.usecases.impl;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.medicamentos.application.repository.Repository;
import es.upsa.tfg.medicamentos.application.usecases.GetMedicamentoByIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class GetMedicamentoByIdUseCaseImpl implements GetMedicamentoByIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public Optional<Medicamento> execute(String id) {
        return repository.getById(id);
    }
}
