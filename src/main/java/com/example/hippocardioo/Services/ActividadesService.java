package com.example.hippocardioo.Services;

import com.example.hippocardioo.Entity.Actividades;
import com.example.hippocardioo.Repository.ActividadesRepository;
import com.example.hippocardioo.Services.DAO.idao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadesService implements idao<Actividades, Long> {

    @Autowired
    private ActividadesRepository actividadesRepository;

    @Override
    public List<Actividades> getAll() {
        return actividadesRepository.findAll();
    }

    @Override
    public Actividades getById(Long id) {
        return actividadesRepository.findById(id).orElse(null);
    }

    @Override
    public Actividades create(Actividades entity) {
        return actividadesRepository.save(entity);
    }

    @Override
    public Actividades update(Actividades entity) {
        Long id = entity.getId();
        if (id == null || !actividadesRepository.existsById(id)) {
            return null;
        }
        return actividadesRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (actividadesRepository.existsById(id)) {
            actividadesRepository.deleteById(id);
        }
    }
}
