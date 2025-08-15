package com.example.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banco.modelo.Transaccion;

@Repository
public interface TransaccionesRepository  extends JpaRepository<Transaccion, Long> {


    
}
