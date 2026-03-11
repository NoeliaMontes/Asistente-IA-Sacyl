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

    //Injección caso de uso
    @Inject
    GetMedicamentoByIdUseCase getMedicamentoById;

    //Método GET de un medicamento específico marcado por el id
    @GET
    //La respuesta se encuentra en JSON
    @Produces(MediaType.APPLICATION_JSON)
    //El id se pasa como PathParam NO como Query
    @Path("/{id}")
    public Response getMedicamentoById(@PathParam("id") String id)
    {
        //El caso de uso devuelve un opcional de medicamento
        Optional<Medicamento> medicamentoOpt = getMedicamentoById.execute(id);
        //En caso de que el opcional  no esté vacío se pasa en la respuesta el medicamento como entidad
        if (medicamentoOpt.isPresent()) return Response.ok().entity(medicamentoOpt.get()).build();
        //En caso de que el opcional esté vacío devolvemos MedicamentoNotFoundException
        //Valorar si mejor devolver un 404.
        else throw new MedicamentoNotFoundException();
    }

}
