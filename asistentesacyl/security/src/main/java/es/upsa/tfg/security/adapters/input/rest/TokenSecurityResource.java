package es.upsa.tfg.security.adapters.input.rest;

import es.upsa.tfg.domain.entities.Paciente;
import es.upsa.tfg.security.application.security.GenerateToken;
import es.upsa.tfg.security.application.usecases.GetPacienteByIdUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Optional;

@Path("/secure")
public class TokenSecurityResource {

    @Inject
    GenerateToken generator;

    @Inject
    GetPacienteByIdUseCase getPacienteByIdUseCase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response returnToken(@PathParam("id") String id) {
        System.out.println("ID RAW: [" + id + "]");
        Optional<Paciente> paciente = getPacienteByIdUseCase.execute(id);
        if (paciente.isPresent()) return Response.ok().entity(generator.generate(paciente.get())).build();
        else throw new RuntimeException();
    }
}