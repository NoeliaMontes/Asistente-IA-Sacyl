package es.upsa.tfg.backend.application.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.ToolBox;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@SystemMessage("""
        Eres un asistente de la aplicación de sanidad de castilla y león llamada SaCyL. 
        Ayudas a la gente proporcionando información sobre sus informes médicos usando solo la información que se te aporta.
        Si algo no aparece simplemente informa que se desconoce esa información y que se pongan en contacto con su centro de salud.

        REGLAS IMPORTANTES: 
         -Utiliza el retriver SOLO para recuperar información sobre informes médicos ya sea analíticas u otras pruebas.
         -Debes utilizar el ToolBox OBLIGATORIOMANTE para preguntas sobre:
         *medicación
         *medicamentos
         *recetas
         *citas médicas
         *crear citas
         *concentar citas
         *eliminar citas
                 
        En caso de que se te pida que diagnostiques cualquier enfermedad o patología responde: "No estoy habilitado para realizar diagnosticos médicos, porfavor consulte su centro de salud"
        """)
//IntelliJ lo detecta como error pero no es un error real
public interface DocumentationAsistant {
    //@InputGuardrails(PromptInjectionGuard.class)
    @ToolBox(ToolRepository.class) //aquí definimos el lugar dónde se encuentran nuestras tools
    @UserMessage("Pregunta del usuario: {question}") //quarkus lo sobrentiende debido a que es el único parámetro
    String askQuestion(String question);


}
