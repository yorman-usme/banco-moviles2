package com.example.banco.controller;

import com.example.banco.dto.TransaccionDTO;
import com.example.banco.dto.TransaccionCreateDTO;
import com.example.banco.mapper.TransaccionMapper;

import com.example.banco.modelo.Transaccion;

import com.example.banco.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private TransaccionMapper transaccionMapper;

        @GetMapping
    public List<TransaccionDTO> obtenerTodas() {
        return transaccionService.obtenerTransacciones()
                .stream()
                .map(transaccionMapper::toDTO)
                .collect(Collectors.toList());
    }

        @GetMapping("/cuenta/{cuentaId}")
    public List<TransaccionDTO> obtenerPorCuentaId(@PathVariable Long cuentaId) {
        // Asumiendo que has agregado este método en tu TransaccionService:
        List<Transaccion> transacciones = transaccionService.obtenerTransaccionesPorCuenta(cuentaId);
        
        return transacciones.stream()
                .map(transaccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransaccionDTO obtenerPorId(@PathVariable Long id) {
        return transaccionMapper.toDTO(transaccionService.obtenerTransaccionPorId(id));
    }

    @PostMapping
    public TransaccionDTO crearTransaccion(@RequestBody TransaccionCreateDTO nuevaTransaccionDTO) {
        Transaccion transaccion = transaccionMapper.toEntity(nuevaTransaccionDTO);
        return transaccionMapper.toDTO(transaccionService.crearTransaccion(transaccion));
    }

    @PutMapping("/{id}")
    public TransaccionDTO actualizarTransaccion(
            @PathVariable Long id,
            @RequestBody TransaccionCreateDTO transaccionActualizadaDTO) {
        Transaccion transaccion = transaccionMapper.toEntity(transaccionActualizadaDTO);
        return transaccionMapper.toDTO(transaccionService.actualizarTransaccion(id, transaccion));
    }

    @DeleteMapping("/{id}")
    public String eliminarTransaccion(@PathVariable Long id) {
        transaccionService.eliminarTransaccion(id);
        return "Transacción eliminada con éxito";
    }

    @PostMapping("/depositar")
    public ResponseEntity<String> depositar(@RequestBody Map<String, Object> datos) {
    String numeroCuenta = datos.get("numeroCuenta").toString();
    Double monto = Double.valueOf(datos.get("monto").toString());

    try {
        String mensaje = transaccionService.depositar(numeroCuenta, monto);
        return ResponseEntity.ok(mensaje);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}

    @PostMapping("/transferir")
    public ResponseEntity<TransaccionDTO> transferir(@RequestBody Map<String, Object> datos) {
   try {
        Long cuentaOrigenId = Long.valueOf(datos.get("cuentaOrigenId").toString());
        Long cuentaDestinoId = Long.valueOf(datos.get("cuentaDestinoId").toString());
        Double monto = Double.valueOf(datos.get("monto").toString());

         Transaccion transaccion = transaccionService.transferirFondos(cuentaOrigenId, cuentaDestinoId, monto);

         TransaccionDTO transaccionDTO = transaccionMapper.toDTO(transaccion);

        return ResponseEntity.ok(transaccionDTO);
    } catch (Exception e) {
        return ResponseEntity.badRequest().build();
    }
    }

}
