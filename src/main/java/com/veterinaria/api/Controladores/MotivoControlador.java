package com.veterinaria.api.Controladores;

import com.veterinaria.api.Entidades.Motivo;
import com.veterinaria.api.LogicaDeNegocio.MotivoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motivos")
public class MotivoControlador {

    @Autowired
    private MotivoServicio motivoServicio;

    // HU08 - Consultar motivos disponibles
    @GetMapping
    public ResponseEntity<?> listarActivos() {
        List<Motivo> motivos = motivoServicio.listarActivos();
        if (motivos.isEmpty()) {
            return ResponseEntity.ok("No hay motivos disponibles");
        }
        return ResponseEntity.ok(motivos);
    }

    // HU06 - Registrar motivo de cita
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Motivo motivo) {
        try {
            Motivo nuevo = motivoServicio.crear(motivo);
            return ResponseEntity.ok(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("No se puede crear: " + e.getMessage());
        }
    }

    // HU07 - Editar motivo
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Motivo motivo) {
        try {
            Motivo actualizado = motivoServicio.editar(id, motivo)
                    .orElseThrow(() -> new RuntimeException("El motivo con ID " + id + " no existe"));
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al editar: " + e.getMessage());
        }
    }

    // HU07 - Desactivar motivo
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivar(@PathVariable Long id) {
        try {
            Motivo desactivado = motivoServicio.desactivar(id)
                    .orElseThrow(() -> new RuntimeException("El motivo con ID " + id + " no existe"));
            return ResponseEntity.ok("Motivo desactivado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Activar motivo
    @PutMapping("/{id}/activar")
    public ResponseEntity<?> activar(@PathVariable Long id) {
        try {
            Motivo activado = motivoServicio.activar(id)
                    .orElseThrow(() -> new RuntimeException("El motivo con ID " + id + " no existe"));
            return ResponseEntity.ok("Motivo activado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Eliminar motivo (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            boolean eliminado = motivoServicio.eliminar(id);
            if (eliminado) {
                return ResponseEntity.ok("Motivo eliminado correctamente");
            } else {
                return ResponseEntity.badRequest().body("No se encontró el motivo con ID " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al eliminar: " + e.getMessage());
        }
    }
}
