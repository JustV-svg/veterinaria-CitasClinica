package com.veterinaria.api.LogicaDeNegocio;

import com.veterinaria.api.Entidades.Cita;
import com.veterinaria.api.Repositorios.CitaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServicioImpl implements CitaServicio {

    @Autowired
    private CitaRepositorio citaRepositorio;

    @Override
    public List<Cita> listarPorMascota(Long mascotaId) {
        return citaRepositorio.findByMascotaIdOrderByFechaHoraAsc(mascotaId);
    }

    @Override
    public Cita crear(Cita cita) {
        // TODO: validar reglas de negocio antes de guardar
        return citaRepositorio.save(cita);
    }

    @Override
    public Optional<Cita> reprogramar(Long id, Cita cita) {
        // TODO: lógica de reprogramar
        return citaRepositorio.findById(id);
    }

    @Override
    public Optional<Cita> cancelar(Long id) {
        // TODO: lógica de cancelar (cambiar estadoId a cancelada)
        return citaRepositorio.findById(id);
    }

    @Override
    public Optional<Cita> completar(Long id) {
        // TODO: lógica de completar (cambiar estadoId a completada)
        return citaRepositorio.findById(id);
    }

    @Override
    public Optional<Cita> actualizarEstado(Long id, Long estadoId) {
        // TODO: lógica de actualizar estado
        return citaRepositorio.findById(id);
    }

    @Override
    public Optional<String> consultarEstado(Long id) {
        // TODO: retornar nombre del estado usando estadoId
        return citaRepositorio.findById(id).map(c -> "EstadoId: " + c.getEstadoId());
    }
}