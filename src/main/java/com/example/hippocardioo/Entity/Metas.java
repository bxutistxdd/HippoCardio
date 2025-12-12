package com.example.hippocardioo.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "metas")
public class Metas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metas", nullable = false)
    private Long idMetas;

    @Column(name = "nombre_metas", nullable = false, length = 100)
    private String nombreMetas;

    @Column(name = "descripcion_metas", nullable = false, length = 100)
    private String descripcionMetas;

    @Column(name = "tipo_metas", nullable = false, length = 100)
    private String tipoMetas;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    // Getters y Setters
    public Long getIdMetas() {
        return idMetas;
    }

    public void setIdMetas(Long idMetas) {
        this.idMetas = idMetas;
    }

    public String getNombreMetas() {
        return nombreMetas;
    }

    public void setNombreMetas(String nombreMetas) {
        this.nombreMetas = nombreMetas;
    }

    public String getDescripcionMetas() {
        return descripcionMetas;
    }

    public void setDescripcionMetas(String descripcionMetas) {
        this.descripcionMetas = descripcionMetas;
    }

    public String getTipoMetas() {
        return tipoMetas;
    }

    public void setTipoMetas(String tipoMetas) {
        this.tipoMetas = tipoMetas;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
