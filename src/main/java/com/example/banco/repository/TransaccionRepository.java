package com.example.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.banco.modelo.Transaccion;
import java.util.List;
@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
     List<Transaccion> findByCuentaId(Long cuentaId);
}
