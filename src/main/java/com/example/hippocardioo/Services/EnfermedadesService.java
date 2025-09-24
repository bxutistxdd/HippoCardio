package com.example.hippocardioo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hippocardioo.Entity.Enfermedades;
import com.example.hippocardioo.Repository.EnfermedadesRepository;
import com.example.hippocardioo.Services.DAO.idao;

@Service
public class EnfermedadesService implements idao<Enfermedades, Long> {

    @Autowired
    private EnfermedadesRepository enfermedadesRepository;

    @Override
    public List<Enfermedades> getAll() {
        return enfermedadesRepository.findAll();
    }

    @Override
    public Enfermedades getById(Long id) {
        return enfermedadesRepository.findById(id).orElse(null);
    }

    @Override
    public Enfermedades create(Enfermedades entity) {
        return enfermedadesRepository.save(entity);
    }

    @Override
    public Enfermedades update(Enfermedades entity) {
        Long id = entity.getIdEnfermedades();
        if (id != null && enfermedadesRepository.existsById(id)) {
            return enfermedadesRepository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (enfermedadesRepository.existsById(id)) {
            enfermedadesRepository.deleteById(id);
        }
    }

    // Métodos personalizados para búsquedas
    public List<Enfermedades> buscarPorTipo(String tipo) {
        return enfermedadesRepository.findByTiposEnfermedadesIgnoreCase(tipo);
    }

    public List<Enfermedades> buscarPorNombre(String nombre) {
        return enfermedadesRepository.findByNombreEnfermedadesContainingIgnoreCase(nombre);
    }
}
