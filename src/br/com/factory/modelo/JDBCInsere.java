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
    static String nomeTabela;

    //insere novo aluno
    public static void inserirAluno(AlunoDAO aluno) throws SQLException {
        
        nomeTabela = "aluno";
        
        try {
            con = ConexaoMySQL.getConexaoMySQL();
            String sql = "insert into " + nomeTabela
                    + "(nome,dataNascimento,email,endereco,bairro,cidade,celular,telefone,conclusaoEM,escola,cor,genero,rg,universidade) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            // preenche os valores
            stmt.setString(1, aluno.getNome());
            stmt.setDate(2, aluno.getDataNascimento());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getEndereco());
            stmt.setString(5, aluno.getBairro());
            stmt.setString(6, aluno.getCidade());
            stmt.setString(7, aluno.getCelular());
            stmt.setString(8, aluno.getTelefone());
            stmt.setString(9, aluno.getConclusaoEM());
            stmt.setString(10, aluno.getEscola());
            stmt.setString(11, aluno.getCor());
            stmt.setString(12, aluno.getGenero());
            stmt.setString(13, aluno.getRg());
            stmt.setString(14, aluno.getUniversidade());
            
            stmt.execute();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.close();
        }
    }
    
    public static void inserirUsuario(UsuarioDAO usuario) throws SQLException {
        nomeTabela = "login";
        
        try {
            con = ConexaoMySQL.getConexaoMySQL();
            String sql = "insert into " + nomeTabela
                    + "(nome,senha) "
                    + "values (?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            // preenche os valores
            stmt.setString(1, usuario.getNome().toUpperCase());
            stmt.setString(2, usuario.getSenha());
            
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
