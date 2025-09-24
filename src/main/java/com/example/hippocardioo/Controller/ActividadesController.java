package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.Actividades;
import com.example.hippocardioo.Services.ActividadesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
public class ActividadesController {

    @Autowired
    private ActividadesService actividadesService;

    @GetMapping
    public List<Actividades> getAll() {
        return actividadesService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actividades> getById(@PathVariable Long id) {
        Actividades actividad = actividadesService.getById(id);
        if (actividad != null) {
            return ResponseEntity.ok(actividad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Actividades> create(@RequestBody Actividades actividad) {
        Actividades nueva = actividadesService.create(actividad);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actividades> update(@PathVariable Long id, @RequestBody Actividades actividad) {
        actividad.setId(id); // ← corregido
        Actividades actualizada = actividadesService.update(actividad);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Actividades actividad = actividadesService.getById(id);
        if (actividad != null) {
            actividadesService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
