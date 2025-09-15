package com.veterinaria.api.LogicaDeNegocio;

import com.veterinaria.api.Entidades.Historial;
import com.veterinaria.api.Repositorios.HistorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialServiceImpl implements HistorialService{

    private final HistorialRepository historialRepository;

    public HistorialServiceImpl(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    @Override
    public Historial crearHistorial(Historial historial) {
        return historialRepository.save(historial);
    }

    @Override
    public Historial actualizarHistorial(Long id, Historial historial) {
        Historial existente = historialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));

        existente.setDiagnostico(historial.getDiagnostico());
        existente.setTratamiento(historial.getTratamiento());
        existente.setObservaciones(historial.getObservaciones());
        return historialRepository.save(existente);
    }

    @Override
    public List<Historial> obtenerPorMascota(Long mascotaId) {
        return historialRepository.findByMascotaId(mascotaId);
    }

    @Override
    public Historial anularHistorial(Long id, String corregidoPor) {
        Historial historial = historialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));

        historial.setAnulado(true);
        historial.setCorregidoPor(corregidoPor);
        return historialRepository.save(historial);
    }
}
