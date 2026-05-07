package es.upsa.tfg.backend.application.utils;

import jakarta.enterprise.context.RequestScoped;

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
