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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Buenos días, indicame en qué puedo ayudarte");
        String question = scanner.nextLine();
        do {
            String answer = assistant.askQuestion(question);
            System.out.println(answer);
            question = scanner.nextLine();
        }while(!question.equals("salir"));

        return 0;
    }


}
