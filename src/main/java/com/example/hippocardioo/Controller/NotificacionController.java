package com.example.hippocardioo.Controller;

import com.example.hippocardioo.patterns.observer.NotificacionSubject;
import com.example.hippocardioo.patterns.observer.UsuarioObserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificacionController {

    @GetMapping("/notificar")
    public String enviarNotificacion() {
        // Creamos el sujeto (gestiona la lista de observadores)
        NotificacionSubject subject = new NotificacionSubject();

        // Creamos observadores (usuarios)
        UsuarioObserver u1 = new UsuarioObserver("Juan");
        UsuarioObserver u2 = new UsuarioObserver("Maria");

        // Los agregamos al sujeto
        subject.agregarObservador(u1);
        subject.agregarObservador(u2);

        // Mandamos la notificación
        subject.notificar("Nuevo consejo de salud disponible ✅");

        return "Notificación enviada a los observadores (revisa la consola)";
    }
}