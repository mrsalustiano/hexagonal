package com.example.hexagonal.ports.out;

import java.util.List;
import java.util.Optional;

import com.example.hexagonal.domain.Pedido;

public interface PedidoRepositoryPort {
	
    Pedido salvar(Pedido pedido);
    Optional<Pedido> buscarPorId(Long id);
    List<Pedido> buscarTodos();
    void deletarPorId(Long id);

}
