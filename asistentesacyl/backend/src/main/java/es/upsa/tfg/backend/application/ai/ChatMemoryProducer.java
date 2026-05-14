package es.upsa.tfg.backend.application.ai;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

//Genera un ChatMemoryProvider nuevo cuando al acceder al @MemoryId no hay uno previo
@ApplicationScoped
public class ChatMemoryProducer {

        //Utiliza @Produces para que sepa generar la bean de ChatMemoryProvider
        @Produces
        public ChatMemoryProvider chatMemoryProvider() {
            //Se utiliza como key memoryId y guardamos un máximo de 5 mensajes en memoria
            return memoryId -> MessageWindowChatMemory.withMaxMessages(5);
        }

}
