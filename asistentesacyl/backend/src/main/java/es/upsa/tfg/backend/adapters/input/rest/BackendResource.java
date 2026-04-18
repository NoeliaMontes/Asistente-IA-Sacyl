package es.upsa.tfg.backend.adapters.input.rest;

import es.upsa.tfg.backend.DocumentationAsistant;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/backend")
public class BackendResource {

    @Inject
    DocumentationAsistant assistant;

    @Inject
    JWTParser parser;


    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{token}")
    @POST
    public String askQuestion(String question, @PathParam("token") String token) throws ParseException
    {
        JsonWebToken jwt = parser.parse(token);
        String userID = jwt.getClaim(Claims.sub.name());
        System.out.println(userID);
        return assistant.askQuestion(question);
    }

}
