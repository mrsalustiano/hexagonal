package com.example.hexagonal.ports.in;


import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hexagonal.domain.Pedido;
import com.example.hexagonal.ports.in.dto.PedidoDTO;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	   private final PedidoUseCase pedidoUseCase;

	    public PedidoController(PedidoUseCase pedidoUseCase) {
	        this.pedidoUseCase = pedidoUseCase;
	    }

	    @PostMapping
	    public Pedido criarPedido(@RequestBody PedidoDTO pedidoDTO) {
	        Pedido pedido = new Pedido(null, pedidoDTO.getDescricao(), pedidoDTO.getValor());
	        return pedidoUseCase.criarPedido(pedido);
	    }
	    
	    

	    @GetMapping
	    public List<Pedido> listarPedidos() {
	        return pedidoUseCase.listarPedidos();
	    }

	    @GetMapping("/{id}")
	    public Optional<Pedido> buscarPedido(@PathVariable Long id) {
	        return pedidoUseCase.obterPedidoPorId(id);
	    }

	    @DeleteMapping("/{id}")
	    public void excluirPedido(@PathVariable Long id) {
	        pedidoUseCase.excluirPedido(id);
	    }
	}

	