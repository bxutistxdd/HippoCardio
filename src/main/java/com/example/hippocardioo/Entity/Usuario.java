package com.example.hippocardioo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Asegúrate de que coincide con tu tabla real
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Coincide con el nombre de la columna en la BD
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String nombre;

    @Column(name = "lastname", nullable = false, length = 100)
    private String apellido;

    @Column(name = "email", nullable = false, length = 100)
    private String correo;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rol", nullable = false)
    private String rol;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public static Object findById(Long usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
