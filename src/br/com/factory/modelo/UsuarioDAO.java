/*
 * Copyright (c) 2014, Luana
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package br.com.factory.modelo;

import br.com.factory.ConexaoMySQL;
import static br.com.factory.modelo.JDBCInsere.nomeTabela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * Classe para o usuário do software
 */
public class UsuarioDAO {
    private int id;
    private String nome, senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(char[] senha) {
        this.senha = new String(senha);
    }
    
    //retorna true se o nome de usuário e senha estiverem corretos
    public static boolean verificaUsuario(String nome, String senha) throws SQLException{
        nome = nome.toUpperCase();
        Connection con = null;
        nomeTabela = "login";
        boolean retorno = false;
        
        try {
            con = ConexaoMySQL.getConexaoMySQL();
            Statement stmt = con.createStatement();
            
            String sql = "select * from " + nomeTabela
                    + "where login = '" + nome + "';";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                String nomee = rs.getString("nome");
                String senhaa = rs.getString("senha");
                
                if(senha.equals(senhaa) && nome.equals(nomee)){
                    retorno = true;
                }
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            con.close();
        }
        return retorno;
    }
}
