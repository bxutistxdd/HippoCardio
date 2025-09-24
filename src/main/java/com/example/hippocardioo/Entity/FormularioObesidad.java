package com.example.hippocardioo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "formularios_obesidad")
public class FormularioObesidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Min(value = 0, message = "La edad debe ser positiva")
    private Integer edad;

    @Email(message = "Correo inválido")
    private String correo;

    @NotNull(message = "El IMC es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El IMC debe ser positivo")
    private Double imc;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Double getImc() { return imc; }
    public void setImc(Double imc) { this.imc = imc; }
}
