package com.veterinaria.api.Controladores;

import com.veterinaria.api.DTOs.HistorialDTO;
import com.veterinaria.api.Entidades.Historial;
import com.veterinaria.api.LogicaDeNegocio.HistorialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/historiales")
public class HistorialController {
    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
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

    @GetMapping("/mascota/{mascotaId}")
    public CompletableFuture<List<HistorialDTO>> obtenerPorMascota(@PathVariable Long mascotaId) {
        return historialService.obtenerPorMascota(mascotaId);
    }

    @PutMapping("/{id}/anular")
    public CompletableFuture<HistorialDTO> anularHistorial(@PathVariable Long id,
                                                           @RequestParam String corregidoPor) {
        return historialService.anularHistorial(id, corregidoPor);
    }
}
