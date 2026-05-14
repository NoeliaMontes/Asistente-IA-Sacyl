package es.upsa.tfg.application.repository.impl;



import es.upsa.tfg.adapters.rest.BackendDao;
import es.upsa.tfg.application.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RepositoryImpl implements Repository
{

    @Inject
    BackendDao backend;

    //Repository consulta el BackendDao para obtener respuesta
    @Override
    public String respuesta(String question, String token)    {
        return backend.respuesta(question,token);
    }

}
