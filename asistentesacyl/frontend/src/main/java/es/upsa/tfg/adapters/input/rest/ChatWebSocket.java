package es.upsa.tfg.adapters.input.rest;

import es.upsa.tfg.application.repository.Repository;
import io.quarkus.websockets.next.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.PathParam;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//La path del websocket es token
@WebSocket(path = "/chat/{token}")
//Lo generamos singleton para no perder accidentalmente la conexión
@Singleton
public class ChatWebSocket {

    //Tenemos formatos predefinidos para los distintos tipos de mensajes
    public enum MessageType {USER_JOINED, USER_LEFT, CHAT_MESSAGE}
    public record ChatMessage(MessageType type, String from, String message) {
    }
    @Inject
    Repository repository;

    //Al abrir el chat mandamos USER_JOINED
    @OnOpen(broadcast = true)
    public ChatMessage onOpen(WebSocketConnection connection) {
        return new ChatMessage(MessageType.USER_JOINED, "Usuario", null);
    }

    //Al salir del chat mandamos USER_LEFT
    @OnClose
    public void onClose(WebSocketConnection connection) {
        ChatMessage departure = new ChatMessage(MessageType.USER_LEFT,"Usuario", null);
        connection.sendTextAndAwait(departure);
    }


    //El más importante, en cada mensaje vemos la conexión y tomamos el parámetro token
    //Utilizamos respository para mandar el mensaje y el token
    @OnTextMessage
    ChatMessage process(WebSocketConnection connection, ChatMessage m)
    {
        connection.sendTextAndAwait(m);
        String token = connection.pathParam("token");
        String answer = repository.respuesta(m.message,token);
        //Devolvemos la respuesta como mensaje
        ChatMessage messagesys = new ChatMessage(MessageType.CHAT_MESSAGE, "System", answer);
        //Enviamos la respuesta
        connection.sendTextAndAwait(messagesys);
        return null;
    }

}