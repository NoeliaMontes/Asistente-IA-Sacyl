package es.upsa.tfg.rest;

import es.upsa.tfg.adapters.rest.BackendDao;
import es.upsa.tfg.rest.restapi.BackendRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class BackendDaoImpl implements BackendDao
{
    @Inject
    @RestClient
    BackendRestClient restClient;


    @Override
    public String respuesta(String question,String token)
    {
        try {
            return restClient.askQuestion(token,question);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }
}
