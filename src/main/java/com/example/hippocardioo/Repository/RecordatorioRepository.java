package com.example.hippocardioo.Repository;

import com.example.hippocardioo.Entity.Recordatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecordatorioRepository extends JpaRepository<Recordatorio, Long> {

    List<Recordatorio> findByNombreRecordatorioContainingIgnoreCase(String nombre);
    List<Recordatorio> findByTipoRecordatorioIgnoreCase(String tipo);

}
