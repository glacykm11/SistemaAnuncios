/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divulgatudo.view;
import java.sql.*;
import com.divulgatudo.controller.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Glacy K. M. Gomes
 */
public class TelaInicial extends javax.swing.JFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form TelaInicial
     */
    public TelaInicial() {
        initComponents();
        conexao = ModuloConexao.conector();
        
        if(conexao!=null){
            jLabelIconeStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/divulgatudo/icones/dbok.png")));
            jLabelIconeStatus.setText("");
        }else{
            jLabelIconeStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/divulgatudo/icones/dbnotok.png")));
            jLabelIconeStatus.setText("");
            //jLabelStatus.setText("Desconectado");
        }
    }
    //método para adicionar anúncios
    private void adicionar() {
        String sql = "insert into tbanuncios(nomeanuncio,cliente,datainicio,datatermino,investimento) values(?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, jTextField1.getText());
            pst.setString(2, jTextField2.getText());
            pst.setString(3, jFormattedTextField1.getText());
            pst.setString(4, jFormattedTextField2.getText());
            pst.setString(5, jTextField5.getText());

//validação dos campos obrigatórios
            if ((jTextField1.getText().isEmpty()) || (jTextField2.getText().isEmpty()) || (jFormattedTextField1.getText().isEmpty()) || (jFormattedTextField2.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {

//a linha abaixo atualiza a tabela usuario com os dados do formulário
                //a etrutura abaixo é usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                //a linha abaixo serve de apoio ao entendimento da lógica
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Anúncio adicionado com sucesso");
                    jTextField1.setText(null);
                    jTextField2.setText(null);
                    jFormattedTextField1.setText(null);
                    jFormattedTextField2.setText(null);
                    jTextField5.setText(null);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
 public void PopularJTable(String sql) {
        try{
            Connection conexao =(Connection)DriverManager.getConnection("jdbc:mysql://localhost/dbanuncios","root","");
            PreparedStatement pst = (PreparedStatement)conexao.prepareStatement(sql);
            pst.execute();
            rs = pst.executeQuery(sql);

            DefaultTableModel model =(DefaultTableModel) jTable1.getModel();
            model.setNumRows(0);
 
            while(rs.next()){
                model.addRow(new Object[] { 
                    //retorna os dados da tabela do BD, cada campo e um coluna.
                    rs.getString("nomeanuncio"),
                    rs.getString("cliente"),
                    rs.getString("datainicio"),
                    rs.getString("datatermino"),
                    rs.getString("investimento"),
                }); 
        } 
        pst.close();
        conexao.close();
    }
    catch (SQLException ex){
     System.out.println("o erro foi " +ex);
    }
 }

  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jLabelStatus = new javax.swing.JLabel();
        jLabelIconeStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabelNome = new javax.swing.JLabel();
        jLabelCliente = new javax.swing.JLabel();
        jLabelCliente1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonCriar = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jLabelCliente2 = new javax.swing.JLabel();
        jLabelCliente3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Anúncios");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabelStatus.setText("Status:");

        jLabelIconeStatus.setText("StatusIcon");
        jLabelIconeStatus.setMaximumSize(new java.awt.Dimension(20, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DIVULGA TUDO");

        jLabelNome.setText("Nome do Anúncio:");

        jLabelCliente.setText("Cliente:");

        jLabelCliente1.setText("Investimento:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("CADASTRO DE ANÚNCIOS");

        jButtonCriar.setText("Cadastrar");
        jButtonCriar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCriarMouseClicked(evt);
            }
        });
        jButtonCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCriarActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabelCliente2.setText("Data Início:");

        jLabelCliente3.setText("Data Término");

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "Cliente", "Data Início", "Data Término", "Investimento"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jMenu1.setText("Cadastro");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Relatórios");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCliente)
                            .addComponent(jLabelCliente1)
                            .addComponent(jLabelNome)
                            .addComponent(jLabelCliente2)
                            .addComponent(jLabelCliente3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jButtonCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jFormattedTextField1)
                                    .addComponent(jFormattedTextField2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1)
                                .addGap(352, 352, 352))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabelStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelIconeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelStatus)
                            .addComponent(jLabelIconeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCliente))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCliente2))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCliente3)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCliente1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jButtonCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(836, 490));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButtonCriarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCriarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCriarMouseClicked

    private void jButtonCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCriarActionPerformed
        // TODO add your handling code here:
        adicionar();
        
    }//GEN-LAST:event_jButtonCriarActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
       this.PopularJTable("SELECT * FROM tbanuncios ORDER BY idAnuncio DESC");
    }//GEN-LAST:event_formWindowOpened

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
        TelaRelatorios telarelatorio = new TelaRelatorios();
        telarelatorio.setVisible(true);
       
    }//GEN-LAST:event_jMenu2MouseClicked

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
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCriar;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCliente;
    private javax.swing.JLabel jLabelCliente1;
    private javax.swing.JLabel jLabelCliente2;
    private javax.swing.JLabel jLabelCliente3;
    private javax.swing.JLabel jLabelIconeStatus;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables

    private void TelaRelatorios(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
