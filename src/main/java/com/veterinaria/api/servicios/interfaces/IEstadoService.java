package com.veterinaria.api.servicios.interfaces;

import com.veterinaria.api.dtos.estado.EstadoGuardar;
import com.veterinaria.api.dtos.estado.EstadoModificar;
import com.veterinaria.api.dtos.estado.EstadoSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEstadoService {

    List<EstadoSalida> obtenerTodos();

    Page<EstadoSalida> obtenerTodosPaginados(Pageable pageable);

    EstadoSalida obtenerPorId(Integer id);

    EstadoSalida crear(EstadoGuardar estadoGuardar);

    EstadoSalida editar(EstadoModificar estadoModificar);

    void eliminarPorId(Integer id);
}