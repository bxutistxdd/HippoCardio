package com.example.hippocardioo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hippocardioo.Entity.Metas;
import com.example.hippocardioo.Repository.MetasRepository;
import com.example.hippocardioo.Services.DAO.idao;

@Service
public class MetasService implements idao<Metas, Long> {

    @Autowired
    private MetasRepository metasRepository;

    @Override
    public List<Metas> getAll() {
        return metasRepository.findAll();
    }

    @Override
    public Metas getById(Long id) {
        return metasRepository.findById(id).orElse(null);
    }

    @Override
    public Metas create(Metas entity) {
        return metasRepository.save(entity);
    }

    @Override
    public Metas update(Metas entity) {
        Long id = entity.getIdMetas();
        if (id != null && metasRepository.existsById(id)) {
            return metasRepository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (metasRepository.existsById(id)) {
            metasRepository.deleteById(id);
        }
    }

    // Métodos personalizados
    public List<Metas> buscarPorEstado(String estado) {
        return metasRepository.findByTipoMetasIgnoreCase(estado);
    }

    public List<Metas> buscarPorNombre(String nombre) {
        return metasRepository.findByNombreMetasContainingIgnoreCase(nombre);
    }
}
