package com.veterinaria.api.Repositorios;

import com.veterinaria.api.Entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaRepositorio extends JpaRepository<Cita, Long> {
    List<Cita> findByMascotaIdOrderByFechaHoraAsc(Long mascotaId);
}
