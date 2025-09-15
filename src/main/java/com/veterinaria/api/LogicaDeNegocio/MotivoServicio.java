package com.veterinaria.api.LogicaDeNegocio;

import com.veterinaria.api.DTOs.MotivoDTO;
import com.veterinaria.api.Entidades.Motivo;
import com.veterinaria.api.Repositorios.MotivoRepositorio;
import com.veterinaria.api.Repositorios.CitaRepositorio;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    public List<MotivoDTO> listarActivos() {
        return motivoRepositorio.findByActivoTrue()
                .stream()
                .map(motivo -> modelMapper.map(motivo, MotivoDTO.class))
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

        Motivo motivo = modelMapper.map(motivoDTO, Motivo.class);
        motivo.setActivo(true);

        Motivo motivoGuardado = motivoRepositorio.save(motivo);
        return modelMapper.map(motivoGuardado, MotivoDTO.class);
    }


    public Optional<MotivoDTO> editar(Long id, MotivoDTO motivoDTO) {
        Optional<Motivo> motivoExistente = motivoRepositorio.findById(id);

        if (motivoExistente.isPresent()) {
            Motivo motivo = motivoExistente.get();


            if (motivoDTO.getNombre() != null &&
                    motivoRepositorio.existsByNombreAndIdNot(motivoDTO.getNombre(), id)) {
                throw new RuntimeException("Ya existe otro motivo con ese nombre");
            }


            if (motivoDTO.getNombre() != null) {
                motivo.setNombre(motivoDTO.getNombre());
            }
            if (motivoDTO.getDescripcion() != null) {
                motivo.setDescripcion(motivoDTO.getDescripcion());
            }


            Motivo motivoActualizado = motivoRepositorio.save(motivo);
            return Optional.of(modelMapper.map(motivoActualizado, MotivoDTO.class));
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

            motivo.setActivo(false);
            Motivo motivoDesactivado = motivoRepositorio.save(motivo);
            return Optional.of(modelMapper.map(motivoDesactivado, MotivoDTO.class));
        }

        return Optional.empty();
    }

    public Optional<MotivoDTO> activar(Long id) {
        Optional<Motivo> motivoExistente = motivoRepositorio.findById(id);

        if (motivoExistente.isPresent()) {
            Motivo motivo = motivoExistente.get();
            motivo.setActivo(true);
            Motivo motivoActivado = motivoRepositorio.save(motivo);
            return Optional.of(modelMapper.map(motivoActivado, MotivoDTO.class));
        }

        return Optional.empty();
    }
}