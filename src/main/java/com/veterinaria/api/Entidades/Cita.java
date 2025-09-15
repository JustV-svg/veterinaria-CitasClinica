package com.veterinaria.api.Entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long mascotaId; // Relación a mascota

    @Column(nullable = false)
    private Long motivoId; // Relación a motivo

    @Column(nullable = false)
    private Long estadoId; // Relación a estado

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    private String observaciones;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMascotaId() { return mascotaId; }
    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }

    public Long getMotivoId() { return motivoId; }
    public void setMotivoId(Long motivoId) { this.motivoId = motivoId; }

    public Long getEstadoId() { return estadoId; }
    public void setEstadoId(Long estadoId) { this.estadoId = estadoId; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}