package es.upsa.tfg.aggregator.implementation.rest.restapi;

import es.upsa.tfg.domain.entities.Medicamento;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import java.util.Optional;

@RegisterRestClient(baseUri = "http://ngnix")
//@RegisterProvider(StringToListParamConverterProvider.class)
//@RegisterProvider(MsPersonasResponseExceptionMapper.class)
@Path("/medicamentos")
public interface MedicamentosRestClient
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
     Medicamento getMedicamentoById(@PathParam("id") String id);

}
