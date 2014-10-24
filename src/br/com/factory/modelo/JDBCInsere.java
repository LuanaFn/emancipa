/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.factory.modelo;

import br.com.factory.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Luana
 */
public class JDBCInsere {

    static Connection con = null;

    public static void inserir(Aluno usuario) throws SQLException {

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            String sql = "insert into usuario "
                    + "(nome,cpf,email,telefone,dataNascimento) "
                    + "values (?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            // preenche os valores
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            stmt.setDate(5, usuario.getDataNascimento());

            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.close();
        }
    }
}
