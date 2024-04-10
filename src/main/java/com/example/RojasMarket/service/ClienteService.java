package com.example.RojasMarket.service;

import com.example.RojasMarket.Repository.ClienteRepository;
import com.example.RojasMarket.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> listaCliente(){
        return clienteRepository.listaCliente();

    }

    public Optional<Cliente> getById(int id){

        return clienteRepository.idCliente(id);
    }

    public void saveCliente(Cliente cliente){
        clienteRepository.saveCliente(cliente.getDni_cliente(), cliente.getNombre_cliente(), cliente.getCorreo_cliente(),cliente.getTelefono_cliente());
    }


    public void updateCliente(Cliente cliente) {
        clienteRepository.updateCliente(
                cliente.getId_cliente(),
                cliente.getDni_cliente(),
                cliente.getNombre_cliente(),
                cliente.getCorreo_cliente(),
                cliente.getTelefono_cliente()
        );
    }


    public void eliminarCliente(int id){
        clienteRepository.eliminarCliente(id);
    }
}
