package com.example.hexagonal.ports.in.dto;

import java.math.BigDecimal;

public class PedidoDTO {

    private String descricao;
    private BigDecimal valor;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

    
}
