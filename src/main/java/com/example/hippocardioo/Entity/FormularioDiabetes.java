package com.example.hippocardioo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "formulario_diabetes")
public class FormularioDiabetes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer edad;
    private String correo;

    @Column(name = "tipo_diabetes")
    private String tipoDiabetes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTipoDiabetes() { return tipoDiabetes; }   // 👈 corregido
    public void setTipoDiabetes(String tipoDiabetes) { this.tipoDiabetes = tipoDiabetes; }
}
