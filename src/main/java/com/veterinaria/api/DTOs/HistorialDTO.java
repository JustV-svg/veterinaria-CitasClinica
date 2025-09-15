package com.veterinaria.api.DTOs;

import java.time.LocalDateTime;

public class HistorialDTO {
    private Long id;
    private Long citaId;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private Boolean anulado;
    private String corregidoPor;
    private LocalDateTime creadoAt;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCitaId() { return citaId; }
    public void setCitaId(Long citaId) { this.citaId = citaId; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Boolean getAnulado() { return anulado; }
    public void setAnulado(Boolean anulado) { this.anulado = anulado; }

    public String getCorregidoPor() { return corregidoPor; }
    public void setCorregidoPor(String corregidoPor) { this.corregidoPor = corregidoPor; }

    public LocalDateTime getCreadoAt() { return creadoAt; }
    public void setCreadoAt(LocalDateTime creadoAt) { this.creadoAt = creadoAt; }
}
