package es.upsa.tfg.backend.application.utils;

import jakarta.enterprise.context.RequestScoped;

//Clase usada para mantener el id del usuario en Backend siempre disponible
@RequestScoped
public class UserContext
{
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
