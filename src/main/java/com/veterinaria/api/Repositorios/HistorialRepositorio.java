package com.veterinaria.api.Repositorios;

import com.veterinaria.api.Entidades.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialRepositorio extends JpaRepository<Historial, Long> {
    List<Historial> findByCita_Id(Long citaId);
}
