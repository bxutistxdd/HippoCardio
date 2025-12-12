package com.example.hippocardioo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "formularios_hipertension")
public class FormularioHipertension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Min(value = 0, message = "La edad debe ser positiva")
    private Integer edad;

    @Email(message = "Correo inválido")
    private String correo;

    @NotBlank(message = "La presión arterial es obligatoria")
    @Column(name = "presion_arterial")
    private String presionArterial;

    @PositiveOrZero(message = "El peso debe ser positivo o cero")
    private Double peso;

    @PositiveOrZero(message = "La altura debe ser positiva o cero")
    private Double altura;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPresionArterial() { return presionArterial; }
    public void setPresionArterial(String presionArterial) { this.presionArterial = presionArterial; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }
}
