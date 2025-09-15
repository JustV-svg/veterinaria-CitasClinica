package com.veterinaria.api.dtos.estado;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter


public class EstadoModificar  implements  Serializable{
    private  Integer id;
    private  String nombre;

}
