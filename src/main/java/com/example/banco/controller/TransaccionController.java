package com.example.banco.controller;

import com.example.banco.dto.TransaccionDTO;
import com.example.banco.dto.TransaccionCreateDTO;
import com.example.banco.mapper.TransaccionMapper;

import com.example.banco.modelo.Transaccion;

import com.example.banco.service.TransaccionService;

import jakarta.validation.Valid;
import com.example.banco.dto.RetiroRequest; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> depositar(@RequestBody Map<String, Object> datos) {
        try {
                if (!datos.containsKey("numeroCuenta") || !datos.containsKey("monto")) {
                    return ResponseEntity.badRequest().body("Faltan campos requeridos: numeroCuenta o monto");
                }

                String numeroCuenta = datos.get("numeroCuenta").toString();
                Double monto = Double.valueOf(datos.get("monto").toString());

                Transaccion transaccion = transaccionService.depositar(numeroCuenta, monto);
                return ResponseEntity.ok(transaccion);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Error al procesar el depósito: " + e.getMessage());
            }
    }

        @PostMapping("/retirar") // <-- Nuevo endpoint
    public ResponseEntity<String> realizarRetiro(@Valid @RequestBody RetiroRequest request) {
        try {
            transaccionService.retirar(request.getNumeroCuenta(), request.getMonto());
            return ResponseEntity.ok("Retiro realizado con éxito.");
        } catch (IllegalArgumentException e) {
            // Error de saldo insuficiente o cuenta no encontrada
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al realizar el retiro.");
        }
    }

    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(@RequestBody Map<String, Object> datos) {
   try {
        Long cuentaOrigenId = Long.valueOf(datos.get("cuentaOrigenId").toString());
        Long cuentaDestinoId = Long.valueOf(datos.get("cuentaDestinoId").toString());
        Double monto = Double.valueOf(datos.get("monto").toString());

        transaccionService.transferirFondos(cuentaOrigenId, cuentaDestinoId, monto);

        return ResponseEntity.ok("Transferencia realizada con éxito.");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("Error al transferir fondos: " + e.getMessage());
    }

}
}