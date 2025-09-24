package com.example.hippocardioo.Repository;

import com.example.hippocardioo.Entity.Enfermedades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnfermedadesRepository extends JpaRepository<Enfermedades, Long> {

    // Buscar por tipo de enfermedad (ignora mayúsculas/minúsculas)
    List<Enfermedades> findByTiposEnfermedadesIgnoreCase(String tipo);

    // Buscar por nombre de enfermedad (contiene texto, ignora mayúsculas/minúsculas)
    List<Enfermedades> findByNombreEnfermedadesContainingIgnoreCase(String nombre);
}
