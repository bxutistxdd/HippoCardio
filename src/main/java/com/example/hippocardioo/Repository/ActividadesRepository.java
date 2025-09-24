package com.example.hippocardioo.Repository;

import com.example.hippocardioo.Entity.Actividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActividadesRepository extends JpaRepository<Actividades, Long> {

    List<Actividades> findByTipo(String tipo);

    List<Actividades> findByNombreContainingIgnoreCase(String nombre);

    List<Actividades> findByFechaInicioAfter(LocalDateTime fecha);

    List<Actividades> findByFechaFinBefore(LocalDateTime fecha);

    List<Actividades> findByFechaInicioBetween(LocalDateTime desde, LocalDateTime hasta);
}
