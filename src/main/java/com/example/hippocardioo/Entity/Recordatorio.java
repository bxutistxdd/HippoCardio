package com.example.hippocardioo.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "recordatorios")
public class Recordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recordatorio", nullable = false)
    private Long idRecordatorio;

    @Column(name = "nombre_recordatorio", nullable = false, length = 100)
    private String nombreRecordatorio;

    @Column(name = "tipo_recordatorio", nullable = false, length = 100)
    private String tipoRecordatorio;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    // Getters y Setters
    public Long getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(Long idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public String getNombreRecordatorio() {
        return nombreRecordatorio;
    }

    public void setNombreRecordatorio(String nombreRecordatorio) {
        this.nombreRecordatorio = nombreRecordatorio;
    }

    public String getTipoRecordatorio() {
        return tipoRecordatorio;
    }

    public void setTipoRecordatorio(String tipoRecordatorio) {
        this.tipoRecordatorio = tipoRecordatorio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
