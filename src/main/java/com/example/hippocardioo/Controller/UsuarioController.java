package com.example.hippocardioo.Controller;

import com.example.hippocardioo.Entity.Usuario;
import com.example.hippocardioo.Services.UsuarioService;
//import com.lowagie.text.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


@PostMapping("/login")
public ResponseEntity<?> login(@RequestParam String correo, @RequestParam String password) {
    Usuario usuario = usuarioService.findByCorreo(correo);

    if (usuario != null && usuario.getPassword().equals(password)) {
        // ✅ Redirigir al perfil
        return ResponseEntity.ok(usuario); // Devuelve el usuario en JSON
        // Si quieres redirección: return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/api/usuarios/perfil/" + usuario.getId())).build();
    }

    return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
}

@GetMapping("/perfil/{id}")
public ResponseEntity<Usuario> getPerfil(@PathVariable Long id) {
    Usuario usuario = usuarioService.getById(id);
    return (usuario != null) ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
}


    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        Usuario actualizado = usuarioService.update(usuario);
        return (actualizado != null) ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Usuario usuario = usuarioService.getById(id);
        if (usuario != null) {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
