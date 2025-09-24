package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.Recordatorio;
import com.example.hippocardioo.Services.RecordatorioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recordatorios")
public class RecordatorioController {

    @Autowired
    private RecordatorioService recordatorioService;

    @GetMapping
    public List<Recordatorio> getAll() {
        return recordatorioService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recordatorio> getById(@PathVariable Long id) {
        Recordatorio recordatorio = recordatorioService.getById(id);
        return (recordatorio != null) ? ResponseEntity.ok(recordatorio) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Recordatorio> create(@RequestBody Recordatorio recordatorio) {
        return ResponseEntity.ok(recordatorioService.create(recordatorio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recordatorio> update(@PathVariable Long id, @RequestBody Recordatorio recordatorio) {
        recordatorio.setIdRecordatorio(id);  // Aquí el setter correcto según la entidad
        Recordatorio updated = recordatorioService.update(recordatorio);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Recordatorio recordatorio = recordatorioService.getById(id);
        if (recordatorio != null) {
            recordatorioService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
