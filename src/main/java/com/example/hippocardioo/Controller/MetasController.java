package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.Metas;
import com.example.hippocardioo.Services.MetasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
public class MetasController {

    @Autowired
    private MetasService metasService;

    @GetMapping
    public List<Metas> getAll() {
        return metasService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metas> getById(@PathVariable Long id) {
        Metas meta = metasService.getById(id);
        return (meta != null) ? ResponseEntity.ok(meta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Metas> create(@RequestBody Metas meta) {
        return ResponseEntity.ok(metasService.create(meta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Metas> update(@PathVariable Long id, @RequestBody Metas meta) {
        meta.setIdMetas(id);  // Ajuste aquí según el nombre del campo en la entidad
        Metas updated = metasService.update(meta);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Metas meta = metasService.getById(id);
        if (meta != null) {
            metasService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔍 Búsquedas personalizadas

    @GetMapping("/buscar/nombre")
    public List<Metas> buscarPorNombre(@RequestParam String nombre) {
        return metasService.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar/estado")
    public List<Metas> buscarPorEstado(@RequestParam String estado) {
        return metasService.buscarPorEstado(estado);
    }
}
