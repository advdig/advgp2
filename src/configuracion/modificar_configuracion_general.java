/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracion;

import conexion.conexion_facturacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author r
 */
public class modificar_configuracion_general extends javax.swing.JFrame {

    public String usu, contra;
    /**
     * Creates new form configuracion_general
     */
    int mangueras;
    private DefaultListModel modelo = new DefaultListModel();
    MiModelo litabla = new MiModelo();
    int valor, valor1, valor2, valor3, valor4;
    int nmangueras = 0;

    public modificar_configuracion_general() {
        initComponents();



        // litabla.setNumRows(nmangueras);
        tabla.setModel(litabla);


        litabla.addColumn("Manguera");
        litabla.addColumn("Surtidor");
        litabla.addColumn("Producto");
        litabla.addColumn("Tanque");
        litabla.addColumn("Numero de Manguera Cemm-44");

        Object datos[] = new Object[5];

        conexion_facturacion n = new conexion_facturacion("root", "manager");
        try {
            n.conectar();

            Statement st_d1 = n.coneccion.createStatement();
            ResultSet rid1 = st_d1.executeQuery("SELECT count(*) FROM adv_facturacion.configuracion;");
            while (rid1.next()) {

                nmangueras = rid1.getInt(1);

            }


            Statement st_d = n.coneccion.createStatement();
            ResultSet rid = st_d.executeQuery("SELECT * FROM adv_facturacion.configuracion;");






            while (rid.next()) {

                datos[0] = rid.getInt(1);
                datos[1] = rid.getInt(2);
                datos[2] = rid.getInt(3);
                datos[3] = rid.getInt(4);
                datos[4] = rid.getInt(5);
                litabla.addRow(datos);
            }





        } catch (ClassNotFoundException ex) {
            Logger.getLogger(modificar_configuracion_general.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(modificar_configuracion_general.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jButton1.setText("Ingresar Configuracion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            conexion_facturacion n = new conexion_facturacion(usu, contra);
            n.conectar();

            for (int i = 0; i < litabla.getRowCount(); i++) {

                System.out.println(i);
                valor = Integer.valueOf(litabla.getValueAt(i, 0).toString());
                valor1 = Integer.valueOf(litabla.getValueAt(i, 1).toString());
                valor2 = Integer.valueOf(litabla.getValueAt(i, 2).toString());
                valor3 = Integer.valueOf(litabla.getValueAt(i, 3).toString());
                valor4 = Integer.valueOf(litabla.getValueAt(i, 4).toString());


                Statement st1 = n.coneccion.createStatement();
                Statement st2 = n.coneccion.createStatement();
                Statement st3 = n.coneccion.createStatement();
                Statement st4 = n.coneccion.createStatement();

                String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET `surtidor`='" + valor1 + "' WHERE `nmanguera`='" + valor + "';";
                st1.executeUpdate(consulta1);

                System.out.println(consulta1);
                
                String consulta2 = "UPDATE `adv_facturacion`.`configuracion` SET `producto_idproducto`='" + valor2 + "' WHERE `nmanguera`='" + valor + "';";
                st2.executeUpdate(consulta2);

                String consulta3 = "UPDATE `adv_facturacion`.`configuracion` SET `tanques_idtanques`='" + valor3 + "' WHERE `nmanguera`='" + valor + "';";
                st3.executeUpdate(consulta3);


                String consulta4 = "UPDATE `adv_facturacion`.`configuracion` SET `nmanguera_cem`='" + valor4 + "' WHERE `nmanguera`='" + valor + "';";
                st4.executeUpdate(consulta4);



            }
            JOptionPane.showMessageDialog(this, "Configuracion Ingresada Correctamente");
            this.setVisible(false);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(modificar_configuracion_general.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(modificar_configuracion_general.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(modificar_configuracion_general.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modificar_configuracion_general.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modificar_configuracion_general.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modificar_configuracion_general.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
    public class MiModelo extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {

            return true;

        }
    }

    public void limpiaTabla() {
        for (int i = 0; i < litabla.getRowCount(); i++) {

            litabla.removeRow(i);
        }
    }

    public void usuarios(String usuario, String contraseña) {

        contra = contraseña;
        usu = usuario;

    }
}
