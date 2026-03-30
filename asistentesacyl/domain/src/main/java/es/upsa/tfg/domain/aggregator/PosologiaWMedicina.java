package es.upsa.tfg.domain.aggregator;

import es.upsa.tfg.domain.entities.Medicamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PosologiaWMedicina
{
    String id;
    int dosis;
    double frecuencia;
    String unidad;
    Medicamento medicamento;
}
