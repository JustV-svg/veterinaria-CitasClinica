package com.veterinaria.api.DTOs;

public class MotivoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean activo;

    public MotivoDTO() {}

    public MotivoDTO(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = true;
    }

    public MotivoDTO(Long id, String nombre, String descripcion, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }


    public static MotivoDTO fromEntity(com.veterinaria.api.Entidades.Motivo motivo) {
        return new MotivoDTO(
                motivo.getId(),
                motivo.getNombre(),
                motivo.getDescripcion(),
                motivo.getActivo()
        );
    }

    public com.veterinaria.api.Entidades.Motivo toEntity() {
        com.veterinaria.api.Entidades.Motivo motivo = new com.veterinaria.api.Entidades.Motivo();
        motivo.setId(this.id);
        motivo.setNombre(this.nombre);
        motivo.setDescripcion(this.descripcion);
        motivo.setActivo(this.activo);
        return motivo;
    }

    public void updateEntity(com.veterinaria.api.Entidades.Motivo motivo) {
        if (this.nombre != null) {
            motivo.setNombre(this.nombre);
        }
        if (this.descripcion != null) {
            motivo.setDescripcion(this.descripcion);
        }
        if (this.activo != null) {
            motivo.setActivo(this.activo);
        }
    }
}