package es.upsa.tfg.backend.application.ai;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

//Genera un ChatMemoryProvider nuevo cuando al acceder al @MemoryId no hay uno previo
@ApplicationScoped
public class ChatMemoryProducer {

    @Inject
    @ConfigProperty(name = "model.memory")
    Integer memory;



    //Utiliza @Produces para que sepa generar la bean de ChatMemoryProvider
        @Produces
        public ChatMemoryProvider chatMemoryProvider() {
            //Se utiliza como key memoryId y guardamos un máximo de 5 mensajes en memoria
            return memoryId -> MessageWindowChatMemory.withMaxMessages(memory);
        }

}
