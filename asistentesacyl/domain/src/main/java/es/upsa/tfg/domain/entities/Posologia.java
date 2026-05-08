package es.upsa.tfg.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Posologia
{
    String id;
    String id_medico;
    String id_paciente;
    String id_medicina;
    int dosis;
    int frecuencia;
    String unidad;
}
