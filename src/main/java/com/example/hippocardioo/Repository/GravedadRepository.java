package com.example.hippocardioo.Repository;

import com.example.hippocardioo.Entity.Gravedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GravedadRepository extends JpaRepository<Gravedad, Long> {

    // Busca por nombre ignorando mayúsculas/minúsculas
    List<Gravedad> findByNombreGravedadIgnoreCase(String nombreGravedad);
    
    // Si necesitas otro método por nivel, asegúrate que la propiedad exista en la entidad
    // List<Gravedad> findByNivelIgnoreCase(String nivel); 
}

