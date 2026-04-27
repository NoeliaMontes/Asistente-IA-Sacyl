package es.upsa.tfg.adapters.rest;


import java.util.Optional;

public interface BackendDao
{
    String respuesta(String question, String token);
}
