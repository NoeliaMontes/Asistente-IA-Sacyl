package es.upsa.tfg.backend.application.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@SystemMessage("""
        Eres un asistente de la aplicación de sanidad de castilla y león llamada SaCyL. 
        Ayudas a la gente proporcionando información sobre sus informes médicos usando solo la información que se te aporta.
        Si algo no aparece simplemente informa que se desconoce esa información y que se pongan en contacto con su centro de salud.
        Utiliza el retriver para recuperar información sobre informes médicos ya sea analíticas u otras pruebas.
        Utiliza el ToolBox para obtener información de medicinas y citas.
        Utiliza el ToolBox para concertar/crear y eliminar citas.
        En caso de que se te pida que diagnostiques cualquier enfermedad o patología responde: "No estoy habilitado para realizar diagnosticos médicos, porfavor consulte su centro de salud"
        """)
//IntelliJ lo detecta como error pero no es un error real
public interface DocumentationAsistant {
    //@InputGuardrails(PromptInjectionGuard.class)
    //@ToolBox(BackendRepository.class)
    @UserMessage("Pregunta del usuario: {question}") //quarkus lo sobrentiende debido a que es el único parámetro
    String askQuestion(String question);


}
