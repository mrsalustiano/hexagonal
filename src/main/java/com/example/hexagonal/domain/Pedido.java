package com.example.hexagonal.domain;

import java.math.BigDecimal;

public class Pedido {

	  private Long id;
	    private String descricao;
	    private BigDecimal valor;

	    public Pedido(Long id, String descricao, BigDecimal valor) {
	        this.id = id;
	        this.descricao = descricao;
	        this.valor = valor;
	    }

		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}

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



		@Override
		public String toString() {
			return "Pedido [id=" + id + ", descricao=" + descricao + ", valor=" + valor + "]";
		}

	    
}
