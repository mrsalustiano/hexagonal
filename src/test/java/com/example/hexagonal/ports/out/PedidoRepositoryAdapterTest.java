package com.example.hexagonal.ports.out;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.hexagonal.domain.Pedido;

@JdbcTest
class PedidoRepositoryAdapterTest {

	
	

	    @Autowired
	    private JdbcTemplate jdbcTemplate;

	    private PedidoRepositoryAdapter pedidoRepository;

	    @BeforeEach
	    void setUp() {
	        pedidoRepository = new PedidoRepositoryAdapter(jdbcTemplate);
	        jdbcTemplate.execute("INSERT INTO pedidos (descricao, valor) VALUES ('Mouse Gamer', 250.00)");
	    }

	    @Test
	    void deveSalvarPedido() {
	        Pedido novoPedido = new Pedido(null, "Fone Bluetooth", new BigDecimal("150.00"));

	        Pedido pedidoSalvo = pedidoRepository.salvar(novoPedido);

	        assertNotNull(pedidoSalvo.getId());
	        assertEquals("Fone Bluetooth", pedidoSalvo.getDescricao());
	    }

	    @Test
	    void deveBuscarPedidoPorId() {
	    	
	    	Pedido novoPedido = new Pedido(null, "Mouse Gamer", new BigDecimal("250.00"));

		    Pedido pedidoSalvo = pedidoRepository.salvar(novoPedido);
	    	
	        Optional<Pedido> pedido = pedidoRepository.buscarPorId(4L);

	        assertTrue(pedido.isPresent());
	        assertEquals("Mouse Gamer", pedido.get().getDescricao());
	    }

	    @Test
	    void deveListarPedidos() {
	        List<Pedido> pedidos = pedidoRepository.buscarTodos();

	        assertFalse(pedidos.isEmpty());
	        assertEquals(1, pedidos.size());
	    }

	    @Test
	    void deveDeletarPedido() {
	        pedidoRepository.deletarPorId(1L);
	        Optional<Pedido> pedido = pedidoRepository.buscarPorId(1L);

	        assertFalse(pedido.isPresent());
	    }
	
}
