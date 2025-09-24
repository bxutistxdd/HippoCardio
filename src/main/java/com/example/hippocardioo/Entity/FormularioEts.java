package com.example.hippocardioo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "formularios_ets")
public class FormularioEts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Min(value = 0, message = "La edad debe ser positiva")
    private Integer edad;

    @Email(message = "Correo inválido")
    private String correo;

    @NotBlank(message = "Debe seleccionar un tipo de ETS")
    @Column(name = "tipo_ets")
    private String tipoEts;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTipoEts() { return tipoEts; }   // 👈 corregido
    public void setTipoEts(String tipoEts) { this.tipoEts = tipoEts; }
}
