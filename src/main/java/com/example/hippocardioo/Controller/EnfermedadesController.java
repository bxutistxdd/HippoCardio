package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.Enfermedades;
import com.example.hippocardioo.Services.EnfermedadesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enfermedades")
public class EnfermedadesController {

    @Autowired
    private EnfermedadesService enfermedadesService;

    @GetMapping
    public List<Enfermedades> getAll() {
        return enfermedadesService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enfermedades> getById(@PathVariable Long id) {
        Enfermedades enfermedad = enfermedadesService.getById(id);
        return (enfermedad != null) ? ResponseEntity.ok(enfermedad) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Enfermedades> create(@RequestBody Enfermedades enfermedad) {
        return ResponseEntity.ok(enfermedadesService.create(enfermedad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enfermedades> update(@PathVariable Long id, @RequestBody Enfermedades enfermedad) {
        enfermedad.setIdEnfermedades(id);
        Enfermedades updated = enfermedadesService.update(enfermedad);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Enfermedades enfermedad = enfermedadesService.getById(id);
        if (enfermedad != null) {
            enfermedadesService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/tipo")
    public List<Enfermedades> buscarPorTipo(@RequestParam String tipo) {
        return enfermedadesService.buscarPorTipo(tipo);
    }

    @GetMapping("/buscar/nombre")
    public List<Enfermedades> buscarPorNombre(@RequestParam String nombre) {
        return enfermedadesService.buscarPorNombre(nombre);
    }
}
