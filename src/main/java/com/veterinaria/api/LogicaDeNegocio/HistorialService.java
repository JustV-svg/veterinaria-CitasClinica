package com.veterinaria.api.LogicaDeNegocio;

import com.veterinaria.api.DTOs.HistorialDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface HistorialService {
    CompletableFuture<HistorialDTO> crearHistorial(HistorialDTO historialDTO);
    CompletableFuture<HistorialDTO> actualizarHistorial(Long id, HistorialDTO historialDTO);
    CompletableFuture<List<HistorialDTO>> obtenerPorMascota(Long mascotaId);
    CompletableFuture<HistorialDTO> anularHistorial(Long id, String corregidoPor);
}
