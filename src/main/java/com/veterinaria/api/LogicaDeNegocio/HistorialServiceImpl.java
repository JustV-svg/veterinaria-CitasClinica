package com.veterinaria.api.LogicaDeNegocio;

import com.veterinaria.api.DTOs.HistorialDTO;
import com.veterinaria.api.Entidades.Historial;
import com.veterinaria.api.Excepciones.HistorialNotFoundException;
import com.veterinaria.api.Repositorios.HistorialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class HistorialServiceImpl implements HistorialService{

    private final HistorialRepository historialRepository;
    private final ModelMapper modelMapper;

    public HistorialServiceImpl(HistorialRepository historialRepository, ModelMapper modelMapper) {
        this.historialRepository = historialRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Async
    public CompletableFuture<HistorialDTO> crearHistorial(HistorialDTO dto) {
        Historial historial = modelMapper.map(dto, Historial.class);
        Historial guardado = historialRepository.save(historial);
        return CompletableFuture.completedFuture(modelMapper.map(guardado, HistorialDTO.class));
    }

    @Override
    @Async
    public CompletableFuture<HistorialDTO> actualizarHistorial(Long id, HistorialDTO dto) {
        Historial existente = historialRepository.findById(id)
                .orElseThrow(() -> new HistorialNotFoundException("Historial no encontrado"));
        existente.setDiagnostico(dto.getDiagnostico());
        existente.setTratamiento(dto.getTratamiento());
        existente.setObservaciones(dto.getObservaciones());
        Historial actualizado = historialRepository.save(existente);
        return CompletableFuture.completedFuture(modelMapper.map(actualizado, HistorialDTO.class));
    }

    @Override
    @Async
    public CompletableFuture<List<HistorialDTO>> obtenerPorMascota(Long mascotaId) {
        List<Historial> historiales = historialRepository.findByMascotaId(mascotaId);
        List<HistorialDTO> dtos = historiales.stream()
                .map(h -> modelMapper.map(h, HistorialDTO.class))
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(dtos);
    }

    @Override
    @Async
    public CompletableFuture<HistorialDTO> anularHistorial(Long id, String corregidoPor) {
        Historial historial = historialRepository.findById(id)
                .orElseThrow(() -> new HistorialNotFoundException("Historial no encontrado"));
        historial.setAnulado(true);
        historial.setCorregidoPor(corregidoPor);
        Historial actualizado = historialRepository.save(historial);
        return CompletableFuture.completedFuture(modelMapper.map(actualizado, HistorialDTO.class));
    }
}
