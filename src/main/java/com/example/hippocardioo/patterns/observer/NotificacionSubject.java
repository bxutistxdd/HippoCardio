package com.example.hippocardioo.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class NotificacionSubject {
    private List<NotificacionObserver> observadores = new ArrayList<>();

    public void agregarObservador(NotificacionObserver observer) {
        observadores.add(observer);
    }

    public void eliminarObservador(NotificacionObserver observer) {
        observadores.remove(observer);
    }

    public void notificar(String mensaje) {
        for (NotificacionObserver obs : observadores) {
            obs.update(mensaje);
        }
    }
}