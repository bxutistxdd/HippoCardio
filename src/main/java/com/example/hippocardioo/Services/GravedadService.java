package com.example.hippocardioo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hippocardioo.Entity.Gravedad;
import com.example.hippocardioo.Repository.GravedadRepository;
import com.example.hippocardioo.Services.DAO.idao;

@Service
public class GravedadService implements idao<Gravedad, Long> {

    @Autowired
    private GravedadRepository gravedadRepository;

    @Override
    public List<Gravedad> getAll() {
        return gravedadRepository.findAll();
    }

    @Override
    public Gravedad getById(Long id) {
        return gravedadRepository.findById(id).orElse(null);
    }

    @Override
    public Gravedad create(Gravedad entity) {
        return gravedadRepository.save(entity);
    }

    @Override
    public Gravedad update(Gravedad entity) {
        Long id = entity.getIdGravedad();
        if (id != null && gravedadRepository.existsById(id)) {
            return gravedadRepository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (gravedadRepository.existsById(id)) {
            gravedadRepository.deleteById(id);
        }
    }

    // Métodos personalizados (opcional)
    public List<Gravedad> buscarPorNivel(String nivel) {
        return gravedadRepository.findByNombreGravedadIgnoreCase(nivel);
    }
}
