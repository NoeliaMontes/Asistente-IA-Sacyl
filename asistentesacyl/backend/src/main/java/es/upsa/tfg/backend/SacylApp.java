package es.upsa.tfg.backend;

import io.quarkus.runtime.QuarkusApplication;
import jakarta.inject.Inject;

import java.util.Scanner;

public class SacylApp implements QuarkusApplication
{

    @Inject
    DocumentationAsistant assistant;

    @Override
    public int run(String... args) throws Exception
    {
        io.quarkus.runtime.Quarkus.waitForExit();
        return 0;
    }


}
