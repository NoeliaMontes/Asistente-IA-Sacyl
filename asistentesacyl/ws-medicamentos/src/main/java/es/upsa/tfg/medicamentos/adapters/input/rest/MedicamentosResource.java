package es.upsa.tfg.medicamentos.adapters.input.rest;

import es.upsa.tfg.domain.entities.Medicamento;
import es.upsa.tfg.domain.exceptions.MedicamentoNotFoundException;
import es.upsa.tfg.medicamentos.application.usecases.GetMedicamentoByIdUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/medicamentos")
public class MedicamentosResource {

    @Inject
    GetMedicamentoByIdUseCase getMedicamentoById;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getMedicamentoById(@PathParam("id") String id)
    {
        Optional<Medicamento> medicamentoOpt = getMedicamentoById.execute(id);

        if (medicamentoOpt.isPresent()) return Response.ok().entity(medicamentoOpt.get()).build();
        else throw new MedicamentoNotFoundException();
    }

}
