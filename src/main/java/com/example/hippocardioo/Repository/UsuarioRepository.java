package com.example.hippocardioo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hippocardioo.Entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar por correo (campo en tu entidad)
    Usuario findByCorreo(String correo);
}
