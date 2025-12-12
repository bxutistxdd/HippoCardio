package com.example.hippocardioo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hippocardioo.Entity.Habitos;
import com.example.hippocardioo.Repository.HabitosRepository;
import com.example.hippocardioo.Services.DAO.idao;

@Service
public class HabitosService implements idao<Habitos, Long> {

    @Autowired
    private HabitosRepository habitosRepository;

    @Override
    public List<Habitos> getAll() {
        return habitosRepository.findAll();
    }

    @Override
    public Habitos getById(Long id) {
        return habitosRepository.findById(id).orElse(null);
    }

    @Override
    public Habitos create(Habitos entity) {
        return habitosRepository.save(entity);
    }

    @Override
    public Habitos update(Habitos entity) {
        Long id = entity.getIdHabitos();
        if (id != null && habitosRepository.existsById(id)) {
            return habitosRepository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (habitosRepository.existsById(id)) {
            habitosRepository.deleteById(id);
        }
    }

    // Métodos personalizados
    public List<Habitos> buscarPorTipo(String tipo) {
        return habitosRepository.findByTipoHabitosIgnoreCase(tipo);
    }

    public List<Habitos> buscarPorNombre(String nombre) {
        return habitosRepository.findByNombreHabitosContainingIgnoreCase(nombre);
    }
}
