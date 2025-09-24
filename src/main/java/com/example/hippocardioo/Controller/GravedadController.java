package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.Gravedad;
import com.example.hippocardioo.Services.GravedadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gravedad")
public class GravedadController {

    @Autowired
    private GravedadService gravedadService;

    @GetMapping
    public List<Gravedad> getAll() {
        return gravedadService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gravedad> getById(@PathVariable Long id) {
        Gravedad gravedad = gravedadService.getById(id);
        return (gravedad != null) ? ResponseEntity.ok(gravedad) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Gravedad> create(@RequestBody Gravedad gravedad) {
        return ResponseEntity.ok(gravedadService.create(gravedad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gravedad> update(@PathVariable Long id, @RequestBody Gravedad gravedad) {
        gravedad.setIdGravedad(id);  // Ajusta según el nombre real del setter en la entidad
        Gravedad updated = gravedadService.update(gravedad);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Gravedad gravedad = gravedadService.getById(id);
        if (gravedad != null) {
            gravedadService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔍 Filtro por nivel
    @GetMapping("/buscar")
    public List<Gravedad> buscarPorNivel(@RequestParam String nivel) {
        return gravedadService.buscarPorNivel(nivel);
    }
}
