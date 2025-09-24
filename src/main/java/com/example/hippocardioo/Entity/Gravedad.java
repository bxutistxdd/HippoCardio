package com.example.hippocardioo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gravedad")
public class Gravedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gravedad", nullable = false)
    private Long idGravedad;

    @Column(name = "nombre_gravedad", nullable = false, length = 100)
    private String nombreGravedad;

    @Column(name = "descripcion_gravedad", nullable = false, length = 100)
    private String descripcionGravedad;

    // Getters y Setters
    public Long getIdGravedad() {
        return idGravedad;
    }

    public void setIdGravedad(Long idGravedad) {
        this.idGravedad = idGravedad;
    }

    public String getNombreGravedad() {
        return nombreGravedad;
    }

    public void setNombreGravedad(String nombreGravedad) {
        this.nombreGravedad = nombreGravedad;
    }

    public String getDescripcionGravedad() {
        return descripcionGravedad;
    }

    public void setDescripcionGravedad(String descripcionGravedad) {
        this.descripcionGravedad = descripcionGravedad;
    }
}
