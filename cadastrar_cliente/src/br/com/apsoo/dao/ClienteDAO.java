package br.com.apsoo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.apsoo.model.Cliente;

public class ClienteDAO {

    public int incluir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente "
                + "(cpf, nome, rua, numero, bairro, cidade, uf, fone, email, data_nascimento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            preencherDados(stmt, cliente);
            return stmt.executeUpdate();
        }
    }

    public int alterar(Cliente cliente) throws SQLException {
        if (cliente.getId() == null) {
            throw new IllegalArgumentException("O id do cliente é obrigatório para alteração.");
        }

        String sql = 
            "UPDATE cliente SET cpf = ?, nome = ?, rua = ?, numero = ?, "
            + "bairro = ?, cidade = ?, uf = ?, fone = ?, email = ?, data_nascimento = ? "
            + "WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            preencherDados(stmt, cliente);
            stmt.setInt(11, cliente.getId());
            return stmt.executeUpdate();
        }
    }

    public int excluir(String cpf) throws SQLException {
        String sql = "DELETE FROM cliente WHERE cpf = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            return stmt.executeUpdate();
        }
    }

    public Cliente buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT id, cpf, nome, rua, numero, bairro, cidade, uf, fone, email, "
                + "data_nascimento FROM cliente WHERE cpf = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Date dataNascimento = rs.getDate("data_nascimento");
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("cpf"),
                            rs.getString("nome"),
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("uf"),
                            rs.getString("fone"),
                            rs.getString("email"),
                            dataNascimento == null ? null : dataNascimento.toLocalDate());
                }
            }
        }
        return null;
    }

    private void preencherDados(PreparedStatement stmt, Cliente cliente) throws SQLException {
        stmt.setString(1, cliente.getCpf());
        stmt.setString(2, cliente.getNome());
        stmt.setString(3, cliente.getRua());
        stmt.setString(4, cliente.getNumero());
        stmt.setString(5, cliente.getBairro());
        stmt.setString(6, cliente.getCidade());
        stmt.setString(7, cliente.getUf());
        stmt.setString(8, cliente.getFone());
        stmt.setString(9, cliente.getEmail());
        if (cliente.getDataNascimento() == null) {
            stmt.setDate(10, null);
        } else {
            stmt.setDate(10, Date.valueOf(cliente.getDataNascimento()));
        }
    }
}
