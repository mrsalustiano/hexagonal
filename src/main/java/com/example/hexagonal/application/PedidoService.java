package com.example.hexagonal.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hexagonal.domain.Pedido;
import com.example.hexagonal.ports.in.PedidoUseCase;
import com.example.hexagonal.ports.out.PedidoRepositoryPort;

@Service
public class PedidoService implements PedidoUseCase {

	  private final PedidoRepositoryPort pedidoRepository;

	    public PedidoService(PedidoRepositoryPort pedidoRepository) {
	        this.pedidoRepository = pedidoRepository;
	    }

	    @Override
	    public Pedido criarPedido(Pedido pedido) {
	        return pedidoRepository.salvar(pedido);
	    }

	    @Override
	    public Optional<Pedido> obterPedidoPorId(Long id) {
	        return pedidoRepository.buscarPorId(id);
	    }

	    @Override
	    public List<Pedido> listarPedidos() {
	        return pedidoRepository.buscarTodos();
	    }

	    @Override
	    public void excluirPedido(Long id) {
	        pedidoRepository.deletarPorId(id);
	    }
}