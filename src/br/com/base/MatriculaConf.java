/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.base;

import br.com.factory.modelo.Aluno;
import br.com.factory.modelo.JDBCInsere;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.read.biff.BiffException;

/**
 *
 * @author Casa
 */
public class MatriculaConf extends javax.swing.JFrame {

    //método que importa inscritos
    private void importaInscritos() throws IOException, BiffException, SQLException {
        String arquivo = "";

        //abre a caixa de diálogo e pega o nome e o caminho do arquivo desejado
        int returnVal = dlgArquivoInscritos.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = dlgArquivoInscritos.getSelectedFile();
            arquivo = file.getAbsolutePath();
        }

        //se a variavel arquivo não estiver vazia, ela é utilizada como parametro
        if (!"".equals(arquivo)) {

            //importa lista de alunos
            List<Aluno> alunos = new ArrayList<>();
            alunos = Aluno.getImportacao(arquivo);

            //insere os alunos listados
            for (int i = 0; i <= alunos.size(); i++) {
                JDBCInsere.inserir(alunos.get(i));
            }
        }
        
        txtProcura.setText(arquivo);
    }

    /**
     * Creates new form Matricula3
     */
    public MatriculaConf() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgArquivoInscritos = new javax.swing.JFileChooser();
        btnImportar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        txtProcura = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_menu1 = new javax.swing.JLabel();

        dlgArquivoInscritos.setDialogTitle("Importar inscritos");
        dlgArquivoInscritos.setFileFilter(new FileNameExtensionFilter("Pasta de Trabalho do Excel 97-2003", new String[]{"xls"}));
        dlgArquivoInscritos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlgArquivoInscritosActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnImportar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnImportar.setText("Importar inscritos");
        btnImportar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImportarMouseClicked(evt);
            }
        });
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Procurar");

        jCheckBox1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jCheckBox1.setText("Selecionar todos");

        jCheckBox2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jCheckBox2.setText("Desmarcar todos");

        txtProcura.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtProcura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProcuraActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setText("OK");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Matrícula");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Confirmar presença");

        lbl_menu1.setForeground(new java.awt.Color(0, 0, 204));
        lbl_menu1.setText("Voltar ao Menu");
        lbl_menu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_menu1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(152, 152, 152)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnImportar)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addGap(63, 63, 63)
                                        .addComponent(jCheckBox2))
                                    .addComponent(jLabel2))))
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_menu1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProcura, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lbl_menu1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addComponent(btnImportar)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtProcura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProcuraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProcuraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProcuraActionPerformed

    private void lbl_menu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_menu1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new TelaPrincipal().setVisible(true);
    }//GEN-LAST:event_lbl_menu1MouseClicked


    private void dlgArquivoInscritosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlgArquivoInscritosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dlgArquivoInscritosActionPerformed

    private void btnImportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportarMouseClicked

    }//GEN-LAST:event_btnImportarMouseClicked

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
        try {
            importaInscritos();
        } catch (IOException ex) {
            Logger.getLogger(MatriculaConf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(MatriculaConf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MatriculaConf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImportarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MatriculaConf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatriculaConf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatriculaConf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatriculaConf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MatriculaConf().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImportar;
    private javax.swing.JFileChooser dlgArquivoInscritos;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbl_menu1;
    private javax.swing.JTextField txtProcura;
    // End of variables declaration//GEN-END:variables
}
