package com.example.hippocardioo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hippocardioo.Entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por correo
    Usuario findByCorreo(String correo);

    // Actualizar contraseña directamente en la BD
    @Modifying
    @Query("UPDATE Usuario u SET u.password = :password WHERE u.id = :id")
    void updatePasswordById(@Param("id") Long id, @Param("password") String password);

    @Query("SELECT u.correo FROM Usuario u")
    List<String> findAllEmails();
}
