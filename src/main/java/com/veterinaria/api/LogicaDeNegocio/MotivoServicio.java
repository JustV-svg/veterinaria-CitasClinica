package com.veterinaria.api.LogicaDeNegocio;


import com.veterinaria.api.Entidades.Motivo;
import com.veterinaria.api.Repositorios.MotivoRepositorio;
import com.veterinaria.api.Repositorios.CitaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotivoServicio {

    @Autowired
    private MotivoRepositorio motivoRepositorio;

    @Autowired
    private CitaRepositorio citaRepositorio;

    // HU08 - Consultar motivos disponibles
    public List<Motivo> listarActivos() {
        return motivoRepositorio.findByActivoTrue();
    }

    // HU06 - Registrar motivo de cita
    public Motivo crear(Motivo motivo) {
        // Validar que el nombre sea único
        Optional<Motivo> motivoExistente = motivoRepositorio.findByNombre(motivo.getNombre());
        if (motivoExistente.isPresent()) {
            throw new RuntimeException("Ya existe un motivo con ese nombre");
        }

        // Validar campo obligatorio
        if (motivo.getNombre() == null || motivo.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre del motivo es obligatorio");
        }

        // Asegurar que esté activo por defecto
        motivo.setActivo(true);

        return motivoRepositorio.save(motivo);
    }

    // HU07 - Editar motivo
    public Optional<Motivo> editar(Long id, Motivo motivoActualizado) {
        Optional<Motivo> motivoExistente = motivoRepositorio.findById(id);

        if (motivoExistente.isPresent()) {
            Motivo motivo = motivoExistente.get();

            // Validar nombre único (excluyendo el motivo actual)
            if (motivoActualizado.getNombre() != null &&
                    motivoRepositorio.existsByNombreAndIdNot(motivoActualizado.getNombre(), id)) {
                throw new RuntimeException("Ya existe otro motivo con ese nombre");
            }

            // Actualizar campos
            if (motivoActualizado.getNombre() != null) {
                motivo.setNombre(motivoActualizado.getNombre());
            }
            if (motivoActualizado.getDescripcion() != null) {
                motivo.setDescripcion(motivoActualizado.getDescripcion());
            }

            return Optional.of(motivoRepositorio.save(motivo));
        }

        return Optional.empty();
    }

    // HU07 - Desactivar motivo
    public Optional<Motivo> desactivar(Long id) {
        Optional<Motivo> motivoExistente = motivoRepositorio.findById(id);

        if (motivoExistente.isPresent()) {
            Motivo motivo = motivoExistente.get();

            // Verificar si está siendo usado en citas
            boolean tieneUso = !citaRepositorio.findAll().stream()
                    .filter(cita -> cita.getMotivoId().equals(id))
                    .findFirst()
                    .isEmpty();

            if (tieneUso) {
                // Solo desactivar, no eliminar
                motivo.setActivo(false);
                return Optional.of(motivoRepositorio.save(motivo));
            } else {
                // Podría eliminarse, pero por consistencia solo desactivamos
                motivo.setActivo(false);
                return Optional.of(motivoRepositorio.save(motivo));
            }
        }

        return Optional.empty();
    }

    // Activar motivo
    public Optional<Motivo> activar(Long id) {
        Optional<Motivo> motivoExistente = motivoRepositorio.findById(id);

        if (motivoExistente.isPresent()) {
            Motivo motivo = motivoExistente.get();
            motivo.setActivo(true);
            return Optional.of(motivoRepositorio.save(motivo));
        }

        return Optional.empty();
    }
    // Eliminar motivo (definitivo)
    public boolean eliminar(Long id) {
        Optional<Motivo> motivoExistente = motivoRepositorio.findById(id);

        if (motivoExistente.isPresent()) {
            // Verificar si está siendo usado en citas
            boolean tieneUso = citaRepositorio.findAll().stream()
                    .anyMatch(cita -> cita.getMotivoId().equals(id));

            if (tieneUso) {
                throw new RuntimeException("No se puede eliminar, el motivo está asociado a citas");
            }

            motivoRepositorio.deleteById(id);
            return true;
        }

        return false;
    }
}

