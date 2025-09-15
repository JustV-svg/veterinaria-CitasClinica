package com.veterinaria.api.Repositorios;
import com.veterinaria.api.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoRepository  extends JpaRepository<Estado, Integer>{
}
