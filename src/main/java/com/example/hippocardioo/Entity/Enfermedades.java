package com.example.hippocardioo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "enfermedades")
public class Enfermedades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enfermedades", nullable = false)
    private Long idEnfermedades;

    @Column(name = "nombre_enfermedades", nullable = false, length = 100)
    private String nombreEnfermedades;

    @Column(name = "descripcion_enfermedades", nullable = false, length = 100)
    private String descripcionEnfermedades;

    @Column(name = "causas_enfermedades", nullable = false, length = 100)
    private String causasEnfermedades;

    @Column(name = "cuidados_enfermedades", nullable = false, length = 100)
    private String cuidadosEnfermedades;

    @Column(name = "tipos_enfermedades", nullable = false, length = 100)
    private String tiposEnfermedades;

    @Column(name = "sintomas_enfermedades", nullable = false, length = 100)
    private String sintomasEnfermedades;

    // Getters y Setters
    public Long getIdEnfermedades() { return idEnfermedades; }
    public void setIdEnfermedades(Long idEnfermedades) { this.idEnfermedades = idEnfermedades; }

    public String getNombreEnfermedades() { return nombreEnfermedades; }
    public void setNombreEnfermedades(String nombreEnfermedades) { this.nombreEnfermedades = nombreEnfermedades; }

    public String getDescripcionEnfermedades() { return descripcionEnfermedades; }
    public void setDescripcionEnfermedades(String descripcionEnfermedades) { this.descripcionEnfermedades = descripcionEnfermedades; }

    public String getCausasEnfermedades() { return causasEnfermedades; }
    public void setCausasEnfermedades(String causasEnfermedades) { this.causasEnfermedades = causasEnfermedades; }

    public String getCuidadosEnfermedades() { return cuidadosEnfermedades; }
    public void setCuidadosEnfermedades(String cuidadosEnfermedades) { this.cuidadosEnfermedades = cuidadosEnfermedades; }

    public String getTiposEnfermedades() { return tiposEnfermedades; }
    public void setTiposEnfermedades(String tiposEnfermedades) { this.tiposEnfermedades = tiposEnfermedades; }

    public String getSintomasEnfermedades() { return sintomasEnfermedades; }
    public void setSintomasEnfermedades(String sintomasEnfermedades) { this.sintomasEnfermedades = sintomasEnfermedades; }
}
