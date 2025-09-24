package com.example.hippocardioo.Services;

import com.example.hippocardioo.Entity.*;
import com.example.hippocardioo.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final FormularioDiabetesRepository diabetesRepo;
    private final FormularioEtsRepository etsRepo;
    private final FormularioHipertensionRepository hipertensionRepo;
    private final FormularioObesidadRepository obesidadRepo;

    public DashboardService(FormularioDiabetesRepository diabetesRepo,
                            FormularioEtsRepository etsRepo,
                            FormularioHipertensionRepository hipertensionRepo,
                            FormularioObesidadRepository obesidadRepo) {
        this.diabetesRepo = diabetesRepo;
        this.etsRepo = etsRepo;
        this.hipertensionRepo = hipertensionRepo;
        this.obesidadRepo = obesidadRepo;
    }

    // ----------------- FILTROS -----------------

    public List<FormularioHipertension> filtrarHipertension(String nombre, Integer edad, String presionArterial, Double peso, Double altura) {
        return hipertensionRepo.findAll().stream()
                .filter(f -> nombre == null || nombre.isBlank() || f.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(f -> edad == null || Objects.equals(f.getEdad(), edad))
                .filter(f -> presionArterial == null || presionArterial.isBlank() || f.getPresionArterial().contains(presionArterial))
                .filter(f -> peso == null || Objects.equals(f.getPeso(), peso))
                .filter(f -> altura == null || Objects.equals(f.getAltura(), altura))
                .collect(Collectors.toList());
    }

    public List<FormularioDiabetes> filtrarDiabetes(String nombre, Integer edad, String tipoDiabetes) {
        return diabetesRepo.findAll().stream()
                .filter(f -> nombre == null || nombre.isBlank() || f.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(f -> edad == null || Objects.equals(f.getEdad(), edad))
                .filter(f -> tipoDiabetes == null || tipoDiabetes.isBlank() || f.getTipoDiabetes().equalsIgnoreCase(tipoDiabetes))
                .collect(Collectors.toList());
    }

    public List<FormularioEts> filtrarEts(String nombre, Integer edad, String tipoEts) {
        return etsRepo.findAll().stream()
                .filter(f -> nombre == null || nombre.isBlank() || f.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(f -> edad == null || Objects.equals(f.getEdad(), edad))
                .filter(f -> tipoEts == null || tipoEts.isBlank() || f.getTipoEts().equalsIgnoreCase(tipoEts))
                .collect(Collectors.toList());
    }

    public List<FormularioObesidad> filtrarObesidad(String nombre, Double imc) {
        return obesidadRepo.findAll().stream()
                .filter(f -> nombre == null || nombre.isBlank() || f.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(f -> imc == null || Objects.equals(f.getImc(), imc))
                .collect(Collectors.toList());
    }
}
