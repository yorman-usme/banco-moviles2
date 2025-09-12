package com.example.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banco.modelo.Cuenta;


@Repository
public interface CuentaRepositrory extends JpaRepository<Cuenta, Long> {

}
