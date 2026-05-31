package es.upsa.tfg.security.adapters.input.rest;

import es.upsa.tfg.domain.dtos.PacienteDto;
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

    //Inyectamos el generador de tokens que utilizará las claves proporcionadas para generar un token
    @Inject
    GenerateToken generator;

    //Obtenemos el método para verificar pacientes
    @Inject
    GetPacienteByIdUseCase getPacienteByIdUseCase;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response returnToken(PacienteDto pacienteDto) {
        //Verificamos si los datos son correctos
        Optional<Paciente> paciente = getPacienteByIdUseCase.execute(pacienteDto);
        //Si los datos son correctos enviamos el token si no da error
        if (paciente.isPresent()) return Response.ok().entity(generator.generate(paciente.get())).build();
        else return Response.status(404).build();
    }
}