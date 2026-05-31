package es.upsa.tfg.backend.adapters.input.rest;

import es.upsa.tfg.backend.application.ai.DocumentationAsistant;
import es.upsa.tfg.backend.application.utils.UserContext;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.time.LocalDateTime;

//A través de esta clase el frontend manda peticiones
@Path("/backend")
public class BackendResource {

    //Inyectamos el asistenteIA
    @Inject
    DocumentationAsistant assistant;

    //Inyectamos el token de seguridad parser
    @Inject
    JWTParser parser;

    //Inyectamos el UserContext para tener acceso al idUsuario
    @Inject
    UserContext context;


    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{token}")
    @POST
    public String askQuestion(String question, @PathParam("token") String token) throws ParseException
    {
        //Obtenemos los parámetros del token pasado en la url
        JsonWebToken jwt = parser.parse(token);
        String userID = jwt.getClaim(Claims.sub.name());
        //Guardamos el userID en UserContext
        context.setUserId(userID);

        //Devolvemos la respuesta de realizar la pregunta al asistente
        //A parte de la pregunta también proporcionamos la fecha actual al asistente
        return assistant.askQuestion(userID,LocalDateTime.now().toString(), question);
    }

}
