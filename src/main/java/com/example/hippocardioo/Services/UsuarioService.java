package com.example.hippocardioo.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hippocardioo.Entity.Usuario;
import com.example.hippocardioo.Repository.UsuarioRepository;
import com.example.hippocardioo.Services.DAO.idao;

@Service
public class UsuarioService implements idao<Usuario, Long> {

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    // ✅ Buscar usuario por correo
    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}

