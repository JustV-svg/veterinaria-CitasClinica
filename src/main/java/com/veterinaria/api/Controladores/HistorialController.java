package com.veterinaria.api.Controladores;

import com.veterinaria.api.DTOs.HistorialDTO;
import com.veterinaria.api.LogicaDeNegocio.HistorialServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/historiales")
public class HistorialController {
    private final HistorialServicio historialService;

    public HistorialController(HistorialServicio historialService) {
        this.historialService = historialService;
    }

    @PostMapping
    public CompletableFuture<HistorialDTO> crearHistorial(@RequestBody HistorialDTO dto) {
        return historialService.crearHistorial(dto);
    }

    @PutMapping("/{id}")
    public CompletableFuture<HistorialDTO> actualizarHistorial(@PathVariable Long id,
                                                               @RequestBody HistorialDTO dto) {
        return historialService.actualizarHistorial(id, dto);
    }

    @GetMapping("/cita/{citaId}")
    public CompletableFuture<List<HistorialDTO>> obtenerPorCita(@PathVariable Long citaId) {
        return historialService.obtenerPorCita(citaId);
    }

    @PutMapping("/{id}/anular")
    public CompletableFuture<HistorialDTO> anularHistorial(@PathVariable Long id,
                                                           @RequestParam String corregidoPor) {
        return historialService.anularHistorial(id, corregidoPor);
    }
}
