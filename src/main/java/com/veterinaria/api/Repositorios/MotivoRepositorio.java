package com.veterinaria.api.Repositorios;

import com.veterinaria.api.Entidades.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MotivoRepositorio extends JpaRepository<Motivo, Long> {
    List<Motivo> findByActivoTrue();
    Optional<Motivo> findByNombre(String nombre);
    boolean existsByNombreAndIdNot(String nombre, Long id);
}