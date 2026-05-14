package es.upsa.tfg.backend.application.ai;

import io.quarkus.runtime.QuarkusApplication;
import jakarta.inject.Inject;

public class SacylApp implements QuarkusApplication
{

    @Inject
    DocumentationAsistant assistant;

    //La aplicación siempre está en ejecución y lista para recibir mensajes
    @Override
    public int run(String... args) throws Exception
    {
        io.quarkus.runtime.Quarkus.waitForExit();
        return 0;
    }


}
