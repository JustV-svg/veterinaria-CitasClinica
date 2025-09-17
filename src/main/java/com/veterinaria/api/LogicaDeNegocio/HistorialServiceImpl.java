package com.veterinaria.api.LogicaDeNegocio;

import com.veterinaria.api.DTOs.HistorialDTO;
import com.veterinaria.api.Entidades.Cita;
import com.veterinaria.api.Entidades.Historial;
import com.veterinaria.api.Excepciones.HistorialNotFoundException;
import com.veterinaria.api.Repositorios.CitaRepositorio;
import com.veterinaria.api.Repositorios.HistorialRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class HistorialServiceImpl implements HistorialServicio {

    private final HistorialRepositorio historialRepository;
    private final CitaRepositorio citaRepositorio;
    private final ModelMapper modelMapper;


    public HistorialServiceImpl(HistorialRepositorio historialRepository, CitaRepositorio citaRepositorio, ModelMapper modelMapper) {
        this.historialRepository = historialRepository;
        this.citaRepositorio = citaRepositorio;
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
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));
        existente.setDiagnostico(dto.getDiagnostico());
        existente.setTratamiento(dto.getTratamiento());
        existente.setObservaciones(dto.getObservaciones());
        existente.setVeterinarioId(dto.getVeterinarioId());
        Historial actualizado = historialRepository.save(existente);
        return CompletableFuture.completedFuture(modelMapper.map(actualizado, HistorialDTO.class));
    }

    @Override
    @Async
    public CompletableFuture<List<HistorialDTO>> obtenerPorCita(Long citaId) {
        List<Historial> historiales = historialRepository.findByCita_Id(citaId);
        List<HistorialDTO> dtos = historiales.stream()
                .map(h -> modelMapper.map(h, HistorialDTO.class))
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(dtos);
    }

    @Override
    @Async
    public CompletableFuture<HistorialDTO> anularHistorial(Long id, String corregidoPor) {
        Historial historial = historialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));
        historial.setAnulado(true);
        historial.setCorregidoPor(corregidoPor);
        Historial actualizado = historialRepository.save(historial);
        return CompletableFuture.completedFuture(modelMapper.map(actualizado, HistorialDTO.class));
    }

    @Async
    public CompletableFuture<List<HistorialDTO>> obtenerTodos() {
        List<HistorialDTO> lista = historialRepository.findAll()
                .stream()
                .map(historial -> modelMapper.map(historial, HistorialDTO.class))
                .toList();

        return CompletableFuture.completedFuture(lista);
    }

}
