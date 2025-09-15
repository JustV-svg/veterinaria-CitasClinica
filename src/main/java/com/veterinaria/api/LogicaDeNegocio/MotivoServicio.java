package com.veterinaria.api.LogicaDeNegocio;

import com.veterinaria.api.DTOs.MotivoDTO;
import com.veterinaria.api.Entidades.Motivo;
import com.veterinaria.api.Repositorios.MotivoRepositorio;
import com.veterinaria.api.Repositorios.CitaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotivoServicio {

    @Autowired
    private MotivoRepositorio motivoRepositorio;

    @Autowired
    private CitaRepositorio citaRepositorio;

    // HU08 - Consultar motivos disponibles
    public List<MotivoDTO> listarActivos() {
        return motivoRepositorio.findByActivoTrue()
                .stream()
                .map(MotivoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public MotivoDTO crear(MotivoDTO motivoDTO) {

        Optional<Motivo> motivoExistente = motivoRepositorio.findByNombre(motivoDTO.getNombre());
        if (motivoExistente.isPresent()) {
            throw new RuntimeException("Ya existe un motivo con ese nombre");
        }

        if (motivoDTO.getNombre() == null || motivoDTO.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre del motivo es obligatorio");
        }

        Motivo motivo = motivoDTO.toEntity();
        motivo.setActivo(true);

        Motivo motivoGuardado = motivoRepositorio.save(motivo);
        return MotivoDTO.fromEntity(motivoGuardado);
    }

    public Optional<MotivoDTO> editar(Long id, MotivoDTO motivoDTO) {
        Optional<Motivo> motivoExistente = motivoRepositorio.findById(id);

        if (motivoExistente.isPresent()) {
            Motivo motivo = motivoExistente.get();


            if (motivoDTO.getNombre() != null &&
                    motivoRepositorio.existsByNombreAndIdNot(motivoDTO.getNombre(), id)) {
                throw new RuntimeException("Ya existe otro motivo con ese nombre");
            }

            motivoDTO.updateEntity(motivo);

            Motivo motivoActualizado = motivoRepositorio.save(motivo);
            return Optional.of(MotivoDTO.fromEntity(motivoActualizado));
        }

        return Optional.empty();
    }

    public Optional<MotivoDTO> desactivar(Long id) {
        Optional<Motivo> motivoExistente = motivoRepositorio.findById(id);

        if (motivoExistente.isPresent()) {
            Motivo motivo = motivoExistente.get();

            boolean tieneUso = !citaRepositorio.findAll().stream()
                    .filter(cita -> cita.getMotivoId().equals(id))
                    .findFirst()
                    .isEmpty();

            if (tieneUso) {

                motivo.setActivo(false);
                Motivo motivoDesactivado = motivoRepositorio.save(motivo);
                return Optional.of(MotivoDTO.fromEntity(motivoDesactivado));
            } else {

                motivo.setActivo(false);
                Motivo motivoDesactivado = motivoRepositorio.save(motivo);
                return Optional.of(MotivoDTO.fromEntity(motivoDesactivado));
            }
        }

        return Optional.empty();
    }

    public Optional<MotivoDTO> activar(Long id) {
        Optional<Motivo> motivoExistente = motivoRepositorio.findById(id);

        if (motivoExistente.isPresent()) {
            Motivo motivo = motivoExistente.get();
            motivo.setActivo(true);
            Motivo motivoActivado = motivoRepositorio.save(motivo);
            return Optional.of(MotivoDTO.fromEntity(motivoActivado));
        }

        return Optional.empty();
    }
}
