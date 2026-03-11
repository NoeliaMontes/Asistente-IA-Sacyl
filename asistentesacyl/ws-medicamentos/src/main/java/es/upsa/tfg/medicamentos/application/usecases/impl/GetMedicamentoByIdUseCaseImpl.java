package es.upsa.tfg.medicamentos.application.usecases.impl;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.medicamentos.application.repository.Repository;
import es.upsa.tfg.medicamentos.application.usecases.GetMedicamentoByIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

//Elegimos Application Scoped ya que este Caso de Uso es igual a lo largo de toda la vida de la aplicación
//Consideramos estre criterio para todos los Application Scoped que aparezcan en este módulo
@ApplicationScoped
public class GetMedicamentoByIdUseCaseImpl implements GetMedicamentoByIdUseCase
{
    //Injectamos el repositorio para poder usar su función para encontrar por Id medicamentos
    @Inject
    Repository repository;

    @Override
    public Optional<Medicamento> execute(String id) {
        return repository.getById(id);
    }
}
