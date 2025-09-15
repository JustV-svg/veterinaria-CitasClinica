package com.veterinaria.api.Controladores;

import com.veterinaria.api.Entidades.Historial;
import com.veterinaria.api.LogicaDeNegocio.HistorialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiales")
public class HistorialController {
    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    // HU13: Crear historial
    @PostMapping
    public Historial crearHistorial(@RequestBody Historial historial) {
        return historialService.crearHistorial(historial);
    }

    // HU14: Actualizar historial
    @PutMapping("/{id}")
    public Historial actualizarHistorial(@PathVariable Long id,
                                         @RequestBody Historial historial) {
        return historialService.actualizarHistorial(id, historial);
    }

    // HU15: Consultar historiales por mascota
    @GetMapping("/mascota/{mascotaId}")
    public List<Historial> obtenerPorMascota(@PathVariable Long mascotaId) {
        return historialService.obtenerPorMascota(mascotaId);
    }

    // HU16: Anular historial
    @PutMapping("/{id}/anular")
    public Historial anularHistorial(@PathVariable Long id,
                                     @RequestParam String corregidoPor) {
        return historialService.anularHistorial(id, corregidoPor);
    }
}
