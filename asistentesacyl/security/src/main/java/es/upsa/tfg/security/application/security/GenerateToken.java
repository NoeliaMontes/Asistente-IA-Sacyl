package es.upsa.tfg.security.application.security;

import es.upsa.tfg.domain.entities.Paciente;

public interface GenerateToken
{
    public String generate(Paciente paciente);
}
