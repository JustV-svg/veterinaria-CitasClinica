package com.veterinaria.api.LogicaDeNegocio;

import com.veterinaria.api.Entidades.Historial;

import java.util.List;

public interface HistorialService {
    Historial crearHistorial(Historial historial);
    Historial actualizarHistorial(Long id, Historial historial);
    List<Historial> obtenerPorMascota(Long mascotaId);
    Historial anularHistorial(Long id, String corregidoPor);
}
