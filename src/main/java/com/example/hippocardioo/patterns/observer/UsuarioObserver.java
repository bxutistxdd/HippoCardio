package com.example.hippocardioo.patterns.observer;

public class UsuarioObserver implements NotificacionObserver {
    private String nombreUsuario;

    public UsuarioObserver(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public void update(String mensaje) {
        System.out.println("📩 Notificación para " + nombreUsuario + ": " + mensaje);
    }
}