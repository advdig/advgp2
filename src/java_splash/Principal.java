/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_splash;

import conexion.conexion_facturacion;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import Principal.Tarea_Credito;
import Principal.Tarea_contable;
import Principal.Tarea_contingencia;
import Principal.Tarea_facturas;
import Principal.version;
import java.util.HashMap;
import java.util.Map;
import system.tray.interfaz;

/**
 *
 * @author r
 */
public class Principal extends javax.swing.JFrame {

    String fecha;
    Date h2;
    public String usuarios, contraseña;
    public String cargo;
    private static final int FSL = 127;
    ObjectOutputStream output;
    File comprobar = new File("comprobar.txt");
    int nmanguera;
    public byte[] comandoinfor = new byte[3];
    private byte[] respuestainfor = new byte[2000];
    private int bytesReadinfor;
    String ip;
    int numerop;
    public byte[] comandoturno = new byte[3];
    private byte[] respuestaturno = new byte[2000];
    private int bytesReadturno;
    Double montos[];
    Double volumen[];
    String rz = null, np = null, d = null, r = null;
    String s1 = null, s2 = null;
    int s3 = 0;
    File firmados = new File("firmados");
    File generados = new File("Generados");
    File autorizados = new File("autorizados");
    File contingencia = new File("contingencia");
    File pdf = new File("pdf");
    File noautorizados = new File("no_autorizados");
    File consumidores_finales = new File("consumidores_finales");
    /**
     * ******************************************
     *
     */
    File notas_firmadas = new File("notas_firmadas");
    File notas_generadas = new File("notas_Generadas");
    File notas_autorizadas = new File("notas_autorizadas");
    File notas_contingencia = new File("notas_contingencia");
    File notas_pdf = new File("notas_pdf");
    File notas_noautorizados = new File("notas_no_autorizados");

    /**
     * Creates new form Principal
     */
    public Principal() {















        interfaz i = new interfaz();




        initComponents();


        this.setExtendedState(this.MAXIMIZED_BOTH);

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/java_splash/ad.png")));

        setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);



        this.addWindowListener(new WindowAdapter() {
            JPasswordField pas = new JPasswordField();
            Object[] obj = {"Ingrese la contraseña del usuario" + ":\n\n", pas};
            Object stringArray[] = {"OK", "Cancel"};
            String horai, horas;

            public void windowClosing(WindowEvent we) {

                String pass = null;
                Component th = null;
                String titulo = "ingrese su contraseña";
                if (JOptionPane.showOptionDialog(th, obj, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION) {
                    try {
                        pass = pas.getText();


                        int id;


                        conexion_facturacion n = new conexion_facturacion("root", "manager");
                        n.conectar();
                        String a = "SELECT idusuarios FROM usuarios WHERE usuario='" + usuarios + "' AND AES_DECRYPT(contraseña,'thekey')='" + pass + "'";
                        ResultSet consulta = n.consulta(a);
                        int idu = 0;
                        try {
                            if (consulta.first()) // si es valido el primer reg. hay una fila, tons el usuario y su pw existen
                            {
                                id = consulta.getInt(1);
                                JOptionPane.showMessageDialog(null, "Usuario Validado Correctamente Cerrando Turno y Aplicacion");
                                comprobar.delete();
                                System.exit(0);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });


        if (firmados.exists()) {
        } else {

            firmados.mkdirs();

        }

        if (consumidores_finales.exists()) {
        } else {

            consumidores_finales.mkdirs();

        }

        if (generados.exists()) {
        } else {

            generados.mkdirs();

        }
        if (autorizados.exists()) {
        } else {

            autorizados.mkdirs();

        }
        if (contingencia.exists()) {
        } else {

            contingencia.mkdirs();

        }
        if (pdf.exists()) {
        } else {

            pdf.mkdirs();

        }
        if (noautorizados.exists()) {
        } else {

            noautorizados.mkdirs();

        }
        /**
         * ***********************
         */
        if (notas_firmadas.exists()) {
        } else {



            notas_firmadas.mkdirs();
        }

        if (notas_generadas.exists()) {
        } else {

            notas_generadas.mkdirs();

        }
        if (notas_autorizadas.exists()) {
        } else {

            notas_autorizadas.mkdirs();

        }
        if (notas_contingencia.exists()) {
        } else {

            notas_contingencia.mkdirs();

        }
        if (notas_pdf.exists()) {
        } else {

            notas_pdf.mkdirs();

        }
        if (notas_noautorizados.exists()) {
        } else {


            notas_noautorizados.mkdirs();

        }


        System.out.println(notas_noautorizados.getAbsolutePath());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        escritorio = new javax.swing.JDesktopPane();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();

        jMenu5.setText("File");
        jMenuBar2.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar2.add(jMenu6);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Advgp2.0");

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(15, 1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_splash/índice.jpg"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jPanel1.add(jPanel2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_splash/splash.png"))); // NOI18N
        jButton1.setText("jButton1");
        jButton1.setBounds(250, 80, 640, 420);
        escritorio.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu9.setText("Ayuda");

        jMenuItem11.setText("Acerca de:");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem11);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        


    }//GEN-LAST:event_jButton2ActionPerformed
    byte[] comando = new byte[3];

    ;
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        version v = new version();
        v.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    public void perfil(String usua, String contra) {
        try {


            servidores();
            usuarios = usua;
            contraseña = contra;

            conexion_facturacion n = new conexion_facturacion(usuarios, contraseña);
            n.conectar();

            Statement st_s5 = n.coneccion.createStatement();

            ResultSet rids5 = st_s5.executeQuery("SELECT cargo FROM adv_facturacion.usuarios where usuario='" + usuarios + "';");
            while (rids5.next()) {

                cargo = rids5.getString(1);

            }


            if (cargo.equalsIgnoreCase("adv")) {
            }


            if (cargo.equalsIgnoreCase("administrador")) {
            }
            if (cargo.equalsIgnoreCase("despachador")) {

                System.out.println("despachador");


            }
            if (cargo.equalsIgnoreCase("Secretaria")) {

                System.out.println("secretaria");
                jButton2.setVisible(false);




            }



            Statement st_m = n.coneccion.createStatement();
            ResultSet ridm = st_m.executeQuery("SELECT count(nmanguera) FROM adv_facturacion.configuracion;");
            while (ridm.next()) {

                nmanguera = ridm.getInt(1);
            }


            montos = new Double[nmanguera + 1];
            volumen = new Double[nmanguera + 1];

            org.quartz.Scheduler scheduler;
            Map<String, String> map = new HashMap<String, String>();

            try {



                map.put("contrasena", contraseña);
                map.put("usuarios", usuarios);



                org.quartz.JobDetail jobDetail = new org.quartz.JobDetail("StatusJob", org.quartz.Scheduler.DEFAULT_GROUP, Tarea_contingencia.class);
                org.quartz.JobDetail jobDetail1 = new org.quartz.JobDetail("StatusJob1", org.quartz.Scheduler.DEFAULT_GROUP, Tarea_Credito.class);
                org.quartz.JobDetail jobDetail2 = new org.quartz.JobDetail("StatusJob2", org.quartz.Scheduler.DEFAULT_GROUP, Tarea_facturas.class);
                org.quartz.JobDetail jobDetail3 = new org.quartz.JobDetail("StatusJob3", org.quartz.Scheduler.DEFAULT_GROUP, Tarea_contable.class);
                

                // Configuramos los parametros de la tarea, en esta caso le decimos en que archivo debe guardar el estado de la VM.  
                jobDetail.getJobDataMap().putAll(map);
                jobDetail1.getJobDataMap().putAll(map);
                jobDetail2.getJobDataMap().putAll(map);
                jobDetail3.getJobDataMap().putAll(map);
                // jobDetail.getJobDataMap().put(Tarea_contingencia.contraseña,contraseña);

                // Configuramos el Trigger que avisará al planificador de cuando debe ejecutar la tarea, en este caso cada 5 segundos.  
                org.quartz.CronTrigger trigger = new org.quartz.CronTrigger("StatusTrigger", org.quartz.Scheduler.DEFAULT_GROUP,   "0 0 23 * * ? ");
                org.quartz.CronTrigger trigger1 = new org.quartz.CronTrigger("StatusTrigger1", org.quartz.Scheduler.DEFAULT_GROUP, "0 0 0 1 1/1 ? *");
                org.quartz.CronTrigger trigger2 = new org.quartz.CronTrigger("StatusTrigger2", org.quartz.Scheduler.DEFAULT_GROUP, "0 0 23 L * ? *");
                org.quartz.CronTrigger trigger3 = new org.quartz.CronTrigger("StatusTrigger3", org.quartz.Scheduler.DEFAULT_GROUP, "0 0 16 * * ? ");


                // Obtenemos el planificador  
                scheduler = org.quartz.impl.StdSchedulerFactory.getDefaultScheduler();


                // La tarea definida en JobDetail será ejecutada en los instantes especificados por el Trigger.  
               // scheduler.scheduleJob(jobDetail, trigger);
                scheduler.scheduleJob(jobDetail1, trigger1);
                scheduler.scheduleJob(jobDetail2, trigger2);
                //scheduler.scheduleJob(jobDetail3, trigger3);

                // Iniciamos las tareas planificadas en el Sheduler  
                //scheduler.start();


            } catch (Exception e) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, e);
            }





            n.coneccion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

//para cortar el papel de mi ticketera
    public void servidores() {

        try {
            java.io.BufferedReader buffer = new java.io.BufferedReader(new java.io.FileReader("servidores.adv"));
            String linea = "";


            int cont = 0;
            while ((linea = buffer.readLine()) != null) {

                //ip=linea.substring(1,2);

                if (cont == 1) {
                    ip = linea.substring(18, linea.length());


                }


                cont++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
