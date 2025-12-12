package com.example.hippocardioo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.hippocardioo.Entity.Usuario;
import com.example.hippocardioo.Repository.UsuarioRepository;
import com.example.hippocardioo.Services.DAO.idao;

@Service
@Transactional
public class UsuarioService implements idao<Usuario, Long> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario create(Usuario entity) {
        return usuarioRepository.save(entity);
    }

    @Override
    public Usuario update(Usuario entity) {
        Long id = entity.getId();
        if (id == null || !usuarioRepository.existsById(id)) {
            return null;
        }
        return usuarioRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        }
    }

    // Buscar usuario por correo
    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    // ===============================
    // Cambiar contraseña seguro
    // ===============================
    public boolean actualizarPassword(String correo, String nuevaPassword) {
        Usuario user = usuarioRepository.findByCorreo(correo);
        if (user == null) return false;

        // Encriptar contraseña **solo aquí**
        String passEncriptada = passwordEncoder.encode(nuevaPassword);

        // Llamar directamente al repositorio para actualizar
        usuarioRepository.updatePasswordById(user.getId(), passEncriptada);

        return true;
    }
}
