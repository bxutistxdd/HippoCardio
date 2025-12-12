package com.example.hippocardioo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hippocardioo.Entity.Recordatorio;
import com.example.hippocardioo.Repository.RecordatorioRepository;
import com.example.hippocardioo.Services.DAO.idao;

@Service
public class RecordatorioService implements idao<Recordatorio, Long> {

    @Autowired
    private RecordatorioRepository recordatorioRepository;

    @Override
    public List<Recordatorio> getAll() {
        return recordatorioRepository.findAll();
    }

    @Override
    public Recordatorio getById(Long id) {
        return recordatorioRepository.findById(id).orElse(null);
    }

    @Override
    public Recordatorio create(Recordatorio entity) {
        return recordatorioRepository.save(entity);
    }

    @Override
    public Recordatorio update(Recordatorio entity) {
        Long id = entity.getIdRecordatorio();
        if (id != null && recordatorioRepository.existsById(id)) {
            return recordatorioRepository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (recordatorioRepository.existsById(id)) {
            recordatorioRepository.deleteById(id);
        }
    }
}
