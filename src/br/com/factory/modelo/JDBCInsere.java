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
    static String nomeTabela = "aluno";

    public static void inserir(Aluno usuario) throws SQLException {

        try {
            con = ConexaoMySQL.getConexaoMySQL();
            String sql = "insert into " + nomeTabela
                    + "(nome,dataNascimento,email,endereco,bairro,cidade,celular,telefone,conclusaoEM,escola,cor,genero,rg,universidade) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            // preenche os valores
            stmt.setString(1, usuario.getNome());
            stmt.setDate(2, usuario.getDataNascimento());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getEndereco());
            stmt.setString(5, usuario.getBairro());
            stmt.setString(6, usuario.getCidade());
            stmt.setString(7, usuario.getCelular());
            stmt.setString(8, usuario.getTelefone());
            stmt.setString(9, usuario.getConclusaoEM());
            stmt.setString(10, usuario.getEscola());
            stmt.setString(11, usuario.getCor());
            stmt.setString(12, usuario.getGenero());
            stmt.setString(13, usuario.getRg());
            stmt.setString(14, usuario.getUniversidade());
            
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
