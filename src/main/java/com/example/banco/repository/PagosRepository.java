package com.example.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.banco.modelo.Pagos;

public interface PagosRepository extends JpaRepository<Pagos, Long> {
}
