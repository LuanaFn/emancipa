/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.factory.modelo;

/**
 *
 * Classe para os alunos que desistiram do curso
 */
public class AlunoDesistenteDAO extends AlunoDAO {
    private String motivo, especificacao;
    private boolean vaiVoltar;

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public boolean isVaiVoltar() {
        return vaiVoltar;
    }

    public void setVaiVoltar(boolean vaiVoltar) {
        this.vaiVoltar = vaiVoltar;
    }
}
