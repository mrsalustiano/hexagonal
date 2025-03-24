package com.example.hexagonal.ports.in;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.hexagonal.domain.Pedido;


@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {



    @Mock
    private PedidoUseCase pedidoUseCase;

    @InjectMocks
    private PedidoController pedidoController;

    private MockMvc mockMvc;

    @Test
    void deveCriarPedido() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();

        Pedido pedido = new Pedido(1L, "Cadeira Gamer", new BigDecimal("700.00"));
        when(pedidoUseCase.criarPedido(any())).thenReturn(pedido);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Cadeira Gamer\", \"valor\": 700.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descricao").value("Cadeira Gamer"));
    }

    @Test
    void deveListarPedidos() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();

        List<Pedido> pedidos = List.of(
                new Pedido(1L, "Mouse Gamer", new BigDecimal("250.00")),
                new Pedido(2L, "Teclado RGB", new BigDecimal("350.00"))
        );

        when(pedidoUseCase.listarPedidos()).thenReturn(pedidos);

        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void deveExcluirPedido() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();

        doNothing().when(pedidoUseCase).excluirPedido(1L);

        mockMvc.perform(delete("/pedidos/1"))
                .andExpect(status().isOk());

        verify(pedidoUseCase, times(1)).excluirPedido(1L);
    }
	
}
