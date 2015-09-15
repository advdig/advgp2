package Principal;

import conexion.Conecciones;
import facturacion.buscar;
import java.text.DateFormat;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import conexion.conexion_facturacion;
import java.net.InetAddress;
//import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import facturacion.facturacion_electronica;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.apache.log4j.PropertyConfigurator;
import sockets.servidor;
import org.apache.log4j.*;
import org.slf4j.LoggerFactory;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author r
 */
public class Surtidores extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    //////
    String ip, ip_matriz;
    /////
    int totals, nsurtidor;
    //////////////////
    Double rtm1 = 0.00, rtm2 = 0.00, rtm3 = 0.00, rtm4 = 0.00, rtm5 = 0.00, rtm6 = 0.00, rtm7 = 0.00, rtm8 = 0.00, rtm9 = 0.00, rtm10 = 0.00, rtm11 = 0.00, rtm12 = 0.00, rtm13 = 0.00, rtm14 = 0.00, rtm15 = 0.00, rtm16 = 0.00;
    int nmanguerasurt1, nmanguerasurt3, nmanguerasurt5, nmanguerasurt7, nmanguerasurt9, nmanguerasurt11, nmanguerasurt13;
    String pextra, psuper, pdiesel;
    String pmanguera1surt1, pmanguera2surt1, pmanguera3surt1, pmanguera1surt3, pmanguera2surt3, pmanguera3surt3;
    String pmanguera1surt5, pmanguera2surt5, pmanguera3surt5, pmanguera1surt7, pmanguera2surt7, pmanguera3surt7;
    String pmanguera1surt9, pmanguera2surt9, pmanguera3surt9, pmanguera1surt11, pmanguera2surt11, pmanguera3surt11;
    /////////////////
    public static int mextrasurtidor1, msupersurtidor1, mdieselsurtidor1, mextrasurtidor2, msupersurtidor2, mdieselsurtidor2, mextrasurtidor3, msupersurtidor3, mdieselsurtidor3, mextrasurtidor4, msupersurtidor4, mdieselsurtidor4, mextrasurtidor5, msupersurtidor5, mdieselsurtidor5, mextrasurtidor6, msupersurtidor6, mdieselsurtidor6, mextrasurtidor7, msupersurtidor7, mdieselsurtidor7, mextrasurtidor8, msupersurtidor8, mdieselsurtidor8;
    public static int mextrasurtidor9, msupersurtidor9, mdieselsurtidor9, mextrasurtidor10, msupersurtidor10, mdieselsurtidor10, mextrasurtidor11, msupersurtidor11, mdieselsurtidor11, mextrasurtidor12, msupersurtidor12, mdieselsurtidor12, mextrasurtidor13, msupersurtidor13, mdieselsurtidor13, mextrasurtidor14, msupersurtidor14, mdieselsurtidor14, mextrasurtidor15, msupersurtidor15, mdieselsurtidor15, mextrasurtidor16, msupersurtidor16, mdieselsurtidor16;
    ////////////////
    BufferedReader in = null;
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    private static final int FSL = 127;
    private buscar buscarc;
    String cliente;
    //////////////////
    int lproducto1, lproducto2, lproducto3;
    ///////////////////////
    int lineas;
    String hturno, minutos, segundos, ampm;
    Date fechaHoraActual;
    public String usuario;
    public int ladopreset, productopreset = 0;
    ////////////////////////
    public byte[] comandoturno = new byte[3];
    private byte[] respuestaturno = new byte[2000];
    private int bytesReadturno;
    public int extran, supern, dieseln;
    //////////////////////////////////////////////
    int nmangueraextrasurt1, nmanguerasupersurt1, nmangueradieselsurt1;
    int nmangueraextrasurt2, nmanguerasupersurt2, nmangueradieselsurt2;
    int nmangueraextrasurt3, nmanguerasupersurt3, nmangueradieselsurt3;
    int nmangueraextrasurt4, nmanguerasupersurt4, nmangueradieselsurt4;
    int nmangueraextrasurt5, nmanguerasupersurt5, nmangueradieselsurt5;
    int nmangueraextrasurt6, nmanguerasupersurt6, nmangueradieselsurt6;
    int nmangueraextrasurt7, nmanguerasupersurt7, nmangueradieselsurt7;
    int nmangueraextrasurt8, nmanguerasupersurt8, nmangueradieselsurt8;
    int nmanguerassurt1, nmanguerassurt2, nmanguerassurt3, nmanguerassurt4, nmanguerassurt5, nmanguerassurt6;
    ////////////////////////////
    public byte[] comandolado1disp1c = new byte[3];
    private byte[] respuestalado1disp1c = new byte[20];
    private int bytesReadturnolado1disp1c;
    /////////////////////////////
    public byte[] comandoinfor = new byte[3];
    private byte[] respuestainfor = new byte[2000];
    private int bytesReadinfor;
    /////////////////////////////
    public byte[] comandoconfig = new byte[3];
    private byte[] respuestaconfig = new byte[5000];
    private int bytesReadconfig;
    /////////////////////////////
    public byte[] comando1disp1 = new byte[3];
    public byte[] comando2disp1 = new byte[3];
    public byte[] comandoRTM1disp1 = new byte[6];
    public byte[] comandoRTM2disp1 = new byte[6];
    private byte[] respuesta1disp1 = new byte[256];
    private byte[] respuesta2disp1 = new byte[256];
    private byte[] respuestaRTM1disp1 = new byte[256];
    private byte[] respuestaRTM2disp1 = new byte[256];
    private int bytesRead1disp1, bytesRead2disp1, bytesRTM1disp1, bytesRTM2disp1;
    ///////////////////////////////////////////////////////////////////////////
    public byte[] comando1disp2 = new byte[3];
    public byte[] comando2disp2 = new byte[3];
    public byte[] comandoRTM1disp2 = new byte[6];
    public byte[] comandoRTM2disp2 = new byte[6];
    private byte[] respuesta1disp2 = new byte[256];
    private byte[] respuesta2disp2 = new byte[256];
    private byte[] respuestaRTM1disp2 = new byte[256];
    private byte[] respuestaRTM2disp2 = new byte[256];
    private int bytesRead1disp2, bytesRead2disp2, bytesRTM1disp2, bytesRTM2disp2;
    ///////////////////////////////////////////////////////////////////////////
    public byte[] comando1disp3 = new byte[3];
    public byte[] comando2disp3 = new byte[3];
    public byte[] comandoRTM1disp3 = new byte[6];
    public byte[] comandoRTM2disp3 = new byte[6];
    private byte[] respuesta1disp3 = new byte[256];
    private byte[] respuesta2disp3 = new byte[256];
    private byte[] respuestaRTM1disp3 = new byte[256];
    private byte[] respuestaRTM2disp3 = new byte[256];
    private int bytesRead1disp3, bytesRead2disp3, bytesRTM1disp3, bytesRTM2disp3;
    ////////////////////////////////////////////////////////////////////////////
    public byte[] comandopreset1 = new byte[11];
    private byte[] respuestapreset1 = new byte[30];
    private int bytesReadpreset1;
    /////////////////////////////////////////////////////////////////////
    public byte[] comando1disp4 = new byte[3];
    public byte[] comando2disp4 = new byte[3];
    public byte[] comandoRTM1disp4 = new byte[6];
    public byte[] comandoRTM2disp4 = new byte[6];
    private byte[] respuesta1disp4 = new byte[256];
    private byte[] respuesta2disp4 = new byte[256];
    private byte[] respuestaRTM1disp4 = new byte[256];
    private byte[] respuestaRTM2disp4 = new byte[256];
    private int bytesRead1disp4, bytesRead2disp4, bytesRTM1disp4, bytesRTM2disp4;
    ///////////////////////////////////////
    public byte[] comando1disp5 = new byte[3];
    public byte[] comando2disp5 = new byte[3];
    public byte[] comandoRTM1disp5 = new byte[6];
    public byte[] comandoRTM2disp5 = new byte[6];
    private byte[] respuesta1disp5 = new byte[256];
    private byte[] respuesta2disp5 = new byte[256];
    private byte[] respuestaRTM1disp5 = new byte[256];
    private byte[] respuestaRTM2disp5 = new byte[256];
    private int bytesRead1disp5, bytesRead2disp5, bytesRTM1disp5, bytesRTM2disp5;
    ///////////////////////////////////////
    public byte[] comando1disp6 = new byte[3];
    public byte[] comando2disp6 = new byte[3];
    public byte[] comandoRTM1disp6 = new byte[7];
    public byte[] comandoRTM2disp6 = new byte[7];
    private byte[] respuesta1disp6 = new byte[256];
    private byte[] respuesta2disp6 = new byte[256];
    private byte[] respuestaRTM1disp6 = new byte[256];
    private byte[] respuestaRTM2disp6 = new byte[256];
    private int bytesRead1disp6, bytesRead2disp6, bytesRTM1disp6, bytesRTM2disp6;
    //////////////////////////////////////////////////////////////
    public byte[] comando1disp7 = new byte[3];
    public byte[] comando2disp7 = new byte[3];
    public byte[] comandoRTM1disp7 = new byte[6];
    public byte[] comandoRTM2disp7 = new byte[6];
    private byte[] respuesta1disp7 = new byte[256];
    private byte[] respuesta2disp7 = new byte[256];
    private byte[] respuestaRTM1disp7 = new byte[256];
    private byte[] respuestaRTM2disp7 = new byte[256];
    private int bytesRead1disp7, bytesRead2disp7, bytesRTM1disp7, bytesRTM2disp7;
    ///////////////////////////////////////////////////////////////////
    public byte[] comando1disp8 = new byte[3];
    public byte[] comando2disp8 = new byte[3];
    public byte[] comandoRTM1disp8 = new byte[6];
    public byte[] comandoRTM2disp8 = new byte[6];
    private byte[] respuesta1disp8 = new byte[256];
    private byte[] respuesta2disp8 = new byte[256];
    private byte[] respuestaRTM1disp8 = new byte[256];
    private byte[] respuestaRTM2disp8 = new byte[256];
    private int bytesRead1disp8, bytesRead2disp8, bytesRTM1disp8, bytesRTM2disp8;
    org.apache.log4j.Logger loge = org.apache.log4j.Logger.getLogger(Surtidores.class);

    ////////////////////////////////////////////////////////////////////////////
    int cont1 = 0;
    DataInputStream entrada = null;
    Thread h1;
    int cont = 0;
    int panel = 0;
    String nombrea, fecha;
    int t = (int) (Math.random() * 500 + 200);
    public String usu, contra;
    InetAddress ping;
    //////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////
    Timer timer1dispensador1 = new Timer(3, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado1dispensador1(113);

            } catch (Exception ex) {

                loge.log(Priority.ERROR, getStackTrace(ex));
                timer1dispensador1.restart();
            }

        }
    });
    Timer timerRTM1dispensador1 = new Timer(6, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarRtm1disp1(238);
            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });

    Timer ew = new Timer(21600000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                n = data.getConnection(usuario, contra);

                Statement st_d = n.createStatement();
                ResultSet rid = st_d.executeQuery("select concat('KILL ',id,';')  from information_schema.processlist where COMMAND='Sleep' and time > 500 ");
                while (rid.next()) {

                    System.out.println(rid.getString(1));

                    Statement st1 = n.createStatement();
                    st1.executeUpdate(rid.getString(1));

                }

                n.close();

            } catch (SQLException ex) {

                loge.log(Priority.ERROR, getStackTrace(ex));
            }

        }
    });
    Timer timerRTM2dispensador1 = new Timer(9, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarRtm2disp1(238);
            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timer2dispensador1 = new Timer(12, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado2dispensador1(114);

            } catch (Exception ex) {

                loge.log(Priority.ERROR, getStackTrace(ex));
                timer2dispensador1.restart();
            }

        }
    });
    //////////////////////////////////////////////////////////////////////
    Timer timer1dispensador2 = new Timer(15, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado1dispensador2(115);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer1dispensador2.restart();
            }

        }
    });
    Timer timerRTM1dispensador2 = new Timer(18, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarRtm1disp2(238);
            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timerRTM2dispensador2 = new Timer(21, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarRtm2disp2(238);
            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timer2dispensador2 = new Timer(24, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado2dispensador2(116);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));

                timer2dispensador2.restart();
            }

        }
    });
    /////////////////////////////////////////////////////
    Timer timer1dispensador3 = new Timer(27, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado1dispensador3(117);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer1dispensador3.restart();
            }

        }
    });
    Timer timerRTM1dispensador3 = new Timer(30, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarRtm1disp3(238);
            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timerRTM2dispensador3 = new Timer(33, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarRtm2disp3(238);
            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timer2dispensador3 = new Timer(36, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado2dispensador3(118);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer2dispensador3.restart();
            }

        }
    });
    Timer timerhora = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            h2.setText(hora());

            /*if (hora().equalsIgnoreCase("23:00:00") || hora().equalsIgnoreCase("12:00:00")) {

             enviar_contingencia();

             }*/
            /*if (hora().equalsIgnoreCase("12:00:00") || hora().equalsIgnoreCase("18:00:00") || hora().equalsIgnoreCase("24:00:00")) {

             enviar_web ew = new enviar_web(usu, contra);
             ew.conectar();

             }*/
            fecha();

            //  enviarf();
        }
    });

    public void enviarf() {
        try {

            conf.conectar();
            Statement st_in = conf.coneccion.createStatement();
            ResultSet ri = st_in.executeQuery("SELECT count(idfactura) FROM adv_facturacion.factura where enviado_contable=0 and Estado_factura='Autorizado' and numero_autorizacion > 0;");

            while (ri.next()) {

                System.out.println("Numero de Facturas" + ri.getInt(1));
                if (ri.getInt(1) >= 28) {

                    enviar_facturas_sofi es = new enviar_facturas_sofi(usu, contra);
                    es.setUsuario(usu);
                    es.setContraseña(contra);

                }

            }

            ri.close();
            conf.coneccion.close();
        } catch (ClassNotFoundException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
        } catch (SQLException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
        }

    }
    ///////////////////////////////////////////////////////////////
    Timer timer1dispensador4 = new Timer(39, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado1dispensador4(119);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer1dispensador4.restart();
            }
        }
    });
    Timer timer2dispensador4 = new Timer(42, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado2dispensador4(120);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer2dispensador4.restart();
            }
        }
    });
    Timer timerRTM1dispensador4 = new Timer(45, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                generarRtm1disp4(238);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timerRTM2dispensador4 = new Timer(48, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                generarRtm1disp4(238);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    /////////////////////////////////////////////////////////
    Timer timer1dispensador5 = new Timer(39, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado1dispensador5(121);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer1dispensador5.restart();
            }
        }
    });
    Timer timer2dispensador5 = new Timer(42, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado2dispensador5(122);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer2dispensador5.restart();
            }
        }
    });
    Timer timerRTM1dispensador5 = new Timer(45, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                generarRtm1disp5(238);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timerRTM2dispensador5 = new Timer(48, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                generarRtm1disp5(238);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timer1dispensador6 = new Timer(40, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado1dispensador6(123);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer1dispensador6.restart();
            }
        }
    });
    Timer timer2dispensador6 = new Timer(44, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado2dispensador6(124);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer2dispensador6.restart();
            }
        }
    });
    Timer timer1dispensador7 = new Timer(70, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado1dispensador7(125);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer1dispensador7.restart();
            }
        }
    });
    Timer timer2dispensador7 = new Timer(38, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado2dispensador7(126);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer2dispensador7.restart();
            }
        }
    });
    Timer timerRTM1dispensador6 = new Timer(48, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                generarRtm1disp6(238);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timerRTM2dispensador6 = new Timer(32, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                generarRtm2disp6(238);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timerRTM2dispensador7 = new Timer(32, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                generarRtm2disp7(238);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timerRTM1dispensador7 = new Timer(32, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                generarRtm1disp7(238);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    //////////////////////////////////////////////////////////
    Timer timer1dispensador8 = new Timer(14, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado1dispensador8(127);

            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
                timer1dispensador8.restart();
            }

        }
    });
    Timer timerRTM1dispensador8 = new Timer(16, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarRtm1disp8(238);
            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timerRTM2dispensador8 = new Timer(18, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarRtm2disp8(238);
            } catch (Exception ex) {
                loge.log(Priority.ERROR, getStackTrace(ex));
            }
        }
    });
    Timer timer2dispensador8 = new Timer(27, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                generarlado2dispensador8(112);

            } catch (Exception ex) {
                timer2dispensador8.restart();
                loge.log(Priority.ERROR, getStackTrace(ex));
            }

        }
    });
    //////////////////////////////////////////////////////////
    private String Impresora_Boleta;
    servidor servidor;
    conexion_facturacion conf;
    Connection n = null;
    DataSource data;
    Conecciones he;

    public Surtidores(String usuarios, String cont) {

        try {

            this.servidor = new servidor();

            PropertyConfigurator.configure("log4j.properties");

            Logger.getLogger(conexion_facturacion.class.getName()).log(Level.SEVERE, null, "prueba");
            servidores();
            servidor();
            initComponents();

            he = new Conecciones(usuario, contra);

            timerhora.start();
            pantalla.setEditable(false);
            this.usu = usuarios;
            this.contra = cont;

            this.setVisible(true);
            this.setLocation(0, 0);
            //this.setUI(null);
           
            configuracion();

            this.setResizable(true);
            //this.setClosable(true);
            this.pack();

            data = he.ConectarMysql();

            ew.start();

            switch (totals / 2) {
                case 1:

                    timer1dispensador1.start();
                    timer2dispensador1.start();
                    break;
                case 2:

                    timer1dispensador1.start();
                    timer2dispensador1.start();
                    timer1dispensador2.start();
                    timer2dispensador2.start();
                    break;
                case 3:
                    timer1dispensador1.start();
                    timer2dispensador1.start();
                    timer1dispensador2.start();
                    timer2dispensador2.start();
                    timer1dispensador3.start();
                    timer2dispensador3.start();
                    break;
                case 4:
                    timer1dispensador1.start();
                    timer2dispensador1.start();
                    timer1dispensador2.start();
                    timer2dispensador2.start();
                    timer1dispensador3.start();
                    timer2dispensador3.start();
                    timer1dispensador4.start();
                    timer2dispensador4.start();
                    break;
                case 5:
                    timer1dispensador1.start();
                    timer2dispensador1.start();
                    timer1dispensador2.start();
                    timer2dispensador2.start();
                    timer1dispensador3.start();
                    timer2dispensador3.start();
                    timer1dispensador4.start();
                    timer2dispensador4.start();
                    timer1dispensador5.start();
                    timer2dispensador5.start();
                case 6:
                    timer1dispensador1.start();
                    timer2dispensador1.start();
                    timer1dispensador2.start();
                    timer2dispensador2.start();
                    timer1dispensador3.start();
                    timer2dispensador3.start();
                    timer1dispensador4.start();
                    timer2dispensador4.start();
                    timer1dispensador5.start();
                    timer2dispensador5.start();
                    timer1dispensador6.start();
                    timer2dispensador6.start();
                case 7:
                    timer1dispensador1.start();
                    timer2dispensador1.start();
                    timer1dispensador2.start();
                    timer2dispensador2.start();
                    timer1dispensador3.start();
                    timer2dispensador3.start();
                    timer1dispensador4.start();
                    timer2dispensador4.start();
                    timer1dispensador5.start();
                    timer2dispensador5.start();
                    timer1dispensador6.start();
                    timer2dispensador6.start();
                    timer1dispensador7.start();
                    timer2dispensador7.start();

                case 8:
                    timer1dispensador1.start();
                    timer2dispensador1.start();
                    timer1dispensador2.start();
                    timer2dispensador2.start();
                    timer1dispensador3.start();
                    timer2dispensador3.start();
                    timer1dispensador4.start();
                    timer2dispensador4.start();
                    timer1dispensador5.start();
                    timer2dispensador5.start();
                    timer1dispensador6.start();
                    timer2dispensador6.start();
                    timer1dispensador7.start();
                    timer2dispensador7.start();
                    timer1dispensador8.start();
                    timer2dispensador8.start();

                    break;

            }

        } catch (Exception ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
        }

    }

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        nsurtidores = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        f = new javax.swing.JLabel();
        h2 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        pantalla = new javax.swing.JTextField();
        ladop = new javax.swing.JToggleButton();
        ladop2 = new javax.swing.JToggleButton();
        bextra = new javax.swing.JToggleButton();
        bsuper = new javax.swing.JToggleButton();
        bdiesel = new javax.swing.JToggleButton();
        teclado = new javax.swing.JPanel();
        uno = new javax.swing.JButton();
        dos = new javax.swing.JButton();
        tres = new javax.swing.JButton();
        cuatro = new javax.swing.JButton();
        cinco = new javax.swing.JButton();
        seis = new javax.swing.JButton();
        siete = new javax.swing.JButton();
        ocho = new javax.swing.JButton();
        nueve = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        cero = new javax.swing.JButton();
        punto = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        panelsrt4 = new javax.swing.JTabbedPane();
        panelsurt1 = new javax.swing.JPanel();
        surtidor1 = new javax.swing.JPanel();
        lado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lado1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        estado1disp1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        estado2disp1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        gaso1disp1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        gaso2disp1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        monto1disp1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        monto2disp1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        volumen1disp1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        volumen2disp1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        id1disp1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        id2disp1 = new javax.swing.JTextField();
        panelsurt2 = new javax.swing.JPanel();
        surtidor2 = new javax.swing.JPanel();
        lado6 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        lado7 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        estado1disp2 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        estado2disp2 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        gaso1disp2 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        gaso2disp2 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        monto1disp2 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        monto2disp2 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        volumen1disp2 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        volumen2disp2 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        id1disp2 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        id2disp2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        montoextrasurtidor3 = new javax.swing.JTextField();
        montoextrasurtidor4 = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        volumenextrasurtidor3 = new javax.swing.JTextField();
        volumenextrasurtidor4 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        montosupersurtidor3 = new javax.swing.JTextField();
        montosupersurtidor4 = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        volumensupersurtidor3 = new javax.swing.JTextField();
        volumensupersurtidor4 = new javax.swing.JTextField();
        montoturnosurt2 = new javax.swing.JLabel();
        volumenturnosurt2 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        montodieselsurtidor3 = new javax.swing.JTextField();
        montodieselsurtidor4 = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        volumendieselsurtidor3 = new javax.swing.JTextField();
        volumendieselsurtidor4 = new javax.swing.JTextField();
        panelsurt3 = new javax.swing.JPanel();
        surtidor3 = new javax.swing.JPanel();
        lado8 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        lado9 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        estado1disp3 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        estado2disp3 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        gaso1disp3 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        gaso2disp3 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        monto1disp3 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        monto2disp3 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        volumen1disp3 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        volumen2disp3 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        id1disp3 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        id2disp3 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        montoextrasurtidor5 = new javax.swing.JTextField();
        montoextrasurtidor6 = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        volumenextrasurtidor5 = new javax.swing.JTextField();
        volumenextrasurtidor6 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        montodieselsurtidor5 = new javax.swing.JTextField();
        montodieselsurtidor6 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        volumendieselsurtidor5 = new javax.swing.JTextField();
        volumendieselsurtidor6 = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        montoturnosurt3 = new javax.swing.JLabel();
        volumenturnosurt3 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        montosupersurtidor5 = new javax.swing.JTextField();
        montosupersurtidor6 = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        volumensupersurtidor5 = new javax.swing.JTextField();
        volumensupersurtidor6 = new javax.swing.JTextField();
        panelsurt4 = new javax.swing.JPanel();
        surtidor4 = new javax.swing.JPanel();
        lado10 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        lado11 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        estado1disp4 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        estado2disp4 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        gaso1disp4 = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        gaso2disp4 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        monto1disp4 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        monto2disp4 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        volumen1disp4 = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        volumen2disp4 = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        id1disp4 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        id2disp4 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        montoextrasurtidor7 = new javax.swing.JTextField();
        montoextrasurtidor8 = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        volumenextrasurtidor7 = new javax.swing.JTextField();
        volumenextrasurtidor8 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        montodieselsurtidor7 = new javax.swing.JTextField();
        montodieselsurtidor8 = new javax.swing.JTextField();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        volumendieselsurtidor7 = new javax.swing.JTextField();
        volumendieselsurtidor8 = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        montoturnosurt4 = new javax.swing.JLabel();
        volumenturnosurt4 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        montosupersurtidor7 = new javax.swing.JTextField();
        montosupersurtidor8 = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        volumensupersurtidor7 = new javax.swing.JTextField();
        volumensupersurtidor8 = new javax.swing.JTextField();
        panelsurt5 = new javax.swing.JPanel();
        panelsurt8 = new javax.swing.JPanel();
        surtidor5 = new javax.swing.JPanel();
        lado12 = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        lado13 = new javax.swing.JTextField();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        estado1disp5 = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        estado2disp5 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        gaso1disp5 = new javax.swing.JTextField();
        jLabel136 = new javax.swing.JLabel();
        gaso2disp5 = new javax.swing.JTextField();
        jLabel137 = new javax.swing.JLabel();
        monto1disp5 = new javax.swing.JTextField();
        jLabel138 = new javax.swing.JLabel();
        monto2disp5 = new javax.swing.JTextField();
        jLabel139 = new javax.swing.JLabel();
        volumen1disp5 = new javax.swing.JTextField();
        jLabel140 = new javax.swing.JLabel();
        volumen2disp5 = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        id1disp5 = new javax.swing.JTextField();
        jLabel142 = new javax.swing.JLabel();
        id2disp5 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        montoextrasurtidor9 = new javax.swing.JTextField();
        montoextrasurtidor10 = new javax.swing.JTextField();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        volumenextrasurtidor9 = new javax.swing.JTextField();
        volumenextrasurtidor10 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        montodieselsurtidor9 = new javax.swing.JTextField();
        montodieselsurtidor10 = new javax.swing.JTextField();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        volumendieselsurtidor9 = new javax.swing.JTextField();
        volumendieselsurtidor10 = new javax.swing.JTextField();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        montoturnosurt5 = new javax.swing.JLabel();
        volumenturnosurt5 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        montosupersurtidor9 = new javax.swing.JTextField();
        montosupersurtidor10 = new javax.swing.JTextField();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        volumensupersurtidor9 = new javax.swing.JTextField();
        volumensupersurtidor10 = new javax.swing.JTextField();
        panelsurt6 = new javax.swing.JPanel();
        panelsurt9 = new javax.swing.JPanel();
        surtidor6 = new javax.swing.JPanel();
        lado14 = new javax.swing.JTextField();
        jLabel157 = new javax.swing.JLabel();
        lado15 = new javax.swing.JTextField();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        estado1disp6 = new javax.swing.JTextField();
        jLabel160 = new javax.swing.JLabel();
        estado2disp6 = new javax.swing.JTextField();
        jLabel161 = new javax.swing.JLabel();
        gaso1disp6 = new javax.swing.JTextField();
        jLabel162 = new javax.swing.JLabel();
        gaso2disp6 = new javax.swing.JTextField();
        jLabel163 = new javax.swing.JLabel();
        monto1disp6 = new javax.swing.JTextField();
        jLabel164 = new javax.swing.JLabel();
        monto2disp6 = new javax.swing.JTextField();
        jLabel165 = new javax.swing.JLabel();
        volumen1disp6 = new javax.swing.JTextField();
        jLabel166 = new javax.swing.JLabel();
        volumen2disp6 = new javax.swing.JTextField();
        jLabel167 = new javax.swing.JLabel();
        id1disp6 = new javax.swing.JTextField();
        jLabel168 = new javax.swing.JLabel();
        id2disp6 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        montoextrasurtidor11 = new javax.swing.JTextField();
        montoextrasurtidor12 = new javax.swing.JTextField();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        volumenextrasurtidor11 = new javax.swing.JTextField();
        volumenextrasurtidor12 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        montodieselsurtidor11 = new javax.swing.JTextField();
        montodieselsurtidor12 = new javax.swing.JTextField();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        volumendieselsurtidor11 = new javax.swing.JTextField();
        volumendieselsurtidor12 = new javax.swing.JTextField();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        montoturnosurt6 = new javax.swing.JLabel();
        volumenturnosurt6 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        montosupersurtidor11 = new javax.swing.JTextField();
        montosupersurtidor12 = new javax.swing.JTextField();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        volumensupersurtidor11 = new javax.swing.JTextField();
        volumensupersurtidor12 = new javax.swing.JTextField();
        panelsurt7 = new javax.swing.JPanel();
        panelsurt10 = new javax.swing.JPanel();
        surtidor7 = new javax.swing.JPanel();
        lado16 = new javax.swing.JTextField();
        jLabel183 = new javax.swing.JLabel();
        lado17 = new javax.swing.JTextField();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        estado1disp7 = new javax.swing.JTextField();
        jLabel186 = new javax.swing.JLabel();
        estado2disp7 = new javax.swing.JTextField();
        jLabel187 = new javax.swing.JLabel();
        gaso1disp7 = new javax.swing.JTextField();
        jLabel188 = new javax.swing.JLabel();
        gaso2disp7 = new javax.swing.JTextField();
        jLabel189 = new javax.swing.JLabel();
        monto1disp7 = new javax.swing.JTextField();
        jLabel190 = new javax.swing.JLabel();
        monto2disp7 = new javax.swing.JTextField();
        jLabel191 = new javax.swing.JLabel();
        volumen1disp7 = new javax.swing.JTextField();
        jLabel192 = new javax.swing.JLabel();
        volumen2disp7 = new javax.swing.JTextField();
        jLabel193 = new javax.swing.JLabel();
        id1disp7 = new javax.swing.JTextField();
        jLabel194 = new javax.swing.JLabel();
        id2disp7 = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jLabel195 = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        montoextrasurtidor13 = new javax.swing.JTextField();
        montoextrasurtidor14 = new javax.swing.JTextField();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        volumenextrasurtidor13 = new javax.swing.JTextField();
        volumenextrasurtidor14 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jLabel199 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        montodieselsurtidor13 = new javax.swing.JTextField();
        montodieselsurtidor14 = new javax.swing.JTextField();
        jLabel201 = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        volumendieselsurtidor13 = new javax.swing.JTextField();
        volumendieselsurtidor14 = new javax.swing.JTextField();
        jLabel203 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        montoturnosurt7 = new javax.swing.JLabel();
        volumenturnosurt7 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        montosupersurtidor13 = new javax.swing.JTextField();
        montosupersurtidor14 = new javax.swing.JTextField();
        jLabel207 = new javax.swing.JLabel();
        jLabel208 = new javax.swing.JLabel();
        volumensupersurtidor13 = new javax.swing.JTextField();
        volumensupersurtidor14 = new javax.swing.JTextField();
        panelsurt11 = new javax.swing.JPanel();
        panelsurt12 = new javax.swing.JPanel();
        surtidor8 = new javax.swing.JPanel();
        lado18 = new javax.swing.JTextField();
        jLabel209 = new javax.swing.JLabel();
        lado19 = new javax.swing.JTextField();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        estado1disp8 = new javax.swing.JTextField();
        jLabel212 = new javax.swing.JLabel();
        estado2disp8 = new javax.swing.JTextField();
        jLabel213 = new javax.swing.JLabel();
        gaso1disp8 = new javax.swing.JTextField();
        jLabel214 = new javax.swing.JLabel();
        gaso2disp8 = new javax.swing.JTextField();
        jLabel215 = new javax.swing.JLabel();
        monto1disp8 = new javax.swing.JTextField();
        jLabel216 = new javax.swing.JLabel();
        monto2disp8 = new javax.swing.JTextField();
        jLabel217 = new javax.swing.JLabel();
        volumen1disp8 = new javax.swing.JTextField();
        jLabel218 = new javax.swing.JLabel();
        volumen2disp8 = new javax.swing.JTextField();
        jLabel219 = new javax.swing.JLabel();
        id1disp8 = new javax.swing.JTextField();
        jLabel220 = new javax.swing.JLabel();
        id2disp8 = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jLabel221 = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        montoextrasurtidor15 = new javax.swing.JTextField();
        montoextrasurtidor16 = new javax.swing.JTextField();
        jLabel223 = new javax.swing.JLabel();
        jLabel224 = new javax.swing.JLabel();
        volumenextrasurtidor15 = new javax.swing.JTextField();
        volumenextrasurtidor16 = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel225 = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        montodieselsurtidor15 = new javax.swing.JTextField();
        montodieselsurtidor16 = new javax.swing.JTextField();
        jLabel227 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        volumendieselsurtidor15 = new javax.swing.JTextField();
        volumendieselsurtidor16 = new javax.swing.JTextField();
        jLabel229 = new javax.swing.JLabel();
        jLabel230 = new javax.swing.JLabel();
        montoturnosurt8 = new javax.swing.JLabel();
        volumenturnosurt8 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel231 = new javax.swing.JLabel();
        jLabel232 = new javax.swing.JLabel();
        montosupersurtidor15 = new javax.swing.JTextField();
        montosupersurtidor16 = new javax.swing.JTextField();
        jLabel233 = new javax.swing.JLabel();
        jLabel234 = new javax.swing.JLabel();
        volumensupersurtidor15 = new javax.swing.JTextField();
        volumensupersurtidor16 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mensajesR = new javax.swing.JTextArea();

        setTitle("Adv-Gp 2.0 Servidor");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setText("Emergencia General");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(837, 376, 183, -1));

        jButton4.setText("Cerrar Turno");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(837, 405, -1, -1));

        nsurtidores.setEditable(false);
        nsurtidores.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(nsurtidores, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 390, 54, 39));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Numero de Surtidores ");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 370, -1, 14));

        f.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel2.add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(819, 468, 221, 43));

        h2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel2.add(h2, new org.netbeans.lib.awtextra.AbsoluteConstraints(819, 529, 221, 43));

        jButton10.setText("Enviar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1088, 164, 72, -1));
        jPanel2.add(pantalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 345, 391, -1));

        ladop.setText("Lado1");
        ladop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ladopActionPerformed(evt);
            }
        });
        jPanel2.add(ladop, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, -1, -1));

        ladop2.setText("Lado2");
        ladop2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ladop2ActionPerformed(evt);
            }
        });
        jPanel2.add(ladop2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, -1, -1));

        bextra.setText("Extra");
        bextra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bextraActionPerformed(evt);
            }
        });
        jPanel2.add(bextra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1088, 55, 72, -1));

        bsuper.setText("Super");
        bsuper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsuperActionPerformed(evt);
            }
        });
        jPanel2.add(bsuper, new org.netbeans.lib.awtextra.AbsoluteConstraints(1088, 89, 72, -1));

        bdiesel.setText("Diesel");
        bdiesel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdieselActionPerformed(evt);
            }
        });
        jPanel2.add(bdiesel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1088, 130, 72, -1));

        teclado.setLayout(new java.awt.GridLayout(4, 0, 3, 0));

        uno.setText("1");
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoActionPerformed(evt);
            }
        });
        teclado.add(uno);

        dos.setText("2");
        dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosActionPerformed(evt);
            }
        });
        teclado.add(dos);

        tres.setText("3");
        tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresActionPerformed(evt);
            }
        });
        teclado.add(tres);

        cuatro.setText("4");
        cuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuatroActionPerformed(evt);
            }
        });
        teclado.add(cuatro);

        cinco.setText("5");
        cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cincoActionPerformed(evt);
            }
        });
        teclado.add(cinco);

        seis.setText("6");
        seis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seisActionPerformed(evt);
            }
        });
        teclado.add(seis);

        siete.setText("7");
        siete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sieteActionPerformed(evt);
            }
        });
        teclado.add(siete);

        ocho.setText("8");
        ocho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ochoActionPerformed(evt);
            }
        });
        teclado.add(ocho);

        nueve.setText("9");
        nueve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nueveActionPerformed(evt);
            }
        });
        teclado.add(nueve);

        borrar.setText("Borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        teclado.add(borrar);

        cero.setText("0");
        cero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ceroActionPerformed(evt);
            }
        });
        teclado.add(cero);

        punto.setText(".");
        punto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puntoActionPerformed(evt);
            }
        });
        teclado.add(punto);

        jPanel2.add(teclado, new org.netbeans.lib.awtextra.AbsoluteConstraints(843, 51, 222, 288));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("Preset");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(922, 11, 75, -1));

        panelsrt4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        panelsrt4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelsrt4ComponentShown(evt);
            }
        });

        panelsurt1.setBackground(new java.awt.Color(255, 255, 255));
        panelsurt1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelsurt1ComponentShown(evt);
            }
        });

        surtidor1.setBackground(new java.awt.Color(0, 0, 0));
        surtidor1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor1.setLayout(new java.awt.GridLayout(6, 4));

        lado.setEditable(false);
        lado.setBackground(new java.awt.Color(0, 0, 0));
        lado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado.setForeground(new java.awt.Color(255, 255, 255));
        lado.setText("Surtidor 1");
        lado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ladoActionPerformed(evt);
            }
        });
        surtidor1.add(lado);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Lado 1");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor1.add(jLabel1);

        lado1.setEditable(false);
        lado1.setBackground(new java.awt.Color(0, 0, 0));
        lado1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado1.setForeground(new java.awt.Color(255, 255, 255));
        lado1.setText("Surtidor 1");
        lado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado1ActionPerformed(evt);
            }
        });
        surtidor1.add(lado1);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Lado 2");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor1.add(jLabel4);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Estado");
        surtidor1.add(jLabel3);

        estado1disp1.setEditable(false);
        estado1disp1.setBackground(new java.awt.Color(255, 204, 51));
        estado1disp1.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor1.add(estado1disp1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Estado");
        surtidor1.add(jLabel5);

        estado2disp1.setEditable(false);
        estado2disp1.setBackground(new java.awt.Color(255, 204, 51));
        estado2disp1.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor1.add(estado2disp1);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Producto");
        surtidor1.add(jLabel2);

        gaso1disp1.setEditable(false);
        gaso1disp1.setBackground(new java.awt.Color(255, 204, 51));
        gaso1disp1.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor1.add(gaso1disp1);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Producto");
        surtidor1.add(jLabel6);

        gaso2disp1.setEditable(false);
        gaso2disp1.setBackground(new java.awt.Color(255, 204, 51));
        gaso2disp1.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor1.add(gaso2disp1);

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Monto");
        surtidor1.add(jLabel10);

        monto1disp1.setEditable(false);
        monto1disp1.setBackground(new java.awt.Color(255, 204, 51));
        monto1disp1.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor1.add(monto1disp1);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Monto");
        surtidor1.add(jLabel8);

        monto2disp1.setEditable(false);
        monto2disp1.setBackground(new java.awt.Color(255, 204, 51));
        monto2disp1.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor1.add(monto2disp1);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Volumen");
        surtidor1.add(jLabel7);

        volumen1disp1.setEditable(false);
        volumen1disp1.setBackground(new java.awt.Color(255, 204, 51));
        volumen1disp1.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor1.add(volumen1disp1);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Volumen");
        surtidor1.add(jLabel9);

        volumen2disp1.setEditable(false);
        volumen2disp1.setBackground(new java.awt.Color(255, 204, 51));
        volumen2disp1.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor1.add(volumen2disp1);

        jLabel33.setBackground(new java.awt.Color(0, 0, 0));
        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("IDV");
        surtidor1.add(jLabel33);

        id1disp1.setEditable(false);
        id1disp1.setBackground(new java.awt.Color(255, 204, 51));
        id1disp1.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor1.add(id1disp1);

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("IDV");
        surtidor1.add(jLabel34);

        id2disp1.setEditable(false);
        id2disp1.setBackground(new java.awt.Color(255, 204, 51));
        id2disp1.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor1.add(id2disp1);

        javax.swing.GroupLayout panelsurt1Layout = new javax.swing.GroupLayout(panelsurt1);
        panelsurt1.setLayout(panelsurt1Layout);
        panelsurt1Layout.setHorizontalGroup(
            panelsurt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(surtidor1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );
        panelsurt1Layout.setVerticalGroup(
            panelsurt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(surtidor1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        panelsrt4.addTab("Surtidor 1", panelsurt1);

        panelsurt2.setBackground(new java.awt.Color(255, 255, 255));
        panelsurt2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelsurt2ComponentShown(evt);
            }
        });

        surtidor2.setBackground(new java.awt.Color(0, 0, 0));
        surtidor2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor2.setLayout(new java.awt.GridLayout(6, 4));

        lado6.setEditable(false);
        lado6.setBackground(new java.awt.Color(0, 0, 0));
        lado6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado6.setForeground(new java.awt.Color(255, 255, 255));
        lado6.setText("Surtidor 2");
        lado6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado6ActionPerformed(evt);
            }
        });
        surtidor2.add(lado6);

        jLabel40.setBackground(new java.awt.Color(0, 0, 0));
        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Lado 1");
        jLabel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor2.add(jLabel40);

        lado7.setEditable(false);
        lado7.setBackground(new java.awt.Color(0, 0, 0));
        lado7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado7.setForeground(new java.awt.Color(255, 255, 255));
        lado7.setText("Surtidor 2");
        lado7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado7ActionPerformed(evt);
            }
        });
        surtidor2.add(lado7);

        jLabel63.setBackground(new java.awt.Color(0, 0, 0));
        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Lado 2");
        jLabel63.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor2.add(jLabel63);

        jLabel41.setBackground(new java.awt.Color(0, 0, 0));
        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Estado");
        surtidor2.add(jLabel41);

        estado1disp2.setEditable(false);
        estado1disp2.setBackground(new java.awt.Color(255, 204, 0));
        estado1disp2.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor2.add(estado1disp2);

        jLabel42.setBackground(new java.awt.Color(0, 0, 0));
        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Estado");
        surtidor2.add(jLabel42);

        estado2disp2.setEditable(false);
        estado2disp2.setBackground(new java.awt.Color(255, 204, 0));
        estado2disp2.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor2.add(estado2disp2);

        jLabel43.setBackground(new java.awt.Color(0, 0, 0));
        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Producto");
        surtidor2.add(jLabel43);

        gaso1disp2.setEditable(false);
        gaso1disp2.setBackground(new java.awt.Color(255, 204, 0));
        gaso1disp2.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor2.add(gaso1disp2);

        jLabel44.setBackground(new java.awt.Color(0, 0, 0));
        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Producto");
        surtidor2.add(jLabel44);

        gaso2disp2.setEditable(false);
        gaso2disp2.setBackground(new java.awt.Color(255, 204, 0));
        gaso2disp2.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor2.add(gaso2disp2);

        jLabel45.setBackground(new java.awt.Color(0, 0, 0));
        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Monto");
        surtidor2.add(jLabel45);

        monto1disp2.setEditable(false);
        monto1disp2.setBackground(new java.awt.Color(255, 204, 0));
        monto1disp2.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor2.add(monto1disp2);

        jLabel46.setBackground(new java.awt.Color(0, 0, 0));
        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Monto");
        surtidor2.add(jLabel46);

        monto2disp2.setEditable(false);
        monto2disp2.setBackground(new java.awt.Color(255, 204, 0));
        monto2disp2.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor2.add(monto2disp2);

        jLabel47.setBackground(new java.awt.Color(0, 0, 0));
        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Volumen");
        surtidor2.add(jLabel47);

        volumen1disp2.setEditable(false);
        volumen1disp2.setBackground(new java.awt.Color(255, 204, 0));
        volumen1disp2.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor2.add(volumen1disp2);

        jLabel48.setBackground(new java.awt.Color(0, 0, 0));
        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Volumen");
        surtidor2.add(jLabel48);

        volumen2disp2.setEditable(false);
        volumen2disp2.setBackground(new java.awt.Color(255, 204, 0));
        volumen2disp2.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor2.add(volumen2disp2);

        jLabel49.setBackground(new java.awt.Color(0, 0, 0));
        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("IDV");
        surtidor2.add(jLabel49);

        id1disp2.setEditable(false);
        id1disp2.setBackground(new java.awt.Color(255, 204, 0));
        id1disp2.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor2.add(id1disp2);

        jLabel50.setBackground(new java.awt.Color(0, 0, 0));
        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("IDV");
        surtidor2.add(jLabel50);

        id2disp2.setEditable(false);
        id2disp2.setBackground(new java.awt.Color(255, 204, 0));
        id2disp2.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor2.add(id2disp2);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new java.awt.GridLayout(4, 2));

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel83.setText("Total Monto Extra  Lado 1");
        jLabel83.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(jLabel83);

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel84.setText("Total Monto  Extra Lado 1");
        jLabel84.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(jLabel84);

        montoextrasurtidor3.setEditable(false);
        montoextrasurtidor3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(montoextrasurtidor3);

        montoextrasurtidor4.setEditable(false);
        montoextrasurtidor4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(montoextrasurtidor4);

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel85.setText("Total Volumen Extra  Lado 2");
        jLabel85.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(jLabel85);

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel86.setText("Total Volumen  Extra Lado 2");
        jLabel86.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(jLabel86);

        volumenextrasurtidor3.setEditable(false);
        volumenextrasurtidor3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(volumenextrasurtidor3);

        volumenextrasurtidor4.setEditable(false);
        volumenextrasurtidor4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.add(volumenextrasurtidor4);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new java.awt.GridLayout(4, 2));

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel87.setText("Total Monto Super  Lado 1");
        jLabel87.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(jLabel87);

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel88.setText("Total Monto  Super Lado 1");
        jLabel88.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(jLabel88);

        montosupersurtidor3.setEditable(false);
        montosupersurtidor3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(montosupersurtidor3);

        montosupersurtidor4.setEditable(false);
        montosupersurtidor4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(montosupersurtidor4);

        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel90.setText("Total Volumen  Super Lado 2");
        jLabel90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(jLabel90);

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel89.setText("Total Volumen Super  Lado 2");
        jLabel89.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(jLabel89);

        volumensupersurtidor3.setEditable(false);
        volumensupersurtidor3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(volumensupersurtidor3);

        volumensupersurtidor4.setEditable(false);
        volumensupersurtidor4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.add(volumensupersurtidor4);

        montoturnosurt2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        montoturnosurt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        volumenturnosurt2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        volumenturnosurt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel64.setText("Total Volumen Surtidor2");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("Total Monto Surtidor 2");

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setLayout(new java.awt.GridLayout(4, 2));

        jLabel115.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel115.setText("Total Monto Diesel  Lado 1");
        jLabel115.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.add(jLabel115);

        jLabel116.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel116.setText("Total Monto  Diesel Lado 1");
        jLabel116.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.add(jLabel116);

        montodieselsurtidor3.setEditable(false);
        montodieselsurtidor3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        montodieselsurtidor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montodieselsurtidor3ActionPerformed(evt);
            }
        });
        jPanel13.add(montodieselsurtidor3);

        montodieselsurtidor4.setEditable(false);
        montodieselsurtidor4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.add(montodieselsurtidor4);

        jLabel117.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel117.setText("Total Volumen  Diesel Lado 2");
        jLabel117.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.add(jLabel117);

        jLabel118.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel118.setText("Total Volumen Diesel  Lado 2");
        jLabel118.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.add(jLabel118);

        volumendieselsurtidor3.setEditable(false);
        volumendieselsurtidor3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        volumendieselsurtidor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volumendieselsurtidor3ActionPerformed(evt);
            }
        });
        jPanel13.add(volumendieselsurtidor3);

        volumendieselsurtidor4.setEditable(false);
        volumendieselsurtidor4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.add(volumendieselsurtidor4);

        javax.swing.GroupLayout panelsurt2Layout = new javax.swing.GroupLayout(panelsurt2);
        panelsurt2.setLayout(panelsurt2Layout);
        panelsurt2Layout.setHorizontalGroup(
            panelsurt2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt2Layout.createSequentialGroup()
                .addGroup(panelsurt2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelsurt2Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(panelsurt2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelsurt2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(volumenturnosurt2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(montoturnosurt2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelsurt2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelsurt2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))))
                .addContainerGap(129, Short.MAX_VALUE))
            .addComponent(surtidor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelsurt2Layout.setVerticalGroup(
            panelsurt2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt2Layout.createSequentialGroup()
                .addComponent(surtidor2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsurt2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(montoturnosurt2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsurt2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumenturnosurt2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelsrt4.addTab("Surtidor 2", panelsurt2);

        panelsurt3.setBackground(new java.awt.Color(255, 255, 255));
        panelsurt3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelsurt3ComponentShown(evt);
            }
        });

        surtidor3.setBackground(new java.awt.Color(0, 0, 0));
        surtidor3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor3.setLayout(new java.awt.GridLayout(6, 4));

        lado8.setEditable(false);
        lado8.setBackground(new java.awt.Color(0, 0, 0));
        lado8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado8.setForeground(new java.awt.Color(255, 255, 255));
        lado8.setText("Surtidor 3");
        lado8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado8ActionPerformed(evt);
            }
        });
        surtidor3.add(lado8);

        jLabel51.setBackground(new java.awt.Color(0, 0, 0));
        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Lado 1");
        jLabel51.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor3.add(jLabel51);

        lado9.setEditable(false);
        lado9.setBackground(new java.awt.Color(0, 0, 0));
        lado9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado9.setForeground(new java.awt.Color(255, 255, 255));
        lado9.setText("Surtidor 3");
        lado9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado9ActionPerformed(evt);
            }
        });
        surtidor3.add(lado9);

        jLabel52.setBackground(new java.awt.Color(0, 0, 0));
        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Lado 2");
        jLabel52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor3.add(jLabel52);

        jLabel53.setBackground(new java.awt.Color(0, 0, 0));
        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Estado");
        surtidor3.add(jLabel53);

        estado1disp3.setEditable(false);
        estado1disp3.setBackground(new java.awt.Color(255, 204, 0));
        estado1disp3.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor3.add(estado1disp3);

        jLabel54.setBackground(new java.awt.Color(0, 0, 0));
        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Estado");
        surtidor3.add(jLabel54);

        estado2disp3.setEditable(false);
        estado2disp3.setBackground(new java.awt.Color(255, 204, 0));
        estado2disp3.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor3.add(estado2disp3);

        jLabel55.setBackground(new java.awt.Color(0, 0, 0));
        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Producto");
        surtidor3.add(jLabel55);

        gaso1disp3.setEditable(false);
        gaso1disp3.setBackground(new java.awt.Color(255, 204, 0));
        gaso1disp3.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor3.add(gaso1disp3);

        jLabel56.setBackground(new java.awt.Color(0, 0, 0));
        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Producto");
        surtidor3.add(jLabel56);

        gaso2disp3.setEditable(false);
        gaso2disp3.setBackground(new java.awt.Color(255, 204, 0));
        gaso2disp3.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor3.add(gaso2disp3);

        jLabel57.setBackground(new java.awt.Color(0, 0, 0));
        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Monto");
        surtidor3.add(jLabel57);

        monto1disp3.setEditable(false);
        monto1disp3.setBackground(new java.awt.Color(255, 204, 0));
        monto1disp3.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor3.add(monto1disp3);

        jLabel58.setBackground(new java.awt.Color(0, 0, 0));
        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Monto");
        surtidor3.add(jLabel58);

        monto2disp3.setEditable(false);
        monto2disp3.setBackground(new java.awt.Color(255, 204, 0));
        monto2disp3.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor3.add(monto2disp3);

        jLabel59.setBackground(new java.awt.Color(0, 0, 0));
        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Volumen");
        surtidor3.add(jLabel59);

        volumen1disp3.setEditable(false);
        volumen1disp3.setBackground(new java.awt.Color(255, 204, 0));
        volumen1disp3.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor3.add(volumen1disp3);

        jLabel60.setBackground(new java.awt.Color(0, 0, 0));
        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Volumen");
        surtidor3.add(jLabel60);

        volumen2disp3.setEditable(false);
        volumen2disp3.setBackground(new java.awt.Color(255, 204, 0));
        volumen2disp3.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor3.add(volumen2disp3);

        jLabel61.setBackground(new java.awt.Color(0, 0, 0));
        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("IDV");
        surtidor3.add(jLabel61);

        id1disp3.setEditable(false);
        id1disp3.setBackground(new java.awt.Color(255, 204, 0));
        id1disp3.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor3.add(id1disp3);

        jLabel62.setBackground(new java.awt.Color(0, 0, 0));
        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("IDV");
        surtidor3.add(jLabel62);

        id2disp3.setEditable(false);
        id2disp3.setBackground(new java.awt.Color(255, 204, 0));
        id2disp3.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor3.add(id2disp3);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new java.awt.GridLayout(4, 2));

        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel91.setText("Total Monto Extra  Lado 1");
        jLabel91.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(jLabel91);

        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel92.setText("Total Monto  Extra Lado 1");
        jLabel92.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(jLabel92);

        montoextrasurtidor5.setEditable(false);
        montoextrasurtidor5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(montoextrasurtidor5);

        montoextrasurtidor6.setEditable(false);
        montoextrasurtidor6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(montoextrasurtidor6);

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel93.setText("Total Volumen Extra  Lado 2");
        jLabel93.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(jLabel93);

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel94.setText("Total Volumen  Extra Lado 2");
        jLabel94.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(jLabel94);

        volumenextrasurtidor5.setEditable(false);
        volumenextrasurtidor5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(volumenextrasurtidor5);

        volumenextrasurtidor6.setEditable(false);
        volumenextrasurtidor6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        volumenextrasurtidor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volumenextrasurtidor6ActionPerformed(evt);
            }
        });
        jPanel9.add(volumenextrasurtidor6);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new java.awt.GridLayout(4, 2));

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel95.setText("Total Monto Diesel  Lado 1");
        jLabel95.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(jLabel95);

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel96.setText("Total Monto  Diesel Lado 1");
        jLabel96.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(jLabel96);

        montodieselsurtidor5.setEditable(false);
        montodieselsurtidor5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(montodieselsurtidor5);

        montodieselsurtidor6.setEditable(false);
        montodieselsurtidor6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(montodieselsurtidor6);

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel97.setText("Total Volumen Diesel  Lado 2");
        jLabel97.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(jLabel97);

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel98.setText("Total Volumen  Diesel Lado 2");
        jLabel98.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(jLabel98);

        volumendieselsurtidor5.setEditable(false);
        volumendieselsurtidor5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.add(volumendieselsurtidor5);

        volumendieselsurtidor6.setEditable(false);
        volumendieselsurtidor6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        volumendieselsurtidor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volumendieselsurtidor6ActionPerformed(evt);
            }
        });
        jPanel10.add(volumendieselsurtidor6);

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel66.setText("Total Volumen Surtidor3");

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel65.setText("Total Monto Surtidor 3");

        montoturnosurt3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        montoturnosurt3.setText("0");
        montoturnosurt3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        volumenturnosurt3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        volumenturnosurt3.setText("0");
        volumenturnosurt3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.setLayout(new java.awt.GridLayout(4, 2));

        jLabel123.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel123.setText("Total Monto Super  Lado 1");
        jLabel123.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.add(jLabel123);

        jLabel124.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel124.setText("Total Monto  Super Lado 1");
        jLabel124.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.add(jLabel124);

        montosupersurtidor5.setEditable(false);
        montosupersurtidor5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        montosupersurtidor5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montosupersurtidor5ActionPerformed(evt);
            }
        });
        jPanel14.add(montosupersurtidor5);

        montosupersurtidor6.setEditable(false);
        montosupersurtidor6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        montosupersurtidor6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montosupersurtidor6ActionPerformed(evt);
            }
        });
        jPanel14.add(montosupersurtidor6);

        jLabel125.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel125.setText("Total Volumen Super  Lado 2");
        jLabel125.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.add(jLabel125);

        jLabel126.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel126.setText("Total Volumen  Super Lado 2");
        jLabel126.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.add(jLabel126);

        volumensupersurtidor5.setEditable(false);
        volumensupersurtidor5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.add(volumensupersurtidor5);

        volumensupersurtidor6.setEditable(false);
        volumensupersurtidor6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.add(volumensupersurtidor6);

        javax.swing.GroupLayout panelsurt3Layout = new javax.swing.GroupLayout(panelsurt3);
        panelsurt3.setLayout(panelsurt3Layout);
        panelsurt3Layout.setHorizontalGroup(
            panelsurt3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt3Layout.createSequentialGroup()
                .addGroup(panelsurt3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelsurt3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelsurt3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelsurt3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(panelsurt3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelsurt3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(montoturnosurt3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(volumenturnosurt3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelsurt3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)))
                .addContainerGap(132, Short.MAX_VALUE))
            .addComponent(surtidor3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelsurt3Layout.setVerticalGroup(
            panelsurt3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt3Layout.createSequentialGroup()
                .addComponent(surtidor3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(panelsurt3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(montoturnosurt3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelsurt3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(volumenturnosurt3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelsrt4.addTab("Surtidor 3", panelsurt3);

        panelsurt4.setBackground(new java.awt.Color(255, 255, 255));
        panelsurt4.setEnabled(false);
        panelsurt4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelsurt4ComponentShown(evt);
            }
        });

        surtidor4.setBackground(new java.awt.Color(0, 0, 0));
        surtidor4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor4.setLayout(new java.awt.GridLayout(6, 4));

        lado10.setEditable(false);
        lado10.setBackground(new java.awt.Color(0, 0, 0));
        lado10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado10.setForeground(new java.awt.Color(255, 255, 255));
        lado10.setText("Surtidor 4");
        lado10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado10ActionPerformed(evt);
            }
        });
        surtidor4.add(lado10);

        jLabel67.setBackground(new java.awt.Color(0, 0, 0));
        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Lado 1");
        jLabel67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor4.add(jLabel67);

        lado11.setEditable(false);
        lado11.setBackground(new java.awt.Color(0, 0, 0));
        lado11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado11.setForeground(new java.awt.Color(255, 255, 255));
        lado11.setText("Surtidor 4");
        lado11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado11ActionPerformed(evt);
            }
        });
        surtidor4.add(lado11);

        jLabel68.setBackground(new java.awt.Color(0, 0, 0));
        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Lado 2");
        jLabel68.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor4.add(jLabel68);

        jLabel69.setBackground(new java.awt.Color(0, 0, 0));
        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Estado");
        surtidor4.add(jLabel69);

        estado1disp4.setEditable(false);
        estado1disp4.setBackground(new java.awt.Color(255, 204, 0));
        estado1disp4.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor4.add(estado1disp4);

        jLabel70.setBackground(new java.awt.Color(0, 0, 0));
        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Estado");
        surtidor4.add(jLabel70);

        estado2disp4.setEditable(false);
        estado2disp4.setBackground(new java.awt.Color(255, 204, 0));
        estado2disp4.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor4.add(estado2disp4);

        jLabel79.setBackground(new java.awt.Color(0, 0, 0));
        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Producto");
        surtidor4.add(jLabel79);

        gaso1disp4.setEditable(false);
        gaso1disp4.setBackground(new java.awt.Color(255, 204, 0));
        gaso1disp4.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor4.add(gaso1disp4);

        jLabel80.setBackground(new java.awt.Color(0, 0, 0));
        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Producto");
        surtidor4.add(jLabel80);

        gaso2disp4.setEditable(false);
        gaso2disp4.setBackground(new java.awt.Color(255, 204, 0));
        gaso2disp4.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor4.add(gaso2disp4);

        jLabel81.setBackground(new java.awt.Color(0, 0, 0));
        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Monto");
        surtidor4.add(jLabel81);

        monto1disp4.setEditable(false);
        monto1disp4.setBackground(new java.awt.Color(255, 204, 0));
        monto1disp4.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor4.add(monto1disp4);

        jLabel82.setBackground(new java.awt.Color(0, 0, 0));
        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("Monto");
        surtidor4.add(jLabel82);

        monto2disp4.setEditable(false);
        monto2disp4.setBackground(new java.awt.Color(255, 204, 0));
        monto2disp4.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor4.add(monto2disp4);

        jLabel99.setBackground(new java.awt.Color(0, 0, 0));
        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("Volumen");
        surtidor4.add(jLabel99);

        volumen1disp4.setEditable(false);
        volumen1disp4.setBackground(new java.awt.Color(255, 204, 0));
        volumen1disp4.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor4.add(volumen1disp4);

        jLabel100.setBackground(new java.awt.Color(0, 0, 0));
        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setText("Volumen");
        surtidor4.add(jLabel100);

        volumen2disp4.setEditable(false);
        volumen2disp4.setBackground(new java.awt.Color(255, 204, 0));
        volumen2disp4.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor4.add(volumen2disp4);

        jLabel101.setBackground(new java.awt.Color(0, 0, 0));
        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setText("IDV");
        surtidor4.add(jLabel101);

        id1disp4.setEditable(false);
        id1disp4.setBackground(new java.awt.Color(255, 204, 0));
        id1disp4.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor4.add(id1disp4);

        jLabel102.setBackground(new java.awt.Color(0, 0, 0));
        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("IDV");
        surtidor4.add(jLabel102);

        id2disp4.setEditable(false);
        id2disp4.setBackground(new java.awt.Color(255, 204, 0));
        id2disp4.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor4.add(id2disp4);

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setLayout(new java.awt.GridLayout(4, 2));

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel103.setText("Total Monto Extra  Lado 1");
        jLabel103.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(jLabel103);

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel104.setText("Total Monto  Extra Lado 2");
        jLabel104.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(jLabel104);

        montoextrasurtidor7.setEditable(false);
        montoextrasurtidor7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor7.setText("0");
        montoextrasurtidor7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(montoextrasurtidor7);

        montoextrasurtidor8.setEditable(false);
        montoextrasurtidor8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor8.setText("0");
        montoextrasurtidor8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(montoextrasurtidor8);

        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel105.setText("Total Volumen Extra  Lado 1");
        jLabel105.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(jLabel105);

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel106.setText("Total Volumen  Extra Lado 2");
        jLabel106.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(jLabel106);

        volumenextrasurtidor7.setEditable(false);
        volumenextrasurtidor7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor7.setText("0");
        volumenextrasurtidor7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(volumenextrasurtidor7);

        volumenextrasurtidor8.setEditable(false);
        volumenextrasurtidor8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor8.setText("0");
        volumenextrasurtidor8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(volumenextrasurtidor8);

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setLayout(new java.awt.GridLayout(4, 2));

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel107.setText("Total Monto Diesel  Lado 1");
        jLabel107.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.add(jLabel107);

        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel108.setText("Total Monto  Diesel Lado 2");
        jLabel108.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.add(jLabel108);

        montodieselsurtidor7.setEditable(false);
        montodieselsurtidor7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor7.setText("0");
        montodieselsurtidor7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.add(montodieselsurtidor7);

        montodieselsurtidor8.setEditable(false);
        montodieselsurtidor8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor8.setText("0");
        montodieselsurtidor8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.add(montodieselsurtidor8);

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel109.setText("Total Volumen Diesel  Lado 1");
        jLabel109.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.add(jLabel109);

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel110.setText("Total Volumen  Diesel Lado 2");
        jLabel110.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.add(jLabel110);

        volumendieselsurtidor7.setEditable(false);
        volumendieselsurtidor7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor7.setText("0");
        volumendieselsurtidor7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.add(volumendieselsurtidor7);

        volumendieselsurtidor8.setEditable(false);
        volumendieselsurtidor8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor8.setText("0");
        volumendieselsurtidor8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.add(volumendieselsurtidor8);

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel111.setText("Total Volumen Surtidor 4");

        jLabel112.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel112.setText("Total Monto Surtidor 4");

        montoturnosurt4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        montoturnosurt4.setText("0");
        montoturnosurt4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        volumenturnosurt4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        volumenturnosurt4.setText("0");
        volumenturnosurt4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.setLayout(new java.awt.GridLayout(4, 2));

        jLabel127.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel127.setText("Total Monto Super Lado 1");
        jLabel127.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.add(jLabel127);

        jLabel128.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel128.setText("Total Monto  Super Lado 2");
        jLabel128.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.add(jLabel128);

        montosupersurtidor7.setEditable(false);
        montosupersurtidor7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor7.setText("0");
        montosupersurtidor7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.add(montosupersurtidor7);

        montosupersurtidor8.setEditable(false);
        montosupersurtidor8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor8.setText("0");
        montosupersurtidor8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.add(montosupersurtidor8);

        jLabel129.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel129.setText("Total Volumen Diesel  Lado 1");
        jLabel129.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.add(jLabel129);

        jLabel130.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel130.setText("Total Volumen  Diesel Lado 2");
        jLabel130.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.add(jLabel130);

        volumensupersurtidor7.setEditable(false);
        volumensupersurtidor7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor7.setText("0");
        volumensupersurtidor7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.add(volumensupersurtidor7);

        volumensupersurtidor8.setEditable(false);
        volumensupersurtidor8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor8.setText("0");
        volumensupersurtidor8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.add(volumensupersurtidor8);

        javax.swing.GroupLayout panelsurt4Layout = new javax.swing.GroupLayout(panelsurt4);
        panelsurt4.setLayout(panelsurt4Layout);
        panelsurt4Layout.setHorizontalGroup(
            panelsurt4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt4Layout.createSequentialGroup()
                .addGroup(panelsurt4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelsurt4Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel112)
                        .addGap(18, 18, 18)
                        .addComponent(montoturnosurt4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsurt4Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volumenturnosurt4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsurt4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelsurt4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)))
                    .addGroup(panelsurt4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
            .addComponent(surtidor4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelsurt4Layout.setVerticalGroup(
            panelsurt4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt4Layout.createSequentialGroup()
                .addComponent(surtidor4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelsurt4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(montoturnosurt4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsurt4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumenturnosurt4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelsrt4.addTab("Surtidor 4", panelsurt4);

        panelsurt8.setBackground(new java.awt.Color(255, 255, 255));
        panelsurt8.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelsurt8ComponentShown(evt);
            }
        });

        surtidor5.setBackground(new java.awt.Color(0, 0, 0));
        surtidor5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor5.setLayout(new java.awt.GridLayout(6, 4));

        lado12.setEditable(false);
        lado12.setBackground(new java.awt.Color(0, 0, 0));
        lado12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado12.setForeground(new java.awt.Color(255, 255, 255));
        lado12.setText("Surtidor 5");
        lado12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado12ActionPerformed(evt);
            }
        });
        surtidor5.add(lado12);

        jLabel131.setBackground(new java.awt.Color(0, 0, 0));
        jLabel131.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(255, 255, 255));
        jLabel131.setText("Lado 1");
        jLabel131.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor5.add(jLabel131);

        lado13.setEditable(false);
        lado13.setBackground(new java.awt.Color(0, 0, 0));
        lado13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado13.setForeground(new java.awt.Color(255, 255, 255));
        lado13.setText("Surtidor 5");
        lado13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado13ActionPerformed(evt);
            }
        });
        surtidor5.add(lado13);

        jLabel132.setBackground(new java.awt.Color(0, 0, 0));
        jLabel132.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(255, 255, 255));
        jLabel132.setText("Lado 2");
        jLabel132.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor5.add(jLabel132);

        jLabel133.setBackground(new java.awt.Color(0, 0, 0));
        jLabel133.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(255, 255, 255));
        jLabel133.setText("Estado");
        surtidor5.add(jLabel133);

        estado1disp5.setEditable(false);
        estado1disp5.setBackground(new java.awt.Color(255, 204, 0));
        estado1disp5.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor5.add(estado1disp5);

        jLabel134.setBackground(new java.awt.Color(0, 0, 0));
        jLabel134.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(255, 255, 255));
        jLabel134.setText("Estado");
        surtidor5.add(jLabel134);

        estado2disp5.setEditable(false);
        estado2disp5.setBackground(new java.awt.Color(255, 204, 0));
        estado2disp5.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor5.add(estado2disp5);

        jLabel135.setBackground(new java.awt.Color(0, 0, 0));
        jLabel135.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(255, 255, 255));
        jLabel135.setText("Producto");
        surtidor5.add(jLabel135);

        gaso1disp5.setEditable(false);
        gaso1disp5.setBackground(new java.awt.Color(255, 204, 0));
        gaso1disp5.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor5.add(gaso1disp5);

        jLabel136.setBackground(new java.awt.Color(0, 0, 0));
        jLabel136.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(255, 255, 255));
        jLabel136.setText("Producto");
        surtidor5.add(jLabel136);

        gaso2disp5.setEditable(false);
        gaso2disp5.setBackground(new java.awt.Color(255, 204, 0));
        gaso2disp5.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor5.add(gaso2disp5);

        jLabel137.setBackground(new java.awt.Color(0, 0, 0));
        jLabel137.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLabel137.setText("Monto");
        surtidor5.add(jLabel137);

        monto1disp5.setEditable(false);
        monto1disp5.setBackground(new java.awt.Color(255, 204, 0));
        monto1disp5.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor5.add(monto1disp5);

        jLabel138.setBackground(new java.awt.Color(0, 0, 0));
        jLabel138.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel138.setForeground(new java.awt.Color(255, 255, 255));
        jLabel138.setText("Monto");
        surtidor5.add(jLabel138);

        monto2disp5.setEditable(false);
        monto2disp5.setBackground(new java.awt.Color(255, 204, 0));
        monto2disp5.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor5.add(monto2disp5);

        jLabel139.setBackground(new java.awt.Color(0, 0, 0));
        jLabel139.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel139.setForeground(new java.awt.Color(255, 255, 255));
        jLabel139.setText("Volumen");
        surtidor5.add(jLabel139);

        volumen1disp5.setEditable(false);
        volumen1disp5.setBackground(new java.awt.Color(255, 204, 0));
        volumen1disp5.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor5.add(volumen1disp5);

        jLabel140.setBackground(new java.awt.Color(0, 0, 0));
        jLabel140.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(255, 255, 255));
        jLabel140.setText("Volumen");
        surtidor5.add(jLabel140);

        volumen2disp5.setEditable(false);
        volumen2disp5.setBackground(new java.awt.Color(255, 204, 0));
        volumen2disp5.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor5.add(volumen2disp5);

        jLabel141.setBackground(new java.awt.Color(0, 0, 0));
        jLabel141.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(255, 255, 255));
        jLabel141.setText("IDV");
        surtidor5.add(jLabel141);

        id1disp5.setEditable(false);
        id1disp5.setBackground(new java.awt.Color(255, 204, 0));
        id1disp5.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor5.add(id1disp5);

        jLabel142.setBackground(new java.awt.Color(0, 0, 0));
        jLabel142.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(255, 255, 255));
        jLabel142.setText("IDV");
        surtidor5.add(jLabel142);

        id2disp5.setEditable(false);
        id2disp5.setBackground(new java.awt.Color(255, 204, 0));
        id2disp5.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor5.add(id2disp5);

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.setLayout(new java.awt.GridLayout(4, 2));

        jLabel143.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel143.setText("Total Monto Extra  Lado 1");
        jLabel143.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.add(jLabel143);

        jLabel144.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel144.setText("Total Monto  Extra Lado 2");
        jLabel144.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.add(jLabel144);

        montoextrasurtidor9.setEditable(false);
        montoextrasurtidor9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor9.setText("0");
        montoextrasurtidor9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.add(montoextrasurtidor9);

        montoextrasurtidor10.setEditable(false);
        montoextrasurtidor10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor10.setText("0");
        montoextrasurtidor10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.add(montoextrasurtidor10);

        jLabel145.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel145.setText("Total Volumen Extra  Lado 1");
        jLabel145.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.add(jLabel145);

        jLabel146.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel146.setText("Total Volumen  Extra Lado 2");
        jLabel146.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.add(jLabel146);

        volumenextrasurtidor9.setEditable(false);
        volumenextrasurtidor9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor9.setText("0");
        volumenextrasurtidor9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.add(volumenextrasurtidor9);

        volumenextrasurtidor10.setEditable(false);
        volumenextrasurtidor10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor10.setText("0");
        volumenextrasurtidor10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.add(volumenextrasurtidor10);

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.setLayout(new java.awt.GridLayout(4, 2));

        jLabel147.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel147.setText("Total Monto Diesel  Lado 1");
        jLabel147.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.add(jLabel147);

        jLabel148.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel148.setText("Total Monto  Diesel Lado 2");
        jLabel148.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.add(jLabel148);

        montodieselsurtidor9.setEditable(false);
        montodieselsurtidor9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor9.setText("0");
        montodieselsurtidor9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.add(montodieselsurtidor9);

        montodieselsurtidor10.setEditable(false);
        montodieselsurtidor10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor10.setText("0");
        montodieselsurtidor10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.add(montodieselsurtidor10);

        jLabel149.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel149.setText("Total Volumen Diesel  Lado 1");
        jLabel149.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.add(jLabel149);

        jLabel150.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel150.setText("Total Volumen  Diesel Lado 2");
        jLabel150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.add(jLabel150);

        volumendieselsurtidor9.setEditable(false);
        volumendieselsurtidor9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor9.setText("0");
        volumendieselsurtidor9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.add(volumendieselsurtidor9);

        volumendieselsurtidor10.setEditable(false);
        volumendieselsurtidor10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor10.setText("0");
        volumendieselsurtidor10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel17.add(volumendieselsurtidor10);

        jLabel151.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel151.setText("Total Volumen Surtidor 5");

        jLabel152.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel152.setText("Total Monto Surtidor 5");

        montoturnosurt5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        montoturnosurt5.setText("0");
        montoturnosurt5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        volumenturnosurt5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        volumenturnosurt5.setText("0");
        volumenturnosurt5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.setLayout(new java.awt.GridLayout(4, 2));

        jLabel153.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel153.setText("Total Monto Super Lado 1");
        jLabel153.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(jLabel153);

        jLabel154.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel154.setText("Total Monto  Super Lado 2");
        jLabel154.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(jLabel154);

        montosupersurtidor9.setEditable(false);
        montosupersurtidor9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor9.setText("0");
        montosupersurtidor9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(montosupersurtidor9);

        montosupersurtidor10.setEditable(false);
        montosupersurtidor10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor10.setText("0");
        montosupersurtidor10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(montosupersurtidor10);

        jLabel155.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel155.setText("Total Volumen Diesel  Lado 1");
        jLabel155.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(jLabel155);

        jLabel156.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel156.setText("Total Volumen  Diesel Lado 2");
        jLabel156.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(jLabel156);

        volumensupersurtidor9.setEditable(false);
        volumensupersurtidor9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor9.setText("0");
        volumensupersurtidor9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(volumensupersurtidor9);

        volumensupersurtidor10.setEditable(false);
        volumensupersurtidor10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor10.setText("0");
        volumensupersurtidor10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.add(volumensupersurtidor10);

        javax.swing.GroupLayout panelsurt8Layout = new javax.swing.GroupLayout(panelsurt8);
        panelsurt8.setLayout(panelsurt8Layout);
        panelsurt8Layout.setHorizontalGroup(
            panelsurt8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt8Layout.createSequentialGroup()
                .addGroup(panelsurt8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelsurt8Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel152)
                        .addGap(18, 18, 18)
                        .addComponent(montoturnosurt5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsurt8Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volumenturnosurt5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsurt8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelsurt8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)))
                    .addGroup(panelsurt8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
            .addComponent(surtidor5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelsurt8Layout.setVerticalGroup(
            panelsurt8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt8Layout.createSequentialGroup()
                .addComponent(surtidor5, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(panelsurt8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(montoturnosurt5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsurt8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumenturnosurt5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelsurt5Layout = new javax.swing.GroupLayout(panelsurt5);
        panelsurt5.setLayout(panelsurt5Layout);
        panelsurt5Layout.setHorizontalGroup(
            panelsurt5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 683, Short.MAX_VALUE)
            .addGroup(panelsurt5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelsurt8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelsurt5Layout.setVerticalGroup(
            panelsurt5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
            .addGroup(panelsurt5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelsurt5Layout.createSequentialGroup()
                    .addComponent(panelsurt8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelsrt4.addTab("Surtidor 5", panelsurt5);

        panelsurt9.setBackground(new java.awt.Color(255, 255, 255));
        panelsurt9.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelsurt9ComponentShown(evt);
            }
        });

        surtidor6.setBackground(new java.awt.Color(0, 0, 0));
        surtidor6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor6.setLayout(new java.awt.GridLayout(6, 4));

        lado14.setEditable(false);
        lado14.setBackground(new java.awt.Color(0, 0, 0));
        lado14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado14.setForeground(new java.awt.Color(255, 255, 255));
        lado14.setText("Surtidor 6");
        lado14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado14ActionPerformed(evt);
            }
        });
        surtidor6.add(lado14);

        jLabel157.setBackground(new java.awt.Color(0, 0, 0));
        jLabel157.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel157.setForeground(new java.awt.Color(255, 255, 255));
        jLabel157.setText("Lado 1");
        jLabel157.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor6.add(jLabel157);

        lado15.setEditable(false);
        lado15.setBackground(new java.awt.Color(0, 0, 0));
        lado15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado15.setForeground(new java.awt.Color(255, 255, 255));
        lado15.setText("Surtidor 6");
        lado15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado15ActionPerformed(evt);
            }
        });
        surtidor6.add(lado15);

        jLabel158.setBackground(new java.awt.Color(0, 0, 0));
        jLabel158.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel158.setForeground(new java.awt.Color(255, 255, 255));
        jLabel158.setText("Lado 2");
        jLabel158.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor6.add(jLabel158);

        jLabel159.setBackground(new java.awt.Color(0, 0, 0));
        jLabel159.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel159.setForeground(new java.awt.Color(255, 255, 255));
        jLabel159.setText("Estado");
        surtidor6.add(jLabel159);

        estado1disp6.setEditable(false);
        estado1disp6.setBackground(new java.awt.Color(255, 204, 0));
        estado1disp6.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor6.add(estado1disp6);

        jLabel160.setBackground(new java.awt.Color(0, 0, 0));
        jLabel160.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(255, 255, 255));
        jLabel160.setText("Estado");
        surtidor6.add(jLabel160);

        estado2disp6.setEditable(false);
        estado2disp6.setBackground(new java.awt.Color(255, 204, 0));
        estado2disp6.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor6.add(estado2disp6);

        jLabel161.setBackground(new java.awt.Color(0, 0, 0));
        jLabel161.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel161.setForeground(new java.awt.Color(255, 255, 255));
        jLabel161.setText("Producto");
        surtidor6.add(jLabel161);

        gaso1disp6.setEditable(false);
        gaso1disp6.setBackground(new java.awt.Color(255, 204, 0));
        gaso1disp6.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor6.add(gaso1disp6);

        jLabel162.setBackground(new java.awt.Color(0, 0, 0));
        jLabel162.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel162.setForeground(new java.awt.Color(255, 255, 255));
        jLabel162.setText("Producto");
        surtidor6.add(jLabel162);

        gaso2disp6.setEditable(false);
        gaso2disp6.setBackground(new java.awt.Color(255, 204, 0));
        gaso2disp6.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor6.add(gaso2disp6);

        jLabel163.setBackground(new java.awt.Color(0, 0, 0));
        jLabel163.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel163.setForeground(new java.awt.Color(255, 255, 255));
        jLabel163.setText("Monto");
        surtidor6.add(jLabel163);

        monto1disp6.setEditable(false);
        monto1disp6.setBackground(new java.awt.Color(255, 204, 0));
        monto1disp6.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor6.add(monto1disp6);

        jLabel164.setBackground(new java.awt.Color(0, 0, 0));
        jLabel164.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel164.setForeground(new java.awt.Color(255, 255, 255));
        jLabel164.setText("Monto");
        surtidor6.add(jLabel164);

        monto2disp6.setEditable(false);
        monto2disp6.setBackground(new java.awt.Color(255, 204, 0));
        monto2disp6.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor6.add(monto2disp6);

        jLabel165.setBackground(new java.awt.Color(0, 0, 0));
        jLabel165.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel165.setForeground(new java.awt.Color(255, 255, 255));
        jLabel165.setText("Volumen");
        surtidor6.add(jLabel165);

        volumen1disp6.setEditable(false);
        volumen1disp6.setBackground(new java.awt.Color(255, 204, 0));
        volumen1disp6.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor6.add(volumen1disp6);

        jLabel166.setBackground(new java.awt.Color(0, 0, 0));
        jLabel166.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel166.setForeground(new java.awt.Color(255, 255, 255));
        jLabel166.setText("Volumen");
        surtidor6.add(jLabel166);

        volumen2disp6.setEditable(false);
        volumen2disp6.setBackground(new java.awt.Color(255, 204, 0));
        volumen2disp6.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor6.add(volumen2disp6);

        jLabel167.setBackground(new java.awt.Color(0, 0, 0));
        jLabel167.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel167.setForeground(new java.awt.Color(255, 255, 255));
        jLabel167.setText("IDV");
        surtidor6.add(jLabel167);

        id1disp6.setEditable(false);
        id1disp6.setBackground(new java.awt.Color(255, 204, 0));
        id1disp6.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor6.add(id1disp6);

        jLabel168.setBackground(new java.awt.Color(0, 0, 0));
        jLabel168.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel168.setForeground(new java.awt.Color(255, 255, 255));
        jLabel168.setText("IDV");
        surtidor6.add(jLabel168);

        id2disp6.setEditable(false);
        id2disp6.setBackground(new java.awt.Color(255, 204, 0));
        id2disp6.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor6.add(id2disp6);

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.setLayout(new java.awt.GridLayout(4, 2));

        jLabel169.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel169.setText("Total Monto Extra  Lado 1");
        jLabel169.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.add(jLabel169);

        jLabel170.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel170.setText("Total Monto  Extra Lado 2");
        jLabel170.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.add(jLabel170);

        montoextrasurtidor11.setEditable(false);
        montoextrasurtidor11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor11.setText("0");
        montoextrasurtidor11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.add(montoextrasurtidor11);

        montoextrasurtidor12.setEditable(false);
        montoextrasurtidor12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor12.setText("0");
        montoextrasurtidor12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.add(montoextrasurtidor12);

        jLabel171.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel171.setText("Total Volumen Extra  Lado 1");
        jLabel171.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.add(jLabel171);

        jLabel172.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel172.setText("Total Volumen  Extra Lado 2");
        jLabel172.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.add(jLabel172);

        volumenextrasurtidor11.setEditable(false);
        volumenextrasurtidor11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor11.setText("0");
        volumenextrasurtidor11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.add(volumenextrasurtidor11);

        volumenextrasurtidor12.setEditable(false);
        volumenextrasurtidor12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor12.setText("0");
        volumenextrasurtidor12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.add(volumenextrasurtidor12);

        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.setLayout(new java.awt.GridLayout(4, 2));

        jLabel173.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel173.setText("Total Monto Diesel  Lado 1");
        jLabel173.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.add(jLabel173);

        jLabel174.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel174.setText("Total Monto  Diesel Lado 2");
        jLabel174.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.add(jLabel174);

        montodieselsurtidor11.setEditable(false);
        montodieselsurtidor11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor11.setText("0");
        montodieselsurtidor11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.add(montodieselsurtidor11);

        montodieselsurtidor12.setEditable(false);
        montodieselsurtidor12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor12.setText("0");
        montodieselsurtidor12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.add(montodieselsurtidor12);

        jLabel175.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel175.setText("Total Volumen Diesel  Lado 1");
        jLabel175.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.add(jLabel175);

        jLabel176.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel176.setText("Total Volumen  Diesel Lado 2");
        jLabel176.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.add(jLabel176);

        volumendieselsurtidor11.setEditable(false);
        volumendieselsurtidor11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor11.setText("0");
        volumendieselsurtidor11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.add(volumendieselsurtidor11);

        volumendieselsurtidor12.setEditable(false);
        volumendieselsurtidor12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor12.setText("0");
        volumendieselsurtidor12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel20.add(volumendieselsurtidor12);

        jLabel177.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel177.setText("Total Volumen Surtidor 6");

        jLabel178.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel178.setText("Total Monto Surtidor 6");

        montoturnosurt6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        montoturnosurt6.setText("0");
        montoturnosurt6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        volumenturnosurt6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        volumenturnosurt6.setText("0");
        volumenturnosurt6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.setLayout(new java.awt.GridLayout(4, 2));

        jLabel179.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel179.setText("Total Monto Super Lado 1");
        jLabel179.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.add(jLabel179);

        jLabel180.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel180.setText("Total Monto  Super Lado 2");
        jLabel180.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.add(jLabel180);

        montosupersurtidor11.setEditable(false);
        montosupersurtidor11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor11.setText("0");
        montosupersurtidor11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.add(montosupersurtidor11);

        montosupersurtidor12.setEditable(false);
        montosupersurtidor12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor12.setText("0");
        montosupersurtidor12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.add(montosupersurtidor12);

        jLabel181.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel181.setText("Total Volumen Diesel  Lado 1");
        jLabel181.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.add(jLabel181);

        jLabel182.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel182.setText("Total Volumen  Diesel Lado 2");
        jLabel182.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.add(jLabel182);

        volumensupersurtidor11.setEditable(false);
        volumensupersurtidor11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor11.setText("0");
        volumensupersurtidor11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.add(volumensupersurtidor11);

        volumensupersurtidor12.setEditable(false);
        volumensupersurtidor12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor12.setText("0");
        volumensupersurtidor12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel21.add(volumensupersurtidor12);

        javax.swing.GroupLayout panelsurt9Layout = new javax.swing.GroupLayout(panelsurt9);
        panelsurt9.setLayout(panelsurt9Layout);
        panelsurt9Layout.setHorizontalGroup(
            panelsurt9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt9Layout.createSequentialGroup()
                .addGroup(panelsurt9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelsurt9Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel178)
                        .addGap(18, 18, 18)
                        .addComponent(montoturnosurt6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsurt9Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel177, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volumenturnosurt6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsurt9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelsurt9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)))
                    .addGroup(panelsurt9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
            .addComponent(surtidor6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelsurt9Layout.setVerticalGroup(
            panelsurt9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt9Layout.createSequentialGroup()
                .addComponent(surtidor6, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(panelsurt9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel178, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(montoturnosurt6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsurt9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel177, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumenturnosurt6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelsurt6Layout = new javax.swing.GroupLayout(panelsurt6);
        panelsurt6.setLayout(panelsurt6Layout);
        panelsurt6Layout.setHorizontalGroup(
            panelsurt6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 683, Short.MAX_VALUE)
            .addGroup(panelsurt6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelsurt9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelsurt6Layout.setVerticalGroup(
            panelsurt6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
            .addGroup(panelsurt6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelsurt6Layout.createSequentialGroup()
                    .addComponent(panelsurt9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelsrt4.addTab("Surtidor 6", panelsurt6);

        panelsurt10.setBackground(new java.awt.Color(255, 255, 255));
        panelsurt10.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelsurt10ComponentShown(evt);
            }
        });

        surtidor7.setBackground(new java.awt.Color(0, 0, 0));
        surtidor7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor7.setLayout(new java.awt.GridLayout(6, 4));

        lado16.setEditable(false);
        lado16.setBackground(new java.awt.Color(0, 0, 0));
        lado16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado16.setForeground(new java.awt.Color(255, 255, 255));
        lado16.setText("Surtidor 7");
        lado16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado16ActionPerformed(evt);
            }
        });
        surtidor7.add(lado16);

        jLabel183.setBackground(new java.awt.Color(0, 0, 0));
        jLabel183.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel183.setForeground(new java.awt.Color(255, 255, 255));
        jLabel183.setText("Lado 1");
        jLabel183.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor7.add(jLabel183);

        lado17.setEditable(false);
        lado17.setBackground(new java.awt.Color(0, 0, 0));
        lado17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado17.setForeground(new java.awt.Color(255, 255, 255));
        lado17.setText("Surtidor 7");
        lado17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado17ActionPerformed(evt);
            }
        });
        surtidor7.add(lado17);

        jLabel184.setBackground(new java.awt.Color(0, 0, 0));
        jLabel184.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel184.setForeground(new java.awt.Color(255, 255, 255));
        jLabel184.setText("Lado 2");
        jLabel184.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor7.add(jLabel184);

        jLabel185.setBackground(new java.awt.Color(0, 0, 0));
        jLabel185.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel185.setForeground(new java.awt.Color(255, 255, 255));
        jLabel185.setText("Estado");
        surtidor7.add(jLabel185);

        estado1disp7.setEditable(false);
        estado1disp7.setBackground(new java.awt.Color(255, 204, 0));
        estado1disp7.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor7.add(estado1disp7);

        jLabel186.setBackground(new java.awt.Color(0, 0, 0));
        jLabel186.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel186.setForeground(new java.awt.Color(255, 255, 255));
        jLabel186.setText("Estado");
        surtidor7.add(jLabel186);

        estado2disp7.setEditable(false);
        estado2disp7.setBackground(new java.awt.Color(255, 204, 0));
        estado2disp7.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor7.add(estado2disp7);

        jLabel187.setBackground(new java.awt.Color(0, 0, 0));
        jLabel187.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel187.setForeground(new java.awt.Color(255, 255, 255));
        jLabel187.setText("Producto");
        surtidor7.add(jLabel187);

        gaso1disp7.setEditable(false);
        gaso1disp7.setBackground(new java.awt.Color(255, 204, 0));
        gaso1disp7.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor7.add(gaso1disp7);

        jLabel188.setBackground(new java.awt.Color(0, 0, 0));
        jLabel188.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel188.setForeground(new java.awt.Color(255, 255, 255));
        jLabel188.setText("Producto");
        surtidor7.add(jLabel188);

        gaso2disp7.setEditable(false);
        gaso2disp7.setBackground(new java.awt.Color(255, 204, 0));
        gaso2disp7.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor7.add(gaso2disp7);

        jLabel189.setBackground(new java.awt.Color(0, 0, 0));
        jLabel189.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel189.setForeground(new java.awt.Color(255, 255, 255));
        jLabel189.setText("Monto");
        surtidor7.add(jLabel189);

        monto1disp7.setEditable(false);
        monto1disp7.setBackground(new java.awt.Color(255, 204, 0));
        monto1disp7.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor7.add(monto1disp7);

        jLabel190.setBackground(new java.awt.Color(0, 0, 0));
        jLabel190.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel190.setForeground(new java.awt.Color(255, 255, 255));
        jLabel190.setText("Monto");
        surtidor7.add(jLabel190);

        monto2disp7.setEditable(false);
        monto2disp7.setBackground(new java.awt.Color(255, 204, 0));
        monto2disp7.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor7.add(monto2disp7);

        jLabel191.setBackground(new java.awt.Color(0, 0, 0));
        jLabel191.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel191.setForeground(new java.awt.Color(255, 255, 255));
        jLabel191.setText("Volumen");
        surtidor7.add(jLabel191);

        volumen1disp7.setEditable(false);
        volumen1disp7.setBackground(new java.awt.Color(255, 204, 0));
        volumen1disp7.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor7.add(volumen1disp7);

        jLabel192.setBackground(new java.awt.Color(0, 0, 0));
        jLabel192.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel192.setForeground(new java.awt.Color(255, 255, 255));
        jLabel192.setText("Volumen");
        surtidor7.add(jLabel192);

        volumen2disp7.setEditable(false);
        volumen2disp7.setBackground(new java.awt.Color(255, 204, 0));
        volumen2disp7.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor7.add(volumen2disp7);

        jLabel193.setBackground(new java.awt.Color(0, 0, 0));
        jLabel193.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel193.setForeground(new java.awt.Color(255, 255, 255));
        jLabel193.setText("IDV");
        surtidor7.add(jLabel193);

        id1disp7.setEditable(false);
        id1disp7.setBackground(new java.awt.Color(255, 204, 0));
        id1disp7.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor7.add(id1disp7);

        jLabel194.setBackground(new java.awt.Color(0, 0, 0));
        jLabel194.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel194.setForeground(new java.awt.Color(255, 255, 255));
        jLabel194.setText("IDV");
        surtidor7.add(jLabel194);

        id2disp7.setEditable(false);
        id2disp7.setBackground(new java.awt.Color(255, 204, 0));
        id2disp7.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor7.add(id2disp7);

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.setLayout(new java.awt.GridLayout(4, 2));

        jLabel195.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel195.setText("Total Monto Extra  Lado 1");
        jLabel195.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.add(jLabel195);

        jLabel196.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel196.setText("Total Monto  Extra Lado 2");
        jLabel196.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.add(jLabel196);

        montoextrasurtidor13.setEditable(false);
        montoextrasurtidor13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor13.setText("0");
        montoextrasurtidor13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.add(montoextrasurtidor13);

        montoextrasurtidor14.setEditable(false);
        montoextrasurtidor14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor14.setText("0");
        montoextrasurtidor14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.add(montoextrasurtidor14);

        jLabel197.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel197.setText("Total Volumen Extra  Lado 1");
        jLabel197.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.add(jLabel197);

        jLabel198.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel198.setText("Total Volumen  Extra Lado 2");
        jLabel198.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.add(jLabel198);

        volumenextrasurtidor13.setEditable(false);
        volumenextrasurtidor13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor13.setText("0");
        volumenextrasurtidor13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.add(volumenextrasurtidor13);

        volumenextrasurtidor14.setEditable(false);
        volumenextrasurtidor14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor14.setText("0");
        volumenextrasurtidor14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.add(volumenextrasurtidor14);

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.setLayout(new java.awt.GridLayout(4, 2));

        jLabel199.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel199.setText("Total Monto Diesel  Lado 1");
        jLabel199.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.add(jLabel199);

        jLabel200.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel200.setText("Total Monto  Diesel Lado 2");
        jLabel200.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.add(jLabel200);

        montodieselsurtidor13.setEditable(false);
        montodieselsurtidor13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor13.setText("0");
        montodieselsurtidor13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.add(montodieselsurtidor13);

        montodieselsurtidor14.setEditable(false);
        montodieselsurtidor14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor14.setText("0");
        montodieselsurtidor14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.add(montodieselsurtidor14);

        jLabel201.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel201.setText("Total Volumen Diesel  Lado 1");
        jLabel201.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.add(jLabel201);

        jLabel202.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel202.setText("Total Volumen  Diesel Lado 2");
        jLabel202.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.add(jLabel202);

        volumendieselsurtidor13.setEditable(false);
        volumendieselsurtidor13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor13.setText("0");
        volumendieselsurtidor13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.add(volumendieselsurtidor13);

        volumendieselsurtidor14.setEditable(false);
        volumendieselsurtidor14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor14.setText("0");
        volumendieselsurtidor14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel23.add(volumendieselsurtidor14);

        jLabel203.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel203.setText("Total Volumen Surtidor 7");

        jLabel204.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel204.setText("Total Monto Surtidor 7");

        montoturnosurt7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        montoturnosurt7.setText("0");
        montoturnosurt7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        volumenturnosurt7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        volumenturnosurt7.setText("0");
        volumenturnosurt7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.setLayout(new java.awt.GridLayout(4, 2));

        jLabel205.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel205.setText("Total Monto Super Lado 1");
        jLabel205.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.add(jLabel205);

        jLabel206.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel206.setText("Total Monto  Super Lado 2");
        jLabel206.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.add(jLabel206);

        montosupersurtidor13.setEditable(false);
        montosupersurtidor13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor13.setText("0");
        montosupersurtidor13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.add(montosupersurtidor13);

        montosupersurtidor14.setEditable(false);
        montosupersurtidor14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor14.setText("0");
        montosupersurtidor14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.add(montosupersurtidor14);

        jLabel207.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel207.setText("Total Volumen Diesel  Lado 1");
        jLabel207.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.add(jLabel207);

        jLabel208.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel208.setText("Total Volumen  Diesel Lado 2");
        jLabel208.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.add(jLabel208);

        volumensupersurtidor13.setEditable(false);
        volumensupersurtidor13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor13.setText("0");
        volumensupersurtidor13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.add(volumensupersurtidor13);

        volumensupersurtidor14.setEditable(false);
        volumensupersurtidor14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor14.setText("0");
        volumensupersurtidor14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel24.add(volumensupersurtidor14);

        javax.swing.GroupLayout panelsurt10Layout = new javax.swing.GroupLayout(panelsurt10);
        panelsurt10.setLayout(panelsurt10Layout);
        panelsurt10Layout.setHorizontalGroup(
            panelsurt10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt10Layout.createSequentialGroup()
                .addGroup(panelsurt10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelsurt10Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel204)
                        .addGap(18, 18, 18)
                        .addComponent(montoturnosurt7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsurt10Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel203, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volumenturnosurt7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsurt10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelsurt10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)))
                    .addGroup(panelsurt10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
            .addComponent(surtidor7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelsurt10Layout.setVerticalGroup(
            panelsurt10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt10Layout.createSequentialGroup()
                .addComponent(surtidor7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(panelsurt10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel204, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(montoturnosurt7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsurt10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel203, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumenturnosurt7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelsurt7Layout = new javax.swing.GroupLayout(panelsurt7);
        panelsurt7.setLayout(panelsurt7Layout);
        panelsurt7Layout.setHorizontalGroup(
            panelsurt7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 683, Short.MAX_VALUE)
            .addGroup(panelsurt7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelsurt10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelsurt7Layout.setVerticalGroup(
            panelsurt7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
            .addGroup(panelsurt7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelsurt7Layout.createSequentialGroup()
                    .addComponent(panelsurt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelsrt4.addTab("Surtidor7", panelsurt7);

        panelsurt12.setBackground(new java.awt.Color(255, 255, 255));
        panelsurt12.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panelsurt12ComponentShown(evt);
            }
        });

        surtidor8.setBackground(new java.awt.Color(0, 0, 0));
        surtidor8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor8.setLayout(new java.awt.GridLayout(6, 4));

        lado18.setEditable(false);
        lado18.setBackground(new java.awt.Color(0, 0, 0));
        lado18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado18.setForeground(new java.awt.Color(255, 255, 255));
        lado18.setText("Surtidor 8");
        lado18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado18ActionPerformed(evt);
            }
        });
        surtidor8.add(lado18);

        jLabel209.setBackground(new java.awt.Color(0, 0, 0));
        jLabel209.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel209.setForeground(new java.awt.Color(255, 255, 255));
        jLabel209.setText("Lado 1");
        jLabel209.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor8.add(jLabel209);

        lado19.setEditable(false);
        lado19.setBackground(new java.awt.Color(0, 0, 0));
        lado19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lado19.setForeground(new java.awt.Color(255, 255, 255));
        lado19.setText("Surtidor 8");
        lado19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lado19ActionPerformed(evt);
            }
        });
        surtidor8.add(lado19);

        jLabel210.setBackground(new java.awt.Color(0, 0, 0));
        jLabel210.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel210.setForeground(new java.awt.Color(255, 255, 255));
        jLabel210.setText("Lado 2");
        jLabel210.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        surtidor8.add(jLabel210);

        jLabel211.setBackground(new java.awt.Color(0, 0, 0));
        jLabel211.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel211.setForeground(new java.awt.Color(255, 255, 255));
        jLabel211.setText("Estado");
        surtidor8.add(jLabel211);

        estado1disp8.setEditable(false);
        estado1disp8.setBackground(new java.awt.Color(255, 204, 0));
        estado1disp8.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        estado1disp8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado1disp8ActionPerformed(evt);
            }
        });
        surtidor8.add(estado1disp8);

        jLabel212.setBackground(new java.awt.Color(0, 0, 0));
        jLabel212.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel212.setForeground(new java.awt.Color(255, 255, 255));
        jLabel212.setText("Estado");
        surtidor8.add(jLabel212);

        estado2disp8.setEditable(false);
        estado2disp8.setBackground(new java.awt.Color(255, 204, 0));
        estado2disp8.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor8.add(estado2disp8);

        jLabel213.setBackground(new java.awt.Color(0, 0, 0));
        jLabel213.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel213.setForeground(new java.awt.Color(255, 255, 255));
        jLabel213.setText("Producto");
        surtidor8.add(jLabel213);

        gaso1disp8.setEditable(false);
        gaso1disp8.setBackground(new java.awt.Color(255, 204, 0));
        gaso1disp8.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor8.add(gaso1disp8);

        jLabel214.setBackground(new java.awt.Color(0, 0, 0));
        jLabel214.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel214.setForeground(new java.awt.Color(255, 255, 255));
        jLabel214.setText("Producto");
        surtidor8.add(jLabel214);

        gaso2disp8.setEditable(false);
        gaso2disp8.setBackground(new java.awt.Color(255, 204, 0));
        gaso2disp8.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor8.add(gaso2disp8);

        jLabel215.setBackground(new java.awt.Color(0, 0, 0));
        jLabel215.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel215.setForeground(new java.awt.Color(255, 255, 255));
        jLabel215.setText("Monto");
        surtidor8.add(jLabel215);

        monto1disp8.setEditable(false);
        monto1disp8.setBackground(new java.awt.Color(255, 204, 0));
        monto1disp8.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor8.add(monto1disp8);

        jLabel216.setBackground(new java.awt.Color(0, 0, 0));
        jLabel216.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel216.setForeground(new java.awt.Color(255, 255, 255));
        jLabel216.setText("Monto");
        surtidor8.add(jLabel216);

        monto2disp8.setEditable(false);
        monto2disp8.setBackground(new java.awt.Color(255, 204, 0));
        monto2disp8.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor8.add(monto2disp8);

        jLabel217.setBackground(new java.awt.Color(0, 0, 0));
        jLabel217.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel217.setForeground(new java.awt.Color(255, 255, 255));
        jLabel217.setText("Volumen");
        surtidor8.add(jLabel217);

        volumen1disp8.setEditable(false);
        volumen1disp8.setBackground(new java.awt.Color(255, 204, 0));
        volumen1disp8.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor8.add(volumen1disp8);

        jLabel218.setBackground(new java.awt.Color(0, 0, 0));
        jLabel218.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel218.setForeground(new java.awt.Color(255, 255, 255));
        jLabel218.setText("Volumen");
        surtidor8.add(jLabel218);

        volumen2disp8.setEditable(false);
        volumen2disp8.setBackground(new java.awt.Color(255, 204, 0));
        volumen2disp8.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor8.add(volumen2disp8);

        jLabel219.setBackground(new java.awt.Color(0, 0, 0));
        jLabel219.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel219.setForeground(new java.awt.Color(255, 255, 255));
        jLabel219.setText("IDV");
        surtidor8.add(jLabel219);

        id1disp8.setEditable(false);
        id1disp8.setBackground(new java.awt.Color(255, 204, 0));
        id1disp8.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor8.add(id1disp8);

        jLabel220.setBackground(new java.awt.Color(0, 0, 0));
        jLabel220.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel220.setForeground(new java.awt.Color(255, 255, 255));
        jLabel220.setText("IDV");
        surtidor8.add(jLabel220);

        id2disp8.setEditable(false);
        id2disp8.setBackground(new java.awt.Color(255, 204, 0));
        id2disp8.setFont(new java.awt.Font("Digital-7", 1, 24)); // NOI18N
        surtidor8.add(id2disp8);

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.setLayout(new java.awt.GridLayout(4, 2));

        jLabel221.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel221.setText("Total Monto Extra  Lado 1");
        jLabel221.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.add(jLabel221);

        jLabel222.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel222.setText("Total Monto  Extra Lado 2");
        jLabel222.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.add(jLabel222);

        montoextrasurtidor15.setEditable(false);
        montoextrasurtidor15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor15.setText("0");
        montoextrasurtidor15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.add(montoextrasurtidor15);

        montoextrasurtidor16.setEditable(false);
        montoextrasurtidor16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montoextrasurtidor16.setText("0");
        montoextrasurtidor16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.add(montoextrasurtidor16);

        jLabel223.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel223.setText("Total Volumen Extra  Lado 1");
        jLabel223.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.add(jLabel223);

        jLabel224.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel224.setText("Total Volumen  Extra Lado 2");
        jLabel224.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.add(jLabel224);

        volumenextrasurtidor15.setEditable(false);
        volumenextrasurtidor15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor15.setText("0");
        volumenextrasurtidor15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.add(volumenextrasurtidor15);

        volumenextrasurtidor16.setEditable(false);
        volumenextrasurtidor16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumenextrasurtidor16.setText("0");
        volumenextrasurtidor16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.add(volumenextrasurtidor16);

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.setLayout(new java.awt.GridLayout(4, 2));

        jLabel225.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel225.setText("Total Monto Diesel  Lado 1");
        jLabel225.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.add(jLabel225);

        jLabel226.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel226.setText("Total Monto  Diesel Lado 2");
        jLabel226.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.add(jLabel226);

        montodieselsurtidor15.setEditable(false);
        montodieselsurtidor15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor15.setText("0");
        montodieselsurtidor15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.add(montodieselsurtidor15);

        montodieselsurtidor16.setEditable(false);
        montodieselsurtidor16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montodieselsurtidor16.setText("0");
        montodieselsurtidor16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.add(montodieselsurtidor16);

        jLabel227.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel227.setText("Total Volumen Diesel  Lado 1");
        jLabel227.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.add(jLabel227);

        jLabel228.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel228.setText("Total Volumen  Diesel Lado 2");
        jLabel228.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.add(jLabel228);

        volumendieselsurtidor15.setEditable(false);
        volumendieselsurtidor15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor15.setText("0");
        volumendieselsurtidor15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.add(volumendieselsurtidor15);

        volumendieselsurtidor16.setEditable(false);
        volumendieselsurtidor16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumendieselsurtidor16.setText("0");
        volumendieselsurtidor16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.add(volumendieselsurtidor16);

        jLabel229.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel229.setText("Total Volumen Surtidor 7");

        jLabel230.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel230.setText("Total Monto Surtidor 7");

        montoturnosurt8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        montoturnosurt8.setText("0");
        montoturnosurt8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        volumenturnosurt8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        volumenturnosurt8.setText("0");
        volumenturnosurt8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.setLayout(new java.awt.GridLayout(4, 2));

        jLabel231.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel231.setText("Total Monto Super Lado 1");
        jLabel231.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.add(jLabel231);

        jLabel232.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel232.setText("Total Monto  Super Lado 2");
        jLabel232.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.add(jLabel232);

        montosupersurtidor15.setEditable(false);
        montosupersurtidor15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor15.setText("0");
        montosupersurtidor15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.add(montosupersurtidor15);

        montosupersurtidor16.setEditable(false);
        montosupersurtidor16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        montosupersurtidor16.setText("0");
        montosupersurtidor16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.add(montosupersurtidor16);

        jLabel233.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel233.setText("Total Volumen Diesel  Lado 1");
        jLabel233.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.add(jLabel233);

        jLabel234.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel234.setText("Total Volumen  Diesel Lado 2");
        jLabel234.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.add(jLabel234);

        volumensupersurtidor15.setEditable(false);
        volumensupersurtidor15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor15.setText("0");
        volumensupersurtidor15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.add(volumensupersurtidor15);

        volumensupersurtidor16.setEditable(false);
        volumensupersurtidor16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        volumensupersurtidor16.setText("0");
        volumensupersurtidor16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.add(volumensupersurtidor16);

        javax.swing.GroupLayout panelsurt12Layout = new javax.swing.GroupLayout(panelsurt12);
        panelsurt12.setLayout(panelsurt12Layout);
        panelsurt12Layout.setHorizontalGroup(
            panelsurt12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt12Layout.createSequentialGroup()
                .addGroup(panelsurt12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelsurt12Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel230)
                        .addGap(18, 18, 18)
                        .addComponent(montoturnosurt8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsurt12Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel229, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volumenturnosurt8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelsurt12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelsurt12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)))
                    .addGroup(panelsurt12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
            .addComponent(surtidor8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelsurt12Layout.setVerticalGroup(
            panelsurt12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsurt12Layout.createSequentialGroup()
                .addComponent(surtidor8, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(panelsurt12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel230, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(montoturnosurt8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsurt12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel229, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumenturnosurt8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelsurt11Layout = new javax.swing.GroupLayout(panelsurt11);
        panelsurt11.setLayout(panelsurt11Layout);
        panelsurt11Layout.setHorizontalGroup(
            panelsurt11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 683, Short.MAX_VALUE)
            .addGroup(panelsurt11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelsurt12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelsurt11Layout.setVerticalGroup(
            panelsurt11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
            .addGroup(panelsurt11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelsurt11Layout.createSequentialGroup()
                    .addComponent(panelsurt12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelsrt4.addTab("Surtidor8", panelsurt11);

        jPanel2.add(panelsrt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 230));

        mensajesR.setEditable(false);
        mensajesR.setColumns(20);
        mensajesR.setRows(5);
        jScrollPane1.setViewportView(mensajesR);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 730, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1763, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelsrt4ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelsrt4ComponentShown
    }//GEN-LAST:event_panelsrt4ComponentShown

    private void panelsurt4ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelsurt4ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_panelsurt4ComponentShown

    private void lado11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado11ActionPerformed

    private void lado10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado10ActionPerformed

    private void panelsurt3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelsurt3ComponentShown
        panel = 3;
        System.out.println("Panel 3");
    }//GEN-LAST:event_panelsurt3ComponentShown

    public String obtenerContenido() {
        return estado1disp1.getText();
    }

    private void lado9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado9ActionPerformed

    private void lado8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado8ActionPerformed

    private void panelsurt2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelsurt2ComponentShown
        panel = 2;
        System.out.println("Panel 2");
    }//GEN-LAST:event_panelsurt2ComponentShown

    private void lado7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado7ActionPerformed

    private void lado6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado6ActionPerformed

    private void puntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puntoActionPerformed
        numeroPulsado(".");
    }//GEN-LAST:event_puntoActionPerformed

    private void ceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ceroActionPerformed
        numeroPulsado(cero.getText());
    }//GEN-LAST:event_ceroActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        pantalla.setText("");
    }//GEN-LAST:event_borrarActionPerformed

    private void nueveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nueveActionPerformed
        numeroPulsado(nueve.getText());
    }//GEN-LAST:event_nueveActionPerformed

    private void ochoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ochoActionPerformed
        numeroPulsado(ocho.getText());
    }//GEN-LAST:event_ochoActionPerformed

    private void sieteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sieteActionPerformed
        numeroPulsado(siete.getText());
    }//GEN-LAST:event_sieteActionPerformed

    private void seisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seisActionPerformed
        numeroPulsado(seis.getText());
    }//GEN-LAST:event_seisActionPerformed

    private void cincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cincoActionPerformed
        numeroPulsado(cinco.getText());
    }//GEN-LAST:event_cincoActionPerformed

    private void cuatroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuatroActionPerformed
        numeroPulsado(cuatro.getText());
    }//GEN-LAST:event_cuatroActionPerformed

    private void tresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tresActionPerformed
        numeroPulsado(tres.getText());
    }//GEN-LAST:event_tresActionPerformed

    private void dosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosActionPerformed
        numeroPulsado(dos.getText());
    }//GEN-LAST:event_dosActionPerformed

    private void unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unoActionPerformed
        numeroPulsado(uno.getText());
    }//GEN-LAST:event_unoActionPerformed

    private void bdieselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdieselActionPerformed
        bextra.setEnabled(false);
        bsuper.setEnabled(false);
        productopreset = dieseln;
    }//GEN-LAST:event_bdieselActionPerformed

    private void bsuperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsuperActionPerformed
        productopreset = supern;
        bextra.setEnabled(false);
        bdiesel.setEnabled(false);
    }//GEN-LAST:event_bsuperActionPerformed

    private void bextraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bextraActionPerformed
        bsuper.setEnabled(false);
        bdiesel.setEnabled(false);
        productopreset = extran;
    }//GEN-LAST:event_bextraActionPerformed

    private void ladop2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ladop2ActionPerformed
        ladopreset = 2;
        ladop.setEnabled(false);
    }//GEN-LAST:event_ladop2ActionPerformed

    private void ladopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ladopActionPerformed
        ladop2.setEnabled(false);
        ladopreset = 1;
    }//GEN-LAST:event_ladopActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        enviarpreset();
        ladop2.setEnabled(true);
        ladop.setEnabled(true);
        bsuper.setEnabled(true);
        bdiesel.setEnabled(true);
        bextra.setEnabled(true);
        pantalla.setText("");

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de cerrar el turno?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == 0) {
            //try {
            // guardar_r_turnos();
            //informacioturno(1);
            // } catch (Exception ex) {
            //   loge.log(Priority.ERROR,getStackTrace(ex));
            // }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    }//GEN-LAST:event_jButton5ActionPerformed

    private void volumendieselsurtidor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volumendieselsurtidor6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_volumendieselsurtidor6ActionPerformed

    private void montodieselsurtidor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montodieselsurtidor3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montodieselsurtidor3ActionPerformed

    private void volumendieselsurtidor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volumendieselsurtidor3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_volumendieselsurtidor3ActionPerformed

    private void volumenextrasurtidor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volumenextrasurtidor6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_volumenextrasurtidor6ActionPerformed

    private void montosupersurtidor6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montosupersurtidor6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montosupersurtidor6ActionPerformed

    private void lado12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado12ActionPerformed

    private void lado13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado13ActionPerformed

    private void panelsurt8ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelsurt8ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_panelsurt8ComponentShown

    private void lado14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado14ActionPerformed

    private void lado15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado15ActionPerformed

    private void panelsurt9ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelsurt9ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_panelsurt9ComponentShown

    private void lado16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado16ActionPerformed

    private void lado17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado17ActionPerformed

    private void panelsurt10ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelsurt10ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_panelsurt10ComponentShown

    private void montosupersurtidor5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montosupersurtidor5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montosupersurtidor5ActionPerformed

    private void panelsurt1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelsurt1ComponentShown

        panel = 1;

        System.out.println("Panel 1");        // TODO add your handling code here:
    }//GEN-LAST:event_panelsurt1ComponentShown

    private void lado18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado18ActionPerformed

    private void lado19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado19ActionPerformed

    private void panelsurt12ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelsurt12ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_panelsurt12ComponentShown

    private void estado1disp8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado1disp8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado1disp8ActionPerformed

    private void lado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lado1ActionPerformed

    private void ladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ladoActionPerformed

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
            java.util.logging.Logger.getLogger(Surtidores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Surtidores("root", "manager").setVisible(true);
                } catch (Exception ex) {
                    //loge.log(Priority.ERROR,getStackTrace(ex));
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bdiesel;
    private javax.swing.JToggleButton bextra;
    private javax.swing.JButton borrar;
    private javax.swing.JToggleButton bsuper;
    private javax.swing.JButton cero;
    private javax.swing.JButton cinco;
    private javax.swing.JButton cuatro;
    private javax.swing.JButton dos;
    public javax.swing.JTextField estado1disp1;
    private javax.swing.JTextField estado1disp2;
    private javax.swing.JTextField estado1disp3;
    private javax.swing.JTextField estado1disp4;
    private javax.swing.JTextField estado1disp5;
    private javax.swing.JTextField estado1disp6;
    private javax.swing.JTextField estado1disp7;
    private javax.swing.JTextField estado1disp8;
    public javax.swing.JTextField estado2disp1;
    private javax.swing.JTextField estado2disp2;
    private javax.swing.JTextField estado2disp3;
    private javax.swing.JTextField estado2disp4;
    private javax.swing.JTextField estado2disp5;
    private javax.swing.JTextField estado2disp6;
    private javax.swing.JTextField estado2disp7;
    private javax.swing.JTextField estado2disp8;
    private javax.swing.JLabel f;
    public javax.swing.JTextField gaso1disp1;
    private javax.swing.JTextField gaso1disp2;
    private javax.swing.JTextField gaso1disp3;
    private javax.swing.JTextField gaso1disp4;
    private javax.swing.JTextField gaso1disp5;
    private javax.swing.JTextField gaso1disp6;
    private javax.swing.JTextField gaso1disp7;
    private javax.swing.JTextField gaso1disp8;
    public javax.swing.JTextField gaso2disp1;
    private javax.swing.JTextField gaso2disp2;
    private javax.swing.JTextField gaso2disp3;
    private javax.swing.JTextField gaso2disp4;
    private javax.swing.JTextField gaso2disp5;
    private javax.swing.JTextField gaso2disp6;
    private javax.swing.JTextField gaso2disp7;
    private javax.swing.JTextField gaso2disp8;
    private javax.swing.JLabel h2;
    public javax.swing.JTextField id1disp1;
    private javax.swing.JTextField id1disp2;
    private javax.swing.JTextField id1disp3;
    private javax.swing.JTextField id1disp4;
    private javax.swing.JTextField id1disp5;
    private javax.swing.JTextField id1disp6;
    private javax.swing.JTextField id1disp7;
    private javax.swing.JTextField id1disp8;
    public javax.swing.JTextField id2disp1;
    private javax.swing.JTextField id2disp2;
    private javax.swing.JTextField id2disp3;
    private javax.swing.JTextField id2disp4;
    private javax.swing.JTextField id2disp5;
    private javax.swing.JTextField id2disp6;
    private javax.swing.JTextField id2disp7;
    private javax.swing.JTextField id2disp8;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lado;
    private javax.swing.JTextField lado1;
    private javax.swing.JTextField lado10;
    private javax.swing.JTextField lado11;
    private javax.swing.JTextField lado12;
    private javax.swing.JTextField lado13;
    private javax.swing.JTextField lado14;
    private javax.swing.JTextField lado15;
    private javax.swing.JTextField lado16;
    private javax.swing.JTextField lado17;
    private javax.swing.JTextField lado18;
    private javax.swing.JTextField lado19;
    private javax.swing.JTextField lado6;
    private javax.swing.JTextField lado7;
    private javax.swing.JTextField lado8;
    private javax.swing.JTextField lado9;
    private javax.swing.JToggleButton ladop;
    private javax.swing.JToggleButton ladop2;
    private javax.swing.JTextArea mensajesR;
    public javax.swing.JTextField monto1disp1;
    private javax.swing.JTextField monto1disp2;
    private javax.swing.JTextField monto1disp3;
    private javax.swing.JTextField monto1disp4;
    private javax.swing.JTextField monto1disp5;
    private javax.swing.JTextField monto1disp6;
    private javax.swing.JTextField monto1disp7;
    private javax.swing.JTextField monto1disp8;
    public javax.swing.JTextField monto2disp1;
    private javax.swing.JTextField monto2disp2;
    private javax.swing.JTextField monto2disp3;
    private javax.swing.JTextField monto2disp4;
    private javax.swing.JTextField monto2disp5;
    private javax.swing.JTextField monto2disp6;
    private javax.swing.JTextField monto2disp7;
    private javax.swing.JTextField monto2disp8;
    private javax.swing.JTextField montodieselsurtidor10;
    private javax.swing.JTextField montodieselsurtidor11;
    private javax.swing.JTextField montodieselsurtidor12;
    private javax.swing.JTextField montodieselsurtidor13;
    private javax.swing.JTextField montodieselsurtidor14;
    private javax.swing.JTextField montodieselsurtidor15;
    private javax.swing.JTextField montodieselsurtidor16;
    private javax.swing.JTextField montodieselsurtidor3;
    private javax.swing.JTextField montodieselsurtidor4;
    private javax.swing.JTextField montodieselsurtidor5;
    private javax.swing.JTextField montodieselsurtidor6;
    private javax.swing.JTextField montodieselsurtidor7;
    private javax.swing.JTextField montodieselsurtidor8;
    private javax.swing.JTextField montodieselsurtidor9;
    private javax.swing.JTextField montoextrasurtidor10;
    private javax.swing.JTextField montoextrasurtidor11;
    private javax.swing.JTextField montoextrasurtidor12;
    private javax.swing.JTextField montoextrasurtidor13;
    private javax.swing.JTextField montoextrasurtidor14;
    private javax.swing.JTextField montoextrasurtidor15;
    private javax.swing.JTextField montoextrasurtidor16;
    private javax.swing.JTextField montoextrasurtidor3;
    private javax.swing.JTextField montoextrasurtidor4;
    private javax.swing.JTextField montoextrasurtidor5;
    private javax.swing.JTextField montoextrasurtidor6;
    private javax.swing.JTextField montoextrasurtidor7;
    private javax.swing.JTextField montoextrasurtidor8;
    private javax.swing.JTextField montoextrasurtidor9;
    private javax.swing.JTextField montosupersurtidor10;
    private javax.swing.JTextField montosupersurtidor11;
    private javax.swing.JTextField montosupersurtidor12;
    private javax.swing.JTextField montosupersurtidor13;
    private javax.swing.JTextField montosupersurtidor14;
    private javax.swing.JTextField montosupersurtidor15;
    private javax.swing.JTextField montosupersurtidor16;
    private javax.swing.JTextField montosupersurtidor3;
    private javax.swing.JTextField montosupersurtidor4;
    private javax.swing.JTextField montosupersurtidor5;
    private javax.swing.JTextField montosupersurtidor6;
    private javax.swing.JTextField montosupersurtidor7;
    private javax.swing.JTextField montosupersurtidor8;
    private javax.swing.JTextField montosupersurtidor9;
    private javax.swing.JLabel montoturnosurt2;
    private javax.swing.JLabel montoturnosurt3;
    private javax.swing.JLabel montoturnosurt4;
    private javax.swing.JLabel montoturnosurt5;
    private javax.swing.JLabel montoturnosurt6;
    private javax.swing.JLabel montoturnosurt7;
    private javax.swing.JLabel montoturnosurt8;
    private javax.swing.JTextField nsurtidores;
    private javax.swing.JButton nueve;
    private javax.swing.JButton ocho;
    private javax.swing.JTabbedPane panelsrt4;
    private javax.swing.JPanel panelsurt1;
    private javax.swing.JPanel panelsurt10;
    private javax.swing.JPanel panelsurt11;
    private javax.swing.JPanel panelsurt12;
    private javax.swing.JPanel panelsurt2;
    private javax.swing.JPanel panelsurt3;
    private javax.swing.JPanel panelsurt4;
    private javax.swing.JPanel panelsurt5;
    private javax.swing.JPanel panelsurt6;
    private javax.swing.JPanel panelsurt7;
    private javax.swing.JPanel panelsurt8;
    private javax.swing.JPanel panelsurt9;
    private javax.swing.JTextField pantalla;
    private javax.swing.JButton punto;
    private javax.swing.JButton seis;
    private javax.swing.JButton siete;
    private javax.swing.JPanel surtidor1;
    private javax.swing.JPanel surtidor2;
    private javax.swing.JPanel surtidor3;
    private javax.swing.JPanel surtidor4;
    private javax.swing.JPanel surtidor5;
    private javax.swing.JPanel surtidor6;
    private javax.swing.JPanel surtidor7;
    private javax.swing.JPanel surtidor8;
    private javax.swing.JPanel teclado;
    private javax.swing.JButton tres;
    private javax.swing.JButton uno;
    public javax.swing.JTextField volumen1disp1;
    private javax.swing.JTextField volumen1disp2;
    private javax.swing.JTextField volumen1disp3;
    private javax.swing.JTextField volumen1disp4;
    private javax.swing.JTextField volumen1disp5;
    private javax.swing.JTextField volumen1disp6;
    private javax.swing.JTextField volumen1disp7;
    private javax.swing.JTextField volumen1disp8;
    public javax.swing.JTextField volumen2disp1;
    private javax.swing.JTextField volumen2disp2;
    private javax.swing.JTextField volumen2disp3;
    private javax.swing.JTextField volumen2disp4;
    private javax.swing.JTextField volumen2disp5;
    private javax.swing.JTextField volumen2disp6;
    private javax.swing.JTextField volumen2disp7;
    private javax.swing.JTextField volumen2disp8;
    private javax.swing.JTextField volumendieselsurtidor10;
    private javax.swing.JTextField volumendieselsurtidor11;
    private javax.swing.JTextField volumendieselsurtidor12;
    private javax.swing.JTextField volumendieselsurtidor13;
    private javax.swing.JTextField volumendieselsurtidor14;
    private javax.swing.JTextField volumendieselsurtidor15;
    private javax.swing.JTextField volumendieselsurtidor16;
    private javax.swing.JTextField volumendieselsurtidor3;
    private javax.swing.JTextField volumendieselsurtidor4;
    private javax.swing.JTextField volumendieselsurtidor5;
    private javax.swing.JTextField volumendieselsurtidor6;
    private javax.swing.JTextField volumendieselsurtidor7;
    private javax.swing.JTextField volumendieselsurtidor8;
    private javax.swing.JTextField volumendieselsurtidor9;
    private javax.swing.JTextField volumenextrasurtidor10;
    private javax.swing.JTextField volumenextrasurtidor11;
    private javax.swing.JTextField volumenextrasurtidor12;
    private javax.swing.JTextField volumenextrasurtidor13;
    private javax.swing.JTextField volumenextrasurtidor14;
    private javax.swing.JTextField volumenextrasurtidor15;
    private javax.swing.JTextField volumenextrasurtidor16;
    private javax.swing.JTextField volumenextrasurtidor3;
    private javax.swing.JTextField volumenextrasurtidor4;
    private javax.swing.JTextField volumenextrasurtidor5;
    private javax.swing.JTextField volumenextrasurtidor6;
    private javax.swing.JTextField volumenextrasurtidor7;
    private javax.swing.JTextField volumenextrasurtidor8;
    private javax.swing.JTextField volumenextrasurtidor9;
    private javax.swing.JTextField volumensupersurtidor10;
    private javax.swing.JTextField volumensupersurtidor11;
    private javax.swing.JTextField volumensupersurtidor12;
    private javax.swing.JTextField volumensupersurtidor13;
    private javax.swing.JTextField volumensupersurtidor14;
    private javax.swing.JTextField volumensupersurtidor15;
    private javax.swing.JTextField volumensupersurtidor16;
    private javax.swing.JTextField volumensupersurtidor3;
    private javax.swing.JTextField volumensupersurtidor4;
    private javax.swing.JTextField volumensupersurtidor5;
    private javax.swing.JTextField volumensupersurtidor6;
    private javax.swing.JTextField volumensupersurtidor7;
    private javax.swing.JTextField volumensupersurtidor8;
    private javax.swing.JTextField volumensupersurtidor9;
    private javax.swing.JLabel volumenturnosurt2;
    private javax.swing.JLabel volumenturnosurt3;
    private javax.swing.JLabel volumenturnosurt4;
    private javax.swing.JLabel volumenturnosurt5;
    private javax.swing.JLabel volumenturnosurt6;
    private javax.swing.JLabel volumenturnosurt7;
    private javax.swing.JLabel volumenturnosurt8;
    // End of variables declaration//GEN-END:variables

    public void comando(int comando) {
        try {
            RandomAccessFile pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            //in = new BufferedReader(new FileReader("\\\\"+ip+"\\pipe\\CEM44POSPIPE"));
            System.out.println("Comando=====> " + comando);

            pipe.write(comando);

            // bytesRead = pipe.read(respuesta, 0, respuesta.length);
            int men = pipe.read();
            //            int men1= in.read();
            System.out.println(respuesta1disp1.toString());

            String resp = "";
            for (int i = 0; i < bytesRead1disp1; i++) {
                resp += String.valueOf((int) (respuesta1disp1[i]));

            }

            //mensaje.setText("Response: " + men);
            pipe.close();
        } catch (Exception ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
        }

    }

    private void AddCheckSum() throws Exception {
        int sum = 0;
        int lrc = 0;

        for (int i = 0; i < comando1disp1.length; i++) {
            sum += (int) (comando1disp1[i]);
        }

        lrc = sum + 1;
        lrc = lrc & 0xff;

        comando1disp1[2] = (byte) lrc;

    }

    private void crearcomando(int IdCommand) throws Exception {

        comando1disp1[0] = (byte) IdCommand;

        comando1disp1[1] = (byte) FSL;

        AddCheckSum();
    }
    RandomAccessFile pipe;
    ///////////////////////////////////////////////////////////////////////////

    public void generarlado1dispensador1(int c1disp1) throws Exception {
        String ppu1 = "";
        try {

            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comando1disp1[0] = (byte) c1disp1;

            comando1disp1[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comando1disp1.length; i++) {
                sum += (int) (comando1disp1[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comando1disp1[2] = (byte) lrc;

            pipe.write(comando1disp1, 0, comando1disp1.length);

            bytesRead1disp1 = pipe.read(respuesta1disp1, 0, respuesta1disp1.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String resp = "", monto1, volu1;

            int cont = 4;
            for (int i = 0; i < bytesRead1disp1; i++) {

                resp += (char) (respuesta1disp1[i]);

            }

            int s1 = resp.indexOf("~");

            if (respuesta1disp1[1] == 1 || respuesta1disp1[1] == 5 || respuesta1disp1[1] == 0) {

                estado1disp1.setText("Disponible");

                estado1disp1.setForeground(Color.black);
                estado1disp1.setBackground(Color.orange);
                cont1 = 0;
                // timerRTM1dispensador1.stop();

                monto1 = resp.substring(4, s1);

                monto1disp1.setText(monto1);

                int s2 = resp.indexOf("~", s1 + 1);

                volu1 = resp.substring(s1 + 1, s2);

                int s3 = resp.indexOf("~", s2 + 1);

                ppu1 = resp.substring(s2 + 1, s3);

                if (ppu1.equalsIgnoreCase(pextra)) {

                    gaso1disp1.setText("Extra");

                }
                if (ppu1.equalsIgnoreCase(psuper)) {

                    gaso1disp1.setText("Super");

                }

                if (ppu1.equalsIgnoreCase(pdiesel)) {

                    gaso1disp1.setText("Diesel");

                }

                volumen1disp1.setText(volu1);

                double monto, iva, total, subtotal;
                String idv1, idv2;

                int s4 = resp.indexOf("~", s3 + 1);

                idv1 = resp.substring(s3 + 2, s4);

                int s5 = resp.indexOf("~", s4 + 1);

                idv2 = resp.substring(s4 + 1, s5);

                if (id1disp1.getText().length() > 0) {

                    if (id1disp1.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        System.out.println("Venta ");

                        // System.out.println("venta");
                        String producto = gaso1disp1.getText();
                        monto = Math.rint(Double.parseDouble(monto1disp1.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        if (total >= 0.05) {

                            if (gaso1disp1.getText().equalsIgnoreCase("Extra")) {

                                rtm1 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 1, mextrasurtidor1, producto, volumen1disp1.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                ////fe.start()

                            }
                            if (gaso1disp1.getText().equalsIgnoreCase("Super")) {
                                rtm1 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 1, msupersurtidor1, producto, volumen1disp1.getText(), ppu1, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp1.getText().equalsIgnoreCase("Diesel")) {
                                rtm1 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 1, mdieselsurtidor1, producto, volumen1disp1.getText(), ppu1, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()
                            }

                        }

                        rtm1 = 0.00;

                    }
                }
                id1disp1.setText(idv1);

            }
            if (respuesta1disp1[1] == 3) {

                cont1++;
                estado1disp1.setText("Despachando");

                //timerRTM1dispensador1.start();
                estado1disp1.setBackground(Color.green);

                monto1disp1.setText("");

                gaso1disp1.setText("");
                //id1disp1.setText("");

                volumen1disp1.setText("");

                if (id1disp1.getText().length() == 0) {
                    id1disp1.setText("0000001");
                }

            }

            if (respuesta1disp1[1] == 2) {

                estado1disp1.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor1 + " or nmanguera=" + msupersurtidor1 + " or nmanguera=" + mdieselsurtidor1 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor1 + " or nmanguera=" + msupersurtidor1 + " or nmanguera=" + mdieselsurtidor1 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {

                            autorizar(65);

                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor1 + " or nmanguera=" + msupersurtidor1 + " or nmanguera=" + mdieselsurtidor1 + ";";

                            st2.executeUpdate(consulta1);

                        } else {
                        }

                    } else {
                    }

                    st_d.close();

                } catch (Exception ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta1disp1[1] == 4) {

                estado1disp1.setText("Autorizado");

            }

            if (respuesta1disp1[1] == 8) {

                estado1disp1.setText("Defectuoso");
                estado1disp1.setForeground(Color.red);

            }

            if (respuesta1disp1[1] == 9) {

                estado1disp1.setText("Anulado");

            }

            if (respuesta1disp1[1] == 10) {

                estado1disp1.setText("Detenido");

            }

        } catch (Exception ex) {
            cont++;

        }

        servidor.setdatos1(estado1disp1.getText(), gaso1disp1.getText(), monto1disp1.getText(), volumen1disp1.getText(), ppu1);

    }

    public void generarlado2dispensador1(int c2disp1) throws Exception {
        String resp1 = "", volu2, mont2, resp2 = null, ppu2 = "";
        try {
            //RandomAccessFile pipe1 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");
            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comando2disp1[0] = (byte) c2disp1;

            comando2disp1[1] = (byte) FSL;

            int sum1 = 0;
            int lrc1 = 0;

            for (int i = 0; i < comando2disp1.length; i++) {
                sum1 += (int) (comando2disp1[i]);
            }

            lrc1 = sum1 + 1;
            lrc1 = lrc1 & 0xff;

            comando2disp1[2] = (byte) lrc1;

            pipe.write(comando2disp1, 0, comando2disp1.length);

            bytesRead2disp1 = pipe.read(respuesta2disp1, 0, respuesta2disp1.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String p1 = null;

            for (int i = 0; i < bytesRead2disp1; i++) {

                resp1 += (char) (respuesta2disp1[i]);
                p1 += (char) (respuesta2disp1[i]);

            }

            int s2 = resp1.indexOf("~");

            if (respuesta2disp1[1] == 1 || respuesta2disp1[1] == 5) {

                estado2disp1.setText("Disponible");
                estado2disp1.setBackground(Color.orange);
                estado2disp1.setForeground(Color.black);

                // timerRTM2dispensador1.stop();
                mont2 = resp1.substring(4, s2);

                monto2disp1.setText(mont2);

                int s3 = resp1.indexOf("~", s2 + 1);

                volu2 = resp1.substring(s2 + 1, s3);

                int s4 = resp1.indexOf("~", s3 + 1);

                ppu2 = resp1.substring(s3 + 1, s4);

                String idv1, idv2;

                int s5 = resp1.indexOf("~", s4 + 1);

                idv1 = resp1.substring(s4 + 2, s5);

                int s6 = resp1.indexOf("~", s5 + 1);

                idv2 = resp1.substring(s5 + 1, s6);

                volumen2disp1.setText(volu2);

                if (ppu2.equalsIgnoreCase(pextra)) {

                    gaso2disp1.setText("Extra");

                }
                if (ppu2.equalsIgnoreCase(psuper)) {

                    gaso2disp1.setText("Super");

                }

                if (ppu2.equalsIgnoreCase(pdiesel)) {

                    gaso2disp1.setText("Diesel");

                }

                double monto, iva, total, subtotal;

                if (id2disp1.getText().length() > 0) {

                    if (id2disp1.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso2disp1.getText();
                        monto = Math.rint(Double.parseDouble(monto2disp1.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        if (total >= 0.05) {

                            if (gaso2disp1.getText().equalsIgnoreCase("Extra")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 2, mextrasurtidor2, producto, volumen2disp1.getText(), ppu2, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()
                                rtm2 = 0.00;
                            }
                            if (gaso2disp1.getText().equalsIgnoreCase("Super")) {
                                rtm2 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 2, msupersurtidor2, producto, volumen2disp1.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso2disp1.getText().equalsIgnoreCase("Diesel")) {
                                rtm2 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 2, mdieselsurtidor2, producto, volumen2disp1.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            rtm2 = 0.00;

                        }

                    }
                }
                id2disp1.setText(idv1);

            }
            if (respuesta2disp1[1] == 3) {

                estado2disp1.setText("Despachando");

                //timerRTM2dispensador1.start();
                gaso2disp1.setText("");
                monto2disp1.setText("");
                //id2disp1.setText("");
                estado2disp1.setBackground(Color.green);
                volumen2disp1.setText("");

                if (id2disp1.getText().length() == 0) {
                    id2disp1.setText("0000001");
                }

            }

            if (respuesta2disp1[1] == 2) {

                estado2disp1.setText("Solicitud");

                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor2 + " or nmanguera=" + msupersurtidor2 + " or nmanguera=" + mdieselsurtidor2 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor2 + " or nmanguera=" + msupersurtidor2 + " or nmanguera=" + mdieselsurtidor2 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {

                            autorizar(66);

                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor2 + " or nmanguera=" + msupersurtidor2 + " or nmanguera=" + mdieselsurtidor2 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }
                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta2disp1[1] == 4) {

                estado2disp1.setText("Autorizado");

            }

            if (respuesta2disp1[1] == 8) {

                estado2disp1.setText("Defectuoso");
                estado2disp1.setForeground(Color.red);

            }

            if (respuesta2disp1[1] == 9) {

                estado2disp1.setText("Anulado");

            }

            if (respuesta2disp1[1] == 10) {

                estado2disp1.setText("Detenido");

            }

            int con = respuesta2disp1[2];

            int con1 = respuesta2disp1[3];

        } catch (Exception ex) {

            cont++;

        }

        servidor.setdatos2(estado2disp1.getText(), gaso2disp1.getText(), monto2disp1.getText(), volumen2disp1.getText(), ppu2);
    }

    public void generarRtm1disp1(int rtm1disp1) throws Exception {
        try {
            try (
                    RandomAccessFile pipertm = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw")) {

                comandoRTM1disp1[0] = (byte) rtm1disp1;

                comandoRTM1disp1[1] = (char) 48;

                comandoRTM1disp1[2] = (char) 48;

                comandoRTM1disp1[3] = (char) 51;

                comandoRTM1disp1[4] = (char) 48;

                comandoRTM1disp1[5] = (char) 49;

                //        comandoRTM1[6] = (byte) FSL;
                int sumr = 0;
                int lrcr = 0;

                for (int i = 0; i < comandoRTM1disp1.length; i++) {

                    sumr += (int) (comandoRTM1disp1[i]);
                }

                lrcr = sumr + 1;

                lrcr = lrcr & 0xff;

                //comandoRTM1[7] = (byte) lrcr;
                pipertm.write(comandoRTM1disp1, 0, comandoRTM1disp1.length);

                bytesRTM1disp1 = pipertm.read(respuestaRTM1disp1, 0, respuestaRTM1disp1.length);
            }

            String respRTM = "", monto = "";

            for (int i = 0; i < bytesRTM1disp1; i++) {

                respRTM += (char) (respuestaRTM1disp1[i]);

            }

            int s2 = respRTM.indexOf("~");

            monto = respRTM.substring(1, s2);

            if (monto.equalsIgnoreCase("-0000001")) {

                monto1disp1.setText("0.00");

            } else {
                monto1disp1.setText(monto);
                rtm1 = Double.parseDouble(monto1disp1.getText());

            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes  " + cont);
        }

    }

    public void generarRtm2disp1(int rtm2disp1) throws Exception {
        try {
            RandomAccessFile pipertm2 = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandoRTM2disp1[0] = (byte) rtm2disp1;

            comandoRTM2disp1[1] = (char) 48;

            comandoRTM2disp1[2] = (char) 48;

            comandoRTM2disp1[3] = (char) 51;

            comandoRTM2disp1[4] = (char) 48;

            comandoRTM2disp1[5] = (char) 50;

            //        comandoRTM1[6] = (byte) FSL;
            int sumr1 = 0;
            int lrcr1 = 0;

            for (int i = 0; i < comandoRTM2disp1.length; i++) {

                sumr1 += (int) (comandoRTM2disp1[i]);
            }

            lrcr1 = sumr1 + 1;

            lrcr1 = lrcr1 & 0xff;

            //comandoRTM1[7] = (byte) lrcr;
            pipertm2.write(comandoRTM2disp1, 0, comandoRTM2disp1.length);

            bytesRTM2disp1 = pipertm2.read(respuestaRTM2disp1, 0, respuestaRTM2disp1.length);
            //bytesRead = pipe.read(respuesta);

            pipertm2.close();

            String respRTM1 = "", monto1 = "";

            for (int i = 0; i < bytesRTM2disp1; i++) {

                respRTM1 += (char) (respuestaRTM2disp1[i]);
            }

            int s2 = respRTM1.indexOf("~");

            monto1 = respRTM1.substring(1, s2);

            if (monto1.equalsIgnoreCase("-0000001")) {

                monto2disp1.setText("0.00");

            } else {
                monto2disp1.setText(monto1);
                rtm2 = Double.valueOf(monto2disp1.getText());
            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes" + cont);
        }

    }
/////////////////////////////////////////////////////////////////////////////////

    public void autorizar(int infor) throws Exception {
        try {
            RandomAccessFile pipeinfor = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandoinfor[0] = (byte) infor;

            comandoinfor[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comandoinfor.length; i++) {
                sum += (int) (comandoinfor[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comandoinfor[2] = (byte) lrc;

            pipeinfor.write(comandoinfor, 0, comandoinfor.length);
            bytesReadinfor = pipeinfor.read(respuestainfor, 0, respuestainfor.length);
            //bytesRead = pipe.read(respuesta);

            pipeinfor.close();

            String resp = "", monto1, volu1;
            int cont = 4;
            for (int i = 0; i < bytesReadinfor; i++) {

                resp += (char) (respuestainfor[i]);
            }

            int s1 = resp.indexOf("~");

            String respRTM = "", montodia1 = "";

            //ontodia1 = respRTM.substring(2, s1);
            // montoturnosurt1.setText(montodia1);
        } catch (Exception ex) {
            autorizar(infor);
        }
    }
    ///////////////////////////////////////////////////////////////////////////

    public void generarlado1dispensador2(int c1disp2) throws Exception {
        String ppu2 = "";
        try {
            //RandomAccessFile pipe1disp2 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");
            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            comando1disp2[0] = (byte) c1disp2;

            comando1disp2[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comando1disp2.length; i++) {
                sum += (int) (comando1disp2[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comando1disp2[2] = (byte) lrc;

            pipe.write(comando1disp2, 0, comando1disp2.length);
            bytesRead1disp2 = pipe.read(respuesta1disp2, 0, respuesta1disp2.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String resp = "", monto1, volu1;

            int cont = 4;
            for (int i = 0; i < bytesRead1disp2; i++) {

                resp += (char) (respuesta1disp2[i]);
            }

            int s1 = resp.indexOf("~");

            if (respuesta1disp2[1] == 1 || respuesta1disp2[1] == 5) {

                estado1disp2.setText("Disponible");

                estado1disp2.setForeground(Color.black);
                estado1disp2.setBackground(Color.orange);

                cont1 = 0;

                // timerRTM1dispensador2.stop();
                monto1 = resp.substring(4, s1);

                monto1disp2.setText(monto1);

                int s2 = resp.indexOf("~", s1 + 1);

                volu1 = resp.substring(s1 + 1, s2);

                int s3 = resp.indexOf("~", s2 + 1);

                ppu2 = resp.substring(s2 + 1, s3);

                String idv1, idv2;

                int s4 = resp.indexOf("~", s3 + 1);

                idv1 = resp.substring(s3 + 2, s4);

                int s5 = resp.indexOf("~", s4 + 1);

                idv2 = resp.substring(s4 + 1, s5);

                volumen1disp2.setText(volu1);

                if (ppu2.equalsIgnoreCase(pextra)) {

                    gaso1disp2.setText("Extra");

                }
                if (ppu2.equalsIgnoreCase(psuper)) {

                    gaso1disp2.setText("Super");

                }

                if (ppu2.equalsIgnoreCase(pdiesel)) {

                    gaso1disp2.setText("Diesel");

                }

                double monto, iva, total, subtotal;

                if (id1disp2.getText().length() > 0) {

                    if (id1disp2.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso1disp2.getText();
                        monto = Math.rint(Double.parseDouble(monto1disp2.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        if (total >= 0.05) {

                            if (gaso1disp2.getText().equalsIgnoreCase("Extra")) {

                                rtm3 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 3, mextrasurtidor3, producto, volumen1disp2.getText(), ppu2, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp2.getText().equalsIgnoreCase("Super")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 3, msupersurtidor3, producto, volumen1disp2.getText(), ppu2, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()
                                rtm3 = 0.00;
                            }
                            if (gaso1disp2.getText().equalsIgnoreCase("Diesel")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 3, mdieselsurtidor3, producto, volumen1disp2.getText(), ppu2, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()

                                rtm3 = 0.00;
                            }

                        }
                        rtm3 = 0.00;

                    }
                }
                id1disp2.setText(idv1);

            }
            if (respuesta1disp2[1] == 3) {

                cont1++;
                estado1disp2.setText("Despachando");

                //  timerRTM1dispensador2.start();
                gaso1disp2.setText("");
                //id1disp2.setText("");
                monto1disp2.setText("");
                estado1disp2.setBackground(Color.green);
                volumen1disp2.setText("");

                if (id1disp2.getText().length() == 0) {
                    id1disp2.setText("0000001");
                }

            }

            if (respuesta1disp2[1] == 2) {

                estado1disp2.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor3 + " or nmanguera=" + msupersurtidor3 + " or nmanguera=" + mdieselsurtidor3 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor3 + " or nmanguera=" + msupersurtidor3 + " or nmanguera=" + mdieselsurtidor3 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {

                            autorizar(67);
                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor3 + " or nmanguera=" + msupersurtidor3 + " or nmanguera=" + mdieselsurtidor3 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }

                    } else {
                    }

                    st_d.close();

                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta1disp2[1] == 4) {

                estado1disp2.setText("Autorizado");

            }

            if (respuesta1disp2[1] == 8) {

                estado1disp2.setText("Defectuoso");
                estado1disp2.setForeground(Color.red);

            }

            if (respuesta1disp2[1] == 9) {

                estado1disp2.setText("Anulado");

            }

            if (respuesta1disp2[1] == 10) {

                estado1disp2.setText("Detenido");
                estado1disp2.setForeground(Color.red);

            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes" + cont);
        }

        servidor.setdatos3(estado1disp2.getText(), gaso1disp2.getText(), monto1disp2.getText(), volumen1disp2.getText(), ppu2);

    }

    public void generarlado2dispensador2(int c2disp2) throws Exception {
        String ppu1 = "";
        try {
            //RandomAccessFile pipe2disp2 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");

            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            comando2disp2[0] = (byte) c2disp2;

            comando2disp2[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comando2disp2.length; i++) {
                sum += (int) (comando2disp2[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comando2disp2[2] = (byte) lrc;

            pipe.write(comando2disp2, 0, comando2disp2.length);
            bytesRead2disp2 = pipe.read(respuesta2disp2, 0, respuesta2disp2.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String resp = "", monto1, volu1;

            int cont = 4;
            for (int i = 0; i < bytesRead2disp2; i++) {

                resp += (char) (respuesta2disp2[i]);
            }

            int s1 = resp.indexOf("~");

            if (respuesta2disp2[1] == 1 || respuesta2disp2[1] == 5) {

                estado2disp2.setText("Disponible");

                estado2disp2.setForeground(Color.black);

                cont1 = 0;

                // timerRTM2dispensador2.stop();
                monto1 = resp.substring(4, s1);

                estado2disp2.setBackground(Color.orange);

                monto2disp2.setText(monto1);

                int s2 = resp.indexOf("~", s1 + 1);

                volu1 = resp.substring(s1 + 1, s2);

                int s3 = resp.indexOf("~", s2 + 1);

                ppu1 = resp.substring(s2 + 1, s3);

                String idv1, idv2;

                int s4 = resp.indexOf("~", s3 + 1);

                idv1 = resp.substring(s3 + 2, s4);

                int s5 = resp.indexOf("~", s4 + 1);

                idv2 = resp.substring(s4 + 1, s5);

                volumen2disp2.setText(volu1);

                if (ppu1.equalsIgnoreCase(pextra)) {

                    gaso2disp2.setText("Extra");

                }
                if (ppu1.equalsIgnoreCase(psuper)) {

                    gaso2disp2.setText("Super");

                }

                if (ppu1.equalsIgnoreCase(pdiesel)) {

                    gaso2disp2.setText("Diesel");

                }

                double monto, iva, total, subtotal;

                if (id2disp2.getText().length() > 0) {

                    if (id2disp2.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso2disp2.getText();
                        monto = Math.rint(Double.parseDouble(monto2disp2.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        rtm4 = 0.00;

                        if (total >= 0.05) {

                            if (gaso2disp2.getText().equalsIgnoreCase("Extra")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 4, mextrasurtidor4, producto, volumen2disp2.getText(), ppu1, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()

                                rtm4 = 0.00;

                            }
                            if (gaso2disp2.getText().equalsIgnoreCase("Super")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 4, msupersurtidor4, producto, volumen2disp2.getText(), ppu1, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()
                                rtm4 = 0.00;

                            }
                            if (gaso2disp2.getText().equalsIgnoreCase("Diesel")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 4, mdieselsurtidor4, producto, volumen2disp2.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm4 = 0.00;
                            }

                        }
                    }
                }

                id2disp2.setText(idv1);

            }
            if (respuesta2disp2[1] == 3) {

                cont1++;
                estado2disp2.setText("Despachando");

                //timerRTM2dispensador2.start();
                estado2disp2.setBackground(Color.green);
                gaso2disp2.setText("");
                monto2disp2.setText("");
                // id2disp2.setText("");

                volumen2disp2.setText("");

                if (id2disp2.getText().length() == 0) {
                    id2disp2.setText("0000001");

                }

            }

            if (respuesta2disp2[1] == 2) {

                estado2disp2.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor4 + " or nmanguera=" + msupersurtidor4 + " or nmanguera=" + mdieselsurtidor4 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor4 + " or nmanguera=" + msupersurtidor4 + " or nmanguera=" + mdieselsurtidor4 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {

                            autorizar(68);

                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor4 + " or nmanguera=" + msupersurtidor4 + " or nmanguera=" + mdieselsurtidor4 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }
                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta2disp2[1] == 4) {

                estado2disp2.setText("Autorizado");

            }

            if (respuesta2disp2[1] == 8) {

                estado2disp2.setText("Defectuoso");
                estado2disp2.setForeground(Color.red);

            }

            if (respuesta2disp2[1] == 9) {

                estado2disp2.setText("Anulado");

            }

            if (respuesta2disp2[1] == 10) {

                estado2disp2.setText("Detenido");

            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes" + cont);
        }

        servidor.setdatos4(estado2disp2.getText(), gaso2disp2.getText(), monto2disp2.getText(), volumen2disp2.getText(), ppu1);
    }

    public void generarRtm1disp2(int rtm1disp2) throws Exception {
        try {

            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            //RandomAccessFile pipertm1disp2 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");

            comandoRTM1disp2[0] = (byte) rtm1disp2;

            comandoRTM1disp2[1] = (char) 48;

            comandoRTM1disp2[2] = (char) 48;

            comandoRTM1disp2[3] = (char) 51;

            comandoRTM1disp2[4] = (char) 48;

            comandoRTM1disp2[5] = (char) 51;

            //        comandoRTM1[6] = (byte) FSL;
            int sumr = 0;
            int lrcr = 0;

            for (int i = 0; i < comandoRTM1disp2.length; i++) {

                sumr += (int) (comandoRTM1disp2[i]);
            }

            lrcr = sumr + 1;

            lrcr = lrcr & 0xff;

            //comandoRTM1[7] = (byte) lrcr;
            pipe.write(comandoRTM1disp2, 0, comandoRTM1disp2.length);

            bytesRTM1disp2 = pipe.read(respuestaRTM1disp2, 0, respuestaRTM1disp2.length);
            //bytesRead = pi pe.read(respuesta);

            pipe.close();

            String respRTM = "", monto1dispe2 = "";

            for (int i = 0; i < bytesRTM1disp2; i++) {

                respRTM += (char) (respuestaRTM1disp2[i]);

            }

            int s2 = respRTM.indexOf("~");

            monto1dispe2 = respRTM.substring(1, s2);

            if (monto1dispe2.equalsIgnoreCase("-0000001")) {

                monto1disp2.setText("0.00");

            } else {
                monto1disp2.setText(monto1dispe2);
                rtm3 = Double.valueOf(monto1disp2.getText());

            }

        } catch (Exception ex) {
            cont++;
            // System.out.println("Se Cruzaron 2 pipes  " + cont);
        }

    }

    public void generarRtm2disp2(int rtm2disp2) throws Exception {
        try {

            RandomAccessFile pipertm2disp2 = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandoRTM2disp2[0] = (byte) rtm2disp2;

            comandoRTM2disp2[1] = (char) 48;

            comandoRTM2disp2[2] = (char) 48;

            comandoRTM2disp2[3] = (char) 51;

            comandoRTM2disp2[4] = (char) 48;

            comandoRTM2disp2[5] = (char) 52;

            //        comandoRTM1[6] = (byte) FSL;
            int sumr = 0;
            int lrcr = 0;

            for (int i = 0; i < comandoRTM2disp2.length; i++) {

                sumr += (int) (comandoRTM2disp2[i]);
            }

            lrcr = sumr + 1;

            lrcr = lrcr & 0xff;

            //comandoRTM1[7] = (byte) lrcr;
            pipertm2disp2.write(comandoRTM2disp2, 0, comandoRTM2disp2.length);

            bytesRTM2disp2 = pipertm2disp2.read(respuestaRTM2disp2, 0, respuestaRTM2disp2.length);
            //bytesRead = pipe.read(respuesta);

            pipertm2disp2.close();

            String respRTM4 = "", monto2dispe2 = "";

            for (int i = 0; i < bytesRTM2disp2; i++) {

                respRTM4 += (char) (respuestaRTM2disp2[i]);

            }

            int s2 = respRTM4.indexOf("~");

            monto2dispe2 = respRTM4.substring(1, s2);

            if (monto2dispe2.equalsIgnoreCase("-0000001")) {

                monto2disp2.setText("0.00");

            } else {

                monto2disp2.setText(monto2dispe2);
                rtm4 = Double.valueOf(monto2disp2.getText());

            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes  " + cont);
        }

    }

////////////////////////////////////////////////////////////////////////////////
    public void generarlado1dispensador3(int c1disp3) throws Exception {

        String ppu2 = "";
        try {
            //RandomAccessFile pipe1disp2 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");
            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            comando1disp3[0] = (byte) c1disp3;

            comando1disp3[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comando1disp3.length; i++) {
                sum += (int) (comando1disp3[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comando1disp3[2] = (byte) lrc;

            pipe.write(comando1disp3, 0, comando1disp3.length);
            bytesRead1disp3 = pipe.read(respuesta1disp3, 0, respuesta1disp3.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String resp = "", monto1, volu1;

            int cont = 4;
            for (int i = 0; i < bytesRead1disp3; i++) {

                resp += (char) (respuesta1disp3[i]);
            }

            int s1 = resp.indexOf("~");

            if (respuesta1disp3[1] == 1 || respuesta1disp3[1] == 5) {

                estado1disp3.setText("Disponible");

                estado1disp3.setForeground(Color.black);
                estado1disp3.setBackground(Color.orange);

                cont1 = 0;

                //  timerRTM1dispensador3.stop();
                monto1 = resp.substring(4, s1);

                if (monto1.substring(0, 1).equalsIgnoreCase("a")) {

                    monto1 = monto1.substring(1, monto1.length());
                }
                if (monto1.substring(0, 1).equals("b")) {

                    monto1 = monto1.substring(1, monto1.length());
                }
                if (monto1.substring(0, 1).equalsIgnoreCase("c")) {

                    monto1 = monto1.substring(1, monto1.length());
                }

                monto1disp3.setText(monto1);

                int s2 = resp.indexOf("~", s1 + 1);

                volu1 = resp.substring(s1 + 1, s2);

                int s3 = resp.indexOf("~", s2 + 1);

                ppu2 = resp.substring(s2 + 1, s3);

                String idv1, idv2;

                int s4 = resp.indexOf("~", s3 + 1);

                idv1 = resp.substring(s3 + 2, s4);

                int s5 = resp.indexOf("~", s4 + 1);

                idv2 = resp.substring(s4 + 1, s5);

                volumen1disp3.setText(volu1);

                if (ppu2.equalsIgnoreCase(pextra)) {

                    gaso1disp3.setText("Extra");

                }
                if (ppu2.equalsIgnoreCase(psuper)) {

                    gaso1disp3.setText("Super");

                }

                if (ppu2.equalsIgnoreCase(pdiesel)) {

                    gaso1disp3.setText("Diesel");

                }

                double monto, iva, total, subtotal;

                if (id1disp3.getText().length() > 0) {
                    if (id1disp3.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso1disp3.getText();
                        monto = Math.rint(Double.parseDouble(monto1disp3.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;
                        //System.out.println(total);

                        if (total >= 0.05) {
                            if (gaso1disp3.getText().equalsIgnoreCase("Extra")) {

                                rtm5 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 5, mextrasurtidor5, producto, volumen1disp3.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp3.getText().equalsIgnoreCase("Super")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 5, msupersurtidor5, producto, volumen1disp3.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm5 = 0.00;
                            }
                            if (gaso1disp3.getText().equalsIgnoreCase("Diesel")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 3, mdieselsurtidor5, producto, volumen1disp3.getText(), ppu2, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()
                                rtm5 = 0.00;

                            }

                        }
                        rtm5 = 0.00;

                    }
                }

                id1disp3.setText(idv1);

            }
            if (respuesta1disp3[1] == 3) {

                cont1++;
                estado1disp3.setText("Despachando");

//                timerRTM1dispensador3.start();
                gaso1disp3.setText("");
                // id1disp3.setText("");
                monto1disp3.setText("");
                estado1disp3.setBackground(Color.green);
                volumen1disp3.setText("");

                if (id1disp3.getText().length() == 0) {
                    id1disp3.setText("0000001");
                }

            }

            if (respuesta1disp3[1] == 2) {

                estado1disp3.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor5 + " or nmanguera=" + msupersurtidor5 + " or nmanguera=" + mdieselsurtidor5 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor5 + " or nmanguera=" + msupersurtidor5 + " or nmanguera=" + mdieselsurtidor5 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {

                            autorizar(69);

                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor5 + " or nmanguera=" + msupersurtidor5 + " or nmanguera=" + mdieselsurtidor5 + ";";

                            st2.executeUpdate(consulta1);

                        } else {
                        }

                    } else {
                    }

                    st_d.close();

                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta1disp3[1] == 4) {

                estado1disp3.setText("Autorizado");

            }

            if (respuesta1disp3[1] == 8) {

                estado1disp3.setText("Defectuoso");
                estado1disp3.setForeground(Color.red);

            }

            if (respuesta1disp3[1] == 9) {

                estado1disp3.setText("Anulado");

            }

            if (respuesta1disp3[1] == 10) {

                estado1disp3.setText("Detenido");
                estado1disp3.setForeground(Color.red);

            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes" + cont);
        }

        servidor.setdatos5(estado1disp3.getText(), gaso1disp3.getText(), monto1disp3.getText(), volumen1disp3.getText(), ppu2);

    }

    public void generarlado2dispensador3(int c2disp3) throws Exception {

        String ppu1 = "";
        try {
            //RandomAccessFile pipe2disp2 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");

            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            comando2disp3[0] = (byte) c2disp3;

            comando2disp3[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comando2disp3.length; i++) {
                sum += (int) (comando2disp3[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comando2disp3[2] = (byte) lrc;

            pipe.write(comando2disp3, 0, comando2disp3.length);
            bytesRead2disp3 = pipe.read(respuesta2disp3, 0, respuesta2disp3.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String resp = "", monto1, volu1;

            int cont = 4;
            for (int i = 0; i < bytesRead2disp3; i++) {

                resp += (char) (respuesta2disp3[i]);
            }

            int s1 = resp.indexOf("~");

            if (respuesta2disp3[1] == 1 || respuesta2disp3[1] == 5) {

                estado2disp3.setText("Disponible");

                estado2disp3.setForeground(Color.black);

                cont1 = 0;

                // timerRTM2dispensador3.stop();
                monto1 = resp.substring(4, s1);

                estado2disp3.setBackground(Color.orange);

                monto2disp3.setText(monto1);

                int s2 = resp.indexOf("~", s1 + 1);

                volu1 = resp.substring(s1 + 1, s2);

                int s3 = resp.indexOf("~", s2 + 1);

                ppu1 = resp.substring(s2 + 1, s3);

                String idv1, idv2;

                int s4 = resp.indexOf("~", s3 + 1);

                idv1 = resp.substring(s3 + 2, s4);

                int s5 = resp.indexOf("~", s4 + 1);

                idv2 = resp.substring(s4 + 1, s5);

                volumen2disp3.setText(volu1);

                if (ppu1.equalsIgnoreCase(pextra)) {

                    gaso2disp3.setText("Extra");

                }
                if (ppu1.equalsIgnoreCase(psuper)) {

                    gaso2disp3.setText("Super");

                }

                if (ppu1.equalsIgnoreCase(pdiesel)) {

                    gaso2disp3.setText("Diesel");

                }

                double monto, iva, total, subtotal;

                if (id2disp3.getText().length() > 0) {
                    if (id2disp3.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso2disp3.getText();
                        monto = Math.rint(Double.parseDouble(monto2disp3.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        if (total >= 0.05) {

                            if (gaso2disp3.getText().equalsIgnoreCase("Extra")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 6, mextrasurtidor6, producto, volumen2disp3.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm6 = 0.00;

                            }
                            if (gaso2disp3.getText().equalsIgnoreCase("Super")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 6, msupersurtidor6, producto, volumen2disp3.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm6 = 0.00;

                            }
                            if (gaso2disp3.getText().equalsIgnoreCase("Diesel")) {
                                // System.out.println("entro");
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 6, mdieselsurtidor6, producto, volumen2disp3.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm6 = 0.00;
                            }
                            rtm6 = 0.00;

                        }
                    }
                }
                id2disp3.setText(idv1);
            }
            if (respuesta2disp3[1] == 3) {

                cont1++;
                estado2disp3.setText("Despachando");

                //  timerRTM2dispensador3.start();
                estado2disp3.setBackground(Color.green);
                gaso2disp3.setText("");
                monto2disp3.setText("");
                // id2disp3.setText("");

                volumen2disp3.setText("");

                if (id2disp3.getText().length() == 0) {
                    id2disp3.setText("0000001");
                }

            }

            if (respuesta2disp3[1] == 2) {

                estado2disp3.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor6 + " or nmanguera=" + msupersurtidor6 + " or nmanguera=" + mdieselsurtidor6 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor6 + " or nmanguera=" + msupersurtidor6 + " or nmanguera=" + mdieselsurtidor6 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {
                            autorizar(70);
                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor6 + " or nmanguera=" + msupersurtidor6 + " or nmanguera=" + mdieselsurtidor6 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }
                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta2disp3[1] == 4) {

                estado2disp3.setText("Autorizado");

            }

            if (respuesta2disp3[1] == 8) {

                estado2disp3.setText("Defectuoso");
                estado2disp3.setForeground(Color.red);

            }

            if (respuesta2disp3[1] == 9) {

                estado2disp3.setText("Anulado");

            }

            if (respuesta2disp3[1] == 10) {

                estado2disp3.setText("Detenido");

            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes" + cont);
        }

        servidor.setdatos6(estado2disp3.getText(), gaso2disp3.getText(), monto2disp3.getText(), volumen2disp3.getText(), ppu1);
    }

    public void generarRtm1disp3(int rtm1disp3) throws Exception {
        try {

            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            //RandomAccessFile pipertm1disp2 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");

            comandoRTM1disp3[0] = (byte) rtm1disp3;

            comandoRTM1disp3[1] = (char) 48;

            comandoRTM1disp3[2] = (char) 48;

            comandoRTM1disp3[3] = (char) 51;

            comandoRTM1disp3[4] = (char) 48;

            comandoRTM1disp3[5] = (char) 53;

            //        comandoRTM1[6] = (byte) FSL;
            int sumr = 0;
            int lrcr = 0;

            for (int i = 0; i < comandoRTM1disp3.length; i++) {

                sumr += (int) (comandoRTM1disp3[i]);
            }

            lrcr = sumr + 1;

            lrcr = lrcr & 0xff;

            //comandoRTM1[7] = (byte) lrcr;
            pipe.write(comandoRTM1disp3, 0, comandoRTM1disp3.length);

            bytesRTM1disp3 = pipe.read(respuestaRTM1disp3, 0, respuestaRTM1disp3.length);
            //bytesRead = pi pe.read(respuesta);

            pipe.close();

            String respRTM = "", monto1dispe3 = "";

            for (int i = 0; i < bytesRTM1disp3; i++) {

                respRTM += (char) (respuestaRTM1disp3[i]);

            }

            int s2 = respRTM.indexOf("~");

            monto1dispe3 = respRTM.substring(1, s2);

            if (monto1dispe3.equalsIgnoreCase("-0000001")) {

                monto1disp3.setText("0.00");

            } else {
                monto1disp3.setText(monto1dispe3);
                rtm5 = Double.valueOf(monto1disp3.getText());

            }

        } catch (Exception ex) {
            cont++;
            // System.out.println("Se Cruzaron 2 pipes  " + cont);
        }

    }

    public void generarRtm2disp3(int rtm2disp3) throws Exception {
        try {

            RandomAccessFile pipertm2disp3 = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandoRTM2disp3[0] = (byte) rtm2disp3;

            comandoRTM2disp3[1] = (char) 48;

            comandoRTM2disp3[2] = (char) 48;

            comandoRTM2disp3[3] = (char) 51;

            comandoRTM2disp3[4] = (char) 48;

            comandoRTM2disp3[5] = (char) 54;

            //        comandoRTM1[6] = (byte) FSL;
            int sumr = 0;
            int lrcr = 0;

            for (int i = 0; i < comandoRTM2disp3.length; i++) {

                sumr += (int) (comandoRTM2disp3[i]);
            }

            lrcr = sumr + 1;

            lrcr = lrcr & 0xff;

            //comandoRTM1[7] = (byte) lrcr;
            pipertm2disp3.write(comandoRTM2disp3, 0, comandoRTM2disp3.length);

            bytesRTM2disp3 = pipertm2disp3.read(respuestaRTM2disp3, 0, respuestaRTM2disp3.length);
            //bytesRead = pipe.read(respuesta);

            pipertm2disp3.close();

            String respRTM4 = "", monto2dispe3 = "";

            for (int i = 0; i < bytesRTM2disp3; i++) {

                respRTM4 += (char) (respuestaRTM2disp3[i]);

            }

            int s2 = respRTM4.indexOf("~");

            monto2dispe3 = respRTM4.substring(1, s2);

            if (monto2dispe3.equalsIgnoreCase("-0000001")) {

                monto2disp3.setText("0.00");

            } else {

                monto2disp3.setText(monto2dispe3);
                rtm6 = Double.valueOf(monto2disp3.getText());
            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes  " + cont);
        }

    }

///////////////////////////////////////////////////////////////////////////////
    public void generarlado1dispensador4(int c1disp4) throws Exception {
        String resp = "", monto1 = "", volu1 = "", ppu1 = "";
        try {

            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comando1disp4[0] = (byte) c1disp4;

            comando1disp4[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comando1disp4.length; i++) {
                sum += (int) (comando1disp4[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comando1disp4[2] = (byte) lrc;

            pipe.write(comando1disp4, 0, comando1disp4.length);
            bytesRead1disp4 = pipe.read(respuesta1disp4, 0, respuesta1disp4.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();
            int codigo = 0;

            int cont = 4;
            for (int i = 0; i < bytesRead1disp4; i++) {

                resp += (char) (respuesta1disp4[i]);
            }

            int s1 = resp.indexOf("~");

            if (respuesta1disp4[1] == 1 || respuesta1disp4[1] == 5) {

                estado1disp4.setText("Disponible");

                estado1disp4.setForeground(Color.black);
                estado1disp4.setBackground(Color.orange);
                cont1 = 0;
                //    timerRTM1dispensador4.stop();

                monto1 = resp.substring(4, s1);

                monto1disp4.setText(monto1);

                int s2 = resp.indexOf("~", s1 + 1);

                volu1 = resp.substring(s1 + 1, s2);

                int s3 = resp.indexOf("~", s2 + 1);

                ppu1 = resp.substring(s2 + 1, s3);

                String idv1, idv2;

                int s4 = resp.indexOf("~", s3 + 1);

                idv1 = resp.substring(s3 + 2, s4);

                int s5 = resp.indexOf("~", s4 + 1);

                idv2 = resp.substring(s4 + 1, s5);

                if (ppu1.equalsIgnoreCase(pextra)) {

                    gaso1disp4.setText("Extra");

                }
                if (ppu1.equalsIgnoreCase(psuper)) {

                    gaso1disp4.setText("Super");

                }

                if (ppu1.equalsIgnoreCase(pdiesel)) {

                    gaso1disp4.setText("Diesel");

                }

                volumen1disp4.setText(volu1);

                double monto, iva, total, subtotal;

                if (id1disp4.getText().length() > 0) {
                    if (id1disp4.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso1disp4.getText();
                        monto = Math.rint(Double.parseDouble(monto1disp4.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        if (total >= 0.05) {
                            if (gaso1disp4.getText().equalsIgnoreCase("Extra")) {

                                rtm7 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 7, mextrasurtidor7, producto, volumen1disp4.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp4.getText().equalsIgnoreCase("Super")) {
                                rtm7 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 7, msupersurtidor7, producto, volumen1disp4.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp4.getText().equalsIgnoreCase("Diesel")) {
                                rtm7 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 7, mdieselsurtidor7, producto, volumen1disp4.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }

                        }
                        rtm7 = 0.00;

                    }
                }
                id1disp4.setText(idv1);

            }
            if (respuesta1disp4[1] == 3) {

                cont1++;
                estado1disp4.setText("Despachando");

                // timerRTM1dispensador4.start();
                estado1disp4.setBackground(Color.green);

                monto1disp4.setText("");
                gaso1disp4.setText("");
                //id1disp4.setText("");

                volumen1disp4.setText("");

                if (id1disp4.getText().length() == 0) {
                    id1disp4.setText("0000001");
                }

            }

            if (respuesta1disp4[1] == 2) {

                estado1disp4.setText("Solicitud");

                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor7 + " or nmanguera=" + msupersurtidor7 + " or nmanguera=" + mdieselsurtidor7 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor7 + " or nmanguera=" + msupersurtidor7 + " or nmanguera=" + mdieselsurtidor7 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {
                            autorizar(71);
                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor7 + " or nmanguera=" + msupersurtidor7 + " or nmanguera=" + mdieselsurtidor7 + ";";

                            st2.executeUpdate(consulta1);

                        } else {
                        }
                    } else {
                    }

                    st_d.close();

                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta1disp4[1] == 4) {

                estado1disp4.setText("Autorizado");

            }

            if (respuesta1disp4[1] == 8) {

                estado1disp4.setText("Defectuoso");
                estado1disp4.setForeground(Color.red);

            }

            if (respuesta1disp4[1] == 9) {

                estado1disp4.setText("Anulado");

            }

            if (respuesta1disp4[1] == 10) {

                estado1disp4.setText("Detenido");

            }

        } catch (Exception ex) {
            cont++;

        }

        servidor.setdatos7(estado1disp4.getText(), gaso1disp4.getText(), monto1disp4.getText(), volumen1disp4.getText(), ppu1);

    }

    public void pagar(int codigo, int cf) {

        byte[] comandoau = new byte[4];
        byte[] respuesta = new byte[256];
        int bytesa;
        try {
            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            System.out.println("surtidor" + codigo);
            System.out.println("codigo" + cf);
            comandoau[0] = (byte) codigo;

            comandoau[1] = (byte) cf;

            comandoau[2] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comandoau.length; i++) {
                sum += (int) (comandoau[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comandoau[3] = (byte) lrc;

            pipe.write(comandoau, 0, comandoau.length);
            bytesa = pipe.read(respuesta, 0, respuesta.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String resp = "", monto1, volu1, ppu1;
            int cont = 4;
            for (int i = 0; i < bytesa; i++) {

                resp += (char) (respuesta[i]);

                System.out.print(respuesta[i]);

            }

            System.out.println(resp);

        } catch (Exception ex) {
        }

    }

    public void generarlado2dispensador4(int c2disp4) throws Exception {

        String resp1 = "", volu2, mont2, resp2 = "", ppu2 = "";
        try {
            //RandomAccessFile pipe1 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");
            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comando2disp4[0] = (byte) c2disp4;

            comando2disp4[1] = (byte) FSL;

            int sum1 = 0;
            int lrc1 = 0;

            for (int i = 0; i < comando2disp4.length; i++) {
                sum1 += (int) (comando2disp4[i]);
            }

            lrc1 = sum1 + 1;
            lrc1 = lrc1 & 0xff;

            comando2disp4[2] = (byte) lrc1;

            pipe.write(comando2disp4, 0, comando2disp4.length);

            bytesRead2disp4 = pipe.read(respuesta2disp4, 0, respuesta2disp4.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String p1 = null;

            for (int i = 0; i < bytesRead2disp4; i++) {

                resp1 += (char) (respuesta2disp4[i]);
                p1 += (char) (respuesta2disp4[i]);

            }

            int s2 = resp1.indexOf("~");

            if (respuesta2disp4[1] == 1 || respuesta2disp4[1] == 5) {

                estado2disp4.setText("Disponible");
                estado2disp4.setBackground(Color.orange);
                estado2disp4.setForeground(Color.black);
                //   timerRTM2dispensador4.stop();
                mont2 = resp1.substring(4, s2);
                monto2disp4.setText(mont2);

                int s3 = resp1.indexOf("~", s2 + 1);

                volu2 = resp1.substring(s2 + 1, s3);

                int s4 = resp1.indexOf("~", s3 + 1);

                ppu2 = resp1.substring(s3 + 1, s4);

                String idv1, idv2;

                int s5 = resp1.indexOf("~", s4 + 1);

                idv1 = resp1.substring(s4 + 2, s5);

                int s6 = resp1.indexOf("~", s5 + 1);

                idv2 = resp1.substring(s5 + 1, s6);

                volumen2disp4.setText(volu2);

                if (ppu2.equalsIgnoreCase(pextra)) {

                    gaso2disp4.setText("Extra");

                }
                if (ppu2.equalsIgnoreCase(psuper)) {

                    gaso2disp4.setText("Super");

                }

                if (ppu2.equalsIgnoreCase(pdiesel)) {

                    gaso2disp4.setText("Diesel");

                }

                double monto, iva, total, subtotal;

                if (id2disp4.getText().length() > 0) {
                    if (id2disp4.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso2disp4.getText();
                        monto = Math.rint(Double.parseDouble(monto2disp4.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        rtm8 = 0.00;

                        if (total >= 0.05) {

                            if (gaso2disp4.getText().equalsIgnoreCase("Extra")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 8, mextrasurtidor8, producto, volumen2disp4.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm8 = 0.00;
                            }
                            if (gaso2disp4.getText().equalsIgnoreCase("Super")) {
                                rtm8 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 8, msupersurtidor8, producto, volumen2disp4.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso2disp4.getText().equalsIgnoreCase("Diesel")) {
                                rtm8 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 8, mdieselsurtidor8, producto, volumen2disp4.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }

                        }

                    }
                }

                id2disp4.setText(idv1);

            }
            if (respuesta2disp4[1] == 3) {

                estado2disp4.setText("Despachando");

                //timerRTM2dispensador4.start();
                monto2disp4.setText("");
                gaso2disp4.setText("");
                // id2disp4.setText("");
                estado2disp4.setBackground(Color.green);
                volumen2disp4.setText("");

                if (id2disp4.getText().length() == 0) {
                    id2disp4.setText("0000001");
                }

            }

            if (respuesta2disp4[1] == 2) {

                estado2disp4.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor8 + " or nmanguera=" + msupersurtidor8 + " or nmanguera=" + mdieselsurtidor8 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor8 + " or nmanguera=" + msupersurtidor8 + " or nmanguera=" + mdieselsurtidor8 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {

                            autorizar(72);
                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor8 + " or nmanguera=" + msupersurtidor8 + " or nmanguera=" + mdieselsurtidor8 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }

                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta2disp4[1] == 4) {

                estado2disp4.setText("Autorizado");

            }

            if (respuesta2disp4[1] == 8) {

                estado2disp4.setText("Defectuoso");
                estado2disp4.setForeground(Color.red);

            }

            if (respuesta2disp4[1] == 9) {

                estado2disp4.setText("Anulado");

            }

            if (respuesta2disp4[1] == 10) {

                estado2disp4.setText("Detenido");

            }

            int con = respuesta2disp4[2];

            int con1 = respuesta2disp4[3];

        } catch (Exception ex) {

            cont++;

        }

        servidor.setdatos8(estado2disp4.getText(), gaso2disp4.getText(), monto2disp4.getText(), volumen2disp4.getText(), ppu2);
    }

    public void generarRtm1disp4(int rtm1disp4) throws Exception {
        try {
            try (
                    RandomAccessFile pipertm = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw")) {

                comandoRTM1disp4[0] = (byte) rtm1disp4;

                comandoRTM1disp4[1] = (char) 48;

                comandoRTM1disp4[2] = (char) 48;

                comandoRTM1disp4[3] = (char) 51;

                comandoRTM1disp4[4] = (char) 48;

                comandoRTM1disp4[5] = (char) 55;

                //        comandoRTM1[6] = (byte) FSL;
                int sumr = 0;
                int lrcr = 0;

                for (int i = 0; i < comandoRTM1disp4.length; i++) {

                    sumr += (int) (comandoRTM1disp4[i]);
                }

                lrcr = sumr + 1;

                lrcr = lrcr & 0xff;

                //comandoRTM1[7] = (byte) lrcr;
                pipertm.write(comandoRTM1disp4, 0, comandoRTM1disp4.length);

                bytesRTM1disp4 = pipertm.read(respuestaRTM1disp4, 0, respuestaRTM1disp4.length);
            }

            String respRTM = "", monto7 = "";

            for (int i = 0; i < bytesRTM1disp4; i++) {

                respRTM += (char) (respuestaRTM1disp4[i]);

            }

            int s2 = respRTM.indexOf("~");

            monto7 = respRTM.substring(1, s2);

            if (monto7.equalsIgnoreCase("-0000001")) {

                monto1disp4.setText("0.00");

            } else {
                monto1disp4.setText(monto7);

                rtm7 = Double.parseDouble(monto1disp4.getText());
            }
        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes  " + cont);
        }

    }

    public void generarRtm2disp4(int rtm2disp4) throws Exception {
        try {
            RandomAccessFile pipertm2 = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandoRTM2disp4[0] = (byte) rtm2disp4;

            comandoRTM2disp4[1] = (char) 48;

            comandoRTM2disp4[2] = (char) 48;

            comandoRTM2disp4[3] = (char) 51;

            comandoRTM2disp4[4] = (char) 48;

            comandoRTM2disp4[5] = (char) 56;

            //        comandoRTM1[6] = (byte) FSL;
            int sumr1 = 0;
            int lrcr1 = 0;

            for (int i = 0; i < comandoRTM2disp4.length; i++) {

                sumr1 += (int) (comandoRTM2disp4[i]);
            }

            lrcr1 = sumr1 + 1;

            lrcr1 = lrcr1 & 0xff;

            //comandoRTM1[7] = (byte) lrcr;
            pipertm2.write(comandoRTM2disp4, 0, comandoRTM2disp4.length);

            bytesRTM2disp4 = pipertm2.read(respuestaRTM2disp4, 0, respuestaRTM2disp4.length);
            //bytesRead = pipe.read(respuesta);

            pipertm2.close();

            String respRTM1 = "", monto8 = "";

            for (int i = 0; i < bytesRTM2disp4; i++) {

                respRTM1 += (char) (respuestaRTM2disp4[i]);
            }

            int s2 = respRTM1.indexOf("~");

            monto8 = respRTM1.substring(1, s2);

            if (monto8.equalsIgnoreCase("-0000001")) {

                monto2disp4.setText("0.00");

            } else {

                monto2disp4.setText(monto8);
                rtm8 = Double.valueOf(monto2disp4.getText());
            }
        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes" + cont);
        }

    }

    /////////////////////////////////////////////////////////////////////////////    
    public void generarlado1dispensador5(int c1disp5) throws Exception {

        String resp = "", monto1, volu1, ppu1 = "";
        try {

            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comando1disp5[0] = (byte) c1disp5;

            comando1disp5[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comando1disp5.length; i++) {
                sum += (int) (comando1disp5[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comando1disp5[2] = (byte) lrc;

            pipe.write(comando1disp5, 0, comando1disp5.length);
            bytesRead1disp5 = pipe.read(respuesta1disp5, 0, respuesta1disp5.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            int cont = 4;
            for (int i = 0; i < bytesRead1disp5; i++) {

                resp += (char) (respuesta1disp5[i]);
            }

            int s1 = resp.indexOf("~");
            if (respuesta1disp5[1] == 1 || respuesta1disp5[1] == 5) {

                estado1disp5.setText("Disponible");

                estado1disp5.setForeground(Color.black);
                estado1disp5.setBackground(Color.orange);
                cont1 = 0;
                // timerRTM1dispensador5.stop();

                monto1 = resp.substring(4, s1);

                monto1disp5.setText(monto1);

                int s2 = resp.indexOf("~", s1 + 1);

                volu1 = resp.substring(s1 + 1, s2);

                int s3 = resp.indexOf("~", s2 + 1);

                ppu1 = resp.substring(s2 + 1, s3);

                String idv1, idv2;

                int s4 = resp.indexOf("~", s3 + 1);

                idv1 = resp.substring(s3 + 2, s4);

                int s5 = resp.indexOf("~", s4 + 1);

                idv2 = resp.substring(s4 + 1, s5);

                if (ppu1.equalsIgnoreCase(pextra)) {

                    gaso1disp5.setText("Extra");

                }
                if (ppu1.equalsIgnoreCase(psuper)) {

                    gaso1disp5.setText("Super");

                }

                if (ppu1.equalsIgnoreCase(pdiesel)) {

                    gaso1disp5.setText("Diesel");

                }

                volumen1disp5.setText(volu1);

                double monto, iva, total, subtotal;

                if (id1disp5.getText().length() > 0) {
                    if (id1disp5.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso1disp5.getText();
                        monto = Math.rint(Double.parseDouble(monto1disp5.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        if (total >= 0.05) {

                            if (gaso1disp5.getText().equalsIgnoreCase("Extra")) {

                                rtm9 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 9, mextrasurtidor9, producto, volumen1disp5.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp5.getText().equalsIgnoreCase("Super")) {
                                rtm9 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 9, msupersurtidor9, producto, volumen1disp5.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp5.getText().equalsIgnoreCase("Diesel")) {
                                rtm9 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 9, mdieselsurtidor9, producto, volumen1disp5.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                        }
                        rtm9 = 0.00;

                    }
                }
                id1disp5.setText(idv1);
            }
            if (respuesta1disp5[1] == 3) {

                cont1++;
                estado1disp5.setText("Despachando");

                // timerRTM1dispensador5.start();
                estado1disp5.setBackground(Color.green);

                monto1disp5.setText("");
                gaso1disp5.setText("");
                //id1disp5.setText("");

                volumen1disp5.setText("");

                if (id1disp5.getText().length() == 0) {
                    id1disp5.setText("0000001");
                }

            }

            if (respuesta1disp5[1] == 2) {

                estado1disp5.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor9 + " or nmanguera=" + msupersurtidor9 + " or nmanguera=" + mdieselsurtidor9 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor9 + " or nmanguera=" + msupersurtidor9 + " or nmanguera=" + mdieselsurtidor9 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {

                            autorizar(73);

                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor9 + " or nmanguera=" + msupersurtidor9 + " or nmanguera=" + mdieselsurtidor9 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }
                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta1disp5[1] == 4) {

                estado1disp5.setText("Autorizado");

            }

            if (respuesta1disp5[1] == 8) {

                estado1disp5.setText("Defectuoso");
                estado1disp5.setForeground(Color.red);

            }

            if (respuesta1disp5[1] == 9) {

                estado1disp5.setText("Anulado");

            }

            if (respuesta1disp5[1] == 10) {

                estado1disp5.setText("Detenido");

            }

        } catch (Exception ex) {
            cont++;

        }

        servidor.setdatos9(estado1disp5.getText(), gaso1disp5.getText(), monto1disp5.getText(), volumen1disp5.getText(), ppu1);

    }

    public void generarlado2dispensador5(int c2disp5) throws Exception {
        String ppu2 = "";
        try {
            //RandomAccessFile pipe1 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");
            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            comando2disp5[0] = (byte) c2disp5;

            comando2disp5[1] = (byte) FSL;

            int sum1 = 0;
            int lrc1 = 0;

            for (int i = 0; i < comando2disp5.length; i++) {
                sum1 += (int) (comando2disp5[i]);
            }

            lrc1 = sum1 + 1;
            lrc1 = lrc1 & 0xff;

            comando2disp5[2] = (byte) lrc1;

            pipe.write(comando2disp5, 0, comando2disp5.length);

            bytesRead2disp5 = pipe.read(respuesta2disp5, 0, respuesta2disp5.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String resp1 = "", volu2, mont2, resp2 = null;
            String p1 = null;

            for (int i = 0; i < bytesRead2disp5; i++) {

                resp1 += (char) (respuesta2disp5[i]);
                p1 += (char) (respuesta2disp5[i]);

            }

            int s2 = resp1.indexOf("~");

            if (respuesta2disp5[1] == 1 || respuesta2disp5[1] == 5) {

                estado2disp5.setText("Disponible");
                estado2disp5.setBackground(Color.orange);
                estado2disp5.setForeground(Color.black);
                // timerRTM2dispensador5.stop();
                mont2 = resp1.substring(4, s2);

                monto2disp5.setText(mont2);

                int s3 = resp1.indexOf("~", s2 + 1);

                volu2 = resp1.substring(s2 + 1, s3);

                int s4 = resp1.indexOf("~", s3 + 1);

                ppu2 = resp1.substring(s3 + 1, s4);

                String idv1, idv2;

                int s5 = resp1.indexOf("~", s4 + 1);

                idv1 = resp1.substring(s4 + 2, s5);

                int s6 = resp1.indexOf("~", s5 + 1);

                idv2 = resp1.substring(s5 + 1, s6);

                volumen2disp5.setText(volu2);

                if (ppu2.equalsIgnoreCase(pextra)) {

                    gaso2disp5.setText("Extra");

                }
                if (ppu2.equalsIgnoreCase(psuper)) {

                    gaso2disp5.setText("Super");

                }

                if (ppu2.equalsIgnoreCase(pdiesel)) {

                    gaso2disp5.setText("Diesel");

                }

                double monto, iva, total, subtotal;

                if (id2disp5.getText().length() > 0) {
                    if (id2disp5.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso2disp5.getText();
                        monto = Math.rint(Double.parseDouble(monto2disp5.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        rtm10 = 0.00;
                        if (total >= 0.05) {

                            if (gaso2disp5.getText().equalsIgnoreCase("Extra")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 10, mextrasurtidor10, producto, volumen2disp5.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()
                                rtm10 = 0.00;
                            }
                            if (gaso2disp5.getText().equalsIgnoreCase("Super")) {
                                rtm10 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 10, msupersurtidor10, producto, volumen2disp5.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso2disp5.getText().equalsIgnoreCase("Diesel")) {
                                rtm10 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 10, mdieselsurtidor10, producto, volumen2disp5.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }

                        }

                    }

                }

                id2disp5.setText(idv1);

            }
            if (respuesta2disp5[1] == 3) {

                estado2disp5.setText("Despachando");

                //  timerRTM2dispensador5.start();
                monto2disp5.setText("");
                gaso2disp5.setText("");
                //id2disp5.setText("");
                estado2disp5.setBackground(Color.green);
                volumen2disp5.setText("");

                if (id2disp5.getText().length() == 0) {
                    id2disp5.setText("0000001");
                }

            }

            if (respuesta2disp5[1] == 2) {

                estado2disp5.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor10 + " or nmanguera=" + msupersurtidor10 + " or nmanguera=" + mdieselsurtidor10 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor10 + " or nmanguera=" + msupersurtidor10 + " or nmanguera=" + mdieselsurtidor10 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {

                            autorizar(74);
                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor10 + " or nmanguera=" + msupersurtidor10 + " or nmanguera=" + mdieselsurtidor10 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }
                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta2disp5[1] == 4) {

                estado2disp5.setText("Autorizado");

            }

            if (respuesta2disp5[1] == 8) {

                estado2disp5.setText("Defectuoso");
                estado2disp5.setForeground(Color.red);

            }

            if (respuesta2disp5[1] == 9) {

                estado2disp5.setText("Anulado");

            }

            if (respuesta2disp5[1] == 10) {

                estado2disp5.setText("Detenido");

            }

            int con = respuesta2disp5[2];

            int con1 = respuesta2disp5[3];

        } catch (Exception ex) {

            cont++;

        }

        servidor.setdatos10(estado2disp5.getText(), gaso2disp5.getText(), monto2disp5.getText(), volumen2disp5.getText(), ppu2);
    }

    public void generarRtm1disp5(int rtm1disp5) throws Exception {
        try {
            try (
                    RandomAccessFile pipertm = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw")) {

                comandoRTM1disp5[0] = (byte) rtm1disp5;

                comandoRTM1disp5[1] = (char) 48;

                comandoRTM1disp5[2] = (char) 48;

                comandoRTM1disp5[3] = (char) 51;

                comandoRTM1disp5[4] = (char) 48;

                comandoRTM1disp5[5] = (byte) (char) 57;

                //        comandoRTM1[6] = (byte) FSL;
                int sumr = 0;
                int lrcr = 0;

                for (int i = 0; i < comandoRTM1disp5.length; i++) {

                    sumr += (int) (comandoRTM1disp5[i]);
                }

                lrcr = sumr + 1;

                lrcr = lrcr & 0xff;

                //comandoRTM1[7] = (byte) lrcr;
                pipertm.write(comandoRTM1disp5, 0, comandoRTM1disp5.length);

                bytesRTM1disp5 = pipertm.read(respuestaRTM1disp5, 0, respuestaRTM1disp5.length);
            }

            String respRTM = "", monto9 = "";

            for (int i = 0; i < bytesRTM1disp5; i++) {

                respRTM += (char) (respuestaRTM1disp5[i]);

            }

            int s2 = respRTM.indexOf("~");

            monto9 = respRTM.substring(1, s2);

            if (monto9.equalsIgnoreCase("-0000001")) {

                monto1disp5.setText("0.00");

            } else {

                monto1disp5.setText(monto9);

                rtm9 = Double.parseDouble(monto1disp5.getText());
            }
        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes  " + cont);
        }

    }

    public void generarRtm2disp5(int rtm2disp5) throws Exception {
        try {

            RandomAccessFile pipertm2 = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandoRTM2disp5[0] = (byte) rtm2disp5;

            comandoRTM2disp5[1] = (char) 48;

            comandoRTM2disp5[2] = (char) 48;

            comandoRTM2disp5[3] = (char) 51;

            comandoRTM2disp5[4] = (byte) (char) 49;

            comandoRTM2disp5[5] = (byte) (char) 48;

            int sumr1 = 0;
            int lrcr1 = 0;

            for (int i = 0; i < comandoRTM2disp5.length; i++) {

                sumr1 += (int) (comandoRTM2disp5[i]);
            }

            lrcr1 = sumr1 + 1;

            lrcr1 = lrcr1 & 0xff;

            //comandoRTM1[7] = (byte) lrcr;
            pipertm2.write(comandoRTM2disp5, 0, comandoRTM2disp5.length);

            bytesRTM2disp5 = pipertm2.read(respuestaRTM2disp5, 0, respuestaRTM2disp5.length);
            //bytesRead = pipe.read(respuesta);

            pipertm2.close();

            String respRTM1 = "", monto10 = "";

            for (int i = 0; i < bytesRTM2disp5; i++) {

                respRTM1 += (char) (respuestaRTM2disp5[i]);
            }

            int s2 = respRTM1.indexOf("~");

            monto10 = respRTM1.substring(1, s2);

            if (monto10.equalsIgnoreCase("-0000001")) {

                monto2disp5.setText("0.00");

            } else {

                monto2disp5.setText(monto10);
                rtm10 = Double.valueOf(monto2disp5.getText());
            }
        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes" + cont);
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    public void generarlado1dispensador6(int c1disp6) throws Exception {
        String resp = "", monto1, volu1, ppu1 = "";
        try {

            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comando1disp6[0] = (byte) c1disp6;

            comando1disp6[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comando1disp6.length; i++) {
                sum += (int) (comando1disp6[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comando1disp6[2] = (byte) lrc;

            pipe.write(comando1disp6, 0, comando1disp6.length);
            bytesRead1disp6 = pipe.read(respuesta1disp6, 0, respuesta1disp6.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            int cont = 4;
            for (int i = 0; i < bytesRead1disp6; i++) {

                resp += (char) (respuesta1disp6[i]);
            }

            int s1 = resp.indexOf("~");
            if (respuesta1disp6[1] == 1 || respuesta1disp6[1] == 5) {

                estado1disp6.setText("Disponible");

                estado1disp6.setForeground(Color.black);
                estado1disp6.setBackground(Color.orange);
                cont1 = 0;
                //  timerRTM1dispensador6.stop();

                monto1 = resp.substring(4, s1);

                monto1disp6.setText(monto1);

                int s2 = resp.indexOf("~", s1 + 1);

                volu1 = resp.substring(s1 + 1, s2);

                int s3 = resp.indexOf("~", s2 + 1);

                ppu1 = resp.substring(s2 + 1, s3);

                String idv1, idv2;

                int s4 = resp.indexOf("~", s3 + 1);

                idv1 = resp.substring(s3 + 2, s4);

                int s5 = resp.indexOf("~", s4 + 1);

                idv2 = resp.substring(s4 + 1, s5);

                if (ppu1.equalsIgnoreCase(pextra)) {

                    gaso1disp6.setText("Extra");

                }
                if (ppu1.equalsIgnoreCase(psuper)) {

                    gaso1disp6.setText("Super");

                }

                if (ppu1.equalsIgnoreCase(pdiesel)) {

                    gaso1disp6.setText("Diesel");

                }

                volumen1disp6.setText(volu1);

                double monto, iva, total, subtotal;

                if (id1disp6.getText().length() > 0) {
                    if (id1disp6.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso1disp6.getText();
                        monto = Math.rint(Double.parseDouble(monto1disp6.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        rtm11 = 0.00;

                        if (total >= 0.05) {

                            if (gaso1disp6.getText().equalsIgnoreCase("Extra")) {

                                rtm11 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 11, mextrasurtidor11, producto, volumen1disp6.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp6.getText().equalsIgnoreCase("Super")) {
                                rtm11 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 11, msupersurtidor11, producto, volumen1disp6.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp6.getText().equalsIgnoreCase("Diesel")) {
                                rtm11 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 11, mdieselsurtidor11, producto, volumen1disp6.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                        }
                    }
                }

                id1disp6.setText(idv1);

            }
            if (respuesta1disp6[1] == 3) {

                cont1++;
                estado1disp6.setText("Despachando");

                //timerRTM1dispensador6.start();
                estado1disp6.setBackground(Color.green);

                gaso1disp6.setText("");
                //id1disp6.setText("");
                monto1disp6.setText("");

                volumen1disp6.setText("");

                if (id1disp6.getText().length() == 0) {
                    id1disp6.setText("0000001");
                }
            }

            if (respuesta1disp6[1] == 2) {

                estado1disp6.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor11 + " or nmanguera=" + msupersurtidor11 + " or nmanguera=" + mdieselsurtidor11 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor11 + " or nmanguera=" + msupersurtidor11 + " or nmanguera=" + mdieselsurtidor11 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {
                            autorizar(75);

                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor11 + " or nmanguera=" + msupersurtidor11 + " or nmanguera=" + mdieselsurtidor11 + ";";

                            st2.executeUpdate(consulta1);

                        }

                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta1disp6[1] == 4) {

                estado1disp6.setText("Autorizado");

            }

            if (respuesta1disp6[1] == 8) {

                estado1disp6.setText("Defectuoso");
                estado1disp6.setForeground(Color.red);

            }

            if (respuesta1disp6[1] == 9) {

                estado1disp6.setText("Anulado");

            }

            if (respuesta1disp6[1] == 10) {

                estado1disp6.setText("Detenido");

            }

        } catch (Exception ex) {
            cont++;

        }

        servidor.setdatos11(estado1disp6.getText(), gaso1disp6.getText(), monto1disp6.getText(), volumen1disp6.getText(), ppu1);

    }

    public void generarlado2dispensador6(int c2disp6) throws Exception {
        try {
            //RandomAccessFile pipe1 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");
            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            comando2disp6[0] = (byte) c2disp6;

            comando2disp6[1] = (byte) FSL;

            int sum1 = 0;
            int lrc1 = 0;

            for (int i = 0; i < comando2disp6.length; i++) {
                sum1 += (int) (comando2disp6[i]);
            }

            lrc1 = sum1 + 1;
            lrc1 = lrc1 & 0xff;

            comando2disp6[2] = (byte) lrc1;

            pipe.write(comando2disp6, 0, comando2disp6.length);

            bytesRead2disp6 = pipe.read(respuesta2disp6, 0, respuesta2disp6.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String resp1 = "", volu2, mont2, resp2 = null, ppu2;
            String p1 = null;

            for (int i = 0; i < bytesRead2disp6; i++) {

                resp1 += (char) (respuesta2disp6[i]);
                p1 += (char) (respuesta2disp5[i]);

            }

            int s2 = resp1.indexOf("~");

            if (respuesta2disp6[1] == 1 || respuesta2disp6[1] == 5) {

                estado2disp6.setText("Disponible");
                estado2disp6.setBackground(Color.orange);
                estado2disp6.setForeground(Color.black);
                // timerRTM2dispensador6.stop();
                mont2 = resp1.substring(4, s2);
                monto2disp6.setText(mont2);

                int s3 = resp1.indexOf("~", s2 + 1);

                volu2 = resp1.substring(s2 + 1, s3);

                int s4 = resp1.indexOf("~", s3 + 1);

                ppu2 = resp1.substring(s3 + 1, s4);

                String idv1, idv2;

                int s5 = resp1.indexOf("~", s4 + 1);

                idv1 = resp1.substring(s4 + 2, s5);

                int s6 = resp1.indexOf("~", s5 + 1);

                idv2 = resp1.substring(s5 + 1, s6);

                volumen2disp6.setText(volu2);

                if (ppu2.equalsIgnoreCase(pextra)) {

                    gaso2disp6.setText("Extra");

                }
                if (ppu2.equalsIgnoreCase(psuper)) {

                    gaso2disp6.setText("Super");

                }

                if (ppu2.equalsIgnoreCase(pdiesel)) {

                    gaso2disp6.setText("Diesel");

                }

                double monto, iva, total, subtotal;

                if (id2disp6.getText().length() > 0) {
                    if (id2disp6.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso2disp6.getText();
                        monto = Math.rint(Double.parseDouble(monto2disp6.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        rtm12 = 0.00;

                        if (total >= 0.05) {

                            if (gaso2disp6.getText().equalsIgnoreCase("Extra")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 12, mextrasurtidor12, producto, volumen2disp6.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm12 = 0.00;
                            }
                            if (gaso2disp6.getText().equalsIgnoreCase("Super")) {
                                rtm12 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 12, msupersurtidor12, producto, volumen2disp6.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso2disp6.getText().equalsIgnoreCase("Diesel")) {
                                rtm12 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 12, mdieselsurtidor12, producto, volumen2disp6.getText(), ppu2, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()
                            }

                        }

                    }

                }
                id2disp6.setText(idv1);

            }
            if (respuesta2disp6[1] == 3) {

                estado2disp6.setText("Despachando");

                // timerRTM2dispensador6.start();
                gaso2disp6.setText("");
                // id2disp6.setText("");
                estado2disp6.setBackground(Color.green);
                monto2disp6.setText("");
                volumen2disp6.setText("");

                if (id2disp6.getText().length() == 0) {
                    id2disp6.setText("0000001");
                }

            }

            if (respuesta2disp6[1] == 2) {

                estado2disp6.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor12 + " or nmanguera=" + msupersurtidor12 + " or nmanguera=" + mdieselsurtidor12 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor12 + " or nmanguera=" + msupersurtidor12 + " or nmanguera=" + mdieselsurtidor12 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {

                            autorizar(76);

                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor12 + " or nmanguera=" + msupersurtidor12 + " or nmanguera=" + mdieselsurtidor12 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }

                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta2disp6[1] == 4) {

                estado2disp6.setText("Autorizado");

            }

            if (respuesta2disp6[1] == 8) {

                estado2disp6.setText("Defectuoso");
                estado2disp6.setForeground(Color.red);

            }

            if (respuesta2disp6[1] == 9) {

                estado2disp6.setText("Anulado");

            }

            if (respuesta2disp6[1] == 10) {

                estado2disp6.setText("Detenido");

            }

            int con = respuesta2disp6[2];

            int con1 = respuesta2disp6[3];

        } catch (Exception ex) {

            cont++;

        }

        servidor.setdatos12(estado2disp6.getText(), gaso2disp6.getText(), monto2disp6.getText(), volumen2disp6.getText(), id2disp6.getText());
    }

    public void generarRtm1disp6(int rtm1disp6) throws Exception {
        try {
            try (
                    RandomAccessFile pipertm = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw")) {

                comandoRTM1disp6[0] = (byte) rtm1disp6;

                comandoRTM1disp6[1] = (char) 48;

                comandoRTM1disp6[2] = (char) 48;

                comandoRTM1disp6[3] = (char) 51;

                comandoRTM1disp6[4] = (char) 49;

                comandoRTM1disp6[5] = (char) 49;

                //        comandoRTM1[6] = (byte) FSL;
                int sumr = 0;
                int lrcr = 0;

                for (int i = 0; i < comandoRTM1disp6.length; i++) {

                    sumr += (int) (comandoRTM1disp6[i]);
                }

                lrcr = sumr + 1;

                lrcr = lrcr & 0xff;

                //comandoRTM1[7] = (byte) lrcr;
                pipertm.write(comandoRTM1disp6, 0, comandoRTM1disp6.length);

                bytesRTM1disp6 = pipertm.read(respuestaRTM1disp6, 0, respuestaRTM1disp6.length);
            }

            String respRTM = "", monto11 = "";

            for (int i = 0; i < bytesRTM1disp6; i++) {

                respRTM += (char) (respuestaRTM1disp6[i]);

            }

            int s2 = respRTM.indexOf("~");

            monto11 = respRTM.substring(1, s2);

            if (monto11.equalsIgnoreCase("-0000001")) {

                monto1disp6.setText("0.00");

            } else {
                monto1disp6.setText(monto11);

                rtm11 = Double.parseDouble(monto1disp6.getText());
            }
        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes  " + cont);
        }

    }

    public void generarRtm2disp6(int rtm2disp6) throws Exception {
        try {
            RandomAccessFile pipertm2 = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandoRTM2disp6[0] = (byte) rtm2disp6;

            comandoRTM2disp6[1] = (char) 48;

            comandoRTM2disp6[2] = (char) 48;

            comandoRTM2disp6[3] = (char) 51;

            comandoRTM2disp6[4] = (char) 49;

            comandoRTM2disp6[5] = (char) 50;

            //        comandoRTM1[6] = (byte) FSL;
            int sumr1 = 0;
            int lrcr1 = 0;

            for (int i = 0; i < comandoRTM2disp6.length; i++) {

                sumr1 += (int) (comandoRTM2disp6[i]);
            }

            lrcr1 = sumr1 + 1;

            lrcr1 = lrcr1 & 0xff;

            //comandoRTM1[7] = (byte) lrcr;
            pipertm2.write(comandoRTM2disp6, 0, comandoRTM2disp6.length);

            bytesRTM2disp6 = pipertm2.read(respuestaRTM2disp6, 0, respuestaRTM2disp6.length);
            //bytesRead = pipe.read(respuesta);

            pipertm2.close();

            String respRTM1 = "", monto12 = "";

            for (int i = 0; i < bytesRTM2disp6; i++) {

                respRTM1 += (char) (respuestaRTM2disp6[i]);
            }

            int s2 = respRTM1.indexOf("~");

            monto12 = respRTM1.substring(1, s2);

            if (monto12.equalsIgnoreCase("-0000001")) {

                monto2disp6.setText("0.00");

            } else {

                monto2disp6.setText(monto12);
                rtm12 = Double.valueOf(monto2disp6.getText());
            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes" + cont);
        }

    }

    ////////////////////////////////////////////////////////////////////////// 
    public void generarlado1dispensador7(int c1disp7) throws Exception {

        String resp = "", monto1, volu1, ppu1 = "";
        try {

            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comando1disp7[0] = (byte) c1disp7;

            comando1disp7[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comando1disp7.length; i++) {
                sum += (int) (comando1disp7[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comando1disp7[2] = (byte) lrc;

            pipe.write(comando1disp7, 0, comando1disp7.length);
            bytesRead1disp7 = pipe.read(respuesta1disp7, 0, respuesta1disp7.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            int cont = 4;
            for (int i = 0; i < bytesRead1disp7; i++) {

                resp += (char) (respuesta1disp7[i]);
            }

            int s1 = resp.indexOf("~");

            if (respuesta1disp7[1] == 1 || respuesta1disp7[1] == 5) {

                estado1disp7.setText("Disponible");

                estado1disp7.setForeground(Color.black);
                estado1disp7.setBackground(Color.orange);
                cont1 = 0;
                // timerRTM1dispensador7.stop();

                monto1 = resp.substring(4, s1);

                monto1disp7.setText(monto1);

                int s2 = resp.indexOf("~", s1 + 1);

                volu1 = resp.substring(s1 + 1, s2);

                int s3 = resp.indexOf("~", s2 + 1);

                ppu1 = resp.substring(s2 + 1, s3);

                String idv1, idv2;

                int s4 = resp.indexOf("~", s3 + 1);

                idv1 = resp.substring(s3 + 2, s4);

                int s5 = resp.indexOf("~", s4 + 1);

                idv2 = resp.substring(s4 + 1, s5);

                if (ppu1.equalsIgnoreCase(pextra)) {

                    gaso1disp7.setText("Extra");

                }
                if (ppu1.equalsIgnoreCase(psuper)) {

                    gaso1disp7.setText("Super");

                }

                if (ppu1.equalsIgnoreCase(pdiesel)) {

                    gaso1disp7.setText("Diesel");

                }

                volumen1disp7.setText(volu1);

                double monto, iva, total, subtotal;

                if (id1disp7.getText().length() > 0) {
                    if (id1disp7.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso1disp7.getText();
                        monto = Math.rint(Double.parseDouble(monto1disp7.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        if (total >= 0.05) {

                            if (gaso1disp7.getText().equalsIgnoreCase("Extra")) {

                                rtm13 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 13, mextrasurtidor13, producto, volumen1disp7.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp7.getText().equalsIgnoreCase("Super")) {
                                rtm13 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 13, msupersurtidor13, producto, volumen1disp7.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso1disp7.getText().equalsIgnoreCase("Diesel")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 13, mdieselsurtidor13, producto, volumen1disp7.getText(), ppu1, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm13 = 0.00;
                            }
                        }
                    }
                }

                id1disp7.setText(idv1);

            }
            if (respuesta1disp7[1] == 3) {

                cont1++;
                estado1disp7.setText("Despachando");

                //timerRTM1dispensador7.start();
                estado1disp7.setBackground(Color.green);

                gaso1disp7.setText("");
                //id1disp7.setText("");
                monto1disp7.setText("");

                volumen1disp7.setText("");

                if (id1disp7.getText().length() == 0) {
                    id1disp7.setText("0000001");
                }
            }

            if (respuesta1disp7[1] == 2) {

                estado1disp7.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor13 + " or nmanguera=" + msupersurtidor13 + " or nmanguera=" + mdieselsurtidor13 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor13 + " or nmanguera=" + msupersurtidor13 + " or nmanguera=" + mdieselsurtidor13 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {
                            autorizar(77);
                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor13 + " or nmanguera=" + msupersurtidor13 + " or nmanguera=" + mdieselsurtidor13 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }
                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta1disp7[1] == 4) {

                estado1disp7.setText("Autorizado");

            }

            if (respuesta1disp7[1] == 8) {

                estado1disp7.setText("Defectuoso");
                estado1disp7.setForeground(Color.red);

            }

            if (respuesta1disp7[1] == 9) {

                estado1disp7.setText("Anulado");

            }

            if (respuesta1disp7[1] == 10) {

                estado1disp7.setText("Detenido");

            }

        } catch (Exception ex) {
            cont++;

        }

        servidor.setdatos13(estado1disp7.getText(), gaso1disp7.getText(), monto1disp7.getText(), volumen1disp7.getText(), ppu1);

    }

    public void generarlado2dispensador7(int c2disp7) throws Exception {
        String resp1 = "", volu2, mont2, resp2 = "", ppu2 = "";
        try {
            //RandomAccessFile pipe1 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");
            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            comando2disp7[0] = (byte) c2disp7;

            comando2disp7[1] = (byte) FSL;

            int sum1 = 0;
            int lrc1 = 0;

            for (int i = 0; i < comando2disp7.length; i++) {
                sum1 += (int) (comando2disp7[i]);
            }

            lrc1 = sum1 + 1;
            lrc1 = lrc1 & 0xff;

            comando2disp7[2] = (byte) lrc1;

            pipe.write(comando2disp7, 0, comando2disp7.length);

            bytesRead2disp7 = pipe.read(respuesta2disp7, 0, respuesta2disp7.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String p1 = null;

            for (int i = 0; i < bytesRead2disp7; i++) {

                resp1 += (char) (respuesta2disp7[i]);
                p1 += (char) (respuesta2disp7[i]);

            }

            int s2 = resp1.indexOf("~");

            if (respuesta2disp7[1] == 1 || respuesta2disp7[1] == 5) {

                estado2disp7.setText("Disponible");
                estado2disp7.setBackground(Color.orange);
                estado2disp7.setForeground(Color.black);
                // timerRTM2dispensador7.stop();
                mont2 = resp1.substring(4, s2);
                monto2disp7.setText(mont2);

                int s3 = resp1.indexOf("~", s2 + 1);

                volu2 = resp1.substring(s2 + 1, s3);

                int s4 = resp1.indexOf("~", s3 + 1);

                ppu2 = resp1.substring(s3 + 1, s4);

                String idv1, idv2;

                int s5 = resp1.indexOf("~", s4 + 1);

                idv1 = resp1.substring(s4 + 2, s5);

                int s6 = resp1.indexOf("~", s5 + 1);

                idv2 = resp1.substring(s5 + 1, s6);

                volumen2disp7.setText(volu2);

                if (ppu2.equalsIgnoreCase(pextra)) {

                    gaso2disp7.setText("Extra");

                }
                if (ppu2.equalsIgnoreCase(psuper)) {

                    gaso2disp7.setText("Super");

                }

                if (ppu2.equalsIgnoreCase(pdiesel)) {

                    gaso2disp7.setText("Diesel");

                }

                double monto, iva, total, subtotal;

                if (id2disp7.getText().length() > 0) {
                    if (id2disp7.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso2disp7.getText();
                        monto = Math.rint(Double.parseDouble(monto2disp7.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        rtm14 = 0.00;

                        if (total >= 0.05) {

                            if (gaso2disp7.getText().equalsIgnoreCase("Extra")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 14, mextrasurtidor14, producto, volumen2disp7.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm14 = 0.00;
                            }
                            if (gaso2disp7.getText().equalsIgnoreCase("Super")) {
                                rtm14 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 14, msupersurtidor14, producto, volumen2disp7.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso2disp7.getText().equalsIgnoreCase("Diesel")) {
                                rtm14 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 14, mdieselsurtidor14, producto, volumen2disp7.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                        }

                    }
                }
                id2disp7.setText(idv1);

            }
            if (respuesta2disp7[1] == 3) {

                estado2disp7.setText("Despachando");

                //timerRTM2dispensador7.start();
                gaso2disp7.setText("");
                // id2disp7.setText("");
                monto2disp7.setText("");
                estado2disp7.setBackground(Color.green);
                volumen2disp7.setText("");

                if (id2disp7.getText().length() == 0) {
                    id2disp1.setText("0000001");
                }

            }

            if (respuesta2disp7[1] == 2) {

                estado2disp7.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor14 + " or nmanguera=" + msupersurtidor14 + " or nmanguera=" + mdieselsurtidor14 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor14 + " or nmanguera=" + msupersurtidor14 + " or nmanguera=" + mdieselsurtidor14 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {
                            autorizar(78);
                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor14 + " or nmanguera=" + msupersurtidor14 + " or nmanguera=" + mdieselsurtidor14 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }
                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta2disp7[1] == 4) {

                estado2disp7.setText("Autorizado");

            }

            if (respuesta2disp7[1] == 8) {

                estado2disp7.setText("Defectuoso");
                estado2disp7.setForeground(Color.red);

            }

            if (respuesta2disp7[1] == 9) {

                estado2disp7.setText("Anulado");

            }

            if (respuesta2disp7[1] == 10) {

                estado2disp7.setText("Detenido");

            }

            int con = respuesta2disp7[2];

            int con1 = respuesta2disp7[3];

        } catch (Exception ex) {

            cont++;

        }

        servidor.setdatos14(estado2disp7.getText(), gaso2disp7.getText(), monto2disp7.getText(), volumen2disp7.getText(), ppu2);
    }

    public void generarRtm1disp7(int rtm1disp7) throws Exception {
        try {
            try (
                    RandomAccessFile pipertm = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw")) {

                comandoRTM1disp7[0] = (byte) rtm1disp7;

                comandoRTM1disp7[1] = (char) 48;

                comandoRTM1disp7[2] = (char) 48;

                comandoRTM1disp7[3] = (char) 51;

                comandoRTM1disp7[4] = (char) 49;

                comandoRTM1disp7[5] = (char) 51;

                //        comandoRTM1[6] = (byte) FSL;
                int sumr = 0;
                int lrcr = 0;

                for (int i = 0; i < comandoRTM1disp7.length; i++) {

                    sumr += (int) (comandoRTM1disp7[i]);
                }

                lrcr = sumr + 1;

                lrcr = lrcr & 0xff;

                //comandoRTM1[7] = (byte) lrcr;
                pipertm.write(comandoRTM1disp7, 0, comandoRTM1disp7.length);

                bytesRTM1disp7 = pipertm.read(respuestaRTM1disp7, 0, respuestaRTM1disp7.length);
            }

            String respRTM = "", monto13 = "";

            for (int i = 0; i < bytesRTM1disp7; i++) {

                respRTM += (char) (respuestaRTM1disp7[i]);

            }

            int s2 = respRTM.indexOf("~");

            monto13 = respRTM.substring(1, s2);

            if (monto13.equalsIgnoreCase("-0000001")) {

                monto1disp7.setText("0.00");

            } else {

                monto1disp7.setText(monto13);

                rtm13 = Double.parseDouble(monto1disp7.getText());
            }
        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes  " + cont);
        }

    }

    public void generarRtm2disp7(int rtm2disp7) throws Exception {
        try {
            RandomAccessFile pipertm2 = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandoRTM2disp7[0] = (byte) rtm2disp7;

            comandoRTM2disp7[1] = (char) 48;

            comandoRTM2disp7[2] = (char) 48;

            comandoRTM2disp7[3] = (char) 51;

            comandoRTM2disp7[4] = (char) 49;

            comandoRTM2disp7[5] = (char) 52;

            //        comandoRTM1[6] = (byte) FSL;
            int sumr1 = 0;
            int lrcr1 = 0;

            for (int i = 0; i < comandoRTM2disp7.length; i++) {

                sumr1 += (int) (comandoRTM2disp7[i]);
            }

            lrcr1 = sumr1 + 1;

            lrcr1 = lrcr1 & 0xff;

            //comandoRTM1[7] = (byte) lrcr;
            pipertm2.write(comandoRTM2disp7, 0, comandoRTM2disp7.length);

            bytesRTM2disp7 = pipertm2.read(respuestaRTM2disp7, 0, respuestaRTM2disp7.length);
            //bytesRead = pipe.read(respuesta);

            pipertm2.close();

            String respRTM1 = "", monto14 = "";

            for (int i = 0; i < bytesRTM2disp7; i++) {

                respRTM1 += (char) (respuestaRTM2disp7[i]);
            }

            int s2 = respRTM1.indexOf("~");

            monto14 = respRTM1.substring(1, s2);

            if (monto14.equalsIgnoreCase("-0000001")) {

                monto2disp7.setText("0.00");

            } else {
                monto2disp7.setText(monto14);
                rtm14 = Double.valueOf(monto2disp7.getText());
            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes" + cont);
        }

    }

    ////////////////////////////////////////////////////////////////////
    public void generarlado1dispensador8(int c1disp8) throws Exception {
        String resp = "", monto1, volu1, ppu1 = "";
        try {

            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comando1disp8[0] = (byte) c1disp8;

            comando1disp8[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comando1disp8.length; i++) {
                sum += (int) (comando1disp8[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comando1disp8[2] = (byte) lrc;

            pipe.write(comando1disp8, 0, comando1disp8.length);
            bytesRead1disp8 = pipe.read(respuesta1disp8, 0, respuesta1disp8.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            int cont = 4;
            for (int i = 0; i < bytesRead1disp8; i++) {

                resp += (char) (respuesta1disp8[i]);
            }

            int s1 = resp.indexOf("~");
            if (respuesta1disp8[1] == 1 || respuesta1disp8[1] == 5) {

                estado1disp8.setText("Disponible");

                estado1disp8.setForeground(Color.black);
                estado1disp8.setBackground(Color.orange);
                cont1 = 0;
                // timerRTM1dispensador8.stop();

                monto1 = resp.substring(4, s1);

                monto1disp8.setText(monto1);

                int s2 = resp.indexOf("~", s1 + 1);

                volu1 = resp.substring(s1 + 1, s2);

                int s3 = resp.indexOf("~", s2 + 1);

                ppu1 = resp.substring(s2 + 1, s3);

                String idv1, idv2;

                int s4 = resp.indexOf("~", s3 + 1);

                idv1 = resp.substring(s3 + 2, s4);

                int s5 = resp.indexOf("~", s4 + 1);

                idv2 = resp.substring(s4 + 1, s5);

                if (ppu1.equalsIgnoreCase(pextra)) {

                    gaso1disp8.setText("Extra");

                }
                if (ppu1.equalsIgnoreCase(psuper)) {

                    gaso1disp8.setText("Super");

                }

                if (ppu1.equalsIgnoreCase(pdiesel)) {

                    gaso1disp8.setText("Diesel");

                }

                volumen1disp8.setText(volu1);

                double monto, iva, total, subtotal;

                if (id1disp1.getText().length() > 0) {
                    if (id1disp8.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso1disp8.getText();
                        monto = Math.rint(Double.parseDouble(monto1disp8.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        if (total >= 0.05) {
                            if (gaso1disp8.getText().equalsIgnoreCase("Extra")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 15, mextrasurtidor15, producto, volumen1disp8.getText(), ppu1, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()
                                rtm15 = 0.00;
                            }
                            if (gaso1disp8.getText().equalsIgnoreCase("Super")) {
                                rtm15 = 0.00;

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 15, msupersurtidor15, producto, volumen1disp8.getText(), ppu1, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()
                            }
                            if (gaso1disp8.getText().equalsIgnoreCase("Diesel")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 15, mdieselsurtidor15, producto, volumen1disp8.getText(), ppu1, iva, subtotal, total);

                                fe.guardar();
                                //fe.start()
                                rtm15 = 0.00;
                            }
                        }

                    }

                }

                id1disp8.setText(idv1);

            }
            if (respuesta1disp8[1] == 3) {

                cont1++;
                estado1disp8.setText("Despachando");

                //timerRTM1dispensador8.start();
                estado1disp8.setBackground(Color.green);

                gaso1disp8.setText("");
                // id1disp8.setText("");
                monto1disp8.setText("");

                volumen1disp8.setText("");

                if (id1disp8.getText().length() == 0) {
                    id1disp8.setText("0000001");
                }
            }

            if (respuesta1disp8[1] == 2) {

                estado1disp8.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor15 + " or nmanguera=" + msupersurtidor15 + " or nmanguera=" + mdieselsurtidor15 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor15 + " or nmanguera=" + msupersurtidor15 + " or nmanguera=" + mdieselsurtidor15 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {
                            autorizar(79);
                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor15 + " or nmanguera=" + msupersurtidor15 + " or nmanguera=" + mdieselsurtidor15 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }
                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta1disp8[1] == 4) {

                estado1disp8.setText("Autorizado");

            }

            if (respuesta1disp8[1] == 8) {

                estado1disp8.setText("Defectuoso");
                estado1disp8.setForeground(Color.red);

            }

            if (respuesta1disp8[1] == 9) {

                estado1disp8.setText("Anulado");

            }

            if (respuesta1disp8[1] == 10) {

                estado1disp8.setText("Detenido");

            }

        } catch (Exception ex) {
            cont++;

        }

        servidor.setdatos15(estado1disp8.getText(), gaso1disp8.getText(), monto1disp8.getText(), volumen1disp8.getText(), ppu1);

    }

    public void generarlado2dispensador8(int c2disp8) throws Exception {
        String resp1 = "", volu2, mont2, resp2 = null, ppu2 = "";
        try {
            //RandomAccessFile pipe1 = new RandomAccessFile("\\\\"+ip+"\\pipe\\CEM44POSPIPE", "rw");
            pipe = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            comando2disp8[0] = (byte) c2disp8;

            comando2disp8[1] = (byte) FSL;

            int sum1 = 0;
            int lrc1 = 0;

            for (int i = 0; i < comando2disp8.length; i++) {
                sum1 += (int) (comando2disp8[i]);
            }

            lrc1 = sum1 + 1;
            lrc1 = lrc1 & 0xff;

            comando2disp8[2] = (byte) lrc1;

            pipe.write(comando2disp8, 0, comando2disp8.length);

            bytesRead2disp8 = pipe.read(respuesta2disp8, 0, respuesta2disp8.length);
            //bytesRead = pipe.read(respuesta);

            pipe.close();

            String p1 = null;

            for (int i = 0; i < bytesRead2disp8; i++) {

                resp1 += (char) (respuesta2disp8[i]);
                p1 += (char) (respuesta2disp8[i]);

            }

            int s2 = resp1.indexOf("~");

            if (respuesta2disp8[1] == 1 || respuesta2disp8[1] == 5) {

                estado2disp8.setText("Disponible");
                estado2disp8.setBackground(Color.orange);
                estado2disp8.setForeground(Color.black);
                // timerRTM2dispensador8.stop();
                mont2 = resp1.substring(4, s2);
                monto2disp8.setText(mont2);

                int s3 = resp1.indexOf("~", s2 + 1);

                volu2 = resp1.substring(s2 + 1, s3);

                int s4 = resp1.indexOf("~", s3 + 1);

                ppu2 = resp1.substring(s3 + 1, s4);

                String idv1, idv2;

                int s5 = resp1.indexOf("~", s4 + 1);

                idv1 = resp1.substring(s4 + 2, s5);

                int s6 = resp1.indexOf("~", s5 + 1);

                idv2 = resp1.substring(s5 + 1, s6);

                volumen2disp8.setText(volu2);

                if (ppu2.equalsIgnoreCase(pextra)) {

                    gaso2disp8.setText("Extra");

                }
                if (ppu2.equalsIgnoreCase(psuper)) {

                    gaso2disp8.setText("Super");

                }

                if (ppu2.equalsIgnoreCase(pdiesel)) {

                    gaso2disp8.setText("Diesel");

                }

                double monto, iva, total, subtotal;

                if (id2disp8.getText().length() > 0) {
                    if (id2disp8.getText().equals(idv1) || idv1.equals("00000000")) {
                    } else {

                        String producto = gaso2disp8.getText();

                        monto = Math.rint(Double.parseDouble(monto2disp8.getText()) * 100) / 100;

                        subtotal = monto / 1.12;

                        iva = monto - subtotal;

                        total = monto;

                        rtm16 = 0.00;

                        if (total >= 0.05) {

                            if (gaso2disp8.getText().equalsIgnoreCase("Extra")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 16, mextrasurtidor16, producto, volumen2disp8.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm16 = 0.00;
                            }
                            if (gaso2disp8.getText().equalsIgnoreCase("Super")) {
                                rtm16 = 0.00;
                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 16, msupersurtidor16, producto, volumen2disp8.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                            }
                            if (gaso2disp8.getText().equalsIgnoreCase("Diesel")) {

                                facturacion_electronica fe = new facturacion_electronica(data, usu, contra, mensajesR, f, h2, 16, mdieselsurtidor16, producto, volumen2disp8.getText(), ppu2, iva, subtotal, total);
                                fe.guardar();
                                //fe.start()

                                rtm16 = 0.00;

                            }

                        }

                    }

                }

                id2disp8.setText(idv1);

            }
            if (respuesta2disp8[1] == 3) {

                estado2disp8.setText("Despachando");

                //timerRTM2dispensador8.start();
                gaso2disp8.setText("");
                //id2disp8.setText("");
                monto2disp8.setText("");
                estado2disp8.setBackground(Color.green);
                volumen2disp8.setText("");

                if (id2disp8.getText().length() == 0) {
                    id2disp8.setText("0000001");
                }

            }

            if (respuesta2disp8[1] == 2) {

                estado2disp8.setText("Solicitud");
                try {
                    conf.conectar();

                    int manguera, autorizar = 0, estado = 0, cliente = 0;

                    Statement st_d = conf.coneccion.createStatement();
                    ResultSet rid = st_d.executeQuery("SELECT sum(autorizar) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor16 + " or nmanguera=" + msupersurtidor16 + " or nmanguera=" + mdieselsurtidor16 + ";");
                    while (rid.next()) {

                        autorizar = rid.getInt(1);

                    }
                    if (autorizar > 0) {

                        Statement st_dc = conf.coneccion.createStatement();
                        ResultSet ridc = st_dc.executeQuery("SELECT sum(cliente_idcliente) from adv_facturacion.configuracion where nmanguera=" + mextrasurtidor16 + " or nmanguera=" + msupersurtidor16 + " or nmanguera=" + mdieselsurtidor16 + ";");
                        while (ridc.next()) {

                            cliente = ridc.getInt(1);

                        }
                        if (cliente > 0) {
                            autorizar(64);

                            Statement st2 = conf.coneccion.createStatement();
                            String consulta1 = "UPDATE `adv_facturacion`.`configuracion` SET autorizar=0 where nmanguera=" + mextrasurtidor16 + " or nmanguera=" + msupersurtidor16 + " or nmanguera=" + mdieselsurtidor16 + ";";

                            st2.executeUpdate(consulta1);
                        } else {
                        }

                    } else {
                    }

                    st_d.close();

                    conf.coneccion.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    loge.log(Priority.ERROR, getStackTrace(ex));
                }

            }
            if (respuesta2disp8[1] == 4) {

                estado2disp8.setText("Autorizado");

            }

            if (respuesta2disp8[1] == 8) {

                estado2disp8.setText("Defectuoso");
                estado2disp8.setForeground(Color.red);

            }

            if (respuesta2disp8[1] == 9) {

                estado2disp8.setText("Anulado");

            }

            if (respuesta2disp8[1] == 10) {

                estado2disp8.setText("Detenido");

            }

            int con = respuesta2disp8[2];

            int con1 = respuesta2disp8[3];

        } catch (Exception ex) {

            cont++;

        }

        servidor.setdatos16(estado2disp8.getText(), gaso2disp8.getText(), monto2disp8.getText(), volumen2disp8.getText(), ppu2);
    }

    public void generarRtm1disp8(int rtm1disp8) throws Exception {
        try {
            try (
                    RandomAccessFile pipertm = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw")) {

                comandoRTM1disp8[0] = (byte) rtm1disp8;

                comandoRTM1disp8[1] = (char) 48;

                comandoRTM1disp8[2] = (char) 48;

                comandoRTM1disp8[3] = (char) 51;

                comandoRTM1disp8[4] = (char) 49;

                comandoRTM1disp8[5] = (char) 53;

                //        comandoRTM1[6] = (byte) FSL;
                int sumr = 0;
                int lrcr = 0;

                for (int i = 0; i < comandoRTM1disp8.length; i++) {

                    sumr += (int) (comandoRTM1disp8[i]);
                }

                lrcr = sumr + 1;

                lrcr = lrcr & 0xff;

                //comandoRTM1[7] = (byte) lrcr;
                pipertm.write(comandoRTM1disp8, 0, comandoRTM1disp8.length);

                bytesRTM1disp8 = pipertm.read(respuestaRTM1disp8, 0, respuestaRTM1disp8.length);
            }

            String respRTM = "", monto15 = "";

            for (int i = 0; i < bytesRTM1disp8; i++) {

                respRTM += (char) (respuestaRTM1disp8[i]);

            }

            int s2 = respRTM.indexOf("~");

            monto15 = respRTM.substring(1, s2);

            if (monto15.equalsIgnoreCase("-0000001")) {

                monto1disp8.setText("0.00");

            } else {

                monto1disp8.setText(monto15);

                rtm15 = Double.parseDouble(monto1disp8.getText());
            }
        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes  " + cont);
        }

    }

    public void generarRtm2disp8(int rtm2disp8) throws Exception {
        try {

            RandomAccessFile pipertm2 = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandoRTM2disp8[0] = (byte) rtm2disp8;

            comandoRTM2disp8[1] = (char) 48;

            comandoRTM2disp8[2] = (char) 48;

            comandoRTM2disp8[3] = (char) 51;

            comandoRTM2disp8[4] = (char) 49;

            comandoRTM2disp8[5] = (char) 54;

            int sumr1 = 0;
            int lrcr1 = 0;

            for (int i = 0; i < comandoRTM2disp8.length; i++) {

                sumr1 += (int) (comandoRTM2disp8[i]);
            }

            lrcr1 = sumr1 + 1;

            lrcr1 = lrcr1 & 0xff;

            //comandoRTM1[7] = (byte) lrcr;
            pipertm2.write(comandoRTM2disp8, 0, comandoRTM2disp8.length);

            bytesRTM2disp8 = pipertm2.read(respuestaRTM2disp8, 0, respuestaRTM2disp8.length);
            //bytesRead = pipe.read(respuesta);

            pipertm2.close();

            String respRTM1 = "", monto16 = "";

            for (int i = 0; i < bytesRTM2disp8; i++) {

                respRTM1 += (char) (respuestaRTM2disp8[i]);
            }

            int s2 = respRTM1.indexOf("~");

            monto16 = respRTM1.substring(1, s2);

            if (monto16.equalsIgnoreCase("-0000001")) {

                monto2disp8.setText("0.00");

            } else {

                monto2disp8.setText(monto16);
                rtm16 = Double.valueOf(monto2disp8.getText());
            }

        } catch (Exception ex) {
            cont++;
            //System.out.println("Se Cruzaron 2 pipes" + cont);
        }

    }
    ////////////////////////////////////////////////////////////////////

    /* public String respuesta(String xml, String tag) {
       
        
     String message = "";






     DOMParser parser = new DOMParser();
     try {
     parser.parse(new InputSource(new java.io.StringReader(xml)));
     org.w3c.dom.Document doc = parser.getDocument();
     String raiz = doc.getDocumentElement().getNodeName();
     doc.getDocumentElement().normalize();
     // System.out.println("El elemento raíz es: " + raiz);
     NodeList listaPersonas = doc.getElementsByTagName(tag);
     //System.out.println(listaPersonas.getLength());


     for (int i = 0; i < listaPersonas.getLength(); i++) {

     Node persona = listaPersonas.item(i);




     message = persona.getTextContent();


     }




     } catch (Exception e) {
     // handle SAXException 
     } catch (SAXException ex) {
     Logger.getLogger(Surtidores.class
     .getName()).log(Level.SEVERE, null, ex);
     }
     return message;
     }
     */
    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();

    }

    public String hora() {
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        return hourFormat.format(date);

    }

    public String fecha() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        f.setText(dateFormat.format(date));

// 	
        if (dateFormat.format(date).substring(0, 2).equalsIgnoreCase("01") && hora().equalsIgnoreCase("00:00:00")) {
        }

        return dateFormat.format(date);
    }

    public static int diasDelMes(int mes, int año) {
        switch (mes) {
            case 1:  // Enero
            case 3:  // Marzo
            case 5:  // Mayo
            case 7:  // Julio
            case 8:  // Agosto
            case 10:  // Octubre
            case 12: // Diciembre
                return 31;
            case 4:  // Abril
            case 6:  // Junio
            case 9:  // Septiembre
            case 11: // Noviembre
                return 30;
            case 2:  // Febrero
                if (((año % 100 == 0) && (año % 400 == 0))
                        || ((año % 100 != 0) && (año % 4 == 0))) {
                    return 29;  // Año Bisiesto
                } else {
                    return 28;
                }
            default:
                throw new java.lang.IllegalArgumentException(
                        "El mes debe estar entre 0 y 11");
        }

    }

    public void enviarpreset() {
        try {

            int digito1 = 0, digito2 = 0, digito3 = 0, digito4 = 0, digito5 = 0, digito6 = 0, decimal1, decimal2;
            int surtidor = 0;
            Double preset;

            if (panel == 1 && ladopreset == 1) {

                surtidor = 17;

            }
            if (panel == 1 && ladopreset == 2) {

                surtidor = 18;

            }

            if (panel == 2 && ladopreset == 1) {

                surtidor = 19;

            }

            if (panel == 2 && ladopreset == 2) {

                surtidor = 20;

            }

            if (panel == 3 && ladopreset == 1) {

                surtidor = 21;

            }

            if (panel == 3 && ladopreset == 2) {

                surtidor = 22;

            }

            preset = Double.parseDouble(pantalla.getText());

            int p_ent;
            p_ent = (int) Math.floor(preset);
            String[] decimal = pantalla.getText().split("\\.");

            String Decimal = decimal[1];

            System.out.println(p_ent + "   " + Decimal);

            String en;
            en = String.valueOf(p_ent);

            String entero = String.valueOf(p_ent);

            System.out.println(en.charAt(0) + " = " + en.codePointAt(0));;
            //System.out.println(en.charAt(1) + " = " + en.codePointAt(1));;

            if (p_ent <= 9) {

                // System.out.println(en.charAt(0) + " = " + en.codePointAt(0));;
                System.out.println("menor a nueve");

                digito2 = Integer.parseInt(Decimal.substring(0, 1));
                digito1 = Integer.parseInt(Decimal.substring(1));
                digito3 = p_ent;
                digito4 = 0;
                digito5 = 0;
                digito6 = 0;

                System.out.println(productopreset);
                System.out.println(surtidor);

                String d2 = String.valueOf(digito2);
                digito2 = d2.codePointAt(0);
                String d1 = String.valueOf(digito1);
                digito1 = d1.codePointAt(0);
                String d3 = String.valueOf(digito3);
                digito3 = d3.codePointAt(0);
                String d4 = String.valueOf(digito4);
                digito4 = d4.codePointAt(0);
                String d5 = String.valueOf(digito5);
                digito5 = d5.codePointAt(0);
                String d6 = String.valueOf(digito6);
                digito6 = d6.codePointAt(0);
                System.out.println("digito1   " + digito3 + "digito2  " + digito2 + "  digito3" + digito1);
            }

            if (p_ent <= 99 && p_ent > 9) {

                System.out.println("menor a cien");

                digito2 = Integer.parseInt(Decimal.substring(0, 1));
                digito1 = Integer.parseInt(Decimal.substring(1));
                digito4 = Integer.parseInt(entero.substring(0, 1));
                digito3 = Integer.parseInt(entero.substring(1));
                digito5 = 0;
                digito6 = 0;

                String d2 = String.valueOf(digito2);
                digito2 = d2.codePointAt(0);
                String d1 = String.valueOf(digito1);
                digito1 = d1.codePointAt(0);
                String d3 = String.valueOf(digito3);
                digito3 = d3.codePointAt(0);
                String d4 = String.valueOf(digito4);
                digito4 = d4.codePointAt(0);
                String d5 = String.valueOf(digito5);
                digito5 = d5.codePointAt(0);
                String d6 = String.valueOf(digito6);
                digito6 = d6.codePointAt(0);

                System.out.println("digito1   " + digito4 + "  digito2  " + digito3 + "  digito3  " + digito2 + "  digito4  " + digito1);
                System.out.println(productopreset);
                System.out.println(surtidor);

            }

            if (p_ent <= 1000 && p_ent > 99) {

                System.out.println("menor a mil");

                digito2 = Integer.parseInt(Decimal.substring(0, 1));
                digito1 = Integer.parseInt(Decimal.substring(1));
                digito5 = Integer.parseInt(entero.substring(0, 1));
                digito4 = Integer.parseInt(entero.substring(1, 2));
                digito3 = Integer.parseInt(entero.substring(2));
                digito6 = 0;

                String d2 = String.valueOf(digito2);
                digito2 = d2.codePointAt(0);
                String d1 = String.valueOf(digito1);
                digito1 = d1.codePointAt(0);
                String d3 = String.valueOf(digito3);
                digito3 = d3.codePointAt(0);
                String d4 = String.valueOf(digito4);
                digito4 = d4.codePointAt(0);
                String d5 = String.valueOf(digito5);
                digito5 = d5.codePointAt(0);
                String d6 = String.valueOf(digito6);
                digito6 = d6.codePointAt(0);

                System.out.println("digito1=" + digito5 + "  digito2=  " + digito4 + " digito3 =" + digito3 + "  digito3 = " + digito2 + "  digito4 = " + digito1);
                System.out.println(productopreset);
                System.out.println(surtidor);

            }

            RandomAccessFile pipepreset = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandopreset1[0] = (byte) surtidor;

            comandopreset1[1] = (byte) productopreset;

            comandopreset1[2] = (byte) 1;

            comandopreset1[3] = (byte) digito6;

            comandopreset1[4] = (byte) digito5;

            comandopreset1[5] = (byte) digito4;

            comandopreset1[6] = (byte) digito3;

            comandopreset1[7] = (byte) digito2;

            comandopreset1[8] = (byte) digito1;

            comandopreset1[9] = (byte) FSL;

            int sumr = 0;
            int lrcr = 0;

            for (int i = 0; i < comandopreset1.length; i++) {

                sumr += (int) (comandopreset1[i]);
            }

            lrcr = sumr + 1;

            lrcr = lrcr & 0xff;

            comandopreset1[10] = (byte) lrcr;

            // pipepreset.write(comandopreset1, 0, comandopreset1.length);
            bytesReadpreset1 = pipepreset.read(respuestapreset1, 0, respuestapreset1.length);
            //bytesRead = pipe.read(respuesta);

            pipepreset.close();

            String resppreset = "", monto = "";

            for (int i = 0; i < bytesReadpreset1; i++) {

                resppreset += (char) (respuestapreset1[i]);
            }

            //int s2 = resppreset.indexOf("~");
            System.out.println(resppreset);

        } catch (Exception ex) {
            Logger.getLogger(Surtidores.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void configuracion() throws Exception {

        conexion_facturacion con = new conexion_facturacion(usu, contra);

        try {
            con.conectar();

            Statement st_d = con.coneccion.createStatement();
            ResultSet rid = st_d.executeQuery("SELECT punit FROM adv_facturacion.producto where nombre='extra';");

            Statement st_u = con.coneccion.createStatement();
            ResultSet riu = st_u.executeQuery("SELECT punit FROM adv_facturacion.producto where nombre='super';");

            Statement st_f = con.coneccion.createStatement();
            ResultSet ridf = st_f.executeQuery("SELECT punit FROM adv_facturacion.producto where nombre='diesel';");

            Statement st_s1 = con.coneccion.createStatement();
            ResultSet rids1 = st_s1.executeQuery("SELECT count(nmanguera) FROM adv_facturacion.configuracion where surtidor=1;");
            while (rids1.next()) {

                nmanguerasurt1 = rids1.getInt(1);
            }

            Statement st_s3 = con.coneccion.createStatement();
            ResultSet rids3 = st_s3.executeQuery("SELECT count(nmanguera) FROM adv_facturacion.configuracion where surtidor=3;");
            while (rids3.next()) {

                nmanguerasurt3 = rids3.getInt(1);
            }

            Statement st_s5 = con.coneccion.createStatement();
            ResultSet rids5 = st_s5.executeQuery("SELECT count(nmanguera) FROM adv_facturacion.configuracion where surtidor=5;");
            while (rids5.next()) {

                nmanguerasurt5 = rids5.getInt(1);
            }
            Statement st_s7 = con.coneccion.createStatement();
            ResultSet rids7 = st_s7.executeQuery("SELECT count(nmanguera) FROM adv_facturacion.configuracion where surtidor=7;");
            while (rids7.next()) {

                nmanguerasurt7 = rids7.getInt(1);
            }

            Statement st_s9 = con.coneccion.createStatement();
            ResultSet rids9 = st_s9.executeQuery("SELECT count(nmanguera) FROM adv_facturacion.configuracion where surtidor=9;");
            while (rids9.next()) {

                nmanguerasurt9 = rids9.getInt(1);
            }

            Statement st_s11 = con.coneccion.createStatement();
            ResultSet rids11 = st_s11.executeQuery("SELECT count(nmanguera) FROM adv_facturacion.configuracion where surtidor=11;");
            while (rids11.next()) {

                nmanguerasurt11 = rids11.getInt(1);
            }

            System.out.println(nmanguerasurt1 + "  " + nmanguerasurt3 + "  " + nmanguerasurt5 + "  " + nmanguerasurt7 + "  " + nmanguerasurt9);

            Statement st_m1 = con.coneccion.createStatement();
            ResultSet ridsm1 = st_m1.executeQuery("SELECT nombre FROM adv_facturacion.configuracion,adv_facturacion.producto where producto_idproducto=idproducto and surtidor=1 order by nmanguera;");
            int cont = 0;
            while (ridsm1.next()) {

                if (cont == 0) {

                    pmanguera1surt1 = ridsm1.getString(1);

                }

                if (cont == 1) {
                    pmanguera2surt1 = ridsm1.getString(1);

                }
                if (cont == 2) {

                    pmanguera3surt1 = ridsm1.getString(1);

                }
                cont++;
            }

            Statement st_m2 = con.coneccion.createStatement();
            ResultSet ridsm2 = st_m2.executeQuery("SELECT nombre FROM adv_facturacion.configuracion,adv_facturacion.producto where producto_idproducto=idproducto and surtidor=3 order by nmanguera;");
            int cont2 = 0;
            while (ridsm2.next()) {

                if (cont2 == 0) {

                    pmanguera1surt3 = ridsm2.getString(1);

                }

                if (cont2 == 1) {
                    pmanguera2surt3 = ridsm2.getString(1);

                }
                if (cont2 == 2) {

                    pmanguera3surt3 = ridsm2.getString(1);

                }
                cont2++;
            }

            Statement st_m3 = con.coneccion.createStatement();
            ResultSet ridsm3 = st_m3.executeQuery("SELECT nombre FROM adv_facturacion.configuracion,adv_facturacion.producto where producto_idproducto=idproducto and surtidor=5 order by nmanguera;");
            int cont3 = 0;
            while (ridsm3.next()) {

                if (cont3 == 0) {

                    pmanguera1surt5 = ridsm3.getString(1);

                }

                if (cont3 == 1) {
                    pmanguera2surt5 = ridsm3.getString(1);

                }
                if (cont3 == 2) {

                    pmanguera3surt5 = ridsm3.getString(1);

                }
                cont3++;
            }

            Statement st_m4 = con.coneccion.createStatement();
            ResultSet ridsm4 = st_m4.executeQuery("SELECT nombre FROM adv_facturacion.configuracion,adv_facturacion.producto where producto_idproducto=idproducto and surtidor=7 order by nmanguera;");
            int cont4 = 0;
            while (ridsm4.next()) {

                if (cont4 == 0) {

                    pmanguera1surt7 = ridsm4.getString(1);

                }

                if (cont4 == 1) {
                    pmanguera2surt7 = ridsm4.getString(1);

                }
                if (cont3 == 2) {

                    pmanguera3surt7 = ridsm4.getString(1);

                }
                cont4++;
            }

            System.out.println("Surtidor 1 " + pmanguera1surt1 + "    " + pmanguera2surt1 + "   " + pmanguera3surt1);
            System.out.println("Surtidor 3 " + pmanguera1surt3 + "    " + pmanguera2surt3 + "   " + pmanguera3surt3);
            System.out.println("Surtidor 5 " + pmanguera1surt5 + "    " + pmanguera2surt5 + "   " + pmanguera3surt5);
            System.out.println("Surtidor 7 " + pmanguera1surt7 + "    " + pmanguera2surt7 + "   " + pmanguera3surt7);

            while (ridf.next()) {

                pdiesel = ridf.getString(1);
            }

            while (riu.next()) {

                psuper = riu.getString(1);
            }

            while (rid.next()) {

                pextra = rid.getString(1);
            }

            con.coneccion.close();

            conexion_facturacion c = new conexion_facturacion(usu, contra);

            c.conectar();

            Statement st_m1a = c.coneccion.createStatement();
            ResultSet ridm1a = st_m1a.executeQuery("SELECT nmanguera from configuracion where surtidor=1 and  producto_idproducto=1");
            if (ridm1a.first()) {

                mextrasurtidor1 = ridm1a.getInt(1);
            }
            System.out.println(mextrasurtidor1);

            Statement st_m1b = c.coneccion.createStatement();
            ResultSet ridm1b = st_m1b.executeQuery("SELECT nmanguera from configuracion where surtidor=1 and  producto_idproducto=2");
            if (ridm1b.first()) {

                msupersurtidor1 = ridm1b.getInt(1);
            }

            Statement st_m1c = c.coneccion.createStatement();
            ResultSet ridm1c = st_m1c.executeQuery("SELECT nmanguera from configuracion where surtidor=1 and  producto_idproducto=3");
            if (ridm1c.first()) {

                mdieselsurtidor1 = ridm1c.getInt(1);
            }
            ////////////////////////////////////////////////////////
            Statement st_m2a = c.coneccion.createStatement();
            ResultSet ridm2a = st_m2a.executeQuery("SELECT nmanguera from configuracion where surtidor=2 and  producto_idproducto=1");
            if (ridm2a.first()) {

                mextrasurtidor2 = ridm2a.getInt(1);
            }

            Statement st_m2b = c.coneccion.createStatement();
            ResultSet ridm2b = st_m2b.executeQuery("SELECT nmanguera from configuracion where surtidor=2 and  producto_idproducto=2");
            if (ridm2b.first()) {

                msupersurtidor2 = ridm2b.getInt(1);
            }

            Statement st_m2c = c.coneccion.createStatement();
            ResultSet ridm2c = st_m2c.executeQuery("SELECT nmanguera from configuracion where surtidor=2 and  producto_idproducto=3");
            if (ridm2c.first()) {

                mdieselsurtidor2 = ridm2c.getInt(1);
            }
            ///////////////////////////////////////////

            Statement st_m3a = c.coneccion.createStatement();
            ResultSet ridm3a = st_m3a.executeQuery("SELECT nmanguera from configuracion where surtidor=3 and  producto_idproducto=1");
            if (ridm3a.first()) {

                mextrasurtidor3 = ridm3a.getInt(1);
            }

            Statement st_m3b = c.coneccion.createStatement();
            ResultSet ridm3b = st_m3b.executeQuery("SELECT nmanguera from configuracion where surtidor=3 and  producto_idproducto=2");
            if (ridm3b.first()) {

                msupersurtidor3 = ridm3b.getInt(1);
            }

            Statement st_m3c = c.coneccion.createStatement();
            ResultSet ridm3c = st_m3c.executeQuery("SELECT nmanguera from configuracion where surtidor=3 and  producto_idproducto=3");
            if (ridm3c.first()) {

                mdieselsurtidor3 = ridm3c.getInt(1);
            }

            ////////////////////////////////////////////////////////////////
            Statement st_m4a = c.coneccion.createStatement();
            ResultSet ridm4a = st_m4a.executeQuery("SELECT nmanguera from configuracion where surtidor=4 and  producto_idproducto=1");
            if (ridm4a.first()) {

                mextrasurtidor4 = ridm4a.getInt(1);
            }

            Statement st_m4b = c.coneccion.createStatement();
            ResultSet ridm4b = st_m4b.executeQuery("SELECT nmanguera from configuracion where surtidor=4 and  producto_idproducto=2");
            if (ridm4b.first()) {

                msupersurtidor4 = ridm4b.getInt(1);
            }

            Statement st_m4c = c.coneccion.createStatement();
            ResultSet ridm4c = st_m4c.executeQuery("SELECT nmanguera from configuracion where surtidor=4 and  producto_idproducto=3");
            if (ridm4c.first()) {

                mdieselsurtidor4 = ridm4c.getInt(1);
            }

            Statement st_m5a = c.coneccion.createStatement();
            ResultSet ridm5a = st_m5a.executeQuery("SELECT nmanguera from configuracion where surtidor=5 and  producto_idproducto=1");
            if (ridm5a.first()) {

                mextrasurtidor5 = ridm5a.getInt(1);
            }

            Statement st_m5b = c.coneccion.createStatement();
            ResultSet ridm5b = st_m5b.executeQuery("SELECT nmanguera from configuracion where surtidor=5 and  producto_idproducto=2");
            if (ridm5b.first()) {

                msupersurtidor5 = ridm5b.getInt(1);
            }

            Statement st_m5c = c.coneccion.createStatement();
            ResultSet ridm5c = st_m5c.executeQuery("SELECT nmanguera from configuracion where surtidor=5 and  producto_idproducto=3");
            if (ridm5c.first()) {

                mdieselsurtidor5 = ridm5c.getInt(1);
            }
            /////////////////////////////////////////////////////////////////
            Statement st_m6a = c.coneccion.createStatement();
            ResultSet ridm6a = st_m6a.executeQuery("SELECT nmanguera from configuracion where surtidor=6 and  producto_idproducto=1");
            if (ridm6a.first()) {

                mextrasurtidor6 = ridm6a.getInt(1);
            }

            Statement st_m6b = c.coneccion.createStatement();
            ResultSet ridm6b = st_m6b.executeQuery("SELECT nmanguera from configuracion where surtidor=6 and  producto_idproducto=2");
            if (ridm6b.first()) {

                msupersurtidor6 = ridm6b.getInt(1);
            }

            Statement st_m6c = c.coneccion.createStatement();
            ResultSet ridm6c = st_m6c.executeQuery("SELECT nmanguera from configuracion where surtidor=6 and  producto_idproducto=3");
            if (ridm6c.first()) {

                mdieselsurtidor6 = ridm6c.getInt(1);
            }
            //////////////////////////////////////////////////////
            Statement st_m7a = c.coneccion.createStatement();
            ResultSet ridm7a = st_m7a.executeQuery("SELECT nmanguera from configuracion where surtidor=7 and  producto_idproducto=1");
            if (ridm7a.first()) {

                mextrasurtidor7 = ridm7a.getInt(1);
            }

            Statement st_m7b = c.coneccion.createStatement();
            ResultSet ridm7b = st_m7b.executeQuery("SELECT nmanguera from configuracion where surtidor=7 and  producto_idproducto=2");
            if (ridm7b.first()) {

                msupersurtidor7 = ridm7b.getInt(1);
            }

            Statement st_m7c = c.coneccion.createStatement();
            ResultSet ridm7c = st_m7c.executeQuery("SELECT nmanguera from configuracion where surtidor=7 and  producto_idproducto=3");
            if (ridm7c.first()) {

                mdieselsurtidor7 = ridm7c.getInt(1);
            }

            //////////////////////////////////
            Statement st_m8a = c.coneccion.createStatement();
            ResultSet ridm8a = st_m8a.executeQuery("SELECT nmanguera from configuracion where surtidor=8 and  producto_idproducto=1");
            if (ridm8a.first()) {

                mextrasurtidor8 = ridm8a.getInt(1);
            }

            Statement st_m8b = c.coneccion.createStatement();
            ResultSet ridm8b = st_m8b.executeQuery("SELECT nmanguera from configuracion where surtidor=8 and  producto_idproducto=2");
            if (ridm8b.first()) {

                msupersurtidor8 = ridm8b.getInt(1);
            }

            Statement st_m8c = c.coneccion.createStatement();
            ResultSet ridm8c = st_m8c.executeQuery("SELECT nmanguera from configuracion where surtidor=8 and  producto_idproducto=3");
            if (ridm8c.first()) {

                mdieselsurtidor8 = ridm8c.getInt(1);
            }
            ///////////////////////////////////////
            Statement st_m9a = c.coneccion.createStatement();
            ResultSet ridm9a = st_m9a.executeQuery("SELECT nmanguera from configuracion where surtidor=9 and  producto_idproducto=1");
            if (ridm9a.first()) {

                mextrasurtidor9 = ridm9a.getInt(1);
            }

            Statement st_m9b = c.coneccion.createStatement();
            ResultSet ridm9b = st_m9b.executeQuery("SELECT nmanguera from configuracion where surtidor=9 and  producto_idproducto=2");
            if (ridm9b.first()) {

                msupersurtidor9 = ridm9b.getInt(1);
            }

            Statement st_m9c = c.coneccion.createStatement();
            ResultSet ridm9c = st_m9c.executeQuery("SELECT nmanguera from configuracion where surtidor=9 and  producto_idproducto=3");
            if (ridm9c.first()) {

                mdieselsurtidor9 = ridm9c.getInt(1);
            }
            ////////////////////////////////////////////////////////////////////////
            Statement st_m10a = c.coneccion.createStatement();
            ResultSet ridm10a = st_m10a.executeQuery("SELECT nmanguera from configuracion where surtidor=10 and  producto_idproducto=1");
            if (ridm10a.first()) {

                mextrasurtidor10 = ridm10a.getInt(1);
            }

            Statement st_m10b = c.coneccion.createStatement();
            ResultSet ridm10b = st_m10b.executeQuery("SELECT nmanguera from configuracion where surtidor=10 and  producto_idproducto=2");
            if (ridm10b.first()) {

                msupersurtidor10 = ridm10b.getInt(1);
            }

            Statement st_m10c = c.coneccion.createStatement();
            ResultSet ridm10c = st_m10c.executeQuery("SELECT nmanguera from configuracion where surtidor=10 and  producto_idproducto=3");
            if (ridm10c.first()) {

                mdieselsurtidor10 = ridm10c.getInt(1);
            }
            /////////////////////////////////////////////////////////////////////
            Statement st_m11a = c.coneccion.createStatement();
            ResultSet ridm11a = st_m11a.executeQuery("SELECT nmanguera from configuracion where surtidor=11 and  producto_idproducto=1");
            if (ridm11a.first()) {

                mextrasurtidor11 = ridm11a.getInt(1);
            }

            Statement st_m11b = c.coneccion.createStatement();
            ResultSet ridm11b = st_m11b.executeQuery("SELECT nmanguera from configuracion where surtidor=11 and  producto_idproducto=2");
            if (ridm11b.first()) {

                msupersurtidor11 = ridm11b.getInt(1);
            }

            Statement st_m11c = c.coneccion.createStatement();
            ResultSet ridm11c = st_m2c.executeQuery("SELECT nmanguera from configuracion where surtidor=11 and  producto_idproducto=3");
            if (ridm11c.first()) {

                mdieselsurtidor11 = ridm11c.getInt(1);
            }

            //////////////////////////////////////
            Statement st_m12a = c.coneccion.createStatement();
            ResultSet ridm12a = st_m12a.executeQuery("SELECT nmanguera from configuracion where surtidor=12 and  producto_idproducto=1");
            if (ridm12a.first()) {

                mextrasurtidor12 = ridm12a.getInt(1);
            }

            Statement st_m12b = c.coneccion.createStatement();
            ResultSet ridm12b = st_m12b.executeQuery("SELECT nmanguera from configuracion where surtidor=12 and  producto_idproducto=2");
            if (ridm12b.first()) {

                msupersurtidor12 = ridm12b.getInt(1);
            }

            Statement st_m12c = c.coneccion.createStatement();
            ResultSet ridm12c = st_m12c.executeQuery("SELECT nmanguera from configuracion where surtidor=12 and  producto_idproducto=3");
            if (ridm12c.first()) {

                mdieselsurtidor12 = ridm12c.getInt(1);
            }
            //////////////////////////////////////////////////
            Statement st_m13a = c.coneccion.createStatement();
            ResultSet ridm13a = st_m13a.executeQuery("SELECT nmanguera from configuracion where surtidor=13 and  producto_idproducto=1");
            if (ridm13a.first()) {

                mextrasurtidor13 = ridm13a.getInt(1);
            }

            Statement st_m13b = c.coneccion.createStatement();
            ResultSet ridm13b = st_m13b.executeQuery("SELECT nmanguera from configuracion where surtidor=13 and  producto_idproducto=2");
            if (ridm13b.first()) {

                msupersurtidor13 = ridm13b.getInt(1);
            }

            Statement st_m13c = c.coneccion.createStatement();
            ResultSet ridm13c = st_m13c.executeQuery("SELECT nmanguera from configuracion where surtidor=13 and  producto_idproducto=3");
            if (ridm13c.first()) {

                mdieselsurtidor13 = ridm13c.getInt(1);
            }

            /////////////////////////////////////////////////
            Statement st_m14a = c.coneccion.createStatement();
            ResultSet ridm14a = st_m14a.executeQuery("SELECT nmanguera from configuracion where surtidor=14 and  producto_idproducto=1");
            if (ridm14a.first()) {

                mextrasurtidor14 = ridm14a.getInt(1);
            }

            Statement st_m14b = c.coneccion.createStatement();
            ResultSet ridm14b = st_m14b.executeQuery("SELECT nmanguera from configuracion where surtidor=14 and  producto_idproducto=2");
            if (ridm14b.first()) {

                msupersurtidor14 = ridm14b.getInt(1);
            }

            Statement st_m14c = c.coneccion.createStatement();
            ResultSet ridm14c = st_m14c.executeQuery("SELECT nmanguera from configuracion where surtidor=14 and  producto_idproducto=3");
            if (ridm14c.first()) {

                mdieselsurtidor14 = ridm14c.getInt(1);
            }

            //////////////////////////////////////////////
            Statement st_m15a = c.coneccion.createStatement();
            ResultSet ridm15a = st_m15a.executeQuery("SELECT nmanguera from configuracion where surtidor=15 and  producto_idproducto=1");
            if (ridm15a.first()) {

                mextrasurtidor15 = ridm12a.getInt(1);
            }

            Statement st_m15b = c.coneccion.createStatement();
            ResultSet ridm15b = st_m15b.executeQuery("SELECT nmanguera from configuracion where surtidor=15 and  producto_idproducto=2");
            if (ridm15b.first()) {

                msupersurtidor15 = ridm15b.getInt(1);
            }

            Statement st_m15c = c.coneccion.createStatement();
            ResultSet ridm15c = st_m15c.executeQuery("SELECT nmanguera from configuracion where surtidor=15 and  producto_idproducto=3");
            if (ridm15c.first()) {

                mdieselsurtidor15 = ridm15c.getInt(1);
            }

            /////////////////////////////////////////////
            Statement st_m16a = c.coneccion.createStatement();
            ResultSet ridm16a = st_m16a.executeQuery("SELECT nmanguera from configuracion where surtidor=16 and  producto_idproducto=1");
            if (ridm16a.first()) {

                mextrasurtidor16 = ridm16a.getInt(1);
            }

            Statement st_m16b = c.coneccion.createStatement();
            ResultSet ridm16b = st_m16b.executeQuery("SELECT nmanguera from configuracion where surtidor=16 and  producto_idproducto=2");
            if (ridm16b.first()) {

                msupersurtidor16 = ridm16b.getInt(1);
            }

            Statement st_m16c = c.coneccion.createStatement();
            ResultSet ridm16c = st_m16c.executeQuery("SELECT nmanguera from configuracion where surtidor=16 and  producto_idproducto=3");
            if (ridm16c.first()) {

                mdieselsurtidor16 = ridm16c.getInt(1);
            }

            //////////////////////////////////////
            c.coneccion.close();

        } catch (ClassNotFoundException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
        } catch (SQLException ex) {
            loge.log(Priority.FATAL, getStackTrace(ex));
        }

        try {

            RandomAccessFile pipeconfig = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");

            comandoconfig[0] = (byte) 101;

            comandoconfig[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comandoconfig.length; i++) {
                sum += (int) (comandoconfig[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            comandoconfig[2] = (byte) lrc;

            pipeconfig.write(comandoconfig, 0, comandoconfig.length);

            bytesReadconfig = pipeconfig.read(respuestaconfig, 0, respuestaconfig.length);
            //bytesRead = pipe.read(respuesta);

            pipeconfig.close();

            String resp = "";
            String resp2 = "";
            int[] resp3 = new int[1000];

            for (int i = 0; i < bytesReadconfig; i++) {

                resp += (respuestaconfig[i]);

                if (i >= 5) {

                    resp2 += (char) (respuestaconfig[i]);
                }

                if (i >= 50) {

                    for (int a = 0; a < bytesReadconfig; a++) {

                        resp3[a] += (respuestaconfig[i]);

                    }

                }

            }
            int n = Integer.valueOf(respuestaconfig[1]) / 2;

            totals = Integer.valueOf(respuestaconfig[1]);

            String numero = String.valueOf(n);
            StringTokenizer tokens1 = new StringTokenizer(resp2, "~");

            int nDatosc1 = tokens1.countTokens();
            String[] datosc1 = new String[nDatosc1];
            int i1 = 0;
            while (tokens1.hasMoreTokens()) {
                String str1 = tokens1.nextToken();
                datosc1[i1] = str1;

                i1++;
            }

            String p1, p2, p3;
            p1 = datosc1[1];
            p2 = datosc1[4];
            p3 = datosc1[7];

            if (p1.equals(psuper)) {

                supern = 1;
                lproducto2 = 97;
                if (p2.equalsIgnoreCase(pextra)) {

                    lproducto1 = 98;
                    extran = 2;
                }
                if (p3.equalsIgnoreCase(pdiesel)) {

                    lproducto3 = 99;
                    dieseln = 3;

                }

            } else if (p1.equalsIgnoreCase(pextra)) {

                extran = 1;
                lproducto1 = 97;

                if (p2.equalsIgnoreCase(psuper)) {

                    lproducto2 = 98;
                    supern = 2;
                }
                if (p3.equalsIgnoreCase(pdiesel)) {

                    lproducto3 = 99;
                    dieseln = 3;

                }

            } else if (p1.equalsIgnoreCase(pdiesel)) {

                lproducto3 = 97;
                dieseln = 1;

                if (p2.equalsIgnoreCase(pextra)) {

                    lproducto1 = 98;
                    extran = 2;
                }
                if (p3.equalsIgnoreCase(psuper)) {

                    lproducto2 = 99;
                    supern = 3;

                }

            }

            nsurtidores.setText(numero);

            System.out.println("Super numero" + supern);
            System.out.println("Extra numero" + extran);
            System.out.println("Diesel numero" + dieseln);

            con.coneccion.close();

        } catch (FileNotFoundException ex) {
            configuracion();
        } catch (SQLException ex) {
            Logger.getLogger(Surtidores.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
    public String estado1;

    private void numeroPulsado(String digito) {
        if (pantalla.getText().equals("0")) {
            pantalla.setText(digito);
        } else {
            pantalla.setText(pantalla.getText() + digito);
        }

    }

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

                if (cont == 3) {
                    ip_matriz = linea.substring(17, linea.length());

                }

                cont++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void usuarios(String usuario, String contraseña) {

        contra = contraseña;
        usu = usuario;

        conf = new conexion_facturacion(usu, contra);

    }

    private static void startRegistry(int RMIPortNum) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list();
        } catch (RemoteException e) {
            LocateRegistry.createRegistry(RMIPortNum);
        }
    }
public static String Ip = "",ServidorID="";


    
    
    public void servidor() {
        String registryURL;
        

        try {

            startRegistry(1099);


           

            registryURL = "rmi://" + ip + ":1099/servidor";

            
            
            ServidorID = System.getProperty("ServidorID");

            Ip = System.getProperty("java.rmi.server.servidor");
           // System.out.println("IP:" + Ip + ": Servidor (" + ServidorID + ")");
            
            
            //Registra la implementación del servidor (montecarlo) en el registro
            Naming.rebind(registryURL, servidor);
            System.out.println("Listo el servidor");

        } catch (RemoteException ex) {
            // loge.log(Priority.ERROR,getStackTrace(ex));
            loge.log(Priority.ERROR, getStackTrace(ex));
            JOptionPane.showMessageDialog(this, "Error las operaciones de playa ya se encuentran abiertas");
            System.exit(0);

        } catch (MalformedURLException ex) {
            Logger.getLogger(Surtidores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
