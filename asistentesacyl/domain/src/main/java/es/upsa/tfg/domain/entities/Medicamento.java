package es.upsa.tfg.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Utilizamos la librería lombok para facilitar los constructor
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Clase Medicamento con sus atributos
public class Medicamento
{
    String id;
    String nombre;
}
