package com.veterinaria.api.Controladores;


import com.veterinaria.api.Entidades.Cita;
import com.veterinaria.api.LogicaDeNegocio.CitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas")
public class CitaControlador {

    @Autowired
    private CitaServicio citaServicio;

    // HU01 - Listar citas por mascota
    @GetMapping("/{mascotaId}")
    public List<Cita> listarPorMascota(@PathVariable Long mascotaId) {
        return citaServicio.listarPorMascota(mascotaId);
    }

    // HU02 - Crear cita
    @PostMapping
    public Cita crear(@RequestBody Cita cita) {
        return citaServicio.crear(cita);
    }

    // HU03 - Reprogramar
    @PutMapping("/{id}/reprogramar")
    public Optional<Cita> reprogramar(@PathVariable Long id, @RequestBody Cita cita) {
        return citaServicio.reprogramar(id, cita);
    }

    // HU04 - Cancelar
    @PutMapping("/{id}/cancelar")
    public Optional<Cita> cancelar(@PathVariable Long id) {
        return citaServicio.cancelar(id);
    }

    // HU05 - Completar
    @PutMapping("/{id}/completar")
    public Optional<Cita> completar(@PathVariable Long id) {
        return citaServicio.completar(id);
    }

    // HU10 & HU11 - Actualizar estado
    @PutMapping("/{id}/estado")
    public Optional<Cita> actualizarEstado(@PathVariable Long id, @RequestParam Long estadoId) {
        return citaServicio.actualizarEstado(id, estadoId);
    }

    // HU12 - Consultar estado
    @GetMapping("/{id}/estado")
    public Optional<String> consultarEstado(@PathVariable Long id) {
        return citaServicio.consultarEstado(id);
    }
}
