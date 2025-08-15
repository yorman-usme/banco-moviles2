package com.example.banco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banco.modelo.Cliente;
import com.example.banco.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
    
    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public Cliente buscarPorNombre(String nombre) {
        return clienteRepository.findByName(nombre);
    }
    
    @Override
    public List<Cliente> obtenerTodas() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    
}
