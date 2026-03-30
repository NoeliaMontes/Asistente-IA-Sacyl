package es.upsa.tfg.aggregator.implementation.rest.restapi;


import es.upsa.tfg.domain.entities.Posologia;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://ngnix")
//@RegisterProvider(StringToListParamConverterProvider.class)
//@RegisterProvider(MsPeliculasResponseExceptionMapper.class)
@Path("/posologias")
public interface PosologiasRestClient
{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
     List<Posologia> getPosologiaByPacienteId(@PathParam("id") String id);

}
