package com.example.hippocardioo.Services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {

    private Map<String, String> tokens = new HashMap<>();

    public String generarToken(String email) {
        String token = UUID.randomUUID().toString();
        tokens.put(token, email);
        return token;
    }

    public String obtenerEmailPorToken(String token) {
        return tokens.get(token);
    }

    public void eliminarToken(String token) {
        tokens.remove(token);
    }
}
