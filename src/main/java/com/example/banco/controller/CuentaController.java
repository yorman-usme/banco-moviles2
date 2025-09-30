package com.example.banco.controller;

import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.CuentaCreateDTO;
import com.example.banco.mapper.CuentaMapper;
import com.example.banco.modelo.Cuenta;
import com.example.banco.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cuentas")
@CrossOrigin(origins = "*")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaMapper cuentaMapper;

    @GetMapping
    public List<CuentaDTO> obtenerTodas() {
        return cuentaService.obtenerCuentas()
                .stream()
                .map(cuentaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CuentaDTO obtenerPorId(@PathVariable Long id) {
        return cuentaMapper.toDTO(cuentaService.obtenerCuentaPorId(id));
    }

    @PostMapping
    public CuentaDTO crearCuenta(@RequestBody CuentaCreateDTO cuentaCreateDTO) {
        Cuenta cuenta = cuentaMapper.toEntity(cuentaCreateDTO);
        return cuentaMapper.toDTO(cuentaService.crearCuenta(cuenta));
    }

    @PutMapping("/{id}")
    public CuentaDTO actualizarCuenta(@PathVariable Long id, @RequestBody CuentaCreateDTO cuentaCreateDTO) {
        Cuenta cuenta = cuentaMapper.toEntity(cuentaCreateDTO);
        return cuentaMapper.toDTO(cuentaService.actualizarCuenta(id, cuenta));
    }

    @DeleteMapping("/{id}")
    public String eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return "Cuenta eliminada con éxito";
    }
}
