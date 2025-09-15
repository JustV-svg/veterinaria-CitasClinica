package com.veterinaria.api.LogicaDeNegocio;

import com.veterinaria.api.Entidades.Cita;
import java.util.List;
import java.util.Optional;

public interface CitaServicio {
    List<Cita> listarPorMascota(Long mascotaId);
    Cita crear(Cita cita);
    Optional<Cita> reprogramar(Long id, Cita cita);
    Optional<Cita> cancelar(Long id);
    Optional<Cita> completar(Long id);
    Optional<Cita> actualizarEstado(Long id, Long estadoId);
    Optional<String> consultarEstado(Long id);
}