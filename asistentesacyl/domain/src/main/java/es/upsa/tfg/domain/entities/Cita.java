package es.upsa.tfg.domain.entities;

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
public class Cita
{
    String id;
    String id_medico;
    String id_paciente;
    String lugar;
    String motivo;
    Date fecha;
    Time hora;
    String tipo;
}
