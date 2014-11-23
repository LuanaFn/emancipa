/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.factory.modelo;

import br.com.base.MatriculaMatricular;
import br.com.factory.ConexaoMySQL;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * Classe com os detalhes dos alunos que
 * se matricularam ou apenas se inscreveram,
 * independentemente de terem confirmado presença ou não,
 * ou de ainda estudarem
 */
public class AlunoDAO {

    private int id;
    private String nome, cpf, email, endereco, bairro,
            cidade, celular, telefone, conclusaoEM, escola, cor,
            genero, rg, universidade, curso;
    private Date dataNascimento;
    private boolean ativo = false; //se o aluno frequenta as aulas. Fica true quando confirma presença
    private boolean aprovado = false; //se ele passou na faculdade
    private boolean desistente = false; //se desistiu do curso

    public boolean isDesistente() {
        return desistente;
    }

    public void setDesistente(boolean desistente) {
        this.desistente = desistente;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getConclusaoEM() {
        return conclusaoEM;
    }

    public void setConclusaoEM(String conclusaoEM) {
        this.conclusaoEM = conclusaoEM;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<AlunoDAO> getLista(String select) {
        Connection con = null;
        try {
            con = ConexaoMySQL.getConexaoMySQL();
            List<AlunoDAO> alunos = new ArrayList<AlunoDAO>();
            PreparedStatement stmt = con.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // criando o objeto AlunoDAO*
                AlunoDAO aluno = new AlunoDAO();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setRg(rs.getString("rg"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setBairro(rs.getString("bairro"));
                aluno.setCidade(rs.getString("cidade"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setCelular(rs.getString("celular"));
                aluno.setConclusaoEM(rs.getString("conclusaoEM"));
                aluno.setEscola(rs.getString("escola"));
                aluno.setCor(rs.getString("cor"));
                aluno.setGenero(rs.getString("genero"));
                aluno.setUniversidade(rs.getString("universidade"));
                aluno.setDataNascimento(rs.getDate("dataNascimento"));

                // adicionando o objeto à lista
                alunos.add(aluno);
            }
            rs.close();
            stmt.close();
            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //método que importa um arquivo do Excel de inscritos que vem do site
    //retorna uma lista de objetos do tipo AlunoDAO com todos os inscritos
    public static List<AlunoDAO> getImportacao(String arquivo) throws IOException, BiffException {

        //cria lista de Alunos
        List<AlunoDAO> alunos = new ArrayList<>();

        //abre o arquivo Excel que foi selecionado pelo usuário
        Workbook workbook = Workbook.getWorkbook(new File(arquivo));
        Sheet sheet = workbook.getSheet(0);

        //pega a quantidade de linhas da tabela
        int linhas = sheet.getRows();
        int colunas = sheet.getColumns();

        for (int i = 1; i < linhas; i++) {

            //informações que serão retiradas da planilha
            String enome = "";
            String eemail = "";
            String eendereco = "";
            String ebairro = "";
            String ecelular = "";
            String enascimento = "";
            String econclusaoEM = "";
            String eescola = "";
            String ecor = "";
            String egenero = "";
            String erg = "";
            String etelefone = "";
            String ecidade = "";
            String ecurso = "";
            String euniversidade = "";

            for (int j = 0; j < colunas; j++) {
                //lê cada célula
                Cell a1 = sheet.getCell(j, i);
                String as1 = a1.getContents();

                //salva o valor das células em variáveis diferentes de acordo com a posição delas
                switch (j) {
                    case 0:
                        enome = as1;
                        break;
                    case 2:
                        eemail = as1;
                        break;
                    case 3:
                        eendereco = as1;
                        break;
                    case 4:
                        ebairro = as1;
                        break;
                    case 5:
                        ecelular = as1;
                        break;
                    case 6:
                        enascimento = as1;
                        break;
                    case 7:
                        econclusaoEM = as1;
                        break;
                    case 8:
                        eescola = as1;
                        break;
                    case 9:
                        ecor = as1;
                        break;
                    case 10:
                        egenero = as1;
                        break;
                    case 11:
                        erg = as1;
                        break;
                    case 12:
                        etelefone = as1;
                        break;
                    case 13:
                        ecidade = as1;
                        break;
                    case 14:
                        ecurso = as1;
                        break;
                    case 15:
                        euniversidade = as1;
                        break;
                }
            }

            // criando o objeto AlunoDAO*
            AlunoDAO aluno = new AlunoDAO();
            aluno.setNome(enome);
            aluno.setRg(erg);
            aluno.setEndereco(eendereco);
            aluno.setBairro(ebairro);
            aluno.setCidade(ecidade);
            aluno.setEmail(eemail);
            aluno.setTelefone(etelefone);
            aluno.setCelular(ecelular);
            aluno.setConclusaoEM(econclusaoEM);
            aluno.setEscola(eescola);
            aluno.setCor(ecor);
            aluno.setGenero(egenero);
            aluno.setUniversidade(euniversidade);
            aluno.setDataNascimento(AlunoDAO.strParaDate(enascimento));
            aluno.setCurso(ecurso);
            
            alunos.add(aluno);
        }
        
        return alunos;
    }
    
    //converte Strings de datas no formato dd/mm/yyyy para o formato java.sql.Date
    public static Date strParaDate(String strData){
        
        //cria variável pra formatar datas de dia-mes-ano
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        //inicializa variável de Data estilo ano-mes-dia
        java.sql.Date data = null;
 
        //tenta converter as datas
        try {
            data = new java.sql.Date(format.parse(strData).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(MatriculaMatricular.class.getName()).log(Level.SEVERE, null, ex);
        }

        //retorna a data convertida
        return data;
    }
    
    //converte sql.Date em String
    public static String dateParaStr(Date dateData){
        //cria variável pra formatar datas de dia-mes-ano
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
 
        //tenta converter a data
        String data = format.format(dateData);

        //retorna a data convertida
        return data;
    }
}
