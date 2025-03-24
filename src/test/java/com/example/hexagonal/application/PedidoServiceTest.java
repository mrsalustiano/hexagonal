package com.example.hexagonal.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.hexagonal.domain.Pedido;
import com.example.hexagonal.ports.out.PedidoRepositoryPort;

class PedidoServiceTest {

	 @Mock
	    private PedidoRepositoryPort pedidoRepository;

	    @InjectMocks
	    private PedidoService pedidoService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void deveCriarPedido() {
	        Pedido pedido = new Pedido(null, "Notebook Gamer", new BigDecimal("5000.00"));
	        Pedido pedidoSalvo = new Pedido(1L, "Notebook Gamer", new BigDecimal("5000.00"));

	        when(pedidoRepository.salvar(pedido)).thenReturn(pedidoSalvo);

	        Pedido resultado = pedidoService.criarPedido(pedido);

	        assertNotNull(resultado);
	        assertEquals(1L, resultado.getId());
	        assertEquals("Notebook Gamer", resultado.getDescricao());
	        verify(pedidoRepository, times(1)).salvar(pedido);
	    }

	    @Test
	    void deveBuscarPedidoPorId() {
	        Pedido pedido = new Pedido(1L, "Smartphone", new BigDecimal("2000.00"));

	        when(pedidoRepository.buscarPorId(1L)).thenReturn(Optional.of(pedido));

	        Optional<Pedido> resultado = pedidoService.obterPedidoPorId(1L);

	        assertTrue(resultado.isPresent());
	        assertEquals(1L, resultado.get().getId());
	        assertEquals("Smartphone", resultado.get().getDescricao());
	        verify(pedidoRepository, times(1)).buscarPorId(1L);
	    }

	    @Test
	    void deveListarPedidos() {
	        List<Pedido> pedidos = List.of(
	                new Pedido(1L, "Teclado Mecânico", new BigDecimal("350.00")),
	                new Pedido(2L, "Monitor 27 Polegadas", new BigDecimal("1500.00"))
	        );

	        when(pedidoRepository.buscarTodos()).thenReturn(pedidos);

	        List<Pedido> resultado = pedidoService.listarPedidos();

	        assertEquals(2, resultado.size());
	        verify(pedidoRepository, times(1)).buscarTodos();
	    }

	    @Test
	    void deveExcluirPedido() {
	        doNothing().when(pedidoRepository).deletarPorId(1L);

	        pedidoService.excluirPedido(1L);

	        verify(pedidoRepository, times(1)).deletarPorId(1L);
	    }

}
