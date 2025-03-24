package com.example.hexagonal.ports.in;

import java.util.List;
import java.util.Optional;

import com.example.hexagonal.domain.Pedido;

public interface PedidoUseCase {

	Pedido criarPedido(Pedido pedido);

	Optional<Pedido> obterPedidoPorId(Long id);

	List<Pedido> listarPedidos();

	void excluirPedido(Long id);
}
