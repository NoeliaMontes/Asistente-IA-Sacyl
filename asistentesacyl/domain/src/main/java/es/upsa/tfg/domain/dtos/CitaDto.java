package es.upsa.tfg.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaDto
{
    String id_medico;
    String id_paciente;
    String lugar;
    String motivo;
    Date fecha;
    Time hora;
    String tipo;
}

