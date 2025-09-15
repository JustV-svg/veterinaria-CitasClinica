package com.veterinaria.api.Repositorios;

import com.veterinaria.api.Entidades.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistorialRepository extends JpaRepository<Historial, Long> {
    @Query("SELECT h FROM Historial h JOIN Cita c ON h.citaId = c.id WHERE c.mascotaId = :mascotaId")
    List<Historial> findByMascotaId(@Param("mascotaId") Long mascotaId);
}
