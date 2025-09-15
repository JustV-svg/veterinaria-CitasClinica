package com.veterinaria.api.Controladores;

import com.veterinaria.api.DTOs.MotivoDTO;
import com.veterinaria.api.LogicaDeNegocio.MotivoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/motivos")
public class MotivoControlador {

    @Autowired
    private MotivoServicio motivoServicio;

    @GetMapping
    public ResponseEntity<?> listarActivos() {
        try {
            List<MotivoDTO> motivos = motivoServicio.listarActivos();
            return ResponseEntity.ok(motivos);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }


    @PostMapping
    public ResponseEntity<?> crear(@RequestBody MotivoDTO motivoDTO) {
        try {
            MotivoDTO motivoCreado = motivoServicio.crear(motivoDTO);
            return ResponseEntity.ok(motivoCreado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody MotivoDTO motivoDTO) {
        try {
            Optional<MotivoDTO> resultado = motivoServicio.editar(id, motivoDTO);
            if (resultado.isPresent()) {
                return ResponseEntity.ok(resultado.get());
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Motivo no encontrado"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivar(@PathVariable Long id) {
        try {
            Optional<MotivoDTO> resultado = motivoServicio.desactivar(id);
            if (resultado.isPresent()) {
                return ResponseEntity.ok(resultado.get());
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Motivo no encontrado"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<?> activar(@PathVariable Long id) {
        try {
            Optional<MotivoDTO> resultado = motivoServicio.activar(id);
            if (resultado.isPresent()) {
                return ResponseEntity.ok(resultado.get());
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Motivo no encontrado"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}