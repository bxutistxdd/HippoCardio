package com.example.hippocardioo.Repository;

import com.example.hippocardioo.Entity.Metas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetasRepository extends JpaRepository<Metas, Long> {

    List<Metas> findByTipoMetasIgnoreCase(String tipo);
    List<Metas> findByNombreMetasContainingIgnoreCase(String nombre);
}
