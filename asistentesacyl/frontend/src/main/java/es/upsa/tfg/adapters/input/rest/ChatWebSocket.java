package es.upsa.tfg.adapters.input.rest;

import es.upsa.tfg.application.repository.Repository;
import io.quarkus.websockets.next.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;

import java.util.Optional;

@WebSocket(path = "/chat/{token}")
@SessionScoped
public class ChatWebSocket {


    public enum MessageType {USER_JOINED, USER_LEFT, CHAT_MESSAGE}
    public record ChatMessage(MessageType type, String from, String message) {
    }
    @Inject
    Repository repository;

    @Inject
    WebSocketConnection connection;

    String token;

    @OnOpen(broadcast = true)
    public ChatMessage onOpen(WebSocketConnection connection) {
        this.token = connection.pathParam("token");
        return new ChatMessage(MessageType.USER_JOINED, "Usuario", null);
    }

    @OnClose
    public void onClose() {
        ChatMessage departure = new ChatMessage(MessageType.USER_LEFT,"Usuario", null);
        connection.broadcast().sendTextAndAwait(departure);
    }


    @OnTextMessage
    ChatMessage process(ChatMessage m)
    {
        connection.broadcast().sendTextAndAwait(m);
        String answer = repository.respuesta(m.message,token);
        ChatMessage messagesys = new ChatMessage(MessageType.CHAT_MESSAGE, "System", answer);
        connection.broadcast().sendTextAndAwait(messagesys);
        return null;
    }

}