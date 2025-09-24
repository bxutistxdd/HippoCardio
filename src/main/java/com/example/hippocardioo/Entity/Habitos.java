package com.example.hippocardioo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "habitos")
public class Habitos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitos", nullable = false)
    private Long idHabitos; // camelCase

    @Column(name = "nombre_habitos", nullable = false, length = 100)
    private String nombreHabitos; // camelCase

    @Column(name = "descripcion_habitos", nullable = false, length = 100)
    private String descripcionHabitos; // camelCase

    @Column(name = "tipo_habitos", nullable = false, length = 100)
    private String tipoHabitos; // camelCase

    // Getters y Setters
    public Long getIdHabitos() {
        return idHabitos;
    }

    public void setIdHabitos(Long idHabitos) {
        this.idHabitos = idHabitos;
    }

    public String getNombreHabitos() {
        return nombreHabitos;
    }

    public void setNombreHabitos(String nombreHabitos) {
        this.nombreHabitos = nombreHabitos;
    }

    public String getDescripcionHabitos() {
        return descripcionHabitos;
    }

    public void setDescripcionHabitos(String descripcionHabitos) {
        this.descripcionHabitos = descripcionHabitos;
    }

    public String getTipoHabitos() {
        return tipoHabitos;
    }

    public void setTipoHabitos(String tipoHabitos) {
        this.tipoHabitos = tipoHabitos;
    }
}
