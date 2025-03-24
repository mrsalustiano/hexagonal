package com.example.hexagonal.ports.out;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.example.hexagonal.domain.Pedido;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final JdbcTemplate jdbcTemplate;
    

    public PedidoRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (descricao, valor) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pedido.getDescricao());
            ps.setBigDecimal(2, pedido.getValor());
            return ps;
        }, keyHolder);

        Long idGerado = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return new Pedido(idGerado, pedido.getDescricao(), pedido.getValor());
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        return jdbcTemplate.query(sql, rowMapper(), id).stream().findFirst();
    }

    @Override
    public List<Pedido> buscarTodos() {
        String sql = "SELECT * FROM pedidos";
        return jdbcTemplate.query(sql, rowMapper());
    }

    @Override
    public void deletarPorId(Long id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

	 RowMapper<Pedido> rowMapper() {
        return (rs, rowNum) -> new Pedido(
                rs.getLong("id"),
                rs.getString("descricao"),
                rs.getBigDecimal("valor")
        );
    }
}