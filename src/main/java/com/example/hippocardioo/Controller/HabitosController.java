package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.Habitos;
import com.example.hippocardioo.Services.HabitosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitos")
public class HabitosController {

    @Autowired
    private HabitosService habitosService;

    @GetMapping
    public List<Habitos> getAll() {
        return habitosService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitos> getById(@PathVariable Long id) {
        Habitos habito = habitosService.getById(id);
        return (habito != null) ? ResponseEntity.ok(habito) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Habitos> create(@RequestBody Habitos habito) {
        return ResponseEntity.ok(habitosService.create(habito));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitos> update(@PathVariable Long id, @RequestBody Habitos habito) {
        habito.setIdHabitos(id);  // Usa el nombre correcto del setter según la entidad
        Habitos updated = habitosService.update(habito);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Habitos habito = habitosService.getById(id);
        if (habito != null) {
            habitosService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔍 Filtros personalizados

    @GetMapping("/buscar/tipo")
    public List<Habitos> buscarPorTipo(@RequestParam String tipo) {
        return habitosService.buscarPorTipo(tipo);
    }

    @GetMapping("/buscar/nombre")
    public List<Habitos> buscarPorNombre(@RequestParam String nombre) {
        return habitosService.buscarPorNombre(nombre);
    }
}
