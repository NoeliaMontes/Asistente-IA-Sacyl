package es.upsa.tfg.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
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
    LocalDate fecha;
    LocalTime hora;
    String tipo;
}
