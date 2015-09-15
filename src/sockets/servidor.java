package sockets;

import Principal.Surtidores;
import static Principal.Surtidores.getStackTrace;
import Principal.impresion;
import conexion.Conecciones;
import conexion.conexion_facturacion;
import java_splash.Principal;
import conexion.conexion_sis_contable;
import facturacion.crear_clave_acceso;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.PrinterName;
import facturacion.crear_factura_credito_1;
import facturacion.crear_nota_credito_normal1;
import static facturacion.facturacion_electronica.getStackTrace;
import java.io.StringWriter;
import java.sql.Connection;
import javax.sql.DataSource;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import seguridad.FirmaDigital;

public class servidor extends UnicastRemoteObject implements metodosServidor {

    org.apache.log4j.Logger loge = org.apache.log4j.Logger.getLogger(servidor.class);
    DataSource data;
    Conecciones he1;
    Connection n = null;
    String usuario, contra;
    Date fechalogs = new Date();
    int nsurtidor, manguera;
    String producto, cang, punit;
    Double iva, subtotal, total;
    Double cupo = 0.00;
    String tp = "";
    String codigop = "";
    Double cantidad, pu, galones, precio;
    String ocont = "";
    int cespecial = 0;
    String nc = "";
    String rz = "", np = "", d = "", r = "", na = "", clientr = "", tcliente = "";
    String s1 = "", s2 = "", tel = "";
    String ss3 = "", ta = "";
    int s3 = 0, idcliente = 0;
    String ti = "", maile = "", contramail = "";
    String ncliente = "", email = "";
    int tanque = 0;
    int turno = 0;
    String cadena;
    File axml;
    String cadenaC = "";
    int idcl = 0, idusuario = 0;
    String cadena1 = "";
    InputStream PKCS12_RESOURCE;
    String PKCS12_PASSWORD;
    Conecciones he;
    private static final int FSL = 127;
    String hora;
    String ip;
    String e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16;
    String p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16;
    String m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16;
    String v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16;
    String ppu1, ppu2, ppu3, ppu4, ppu5, ppu6, ppu7, ppu8, ppu9, ppu10, ppu11, ppu12, ppu13, ppu14, ppu15, ppu16;
    public byte[] comandoturno = new byte[3];
    private byte[] respuestaturno = new byte[2000];
    private int bytesReadturno;
    Double montos1[];
    Double volumen1[];
    Double montos2[];
    Double volumen2[];
    int nmanguera;
    String fecha;
    public conexion_sis_contable con = new conexion_sis_contable("useradv ", "adv111");
    org.apache.log4j.Logger logg = org.apache.log4j.Logger.getLogger(servidor.class);

    public servidor() throws java.rmi.RemoteException {
        super();

        PropertyConfigurator.configure("log4j.properties");

        servidores();
        he1 = new Conecciones("adv", "advgp2014");
        try {

            data = he1.ConectarMysql();

        } catch (SQLException ex) {
            logg.log(Priority.ERROR, ex);
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setdatos1(String estado, String producto, String monto, String volumen, String ppu) {

        e1 = estado;
        p1 = producto;
        m1 = monto;
        v1 = volumen;
        ppu1 = ppu;
    }

    public void setdatos2(String estado, String producto, String monto, String volumen, String ppu) {

        e2 = estado;
        p2 = producto;
        m2 = monto;
        v2 = volumen;
        ppu2 = ppu;
    }

    public void setdatos3(String estado, String producto, String monto, String volumen, String ppu) {

        e3 = estado;
        p3 = producto;
        m3 = monto;
        v3 = volumen;
        ppu3 = ppu;
    }

    public void setdatos4(String estado, String producto, String monto, String volumen, String ppu) {

        e4 = estado;
        p4 = producto;
        m4 = monto;
        v4 = volumen;
        ppu4 = ppu;
    }

    public void setdatos5(String estado, String producto, String monto, String volumen, String ppu) {

        e5 = estado;
        p5 = producto;
        m5 = monto;
        v5 = volumen;
        ppu5 = ppu;
    }

    public void setdatos6(String estado, String producto, String monto, String volumen, String ppu) {

        e6 = estado;
        p6 = producto;
        m6 = monto;
        v6 = volumen;
        ppu6 = ppu;
    }

    public void setdatos7(String estado, String producto, String monto, String volumen, String ppu) {

        e7 = estado;
        p7 = producto;
        m7 = monto;
        v7 = volumen;
        ppu7 = ppu;
    }

    public void setdatos8(String estado, String producto, String monto, String volumen, String ppu) {

        e8 = estado;
        p8 = producto;
        m8 = monto;
        v8 = volumen;
        ppu8 = ppu;
    }

    public void setdatos9(String estado, String producto, String monto, String volumen, String ppu) {

        e9 = estado;
        p9 = producto;
        m9 = monto;
        v9 = volumen;
        ppu9 = ppu;
    }

    public void setdatos10(String estado, String producto, String monto, String volumen, String ppu) {

        e10 = estado;
        p10 = producto;
        m10 = monto;
        v10 = volumen;
        ppu10 = ppu;
    }

    public void setdatos11(String estado, String producto, String monto, String volumen, String ppu) {

        e11 = estado;
        p11 = producto;
        m11 = monto;
        v11 = volumen;
        ppu11 = ppu;
    }

    public void setdatos12(String estado, String producto, String monto, String volumen, String ppu) {

        e12 = estado;
        p12 = producto;
        m12 = monto;
        v12 = volumen;
        ppu12 = ppu;
    }

    public void setdatos13(String estado, String producto, String monto, String volumen, String ppu) {

        e13 = estado;
        p13 = producto;
        m13 = monto;
        v13 = volumen;
        ppu13 = ppu;
    }

    public void setdatos14(String estado, String producto, String monto, String volumen, String ppu) {

        e14 = estado;
        p14 = producto;
        m14 = monto;
        v14 = volumen;
        ppu14 = ppu;
    }

    public void setdatos15(String estado, String producto, String monto, String volumen, String ppu) {

        e15 = estado;
        p15 = producto;
        m15 = monto;
        v15 = volumen;
        ppu15 = ppu;
    }

    public void setdatos16(String estado, String producto, String monto, String volumen, String ppu) {

        e16 = estado;
        p16 = producto;
        m16 = monto;
        v16 = volumen;
        ppu16 = ppu;
    }

    public String estadosurtidor(String a) {

        if (a.equalsIgnoreCase("Surtidor 1")) {

            return (e1);

        }
        if (a.equalsIgnoreCase("Surtidor 2")) {

            return (e2);

        }
        if (a.equalsIgnoreCase("Surtidor 3")) {

            return (e3);

        }
        if (a.equalsIgnoreCase("Surtidor 4")) {

            return (e4);

        }

        if (a.equalsIgnoreCase("Surtidor 5")) {

            return (e5);

        }

        if (a.equalsIgnoreCase("Surtidor 6")) {

            return (e6);

        }

        if (a.equalsIgnoreCase("Surtidor 7")) {

            return (e7);

        }

        if (a.equalsIgnoreCase("Surtidor 8")) {

            return (e8);

        }
        if (a.equalsIgnoreCase("Surtidor 9")) {

            return (e9);

        }
        if (a.equalsIgnoreCase("Surtidor 10")) {

            return (e10);

        }

        if (a.equalsIgnoreCase("Surtidor 11")) {

            return (e11);

        }
        if (a.equalsIgnoreCase("Surtidor 12")) {

            return (e12);

        }
        if (a.equalsIgnoreCase("Surtidor 13")) {

            return (e13);

        }
        if (a.equalsIgnoreCase("Surtidor 14")) {

            return (e14);

        }

        if (a.equalsIgnoreCase("Surtidor 15")) {

            return (e15);

        }

        if (a.equalsIgnoreCase("Surtidor 16")) {

            return (e16);

        }

        return null;
    }

    public String productosurtidor(String a) {

        if (a.equalsIgnoreCase("Surtidor 1")) {

            return (p1);

        }
        if (a.equalsIgnoreCase("Surtidor 2")) {

            return (p2);

        }
        if (a.equalsIgnoreCase("Surtidor 3")) {

            return (p3);

        }
        if (a.equalsIgnoreCase("Surtidor 4")) {

            return (p4);

        }

        if (a.equalsIgnoreCase("Surtidor 5")) {

            return (p5);

        }

        if (a.equalsIgnoreCase("Surtidor 6")) {

            return (p6);

        }
        if (a.equalsIgnoreCase("Surtidor 7")) {

            return (p7);

        }

        if (a.equalsIgnoreCase("Surtidor 8")) {

            return (p8);

        }
        if (a.equalsIgnoreCase("Surtidor 9")) {

            return (p9);

        }
        if (a.equalsIgnoreCase("Surtidor 10")) {

            return (p10);

        }

        if (a.equalsIgnoreCase("Surtidor 11")) {

            return (p11);

        }
        if (a.equalsIgnoreCase("Surtidor 12")) {

            return (p12);

        }
        if (a.equalsIgnoreCase("Surtidor 13")) {

            return (p13);

        }
        if (a.equalsIgnoreCase("Surtidor 14")) {

            return (p14);

        }

        if (a.equalsIgnoreCase("Surtidor 15")) {

            return (p15);

        }

        if (a.equalsIgnoreCase("Surtidor 16")) {

            return (p16);

        }

        return null;
    }

    public String montosurtidor(String a) {

        if (a.equalsIgnoreCase("Surtidor 1")) {

            return (m1);

        }
        if (a.equalsIgnoreCase("Surtidor 2")) {

            return (m2);

        }
        if (a.equalsIgnoreCase("Surtidor 3")) {

            return (m3);

        }
        if (a.equalsIgnoreCase("Surtidor 4")) {

            return (m4);

        }

        if (a.equalsIgnoreCase("Surtidor 5")) {

            return (m5);

        }

        if (a.equalsIgnoreCase("Surtidor 6")) {

            return (m6);

        }
        if (a.equalsIgnoreCase("Surtidor 7")) {

            return (m7);

        }

        if (a.equalsIgnoreCase("Surtidor 8")) {

            return (m8);

        }
        if (a.equalsIgnoreCase("Surtidor 9")) {

            return (m9);

        }
        if (a.equalsIgnoreCase("Surtidor 10")) {

            return (m10);

        }

        if (a.equalsIgnoreCase("Surtidor 11")) {

            return (m11);

        }
        if (a.equalsIgnoreCase("Surtidor 12")) {

            return (m12);

        }
        if (a.equalsIgnoreCase("Surtidor 13")) {

            return (m13);

        }
        if (a.equalsIgnoreCase("Surtidor 14")) {

            return (m14);

        }

        if (a.equalsIgnoreCase("Surtidor 15")) {

            return (m15);

        }

        if (a.equalsIgnoreCase("Surtidor 16")) {

            return (m16);

        }

        return null;
    }

    public String volumensurtidor(String a) {

        if (a.equalsIgnoreCase("Surtidor 1")) {

            return (v1);

        }
        if (a.equalsIgnoreCase("Surtidor 2")) {

            return (v2);

        }
        if (a.equalsIgnoreCase("Surtidor 3")) {

            return (v3);

        }
        if (a.equalsIgnoreCase("Surtidor 4")) {

            return (v4);

        }

        if (a.equalsIgnoreCase("Surtidor 5")) {

            return (v5);

        }

        if (a.equalsIgnoreCase("Surtidor 6")) {

            return (v6);

        }
        if (a.equalsIgnoreCase("Surtidor 7")) {

            return (v7);

        }

        if (a.equalsIgnoreCase("Surtidor 8")) {

            return (v8);

        }
        if (a.equalsIgnoreCase("Surtidor 9")) {

            return (v9);

        }
        if (a.equalsIgnoreCase("Surtidor 10")) {

            return (v10);

        }

        if (a.equalsIgnoreCase("Surtidor 11")) {

            return (v11);

        }
        if (a.equalsIgnoreCase("Surtidor 12")) {

            return (v12);

        }
        if (a.equalsIgnoreCase("Surtidor 13")) {

            return (v13);

        }
        if (a.equalsIgnoreCase("Surtidor 14")) {

            return (v14);

        }

        if (a.equalsIgnoreCase("Surtidor 15")) {

            return (v15);

        }

        if (a.equalsIgnoreCase("Surtidor 16")) {

            return (v16);

        }

        return null;
    }

    public String ppusurtidor(String a) {

        if (a.equalsIgnoreCase("Surtidor 1")) {

            return (ppu1);

        }
        if (a.equalsIgnoreCase("Surtidor 2")) {

            return (ppu2);

        }
        if (a.equalsIgnoreCase("Surtidor 3")) {

            return (ppu3);

        }
        if (a.equalsIgnoreCase("Surtidor 4")) {

            return (ppu4);

        }

        if (a.equalsIgnoreCase("Surtidor 5")) {

            return (ppu5);

        }

        if (a.equalsIgnoreCase("Surtidor 6")) {

            return (ppu6);

        }
        if (a.equalsIgnoreCase("Surtidor 7")) {

            return (ppu7);

        }

        if (a.equalsIgnoreCase("Surtidor 8")) {

            return (ppu8);

        }
        if (a.equalsIgnoreCase("Surtidor 9")) {

            return (ppu9);

        }
        if (a.equalsIgnoreCase("Surtidor 10")) {

            return (ppu10);

        }

        if (a.equalsIgnoreCase("Surtidor 11")) {

            return (ppu11);

        }
        if (a.equalsIgnoreCase("Surtidor 12")) {

            return (ppu12);

        }
        if (a.equalsIgnoreCase("Surtidor 13")) {

            return (ppu13);

        }
        if (a.equalsIgnoreCase("Surtidor 14")) {

            return (ppu14);

        }

        if (a.equalsIgnoreCase("Surtidor 15")) {

            return (ppu15);

        }

        if (a.equalsIgnoreCase("Surtidor 16")) {

            return (ppu16);

        }

        return null;
    }

    public static void main(String args[]) {
        try {
            metodosServidor calculator;
            LocateRegistry.createRegistry(1099);
            calculator = new servidor();
            Naming.bind("servidor", calculator);
            System.out.println("El servidor esta listo\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int Turno(int turno) throws RemoteException {
        try {

            conexion_facturacion n = new conexion_facturacion("root", "manager");
            n.conectar();
            System.out.println(" turno");

            RandomAccessFile pipeturno = new RandomAccessFile("\\\\" + ip + "\\pipe\\CEM44POSPIPE", "rw");
            comandoturno[0] = (byte) turno;

            comandoturno[1] = (byte) FSL;

            int sum = 0;
            int lrc = 0;

            for (int i = 0; i < comandoturno.length; i++) {
                sum += (int) (comandoturno[i]);
            }

            lrc = sum + 1;
            lrc = lrc & 0xff;

            // comandoturno[2] = (byte) lrc;
            pipeturno.write(comandoturno, 0, comandoturno.length);
            bytesReadturno = pipeturno.read(respuestaturno, 0, respuestaturno.length);

            String resp = "", monto1, volu1, montosur1 = null;

            int cont = 4;
            int r = 0;

            for (int i = 0; i < bytesReadturno; i++) {

                resp += (char) (respuestaturno[i]);
                r += (int) (respuestaturno[i]);

            }

            System.out.println(r);

            Statement st_m = n.coneccion.createStatement();
            ResultSet ridm = st_m.executeQuery("SELECT count(nmanguera) FROM adv_facturacion.configuracion where nmanguera <> 0;");
            while (ridm.next()) {

                nmanguera = ridm.getInt(1);
            }

            System.out.println(nmanguera);

            montos1 = new Double[nmanguera + 1];
            volumen1 = new Double[nmanguera + 1];

            montos2 = new Double[nmanguera + 1];
            volumen2 = new Double[nmanguera + 1];

            if (r == -1) {

                System.out.println("entro");
                System.out.println("nmanguera" + nmanguera);

                for (int p = 1; p <= nmanguera; p++) {

                    montos1[p] = 0.00;

                }

                for (int a = 1; a <= nmanguera; a++) {

                    volumen1[a] = 0.00;

                }

            } else {

                System.out.println(resp);
                String respuesta = "";

                StringTokenizer tokens = new StringTokenizer(resp, "~");

                int nDatos = tokens.countTokens();
                String[] datos = new String[nDatos];
                int i = 0;
                while (tokens.hasMoreTokens()) {
                    String str = tokens.nextToken();
                    datos[i] = str;

                    i++;
                }

                int pv = 24;
                for (int p = 1; p <= nmanguera; p++) {

                    montos1[p] = Double.parseDouble(datos[pv]);
                    pv = pv + 6;

                }

                int v = 25;
                for (int a = 1; a <= nmanguera; a++) {

                    volumen1[a] = Double.parseDouble(datos[v]);
                    v = v + 6;
                }

            }

        } catch (FileNotFoundException ex) {

            //Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            Turno(turno);
        } catch (ArrayIndexOutOfBoundsException | IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;

    }

    @Override
    public int cerrarTurno(int turno, String usuario) throws RemoteException {
        int validar = 0;
        int surtidor, idu = 0;
        int idu1 = 0, id1 = 0;
        int str1 = 0;

        try {

            n = data.getConnection("adv", "advgp2014");

            data = he1.ConectarMysql();



            // data = he1.ConectarMysql();
            System.out.println("entro a turnos entrada ");
            fecha();


            FileWriter file = null;
            Font fuente;
            String impresora = "";
            String rz = "", nombre = "";
            String np = "";
            String d = "";
            String r = "";
            String s1 = "";
            String s2 = "";
            String fi = "", hi = "";
            boolean ingresar = true;
            int m = 0, me = 0;
            int id = 0;
            int s3 = 0;

            int estado;
            int ta, ids;
            Date hs;
            String h = "";

            Statement st_d1 = n.createStatement();
            ResultSet rid1 = st_d1.executeQuery("SELECT * from datos_gasolinera");

            while (rid1.next()) {

                rz = rid1.getString(2);
                np = rid1.getString(5);
                d = rid1.getString(4);
                r = rid1.getString(3);
                s1 = rid1.getString(6);
                s2 = rid1.getString(7);

            }

            Statement st_u = n.createStatement();
            ResultSet riu = st_u.executeQuery("SELECT idusuarios,nombre from adv_facturacion.usuarios where usuario='" + usuario + "';");
            while (riu.next()) {

                idu1 = riu.getInt(1);
                nombre = riu.getString(2);

            }

            file = new FileWriter("ingreso" + idu1 + ".txt");
            BufferedWriter buffer = new BufferedWriter(file);
            PrintWriter ps = new PrintWriter(buffer);

            Turno(1);

            hora();

            setFormato(2, ps);

            ps.println("Ticket de Salida   ");
            ps.println("                 ");
            Dibuja_Linea(ps);
            ps.println("Ingreso de turno usuario: " + nombre);
            ps.println("Hora de Ingreso " + hora);
            ps.println("Fecha " + fecha);




            Statement st_m2 = n.createStatement();
            ResultSet rim2 = st_m2.executeQuery("SELECT  fecha_final,hora_final FROM adv_facturacion.cierre_diario  order by idcierre_diario desc limit 1");

            if (rim2.first()) {

                fi = rim2.getString(1);
                hi = rim2.getString(2);


            } else {

                fi = fecha;
                hi = hora;


            }


            Statement st_m = n.createStatement();
            ResultSet rim = st_m.executeQuery("SELECT nmanguera from adv_facturacion.configuracion where nmanguera <> 0 order by nmanguera ");
            while (rim.next()) {



                str1 = Integer.valueOf(rim.getInt(1));







                System.out.println(str1);


                Statement st_m1 = n.createStatement();
                ResultSet rim1 = st_m1.executeQuery("SELECT nmanguera_cem FROM adv_facturacion.configuracion where nmanguera='" + str1 + "'");

                while (rim1.next()) {

                    m = rim1.getInt(1);



                    PreparedStatement islae = n.prepareStatement("UPDATE `adv_facturacion`.`configuracion` SET `usuarios_idusuarios`='" + idu1 + "' WHERE nmanguera='" + str1 + "'");

                    islae.executeUpdate();

                    ps.println("M:" + str1 + "|  Monto:" + 0.00 + "|    Vol:" + 0.00);

                    Statement st1 = n.createStatement();

                    String consulta;







                    consulta = "INSERT INTO `adv_facturacion`.`cierre_diario` (`monto`,`volumen`,`configuracion_nmanguera`,`fecha_inicial`,`hora_inicio`,fecha_final,hora_final) VALUES ('" + montos1[m] + "','" + volumen1[m] + "','" + str1 + "','" + fi + "','" + hi + "','" + fecha + "','" + hora + "');";



                    System.out.println(consulta);

                    st1.executeUpdate(consulta);

                    me = str1;

                }






            }




            ps.println("                                 ");
            ps.println("                                 ");
            correr(8, ps);
            cortar(ps);
            ps.close();


            if (validar == 0) {

                Statement st_im = n.createStatement();
                ResultSet ridim = st_im.executeQuery("SELECT impresora FROM adv_facturacion.configuracion where nmanguera=" + me);

                while (ridim.next()) {

                    impresora = ridim.getString(1);

                }

                System.out.println(impresora);



                FileInputStream inputStream = new FileInputStream("ingreso" + idu1 + ".txt");

                //Formato de Documento
                DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
                //Lectura de Documento
                Doc document = new SimpleDoc(inputStream, docFormat, null);

                //Inclusion del nombre de impresora y sus atributos
                AttributeSet attributeSet = new HashAttributeSet();
                attributeSet.add(new PrinterName(impresora, null));
                attributeSet = new HashAttributeSet();
                //Soporte de color o no
                attributeSet.add(ColorSupported.NOT_SUPPORTED);

                //Busqueda de la impresora por el nombre asignado en attributeSet
                PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

                for (int i = 0; i < services.length; i++) {

                    if (services[i].getName().equals(impresora)) {

                        DocPrintJob printJob = services[i].createPrintJob();
                        //Envio a la impresora
                        printJob.print(document, new HashPrintRequestAttributeSet());

                        System.out.println("Imprimiendo en : " + services[i].getName());

                        i = services.length;

                    }

                }

                buffer.close();
                ps.close();
                inputStream.close();
                validar = 1;

            }




        } catch (SQLException ex) {
            System.out.println("entro para arreglar");

            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            validar = 0;
        } catch (IOException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            validar = 0;
        } catch (PrintException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            validar = 2;
        } catch (Exception ex) {

            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            validar = 0;

        }

        return 1;

    }

    private void Dibuja_Linea(PrintWriter ps) {
        try {
            ps.println("---------------------------------");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

//para cortar el papel de mi ticketera
    private void cortar(PrintWriter ps) {

        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'i'};
            ps.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void correr(int fin, PrintWriter pw) {
        try {
            int i = 0;
            for (i = 1; i <= fin; i++) {
                pw.println("");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void setFormato(double formato, PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, '!', (char) formato};
            pw.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }

    }

    public String fecha() {
        try {

            n = data.getConnection("adv", "advgp2014");

            String nombrec = "select CURDATE();";

            Statement st_idfac = n.createStatement();
            ResultSet idfac = st_idfac.executeQuery(nombrec);

            while (idfac.next()) {

                fecha = idfac.getString(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fecha;
    }

    public String hora() {



        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

        hora = formateador.format(ahora);
       
        System.out.println(hora);



        return hora;
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

                cont++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int entrada_turnos(String manguera, String usu) {
        int validar = 0;
        int surtidor, idu = 0;
        int idu1 = 0, id1 = 0;
        int str1 = 0;

        try {

            n = data.getConnection("adv", "advgp2014");

            data = he1.ConectarMysql();



            // data = he1.ConectarMysql();
            System.out.println("entro a turnos entrada ");
            fecha();
            hora();

            FileWriter file = null;
            Font fuente;
            String impresora = "";
            String rz = "", nombre = "";
            String np = "";
            String d = "";
            String r = "";
            String s1 = "";
            String s2 = "";

            boolean ingresar = true;
            int m = 0, me = 0;
            int id = 0;
            int s3 = 0;

            int estado;
            int ta, ids;
            Date hs;
            String h = "";

            Statement st_d1 = n.createStatement();
            ResultSet rid1 = st_d1.executeQuery("SELECT * from datos_gasolinera");

            while (rid1.next()) {

                rz = rid1.getString(2);
                np = rid1.getString(5);
                d = rid1.getString(4);
                r = rid1.getString(3);
                s1 = rid1.getString(6);
                s2 = rid1.getString(7);

            }

            Statement st_u = n.createStatement();
            ResultSet riu = st_u.executeQuery("SELECT idusuarios,nombre from adv_facturacion.usuarios where usuario='" + usu + "';");
            while (riu.next()) {

                idu1 = riu.getInt(1);
                nombre = riu.getString(2);

            }

            file = new FileWriter("ingreso" + idu1 + ".txt");
            BufferedWriter buffer = new BufferedWriter(file);
            PrintWriter ps = new PrintWriter(buffer);

            Turno(2);

            setFormato(2, ps);

            ps.println("Ticket de Ingreso    ");
            ps.println("                 ");
            Dibuja_Linea(ps);
            ps.println("Ingreso de turno usuario: " + nombre);
            ps.println("Hora de Ingreso " + hora);
            ps.println("Fecha " + fecha);

            String[] mangueras = manguera.split(",");

            for (int i = 0; i < mangueras.length; i++) {

                str1 = Integer.valueOf(mangueras[i]);

                System.out.println("SELECT usuario,islas.mangueras FROM adv_facturacion.configuracion,adv_facturacion.islas,usuarios where idusuarios=islas.usuarios_idusuarios and configuracion.usuarios_idusuarios=islas.usuarios_idusuarios and nmanguera='" + str1 + "' and configuracion.usuarios_idusuarios <> 0 ");

                Statement st_vm = n.createStatement();
                ResultSet vm = st_vm.executeQuery("SELECT usuario,islas.mangueras FROM adv_facturacion.configuracion,adv_facturacion.islas,usuarios where idusuarios=islas.usuarios_idusuarios and configuracion.usuarios_idusuarios=islas.usuarios_idusuarios and nmanguera='" + str1 + "' and configuracion.usuarios_idusuarios <> 0 ");

                if (vm.first()) {




                    System.out.println(vm.getString(2) + "  " + vm.getString(1));



                    int idu2 = salida_turnos(vm.getString(2), vm.getString(1));



                    Statement st2 = n.createStatement();
                    st2.executeUpdate("DELETE FROM surtidores WHERE usuarios_idusuarios=" + idu2);

                    System.out.println(idu1);

                    Statement st3 = n.createStatement();
                    st3.executeUpdate("UPDATE `adv_facturacion`.`islas` SET `usuarios_idusuarios`='0' WHERE usuarios_idusuarios=" + idu2);


                    Statement st4 = n.createStatement();
                    st4.executeUpdate("UPDATE `adv_facturacion`.`configuracion` SET `usuarios_idusuarios`='0' WHERE `usuarios_idusuarios`=" + idu2);



                    validar = 3;
                    break;





                } else {





                    System.out.println(str1);


                    Statement st_m = n.createStatement();
                    ResultSet rim = st_m.executeQuery("SELECT nmanguera_cem FROM adv_facturacion.configuracion where nmanguera='" + str1 + "'");

                    while (rim.next()) {

                        m = rim.getInt(1);



                        PreparedStatement islae = n.prepareStatement("UPDATE `adv_facturacion`.`configuracion` SET `usuarios_idusuarios`='" + idu1 + "' WHERE nmanguera='" + str1 + "'");

                        islae.executeUpdate();

                        ps.println("M:" + str1 + "|  Monto:" + 0.00 + "|    Vol:" + 0.00);

                        Statement st1 = n.createStatement();

                        String consulta;



                        consulta = "INSERT INTO `adv_facturacion`.`surtidores` (`usuarios_idusuarios`,`hora_entrada`,`monto_entrada_manguera`,`nmanguera`,`volumen_entrada_manguera`,`fecha_entrada`) VALUES ('" + idu1 + "','" + hora + "','" + montos1[m] + "','" + str1 + "','" + volumen1[m] + "','" + fecha + "');";



                        System.out.println(consulta);

                        st1.executeUpdate(consulta);

                        me = str1;

                    }






                }


            }

            ps.println("                                 ");
            ps.println("                                 ");
            correr(8, ps);
            cortar(ps);
            ps.close();


            if (validar == 0) {

                Statement st_im = n.createStatement();
                ResultSet ridim = st_im.executeQuery("SELECT impresora FROM adv_facturacion.configuracion where nmanguera=" + me);

                while (ridim.next()) {

                    impresora = ridim.getString(1);

                }

                System.out.println(impresora);



                FileInputStream inputStream = new FileInputStream("ingreso" + idu1 + ".txt");

                //Formato de Documento
                DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
                //Lectura de Documento
                Doc document = new SimpleDoc(inputStream, docFormat, null);

                //Inclusion del nombre de impresora y sus atributos
                AttributeSet attributeSet = new HashAttributeSet();
                attributeSet.add(new PrinterName(impresora, null));
                attributeSet = new HashAttributeSet();
                //Soporte de color o no
                attributeSet.add(ColorSupported.NOT_SUPPORTED);

                //Busqueda de la impresora por el nombre asignado en attributeSet
                PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

                for (int i = 0; i < services.length; i++) {

                    if (services[i].getName().equals(impresora)) {

                        DocPrintJob printJob = services[i].createPrintJob();
                        //Envio a la impresora
                        printJob.print(document, new HashPrintRequestAttributeSet());

                        System.out.println("Imprimiendo en : " + services[i].getName());

                        i = services.length;

                    }

                }

                buffer.close();
                ps.close();
                inputStream.close();
                validar = 1;

            }




        } catch (SQLException ex) {
            System.out.println("entro para arreglar");

            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            validar = 0;
        } catch (IOException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            validar = 0;
        } catch (PrintException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            validar = 2;
        } catch (Exception ex) {

            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            validar = 0;

        }

        return validar;

    }

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    @Override
    public int salida_turnos(String mangueras, String usuarios) throws RemoteException {
        int validar = 0;

        try {

            n = data.getConnection("adv", "advgp2014");

            FileWriter file1 = null;
            String hori = "", fechai = "";
            fecha();
            hora();
            Font fuente;

            String impresora = "";
            String rz = "";
            String np = "";
            String d = "";
            String r = "";
            String s1 = "";
            String s2 = "";
            int surtidor, idu = 0;
            boolean ingresar = true;
            int m = 0, me = 0;
            int id = 0;
            int producto;
            double vdiesel = 0.00, vextra = 0.00, vsuperg = 0.00;
            double diesel = 0.00, extra = 0.00, superg = 0.00;

            int s3 = 0;

            int cierre = 0;

            String codigos, f;
            int idu1 = 0, id1 = 0;
            int estado;
            int ta, ids;
            Date hs;
            String h;
            Double totalp = 0.00, totalvo = 0.00;

            Statement st_d1 = n.createStatement();
            ResultSet rid1 = st_d1.executeQuery("SELECT * from datos_gasolinera");

            while (rid1.next()) {

                rz = rid1.getString(2);
                np = rid1.getString(5);
                d = rid1.getString(4);
                r = rid1.getString(3);
                s1 = rid1.getString(6);
                s2 = rid1.getString(7);
                cierre = rid1.getInt(14);

            }
            Statement st_d2 = n.createStatement();
            ResultSet rid2 = st_d2.executeQuery("SELECT count(*) FROM adv_facturacion.reportes_usuario;");

            while (rid2.next()) {
                s3 = rid2.getInt(1) + 1;
            }
            int idre;
            idre = s3;

            Statement st_u = n.createStatement();
            ResultSet riu = st_u.executeQuery("SELECT idusuarios from adv_facturacion.usuarios where usuario='" + usuarios + "';");
            while (riu.next()) {

                idu1 = riu.getInt(1);

            }

            file1 = new FileWriter("salida" + idu1 + ".txt");
            BufferedWriter buffer1 = new BufferedWriter(file1);
            PrintWriter ps1 = new PrintWriter(buffer1);

            Turno(2);

            Statement st_n = n.createStatement();
            ResultSet rin = st_n.executeQuery("SELECT nombre FROM adv_facturacion.usuarios where idusuarios=" + idu1 + ";");
            String nombre = "";

            while (rin.next()) {

                nombre = rin.getString(1);
            }

            Statement st_s = n.createStatement();
            ResultSet ris = st_s.executeQuery("SELECT count(*) FROM adv_facturacion.surtidores;");

            while (ris.next()) {

                id1 = ris.getInt(1) + 1;
            }

            setFormato(2, ps1);

            ps1.println("Ticket de Salida    ");
            ps1.println("                 ");
            ps1.println("Numero:" + id1);
            Dibuja_Linea(ps1);
            ps1.println("Salida de turno usuario:");
            ps1.println(nombre);
            ps1.println("Hora de Salida " + hora);
            ps1.println();
            ps1.println("Fecha " + fecha);

            System.out.println("SELECT nmanguera FROM adv_facturacion.surtidores,adv_facturacion.usuarios where usuarios_idusuarios=idusuarios and usuario='" + usuarios + "'");

            Statement st_s1 = n.createStatement();
            ResultSet ris1 = st_s1.executeQuery("SELECT nmanguera FROM adv_facturacion.surtidores,adv_facturacion.usuarios where usuarios_idusuarios=idusuarios and usuario='" + usuarios + "'");

            while (ris1.next()) {

                Statement st_m = n.createStatement();
                ResultSet rim = st_m.executeQuery("SELECT nmanguera_cem FROM adv_facturacion.configuracion where nmanguera='" + ris1.getInt(1) + "'");

                while (rim.next()) {
                    m = rim.getInt(1);

                    Statement st_me = n.createStatement();
                    ResultSet rime = st_me.executeQuery("SELECT nmanguera,producto_idproducto FROM adv_facturacion.configuracion where nmanguera_cem=" + m + ";");

                    while (rime.next()) {

                        me = rime.getInt(1);

                        producto = rime.getInt(2);

                        Statement st = n.createStatement();
                        ResultSet ri = st.executeQuery("SELECT monto_entrada_manguera , volumen_entrada_manguera FROM adv_facturacion.surtidores where nmanguera=" + me + ";");

                        while (ri.next()) {

                            Double totalv;
                            Double totalg;

                            totalv = montos1[m] - ri.getDouble(1);
                            totalg = volumen1[m] - ri.getDouble(2);

                            if (producto == 1) {

                                extra = extra + totalv;
                                vextra = vextra + totalg;

                            }
                            if (producto == 2) {

                                superg = superg + totalv;
                                vsuperg = vsuperg + totalg;

                            }
                            if (producto == 3) {

                                diesel = diesel + totalv;
                                vdiesel = vdiesel + totalg;

                            }

                            if (cierre == 1) {

                                DecimalFormat df = new DecimalFormat("#.##");

                                totalv = Double.valueOf(df.format(totalv).replace(",", "."));
                                totalg = Double.valueOf(df.format(totalg).replace(",", "."));

                                ps1.println("M:" + me + "| Monto:" + totalv);

                                ps1.println("M:" + me + "| Vol:" + totalg);
                                ps1.println("-------------------------------");

                            } else if (cierre == 2) {
                                ps1.println("M:" + me + "| Monto:");

                                ps1.println("M:" + me + "| Vol:");
                                ps1.println("-------------------------------");

                            }

                            System.out.println(idu1);


                            Statement sth = n.createStatement();
                            ResultSet rih = sth.executeQuery("SELECT hora_entrada,fecha_entrada FROM adv_facturacion.surtidores where usuarios_idusuarios=" + idu1 + " group by hora_entrada;");

                            while (rih.next()) {

                                hori = rih.getString(1);
                                fechai = rih.getString(2);
                            }

                            System.out.println(hori);
                            totalp = totalp + totalv;
                            totalvo = totalvo + totalg;

                            Statement str = n.createStatement();
                            String cons = "INSERT INTO `adv_facturacion`.`reportes_usuario` (`manguera`, `ventas`, `fecha_final`, `horai`, `horas`, `usuarios_idusuarios`,`volumen`,`lec_inicial`,`lec_final`,`fecha_inicial`) VALUES ('" + me + "', '" + totalv + "', '" + fecha + "', '" + hori + "', '" + hora + "', '" + idu1 + "','" + totalg + "', '" + ri.getDouble(2) + "','" + volumen1[m] + "','" + fechai + "');";
                            str.executeUpdate(cons);
                            idre++;
                        }

                    }

                    id++;
                }

            }

            if (cierre == 1) {

                ps1.println("-------------------------------");
                ps1.println("Monto Total Extra: " + extra);
                ps1.println("Volumen Total Extra: " + vextra);
                ps1.println("-------------------------------");
                ps1.println("Monto Total Super: " + superg);
                ps1.println("Volumen Total Super: " + vsuperg);
                ps1.println("-------------------------------");
                ps1.println("Monto Total Diesel: " + diesel);
                ps1.println("Volumen Total Diesel: " + vdiesel);
                ps1.println("-------------------------------");
                ps1.println("Monto Total Turno: " + totalp);
                ps1.println("Volumen Total Turno : " + totalvo);
            } else if (cierre == 2) {

                ps1.println("Monto Total Turno: ");
                ps1.println("Volumen Total Turno : ");
            }

            String consulta = "";
            String consultaf = "";
            String hora1;

            hora1 = "(CONVERT(concat_ws(' ', fecha, hora),DATETIME) >= CONVERT('" + fechai + " " + hori + "',DATETIME) and CONVERT(concat_ws(' ', fecha, hora),DATETIME) <= CONVERT('" + fecha + " " + hora + "',DATETIME)) ";

            consulta = "SELECT count(*),sum(total),sum(cantidad) ,nombre FROM adv_facturacion.pagare,configuracion,producto where configuracion_nmanguera=nmanguera and producto_idproducto=idproducto and  pagare.usuarios_idusuarios=" + idu1 + " and " + hora1 + " group by idproducto;";
            consultaf = "SELECT sum(factura_detalle.total),sum(cantidad) ,nombre FROM adv_facturacion.factura,factura_detalle,configuracion,producto where idfactura=factura_idfactura and configuracion_nmanguera=nmanguera and factura_detalle.producto_idproducto=idproducto and  factura.usuarios_idusuarios=" + idu1 + " and " + hora1 + " group by idproducto;";

            Statement spag = n.createStatement();
            ResultSet rpag = spag.executeQuery(consulta);
            int npa = 0;

            ps1.println("-------------------------------");
            ps1.println("Detalle de Pagares");
            while (rpag.next()) {

                npa = npa + rpag.getInt(1);

                ps1.println("Monto Total " + rpag.getString(4) + " " + rpag.getDouble(2));
                ps1.println("Volumen Total " + rpag.getString(4) + " " + rpag.getDouble(3));
                ps1.println("-------------------------------");

            }

            ps1.println("Total de Pagares Emitidos :" + npa);

            Statement sfacturas = n.createStatement();
            ResultSet rfacturas = sfacturas.executeQuery(consultaf);

            ps1.println("-------------------------------");
            ps1.println("Detalle de Facturas");
            while (rfacturas.next()) {

                ps1.println("Monto Total " + rfacturas.getString(3) + " " + rfacturas.getDouble(1));
                ps1.println("Cantidad Total " + rfacturas.getString(3) + " " + rfacturas.getDouble(2));
                ps1.println("-------------------------------");

            }


            ps1.println("                                  ");
            ps1.println("                                   ");
            ps1.println("Cantidad de Dinero Entregado:      ");
            ps1.println("Cantidad de Dinero Faltante:       ");
            correr(8, ps1);
            cortar(ps1);
            ps1.close();

            Statement st_im = n.createStatement();
            ResultSet ridim = st_im.executeQuery("SELECT impresora FROM adv_facturacion.configuracion where nmanguera=" + me);

            while (ridim.next()) {

                impresora = ridim.getString(1);

            }

            System.out.println(impresora);



            



            validar = idu1;


            FileInputStream inputStream = new FileInputStream("salida" + idu1 + ".txt");

            //Formato de Documento
            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            //Lectura de Documento
            Doc document = new SimpleDoc(inputStream, docFormat, null);

            //Inclusion del nombre de impresora y sus atributos
            AttributeSet attributeSet = new HashAttributeSet();
            attributeSet.add(new PrinterName(impresora, null));
            attributeSet = new HashAttributeSet();
            //Soporte de color o no
            attributeSet.add(ColorSupported.NOT_SUPPORTED);

            //Busqueda de la impresora por el nombre asignado en attributeSet
            PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

            for (int i = 0; i < services.length; i++) {

                if (services[i].getName().equals(impresora)) {

                    DocPrintJob printJob = services[i].createPrintJob();
                    //Envio a la impresora
                    printJob.print(document, new HashPrintRequestAttributeSet());

                    System.out.println("Imprimiendo en : " + services[i].getName());

                    i = services.length;

                }

            }

            buffer1.close();
            ps1.close();
            inputStream.close();

            
            
            //Busqueda de la impresora por el nombre asignado en attributeSet


            

        } catch (IOException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrintException ex) {
            
            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {

            validar = 0;
            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);

        }

        return validar;

    }

    @Override
    public int salida_turnos_encurso(String mangueras, String usuarios) throws RemoteException {
        int validar = 0;

        try {

            n = data.getConnection("adv", "advgp2014");

            FileWriter file1 = null;
            String hori = "", fechai = "";
            fecha();
            hora();
            Font fuente;

            String impresora = "";
            String rz = "";
            String np = "";
            String d = "";
            String r = "";
            String s1 = "";
            String s2 = "";
            int surtidor, idu = 0;
            boolean ingresar = true;
            int m = 0, me = 0;
            int id = 0;
            int producto;
            double vdiesel = 0.00, vextra = 0.00, vsuperg = 0.00;
            double diesel = 0.00, extra = 0.00, superg = 0.00;

            int s3 = 0;

            int cierre = 0;

            String codigos, f;
            int idu1 = 0, id1 = 0;
            int estado;
            int ta, ids;
            Date hs;
            String h;
            Double totalp = 0.00, totalvo = 0.00;

            Statement st_d1 = n.createStatement();
            ResultSet rid1 = st_d1.executeQuery("SELECT * from datos_gasolinera");

            while (rid1.next()) {

                rz = rid1.getString(2);
                np = rid1.getString(5);
                d = rid1.getString(4);
                r = rid1.getString(3);
                s1 = rid1.getString(6);
                s2 = rid1.getString(7);
                cierre = rid1.getInt(14);

            }
            Statement st_d2 = n.createStatement();
            ResultSet rid2 = st_d2.executeQuery("SELECT count(*) FROM adv_facturacion.reportes_usuario;");

            while (rid2.next()) {
                s3 = rid2.getInt(1) + 1;
            }
            int idre;
            idre = s3;

            Statement st_u = n.createStatement();
            ResultSet riu = st_u.executeQuery("SELECT idusuarios from adv_facturacion.usuarios where usuario='" + usuarios + "';");
            while (riu.next()) {

                idu1 = riu.getInt(1);

            }

            file1 = new FileWriter("salida" + idu1 + ".txt");
            BufferedWriter buffer1 = new BufferedWriter(file1);
            PrintWriter ps1 = new PrintWriter(buffer1);

            Turno(2);

            Statement st_n = n.createStatement();
            ResultSet rin = st_n.executeQuery("SELECT nombre FROM adv_facturacion.usuarios where idusuarios=" + idu1 + ";");
            String nombre = "";

            while (rin.next()) {

                nombre = rin.getString(1);
            }

            Statement st_s = n.createStatement();
            ResultSet ris = st_s.executeQuery("SELECT count(*) FROM adv_facturacion.surtidores;");
            while (ris.next()) {
                id1 = ris.getInt(1) + 1;
            }

            setFormato(2, ps1);

            ps1.println("Ticket de Salida En Curso    ");
            ps1.println("Consulta de turno usuario:");
            ps1.println(nombre);
            ps1.println("Hora de Consulta " + hora);
            ps1.println("Fecha " + fecha);

            System.out.println("SELECT nmanguera FROM adv_facturacion.surtidores,adv_facturacion.usuarios where usuarios_idusuarios=idusuarios and usuario='" + usuarios + "'");

            Statement st_s1 = n.createStatement();
            ResultSet ris1 = st_s1.executeQuery("SELECT nmanguera FROM adv_facturacion.surtidores,adv_facturacion.usuarios where usuarios_idusuarios=idusuarios and usuario='" + usuarios + "'");

            while (ris1.next()) {

                Statement st_m = n.createStatement();
                ResultSet rim = st_m.executeQuery("SELECT nmanguera_cem FROM adv_facturacion.configuracion where nmanguera='" + ris1.getInt(1) + "'");

                while (rim.next()) {
                    m = rim.getInt(1);

                    Statement st_me = n.createStatement();
                    ResultSet rime = st_me.executeQuery("SELECT nmanguera,producto_idproducto FROM adv_facturacion.configuracion where nmanguera_cem=" + m + ";");

                    while (rime.next()) {

                        me = rime.getInt(1);

                        producto = rime.getInt(2);

                        Statement st = n.createStatement();
                        ResultSet ri = st.executeQuery("SELECT monto_entrada_manguera , volumen_entrada_manguera FROM adv_facturacion.surtidores where nmanguera=" + me + ";");

                        while (ri.next()) {

                            Double totalv;
                            Double totalg;

                            totalv = montos1[m] - ri.getDouble(1);
                            totalg = volumen1[m] - ri.getDouble(2);

                            if (producto == 1) {

                                extra = extra + totalv;
                                vextra = vextra + totalg;

                            }
                            if (producto == 2) {

                                superg = superg + totalv;
                                vsuperg = vsuperg + totalg;

                            }
                            if (producto == 3) {

                                diesel = diesel + totalv;
                                vdiesel = vdiesel + totalg;

                            }

                            if (cierre == 1) {

                                DecimalFormat df = new DecimalFormat("#.##");

                                totalv = Double.valueOf(df.format(totalv).replace(",", "."));
                                totalg = Double.valueOf(df.format(totalg).replace(",", "."));

                                // ps1.println("M:" + me + "| Monto:" + totalv);

                                //ps1.println("M:" + me + "| Vol:" + totalg);
                                //  ps1.println("-------------------------------");

                            } else if (cierre == 2) {
                                //ps1.println("M:" + me + "| Monto:");
                                //  ps1.println("M:" + me + "| Vol:");
                                //   ps1.println("-------------------------------");
                            }

                            System.out.println(idu1);


                            Statement sth = n.createStatement();
                            ResultSet rih = sth.executeQuery("SELECT hora_entrada,fecha_entrada FROM adv_facturacion.surtidores where usuarios_idusuarios=" + idu1 + " group by hora_entrada;");

                            while (rih.next()) {

                                hori = rih.getString(1);
                                fechai = rih.getString(2);
                            }

                            System.out.println(hori);
                            totalp = totalp + totalv;
                            totalvo = totalvo + totalg;


                            idre++;
                        }

                    }

                    id++;
                }

            }

            if (cierre == 1) {

                // ps1.println("-------------------------------");
                // ps1.println("Monto Total Extra: " + extra);
                //ps1.println("Volumen Total Extra: " + vextra);
                //ps1.println("-------------------------------");
                //ps1.println("Monto Total Super: " + superg);
                //ps1.println("Volumen Total Super: " + vsuperg);
                //ps1.println("-------------------------------");
                //ps1.println("Monto Total Diesel: " + diesel);
                //ps1.println("Volumen Total Diesel: " + vdiesel);
                //  ps1.println("-------------------------------");
                ps1.println("Monto Total Turno: " + totalp);
                ps1.println("Volumen Total Turno : " + totalvo);
            } else if (cierre == 2) {

                ps1.println("Monto Total Turno: ");
                ps1.println("Volumen Total Turno : ");
            }

            String consulta = "";
            String consultaf = "";
            String hora1;

            hora1 = "(CONVERT(concat_ws(' ', fecha, hora),DATETIME) >= CONVERT('" + fechai + " " + hori + "',DATETIME) and CONVERT(concat_ws(' ', fecha, hora),DATETIME) <= CONVERT('" + fecha + " " + hora + "',DATETIME)) ";

            consulta = "SELECT count(*),sum(total),sum(cantidad) ,nombre FROM adv_facturacion.pagare,configuracion,producto where configuracion_nmanguera=nmanguera and producto_idproducto=idproducto and  pagare.usuarios_idusuarios=" + idu1 + " and " + hora1 + " group by idproducto;";
            consultaf = "SELECT sum(factura_detalle.total),sum(cantidad) ,nombre FROM adv_facturacion.factura,factura_detalle,configuracion,producto where idfactura=factura_idfactura and configuracion_nmanguera=nmanguera and factura_detalle.producto_idproducto=idproducto and  factura.usuarios_idusuarios=" + idu1 + " and " + hora1 + " group by idproducto;";

            Statement spag = n.createStatement();
            ResultSet rpag = spag.executeQuery(consulta);
            int npa = 0;

            // ps1.println("-------------------------------");
            // ps1.println("Detalle de Pagares");
            while (rpag.next()) {

                npa = npa + rpag.getInt(1);

                //  ps1.println("Monto Total " + rpag.getString(4) + " " + rpag.getDouble(2));
                // ps1.println("Volumen Total " + rpag.getString(4) + " " + rpag.getDouble(3));
                // ps1.println("-------------------------------");

            }

            // ps1.println("Total de Pagares Emitidos :" + npa);

            Statement sfacturas = n.createStatement();
            ResultSet rfacturas = sfacturas.executeQuery(consultaf);

            //ps1.println("-------------------------------");
            //ps1.println("Detalle de Facturas");
            while (rfacturas.next()) {
                //  ps1.println("Monto Total " + rfacturas.getString(3) + " " + rfacturas.getDouble(1));
                // ps1.println("Cantidad Total " + rfacturas.getString(3) + " " + rfacturas.getDouble(2));
                //ps1.println("-------------------------------");
            }


            // ps1.println("                                  ");
            //ps1.println("                                   ");
            //ps1.println("Cantidad de Dinero Entregado:      ");
            //ps1.println("Cantidad de Dinero Faltante:       ");
            correr(8, ps1);
            cortar(ps1);
            ps1.close();

            Statement st_im = n.createStatement();
            ResultSet ridim = st_im.executeQuery("SELECT impresora FROM adv_facturacion.configuracion where nmanguera=" + me);

            while (ridim.next()) {

                impresora = ridim.getString(1);

            }

            System.out.println(impresora);


            validar = 1;






            FileInputStream inputStream = new FileInputStream("salida" + idu1 + ".txt");

            //Formato de Documento
            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            //Lectura de Documento
            Doc document = new SimpleDoc(inputStream, docFormat, null);

            //Inclusion del nombre de impresora y sus atributos
            AttributeSet attributeSet = new HashAttributeSet();
            attributeSet.add(new PrinterName(impresora, null));
            attributeSet = new HashAttributeSet();
            //Soporte de color o no
            attributeSet.add(ColorSupported.NOT_SUPPORTED);

            //Busqueda de la impresora por el nombre asignado en attributeSet
            PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

            for (int i = 0; i < services.length; i++) {

                if (services[i].getName().equals(impresora)) {

                    DocPrintJob printJob = services[i].createPrintJob();
                    //Envio a la impresora
                    printJob.print(document, new HashPrintRequestAttributeSet());

                    System.out.println("Imprimiendo en : " + services[i].getName());

                    i = services.length;

                }

            }

            buffer1.close();
            ps1.close();
            inputStream.close();

            //Busqueda de la impresora por el nombre asignado en attributeSet


            validar = 1;

        } catch (IOException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrintException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            validar = 1;
        } catch (Exception ex) {

            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);

        }

        return validar;

    }

    public int impresion1(String arg, String arg1) {

        System.out.println("entro impresion");

        FileWriter file1 = null;
        String impresora = null;
        Font fuente;

        try {

            n = data.getConnection("adv", "advgp2014");
            file1 = new FileWriter("ride" + arg1 + ".txt");
            BufferedWriter buffer = new BufferedWriter(file1);
            PrintWriter ps = new PrintWriter(buffer);

            Statement st_im = n.createStatement();
            ResultSet ridim = st_im.executeQuery("SELECT impresora FROM adv_facturacion.configuracion where nmanguera=" + arg1);

            while (ridim.next()) {

                impresora = ridim.getString(1);

            }

            Statement st_f = n.createStatement();
            ResultSet ridf = st_f.executeQuery("SELECT\n"
                    + "     datos_gasolinera.`razon_social` AS datos_gasolinera_razon_social,\n"
                    + "     datos_gasolinera.`ruc` AS datos_gasolinera_ruc,\n"
                    + "     datos_gasolinera.`direccion` AS datos_gasolinera_direccion,\n"
                    + "     datos_gasolinera.`nombre_comercial` AS datos_gasolinera_nombre_comercial,\n"
                    + "     datos_gasolinera.`contribuyente_especial` AS datos_gasolinera_contribuyente_especial,\n"
                    + "     IF(datos_gasolinera.`tipo_ambiente`=1,'Pruebas','Produccion' )AS 	datos_gasolinera_tipo_ambiente,\n"
                    + "     datos_gasolinera.`obligado_llevar_contabilidad` AS datos_gasolinera_obligado_llevar_contabilidad,\n"
                    + "     factura.`numero` AS factura_numero,\n"
                    + "     factura.`fecha` AS factura_fecha,\n"
                    + "     factura.`hora` AS factura_hora,\n"
                    + "     factura.`numero_autorizacion` AS factura_numero_autorizacion,\n"
                    + "     factura_detalle.`cantidad` AS factura_cantidad,\n"
                    + "     factura.`subtotal` AS factura_subtotal,\n"
                    + "     factura.`total` AS factura_total,\n"
                    + "     factura.`iva` AS factura_detalle_iva,\n"
                    + "     producto.`nombre` AS producto_nombre,\n"
                    + "     format(factura_detalle.`subtotal`/ factura_detalle.`cantidad`,3) as producto_punit,\n"
                    + "     cliente.`nombre` AS cliente_nombre,\n"
                    + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                    + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso,\n"
                    + "     clave_acceso.`tipo` AS clave_acceso_tipo,\n"
                    + "     producto.`idproducto` AS producto_idproducto\n,"
                    + "     datos_gasolinera.nombre_comercial,   "
                    + "     factura_detalle.`configuracion_nmanguera` AS nmanguera,    "
                    + "     datos_gasolinera.`pagina_web` AS datos_gasolinera_contribuyente_especial,\n"
                    + "     IFNULL(codigos.`codigo`,' ') AS codigos_codigo\n"
                    + "FROM\n"
                    + "     `datos_gasolinera` datos_gasolinera INNER JOIN `factura` factura ON datos_gasolinera.`iddatos_gasolinera` = factura.`datos_gasolinera_iddatos_gasolinera`\n"
                    + "     INNER JOIN `factura_detalle` factura_detalle ON factura.`idfactura` = factura_detalle.`factura_idfactura`\n"
                    + "     INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                    + "     INNER JOIN `clave_acceso` clave_acceso ON factura.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                    + "     INNER JOIN `configuracion` configuracion ON factura_detalle.`configuracion_nmanguera` = configuracion.`nmanguera`\n"
                    + "     INNER JOIN `producto` producto ON configuracion.`producto_idproducto` = producto.`idproducto`\n"
                    + "      LEFT JOIN `codigos` codigos ON cliente.`idcliente` = codigos.`cliente_idcliente` "
                    + "where factura.numero='" + arg + "'");

            System.out.println("SELECT\n"
                    + "     datos_gasolinera.`razon_social` AS datos_gasolinera_razon_social,\n"
                    + "     datos_gasolinera.`ruc` AS datos_gasolinera_ruc,\n"
                    + "     datos_gasolinera.`direccion` AS datos_gasolinera_direccion,\n"
                    + "     datos_gasolinera.`nombre_comercial` AS datos_gasolinera_nombre_comercial,\n"
                    + "     datos_gasolinera.`contribuyente_especial` AS datos_gasolinera_contribuyente_especial,\n"
                    + "     IF(datos_gasolinera.`tipo_ambiente`=1,'Pruebas','Produccion' )AS 	datos_gasolinera_tipo_ambiente,\n"
                    + "     datos_gasolinera.`obligado_llevar_contabilidad` AS datos_gasolinera_obligado_llevar_contabilidad,\n"
                    + "     factura.`numero` AS factura_numero,\n"
                    + "     factura.`fecha` AS factura_fecha,\n"
                    + "     factura.`hora` AS factura_hora,\n"
                    + "     factura.`numero_autorizacion` AS factura_numero_autorizacion,\n"
                    + "     factura_detalle.`cantidad` AS factura_cantidad,\n"
                    + "     factura.`subtotal` AS factura_subtotal,\n"
                    + "     factura.`total` AS factura_total,\n"
                    + "     factura.`iva` AS factura_detalle_iva,\n"
                    + "     producto.`nombre` AS producto_nombre,\n"
                    + "     format(factura_detalle.`subtotal`/ factura_detalle.`cantidad`,3) as producto_punit,\n"
                    + "     cliente.`nombre` AS cliente_nombre,\n"
                    + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                    + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso,\n"
                    + "     clave_acceso.`tipo` AS clave_acceso_tipo,\n"
                    + "     producto.`idproducto` AS producto_idproducto\n,"
                    + "     datos_gasolinera.nombre_comercial,   "
                    + "     factura_detalle.`configuracion_nmanguera` AS nmanguera,    "
                    + "     datos_gasolinera.`pagina_web` AS datos_gasolinera_contribuyente_especial\n"
                    + "FROM\n"
                    + "     `datos_gasolinera` datos_gasolinera INNER JOIN `factura` factura ON datos_gasolinera.`iddatos_gasolinera` = factura.`datos_gasolinera_iddatos_gasolinera`\n"
                    + "     INNER JOIN `factura_detalle` factura_detalle ON factura.`idfactura` = factura_detalle.`factura_idfactura`\n"
                    + "     INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                    + "     INNER JOIN `clave_acceso` clave_acceso ON factura.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                    + "     INNER JOIN `configuracion` configuracion ON factura_detalle.`configuracion_nmanguera` = configuracion.`nmanguera`\n"
                    + "     INNER JOIN `producto` producto ON configuracion.`producto_idproducto` = producto.`idproducto`\n"
                    + "where factura.numero='" + arg + "'");

            while (ridf.next()) {

                System.out.println(ridf.getString(2));
                setFormato(2, ps);

                char[] ESC_CUT_PAPER = new char[]{0x1B, 'R', (char) 7};
                ps.write(ESC_CUT_PAPER);

                ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 24};
                ps.write(ESC_CUT_PAPER);

                ps.println(ridf.getString(23).replace("ñ", "|"));

                ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 1};
                ps.write(ESC_CUT_PAPER);

                ps.println(ridf.getString(1).replace("ñ", "|").toUpperCase());
                ps.println("RUC:          " + ridf.getString(2));
                ps.println(ridf.getString(3));

                if (ridf.getInt(5) == 0) {
                } else {
                    ps.println("Contribuyente Especial");
                    ps.println("Segun Resolucion Numero: " + ridf.getString(5));
                }
                ps.println("Obligado a llevar Contabilidad:" + ridf.getString(7));
                Dibuja_Linea(ps);
                ps.println("Factura #:    " + ridf.getString(8));
                ps.println("Cliente: " + ridf.getString(18).replace("ñ", "|"));
                ps.println("Ruc/CI: " + ridf.getString(19) + "  Codigo:" + ridf.getString(26));
                ps.println("Fecha: " + ridf.getString(9) + " Hora Emision: " + ridf.getString(10));

                Dibuja_Linea(ps);

                Statement st_fd = n.createStatement();
                ResultSet ridfd = st_fd.executeQuery("SELECT\n"
                        + "     factura_detalle.`cantidad` AS factura_detalle_cantidad,\n"
                        + "	producto.`punit` AS producto_punit,\n"
                        + "    producto.idproducto, \n"
                        + "    factura_detalle.`configuracion_nmanguera` AS factura_detalle_configuracion_nmanguera,\n"
                        + "     factura_detalle.`subtotal` AS factura_detalle_total,\n"
                        + "     producto.`nombre` AS producto_nombre\n"
                        + "   \n"
                        + "FROM\n"
                        + "     `factura` factura INNER JOIN `factura_detalle` factura_detalle ON factura.`idfactura` = factura_detalle.`factura_idfactura`\n"
                        + "     INNER JOIN `producto` producto ON factura_detalle.`producto_idproducto` = producto.`idproducto`\n"
                        + "where factura.numero='" + arg + "'");

                DecimalFormat df = new DecimalFormat("#.####");
                while (ridfd.next()) {
                    ps.println("Cod Principal:  " + ridfd.getString(3));
                    ps.println("Manguera:       " + ridfd.getString(4));
                    ps.println("Cantidad:       " + ridfd.getString(1));
                    ps.println("Descripcion:    " + ridfd.getString(6));
                    ps.println("Precio Unitario:" + df.format(ridfd.getDouble(2) / 1.12));
                    ps.println("Subtotal       :" + ridfd.getString(5));
                    Dibuja_Linea(ps);
                }
                ps.println("Subtotal 12%: " + ridf.getString(13));
                ps.println("IVA 12%:      " + ridf.getString(15));
                ps.println("Valor Total:  " + ridf.getString(14));
                Dibuja_Linea(ps);
                ps.println("Clave de Acceso: ");
                ps.println(ridf.getString(20));

                if (ridf.getString(11).length() == 0) {
                } else {
                    ps.println("# de Autorizacion: ");
                    ps.println(ridf.getString(11));

                    ps.println("Fecha y Hora Autorizacion:");
                    ps.println(ridf.getString(9) + " " + ridf.getString(10));
                }
                ps.println("AMBIENTE:" + ridf.getString(6).toUpperCase());
                ps.println("EMISION:" + ridf.getString(21).toUpperCase());
                Dibuja_Linea(ps);

                ps.println("    SISTEMA ADV-BOX -ADVANTECH");
                ps.println("       2829421 - 0999064457");
                if (ridf.getString(11).length() == 0) {
                    ps.println("DOCUMENTO SIN VALIDEZ TRIBUTARIA");
                    ps.println("DESCARGAR FACTURA AUTORIZADA ");
                    ps.println("EN LAS PROXIMAS 24 HORAS ");
                    ps.println("DE " + ridf.getString(25) + "");
                    ps.println("CONSULTE SU VALIDEZ EN ");
                    ps.println("http://www.tinyurl.com/sridocs");
                } else {

                    ps.println("DESCARGAR FACTURA AUTORIZADA ");
                    ps.println("EN LAS PROXIMAS 24 HORAS ");
                    ps.println("DE " + ridf.getString(25) + "");
                    ps.println("CONSULTE SU VALIDEZ EN ");
                    ps.println("http://www.tinyurl.com/sridocs");

                }
                ps.println("                                     ");
                ps.println("                                     ");
                ps.println("                                     ");
                correr(5, ps);
                cortar(ps);
                ps.close();

            }

            System.out.println("impresora " + impresora);

            FileInputStream inputStream = new FileInputStream("ride" + arg1 + ".txt");

            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            //Lectura de Documento
            Doc document = new SimpleDoc(inputStream, docFormat, null);

            //Inclusion del nombre de impresora y sus atributos
            AttributeSet attributeSet = new HashAttributeSet();
            attributeSet.add(new PrinterName(impresora, null));
            attributeSet = new HashAttributeSet();
            //Soporte de color o no
            attributeSet.add(ColorSupported.NOT_SUPPORTED);

            //Busqueda de la impresora por el nombre asignado en attributeSet
            PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

            for (int i = 0; i < services.length; i++) {

                if (services[i].getName().equals(impresora)) {

                    DocPrintJob printJob = services[i].createPrintJob();
                    //Envio a la impresora
                    printJob.print(document, new HashPrintRequestAttributeSet());

                    System.out.println("Imprimiendo en : " + services[i].getName());

                    i = services.length;

                }

            }

            buffer.close();
            ps.close();
            inputStream.close();

        } catch (Exception ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

        return 0;
    }

    @Override
    public int impresion(String arg, String arg1) throws RemoteException {

        System.out.println("entro impresion");

        FileWriter file1 = null;
        String impresora = null;
        Font fuente;

        try {

            n = data.getConnection("adv", "advgp2014");
            file1 = new FileWriter("ride" + arg1 + ".txt");
            BufferedWriter buffer = new BufferedWriter(file1);
            PrintWriter ps = new PrintWriter(buffer);

            Statement st_im = n.createStatement();
            ResultSet ridim = st_im.executeQuery("SELECT impresora FROM adv_facturacion.configuracion where nmanguera=" + arg1);

            while (ridim.next()) {

                impresora = ridim.getString(1);

            }

            Statement st_f = n.createStatement();
            ResultSet ridf = st_f.executeQuery("SELECT\n"
                    + "     datos_gasolinera.`razon_social` AS datos_gasolinera_razon_social,\n"
                    + "     datos_gasolinera.`ruc` AS datos_gasolinera_ruc,\n"
                    + "     datos_gasolinera.`direccion` AS datos_gasolinera_direccion,\n"
                    + "     datos_gasolinera.`nombre_comercial` AS datos_gasolinera_nombre_comercial,\n"
                    + "     datos_gasolinera.`contribuyente_especial` AS datos_gasolinera_contribuyente_especial,\n"
                    + "     IF(datos_gasolinera.`tipo_ambiente`=1,'Pruebas','Produccion' )AS 	datos_gasolinera_tipo_ambiente,\n"
                    + "     datos_gasolinera.`obligado_llevar_contabilidad` AS datos_gasolinera_obligado_llevar_contabilidad,\n"
                    + "     factura.`numero` AS factura_numero,\n"
                    + "     factura.`fecha` AS factura_fecha,\n"
                    + "     factura.`hora` AS factura_hora,\n"
                    + "     factura.`numero_autorizacion` AS factura_numero_autorizacion,\n"
                    + "     factura_detalle.`cantidad` AS factura_cantidad,\n"
                    + "     factura.`subtotal` AS factura_subtotal,\n"
                    + "     factura.`total` AS factura_total,\n"
                    + "     factura.`iva` AS factura_detalle_iva,\n"
                    + "     producto.`nombre` AS producto_nombre,\n"
                    + "     format(factura_detalle.`subtotal`/ factura_detalle.`cantidad`,3) as producto_punit,\n"
                    + "     cliente.`nombre` AS cliente_nombre,\n"
                    + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                    + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso,\n"
                    + "     clave_acceso.`tipo` AS clave_acceso_tipo,\n"
                    + "     producto.`idproducto` AS producto_idproducto\n,"
                    + "     datos_gasolinera.nombre_comercial,   "
                    + "     factura_detalle.`configuracion_nmanguera` AS nmanguera,   "
                    + "     datos_gasolinera.`pagina_web` AS datos_gasolinera_contribuyente_especial,\n"
                    + "     IFNULL(codigos.`codigo`,' ') AS codigos_codigo\n"
                    + "FROM\n"
                    + "     `datos_gasolinera` datos_gasolinera INNER JOIN `factura` factura ON datos_gasolinera.`iddatos_gasolinera` = factura.`datos_gasolinera_iddatos_gasolinera`\n"
                    + "     INNER JOIN `factura_detalle` factura_detalle ON factura.`idfactura` = factura_detalle.`factura_idfactura`\n"
                    + "     INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                    + "     INNER JOIN `clave_acceso` clave_acceso ON factura.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                    + "     INNER JOIN `configuracion` configuracion ON factura_detalle.`configuracion_nmanguera` = configuracion.`nmanguera`\n"
                    + "     INNER JOIN `producto` producto ON configuracion.`producto_idproducto` = producto.`idproducto`\n"
                    + "      LEFT JOIN `codigos` codigos ON cliente.`idcliente` = codigos.`cliente_idcliente` "
                    + "where factura.numero='" + arg + "'");

            while (ridf.next()) {

                System.out.println(ridf.getString(2));
                setFormato(2, ps);

                char[] ESC_CUT_PAPER = new char[]{0x1B, 'R', (char) 7};
                ps.write(ESC_CUT_PAPER);

                ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 24};
                ps.write(ESC_CUT_PAPER);

                ps.println(ridf.getString(23).replace("ñ", "|"));

                ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 1};
                ps.write(ESC_CUT_PAPER);

                ps.println(ridf.getString(1).replace("ñ", "|").toUpperCase());
                ps.println("RUC:          " + ridf.getString(2));
                ps.println(ridf.getString(3));

                if (ridf.getInt(5) == 0) {
                } else {
                    ps.println("Contribuyente Especial");
                    ps.println("Segun Resolucion Numero: " + ridf.getString(5));
                }
                ps.println("Obligado a llevar Contabilidad:" + ridf.getString(7));
                Dibuja_Linea(ps);
                ps.println("Factura #:    " + ridf.getString(8));

                ps.println("Cliente: " + ridf.getString(18).replace("ñ", "|"));
                ps.println("Ruc/CI: " + ridf.getString(19) + "  Codigo:" + ridf.getString(26));
                ps.println("Fecha: " + ridf.getString(9) + " Hora Emision: " + ridf.getString(10));
                Dibuja_Linea(ps);

                Statement st_fd = n.createStatement();
                ResultSet ridfd = st_fd.executeQuery("SELECT\n"
                        + "     factura_detalle.`cantidad` AS factura_detalle_cantidad,\n"
                        + "	producto.`punit` AS producto_punit,\n"
                        + "    producto.idproducto, \n"
                        + "    factura_detalle.`configuracion_nmanguera` AS factura_detalle_configuracion_nmanguera,\n"
                        + "     factura_detalle.`subtotal` AS factura_detalle_total,\n"
                        + "     producto.`nombre` AS producto_nombre\n"
                        + "   \n"
                        + "FROM\n"
                        + "     `factura` factura INNER JOIN `factura_detalle` factura_detalle ON factura.`idfactura` = factura_detalle.`factura_idfactura`\n"
                        + "     INNER JOIN `producto` producto ON factura_detalle.`producto_idproducto` = producto.`idproducto`\n"
                        + "where factura.numero='" + arg + "'");

                DecimalFormat df = new DecimalFormat("#.####");
                while (ridfd.next()) {
                    ps.println("Cod Principal:  " + ridfd.getString(3));
                    ps.println("Manguera:       " + ridfd.getString(4));
                    if (ridfd.getString(3).equalsIgnoreCase("1") || ridfd.getString(3).equalsIgnoreCase("2") || ridfd.getString(3).equalsIgnoreCase("3")) {
                        ps.println("Cantidad:       " + ridfd.getString(1) + "gls");
                    } else {

                        ps.println("Cantidad:       " + ridfd.getInt(1));

                    }

                    ps.println("Descripcion:    " + ridfd.getString(6));
                    ps.println("Precio Unitario:" + df.format(ridfd.getDouble(2) / 1.12));
                    ps.println("Subtotal       :" + ridfd.getString(5));
                    Dibuja_Linea(ps);
                }
                ps.println("Subtotal 12%: " + ridf.getString(13));
                ps.println("IVA 12%:      " + ridf.getString(15));
                ps.println("Valor Total:  " + ridf.getString(14));
                Dibuja_Linea(ps);
                ps.println("Clave de Acceso: ");
                ps.println(ridf.getString(20));

                if (ridf.getString(11).length() == 0) {
                } else {
                    ps.println("# de Autorizacion: ");
                    ps.println(ridf.getString(11));

                    ps.println("Fecha y Hora Autorizacion:");
                    ps.println(ridf.getString(9) + " " + ridf.getString(10));
                }
                ps.println("AMBIENTE:" + ridf.getString(6).toUpperCase());
                ps.println("EMISION:" + ridf.getString(21).toUpperCase());
                Dibuja_Linea(ps);

                ps.println("    SISTEMA ADV-BOX -ADVANTECH");
                ps.println("       2829421 - 0999064457");
                if (ridf.getString(8).equalsIgnoreCase("CONTINGENCIA")) {
                    ps.println("DOCUMENTO SIN VALIDEZ TRIBUTARIA");
                    ps.println("DESCARGAR FACTURA AUTORIZADA ");
                    ps.println("EN LAS PROXIMAS 24 HORAS ");
                    ps.println("DE " + ridf.getString(25) + "");
                    ps.println("CONSULTE SU VALIDEZ EN ");
                    ps.println("http://www.tinyurl.com/sridocs");
                } else {

                    ps.println("DESCARGAR FACTURA AUTORIZADA ");
                    ps.println("EN LAS PROXIMAS 24 HORAS ");
                    ps.println("DE " + ridf.getString(25) + "");
                    ps.println("CONSULTE SU VALIDEZ EN ");
                    ps.println("http://www.tinyurl.com/sridocs");

                }
                ps.println("                                     ");
                ps.println("                                     ");
                ps.println("                                     ");
                correr(5, ps);
                cortar(ps);
                ps.close();

            }

            System.out.println("impresora " + impresora);

            FileInputStream inputStream = new FileInputStream("ride" + arg1 + ".txt");

            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            //Lectura de Documento
            Doc document = new SimpleDoc(inputStream, docFormat, null);

            //Inclusion del nombre de impresora y sus atributos
            AttributeSet attributeSet = new HashAttributeSet();
            attributeSet.add(new PrinterName(impresora, null));
            attributeSet = new HashAttributeSet();
            //Soporte de color o no
            attributeSet.add(ColorSupported.NOT_SUPPORTED);

            //Busqueda de la impresora por el nombre asignado en attributeSet
            PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

            for (int i = 0; i < services.length; i++) {

                if (services[i].getName().equals(impresora)) {

                    DocPrintJob printJob = services[i].createPrintJob();
                    //Envio a la impresora
                    printJob.print(document, new HashPrintRequestAttributeSet());

                    System.out.println("Imprimiendo en : " + services[i].getName());

                    i = services.length;

                }

            }

            buffer.close();
            ps.close();
            inputStream.close();

        } catch (Exception ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

        return 1;
    }

    @Override
    public int factura(int punto, String s1, String s2, String s3, int idcliente, String metodo_pago, int idusuario, Double subtotal, Double total, Double iva, Double[] cantidadp, Double[] subtotalp, Double[] totalp, Double[] ivap, String[] idproducto, String contraseña, String nusuario, Double[] punit, String[] nproducto, String[] detalled) throws RemoteException {
        int validar = 0;

        try {

            n = data.getConnection("adv", "advgp2014");

            Statement st_in = n.createStatement();
            ResultSet ri = st_in.executeQuery("SELECT iddatos_gasolinera,razon_social,ruc,direccion,email_estacion,secuencia1_factura,s2,despachadores_turno,tipo_ambiente,obligado_llevar_contabilidad,nombre_comercial,contribuyente_especial,certificado_digital,contraseña_certificado,tipo_cierre_turnos,contraseña_mail,AES_DECRYPT(contraseña_certificado,'thekey'),AES_DECRYPT(contraseña_mail,'thekey') FROM adv_facturacion.datos_gasolinera , adv_facturacion.punto_emision where datos_gasolinera_iddatos_gasolinera=iddatos_gasolinera and idpunto_emision=" + punto + ";");

            while (ri.next()) {

                rz = ri.getString(2);
                np = ri.getString(5);
                d = ri.getString(4);
                r = ri.getString(3);
                s1 = ri.getString(6);
                s2 = ri.getString(7);
                ta = ri.getString(9);
                ocont = ri.getString(10);
                nc = ri.getString(11);
                cespecial = ri.getInt(12);
                Blob archivo = ri.getBlob(13);

                PKCS12_RESOURCE = archivo.getBinaryStream();
                PKCS12_PASSWORD = ri.getString(17);
                maile = ri.getString(5);
                contramail = ri.getString(18);
            }

            Statement clien = n.createStatement();
            ResultSet rclien = clien.executeQuery("SELECT nombre,idcliente,cedula_ruc,tipo_identificacion,email,cupo_cliente FROM adv_facturacion.cliente where idcliente=" + idcliente + ";");

            while (rclien.next()) {

                ncliente = rclien.getString(1).replace("ñ", "n");

                idcliente = rclien.getInt(2);

                clientr = rclien.getString(3);

                tcliente = rclien.getString(4);

                email = rclien.getString(5);

                cupo = rclien.getDouble(6);

            }

            if (ncliente.equalsIgnoreCase("Consumidor Final")) {

                clientr = "9999999999999";

                ti = "07";

            } else {

                if (tcliente.equalsIgnoreCase("cedula")) {

                    ti = "05";

                }

                if (tcliente.equalsIgnoreCase("ruc")) {

                    ti = "04";

                }
                if (tcliente.equalsIgnoreCase("placa")) {

                    ti = "09";

                }
                if (tcliente.equalsIgnoreCase("pasaporte")) {

                    ti = "06";

                }
            }

            String fechaEmision = String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance());
            String cn = "12345678";
            String te = "1";

            crear_clave_acceso clave = new crear_clave_acceso();

            System.out.println(fecha + "01" + r + ta + s1 + s2 + s3 + cn + te);

            cadena = clave.crear_clave_acceso(fechaEmision.replace("/", ""), "01", r, ta, s1 + s2, s3, cn, te);

            System.out.println(iva);

            int creaFactura = new crear_factura_credito_1().crear_factura_credito(idproducto, nc, ocont, cespecial, ta, rz, r, cadena, s1, s2, s3, d, fechaEmision, ti, ncliente, clientr, subtotal, iva, total, nproducto, cantidadp, punit, ivap, totalp, subtotalp, 1);

            if (creaFactura == 1) {

                FirmaDigital fd = new FirmaDigital("generados\\" + s1 + "-" + s2 + "-" + s3 + ".xml", "firmados\\firmado" + s1 + "-" + s2 + "-" + s3 + ".xml");
                fd.setPKCS12_RESOURCE(PKCS12_RESOURCE);
                fd.setPKCS12_PASSWORD(PKCS12_PASSWORD);
                fd.firmarDocumentoXML();

                //File xsd = new File("factura_v1.0.0.xsd");
                File temp = new File("firmados\\firmado" + s1 + "-" + s2 + "-" + s3 + ".xml");

                Statement st3 = n.createStatement();
                st3.executeUpdate("INSERT INTO factura (numero, fecha, hora, metodo_pago, punto_emision_idpunto_emision, "
                        + "cliente_idcliente, usuarios_idusuarios, datos_gasolinera_iddatos_gasolinera, "
                        + "subtotal, iva, total,numero_autorizacion) "
                        + "VALUES ('" + s1 + "-" + s2 + "-" + s3 + "', '" + String.format("%tF", Calendar.getInstance()) + "', '" + String.format("%tT", Calendar.getInstance())
                        + "', '" + metodo_pago + "', " + punto + ", " + idcliente + ", " + idusuario + ", 1, " + subtotal + ", " + iva + ", " + total + ",'')");

                Statement st4 = n.createStatement();
                st4.executeUpdate("INSERT INTO clave_acceso (clave_acceso, fecha, estado, tipo) VALUES ('" + cadena + "', CURDATE(), 1, 'normal')");

                Statement clave1 = n.createStatement();
                ResultSet rclave = clave1.executeQuery("SELECT idclave_acceso from clave_acceso where clave_acceso='" + cadena + "'");

                int idClave = 0;
                while (rclave.next()) {

                    idClave = rclave.getInt(1);

                }

                int idf = 0;
                Statement idfactura = n.createStatement();
                ResultSet rfactura = idfactura.executeQuery("SELECT idfactura FROM factura  WHERE numero = '" + s1 + "-" + s2 + "-" + s3 + "'");

                while (rfactura.next()) {

                    idf = rfactura.getInt(1);

                }

                String idp = null;
                Double pu = 0.00;
                int tpr = 0;
                for (int i = 0; i < nproducto.length; i++) {

                    Statement idpro = n.createStatement();
                    ResultSet rs1 = idpro.executeQuery("SELECT idproducto,punit,precio_sin_iva,tipo_producto_idtipo_producto,stock_actual FROM producto where nombre='" + nproducto[i] + "'");
                    if (rs1.next()) {
                        System.out.println("tipo  " + rs1.getInt(4));
                        idp = rs1.getString(1);
                        if (rs1.getInt(4) == 4) {
                            System.out.println("sin iva");
                            pu = rs1.getDouble(3);
                        } else {
                            pu = rs1.getDouble(2);
                        }
                        System.out.println("pu " + pu);
                    }
                    double cantidad_actual = Double.parseDouble(rs1.getString(5));
                    double dif = cantidad_actual - cantidadp[i];
                    String idprod = rs1.getString(1);
                    PreparedStatement stock = n.prepareStatement("UPDATE producto SET stock_Actual = '" + dif
                            + "' WHERE idproducto = " + idprod);
                    stock.execute();


                    System.out.println(cantidadp[i]);

                    System.out.println(idp);
                    if (idp.equals(null)) {
                        idp = "0";
                    }





                    PreparedStatement factura_detalle = n.prepareStatement("INSERT INTO factura_detalle (cantidad, subtotal, iva, total, factura_idfactura, producto_idproducto,detalle_adicional,p_unit) "
                            + "VALUES (" + cantidadp[i] + ", " + subtotalp[i] + ", " + ivap[i] + ", " + totalp[i] + ", " + idf + ", " + idp + ", '" + detalled[i] + "'," + pu + ")");

                    factura_detalle.execute();

                }

                FileInputStream in;

                PreparedStatement factura = n.prepareStatement("UPDATE factura SET Estado_factura = 'CONTINGENCIA', "
                        + "clave_acceso_idclave_acceso = " + idClave + " "
                        + "WHERE idfactura = " + idf);
                factura.execute();

                PreparedStatement contingencia = n.prepareStatement("INSERT INTO adv_xml.xml_contingencia (xml_contingencia, xml_factura,motivo)VALUES(?, '" + idf + "', 'Indisponibilidad del Sistema')");

                in = new FileInputStream(temp);
                contingencia.setBinaryStream(1, in, (int) temp.length());
                contingencia.executeUpdate();
                contingencia.close();

                in.close();
                validar = 1;

            }

            if (punto == 3) {

                Statement idfactura = n.createStatement();
                ResultSet rfactura = idfactura.executeQuery("SELECT nmanguera FROM configuracion  WHERE usuarios_idusuarios= " + idusuario + "");

                if (rfactura.first()) {




                    System.out.println(s1 + "-" + "-" + s2 + "-" + s3);
                    System.out.println(rfactura.getString(1));

                    impresion(s1 + "-" + s2 + "-" + s3, rfactura.getString(1));

                }







            }

        } catch (SQLException ex) {

            loge.log(Priority.ERROR, getStackTrace(ex));
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);

            validar = 0;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);

            validar = 0;

        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);

            validar = 0;
        }

        return validar;

    }

    @Override
    public int reparar_factura(String estado, int punto, String s1, String s2, String s3, int idcliente, String metodo_pago, int idusuario, Double subtotal, Double total, Double iva, Double[] cantidadp, Double[] subtotalp, Double[] totalp, Double[] ivap, String[] idproducto, String contraseña, String nusuario, Double[] punit, String[] nproducto, String[] detalled, String fecha) throws RemoteException {
        int validar = 0;

        int tipo = 0;

        try {
            n = data.getConnection("adv", "advgp2014");

            Statement st_in = n.createStatement();
            ResultSet ri = st_in.executeQuery("SELECT iddatos_gasolinera,razon_social,ruc,direccion,email_estacion,secuencia1_factura,s2,despachadores_turno,tipo_ambiente,obligado_llevar_contabilidad,nombre_comercial,contribuyente_especial,certificado_digital,contraseña_certificado,tipo_cierre_turnos,contraseña_mail,AES_DECRYPT(contraseña_certificado,'thekey'),AES_DECRYPT(contraseña_mail,'thekey') FROM adv_facturacion.datos_gasolinera , adv_facturacion.punto_emision where datos_gasolinera_iddatos_gasolinera=iddatos_gasolinera and idpunto_emision=" + punto + ";");

            while (ri.next()) {

                rz = ri.getString(2);
                np = ri.getString(5);
                d = ri.getString(4);
                r = ri.getString(3);
                s1 = ri.getString(6);
                s2 = ri.getString(7);
                ta = ri.getString(9);
                ocont = ri.getString(10);
                nc = ri.getString(11);
                cespecial = ri.getInt(12);
                Blob archivo = ri.getBlob(13);

                PKCS12_RESOURCE = archivo.getBinaryStream();
                PKCS12_PASSWORD = ri.getString(17);
                maile = ri.getString(5);
                contramail = ri.getString(18);
            }

            Statement clien = n.createStatement();
            ResultSet rclien = clien.executeQuery("SELECT nombre,idcliente,cedula_ruc,tipo_identificacion,email,cupo_cliente FROM adv_facturacion.cliente where idcliente=" + idcliente + ";");

            while (rclien.next()) {

                ncliente = rclien.getString(1).replace("ñ", "n");

                idcliente = rclien.getInt(2);

                clientr = rclien.getString(3);

                tcliente = rclien.getString(4);

                email = rclien.getString(5);

                cupo = rclien.getDouble(6);

            }

            if (ncliente.equalsIgnoreCase("Consumidor Final")) {

                clientr = "9999999999999";

                ti = "07";

            } else {

                if (tcliente.equalsIgnoreCase("cedula")) {

                    ti = "05";

                }

                if (tcliente.equalsIgnoreCase("ruc")) {

                    ti = "04";

                }
                if (tcliente.equalsIgnoreCase("placa")) {

                    ti = "09";

                }
                if (tcliente.equalsIgnoreCase("pasaporte")) {

                    ti = "06";

                }
            }


            String cn = "12345678";
            String te = "1";
            String fechaEmision = null;
            int idcl = 0;
            crear_clave_acceso n1 = new crear_clave_acceso();

            if (estado.equalsIgnoreCase("No enviado")) {

                fechaEmision = String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance());

                System.out.println(fecha + "01" + r + ta + s1 + s2 + s3 + cn + te);

                cadena = n1.crear_clave_acceso(fechaEmision.replace("/", ""), "01", r, ta, s1 + s2, s3, cn, te);

                System.out.println(iva);

            } else {

                Statement idcadena = n.createStatement();
                ResultSet rfcadena = idcadena.executeQuery("SELECT clave_acceso,factura.fecha,IF(clave_acceso.tipo = 'contingencia', 2, 1),idclave_acceso FROM factura,clave_acceso  WHERE clave_acceso_idclave_acceso=idclave_acceso and numero = '" + s1 + "-" + s2 + "-" + s3 + "'");

                while (rfcadena.next()) {

                    idcl = rfcadena.getInt(4);

                    fechaEmision = String.format("%1$td/%1$tm/%1$tY", rfcadena.getDate(2));

                    cadena = n1.crear_clave_acceso(fechaEmision.replace("/", ""), "01", r, ta, s1 + s2, s3, cn, te);




                    tipo = rfcadena.getInt(3);

                }

            }
            int creaFactura = new crear_factura_credito_1().crear_factura_credito(idproducto, nc, ocont, cespecial, ta, rz, r, cadena, s1, s2, s3, d, fechaEmision, ti, ncliente, clientr, subtotal, iva, total, nproducto, cantidadp, punit, ivap, totalp, subtotalp, tipo);

            if (creaFactura == 1) {

                FirmaDigital fd = new FirmaDigital("generados\\" + s1 + "-" + s2 + "-" + s3 + ".xml", "firmados\\firmado" + s1 + "-" + s2 + "-" + s3 + ".xml");
                fd.setPKCS12_RESOURCE(PKCS12_RESOURCE);
                fd.setPKCS12_PASSWORD(PKCS12_PASSWORD);
                fd.firmarDocumentoXML();

                //File xsd = new File("factura_v1.0.0.xsd");
                File temp = new File("firmados\\firmado" + s1 + "-" + s2 + "-" + s3 + ".xml");
                int idf = 0;

                Statement idfactura = n.createStatement();
                ResultSet rfactura = idfactura.executeQuery("SELECT idfactura FROM factura  WHERE numero = '" + s1 + "-" + s2 + "-" + s3 + "'");

                while (rfactura.next()) {

                    idf = rfactura.getInt(1);

                }

                if (estado.equalsIgnoreCase("No enviado")) {

                    Statement st4 = n.createStatement();
                    st4.executeUpdate("INSERT INTO clave_acceso (clave_acceso, fecha, estado, tipo) VALUES ('" + cadena + "', CURDATE(), 1, 'normal')");
                    Statement clave = n.createStatement();
                    ResultSet rclave = clave.executeQuery("SELECT idclave_acceso from clave_acceso where clave_acceso='" + cadena + "'");

                    int idClave = 0;
                    while (rclave.next()) {

                        idClave = rclave.getInt(1);

                    }

                    PreparedStatement facturar = n.prepareStatement("UPDATE factura SET recibida=1 ,clave_acceso_idclave_acceso=" + idClave
                            + " WHERE idfactura = " + idf);
                    facturar.execute();

                } else {

                    Statement st4 = n.createStatement();
                    st4.executeUpdate("UPDATE `adv_facturacion`.`clave_acceso` SET `clave_acceso`='" + cadena + "' WHERE `idclave_acceso`='" + idcl + "';");


                }

                FileInputStream in;

                PreparedStatement facturae = n.prepareStatement("UPDATE factura SET Estado_factura = 'CONTINGENCIA',cliente_idcliente=" + idcliente
                        + " WHERE idfactura = " + idf);
                facturae.execute();

                PreparedStatement contingencia = n.prepareStatement("INSERT INTO adv_xml.xml_contingencia (xml_contingencia, xml_factura,motivo)VALUES(?, '" + idf + "', 'Indisponibilidad del Sistema')");

                in = new FileInputStream(temp);
                contingencia.setBinaryStream(1, in, (int) temp.length());
                contingencia.executeUpdate();
                contingencia.close();

                in.close();
                validar = 1;

            }

        } catch (SQLException ex) {

            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);

            validar = 0;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);

            validar = 0;

        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);

            validar = 0;
        }

        return validar;

    }

    @Override
    public int impresion_pagare(String npagare) throws RemoteException {

        String impresora = "";

        InputStream inStream = null;
        FileOutputStream fos = null;
        try {
            n = data.getConnection("adv", "advgp2014");

            Statement st_im = n.createStatement();
            ResultSet ridim = st_im.executeQuery("SELECT impresora FROM adv_facturacion.configuracion where nmanguera=0");

            while (ridim.next()) {

                impresora = ridim.getString(1);

            }

            System.out.println("SELECT pagare FROM adv_facturacion.pagare where idpagare=" + npagare + "");

            Statement st_p = n.createStatement();
            ResultSet consultan = st_p.executeQuery("SELECT pagare FROM adv_facturacion.pagare where idpagare=" + npagare + "");

            if (consultan.first()) {

                Blob bin = consultan.getBlob(1);

                System.out.println(consultan.getBlob(1).getBinaryStream());

                File file = new File("pagare.txt");
                fos = new FileOutputStream(file);
                inStream = bin.getBinaryStream();
                int size = (int) bin.length();
                byte[] buffer = new byte[size];
                int length = -1;
                while ((length = inStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, length);
                }

            }

            System.out.println("Impresora");
            System.out.println("Entro");

            FileInputStream inputStream = new FileInputStream("pagare.txt");
            System.out.println(inputStream.getFD());

            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            //Lectura de Documento
            Doc document = new SimpleDoc(inputStream, docFormat, null);

            //Inclusion del nombre de impresora y sus atributos
            AttributeSet attributeSet = new HashAttributeSet();
            attributeSet.add(new PrinterName(impresora, null));
            attributeSet = new HashAttributeSet();
            //Soporte de color o no
            attributeSet.add(ColorSupported.NOT_SUPPORTED);

            //Busqueda de la impresora por el nombre asignado en attributeSet
            PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

            for (int i = 0; i < services.length; i++) {

                if (services[i].getName().equals(impresora)) {
                    try {
                        DocPrintJob printJob = services[i].createPrintJob();
                        //Envio a la impresora
                        printJob.print(document, new HashPrintRequestAttributeSet());

                        System.out.println("Imprimiendo en : " + services[i].getName());

                        i = services.length;
                    } catch (PrintException ex) {
                        Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        } catch (SQLException | IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            /* try {
             fos.close();
             inStream.close();
             } catch (IOException ex) {
             Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
             }*/
        }

        return 0;

    }

    public void imprimir_pagare_lubricantes(int npagare) {

        try {
            int idpagare = 0, idusuarios;
            String impresora = "", ruc = "";
            Font fuente;
            FileWriter file = null;
            file = new FileWriter("ticket" + manguera + ".txt");

            BufferedWriter buffer = new BufferedWriter(file);
            PrintWriter ps = new PrintWriter(buffer);

            char[] ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 0};
            ps.write(ESC_CUT_PAPER);

            String nombred = "";
            Statement st_im1 = n.createStatement();
            ResultSet ridim1 = st_im1.executeQuery("SELECT\n"
                    + "     usuarios.`nombre` AS usuarios_nombre,\n"
                    + "     pagare_lubricantes.`total` AS pagare_lubricantes_total,\n"
                    + "     cliente.`nombre` AS cliente_nombre,\n"
                    + "     datos_gasolinera.`ruc` AS datos_gasolinera_ruc,\n"
                    + "     datos_gasolinera.`razon_social` AS datos_gasolinera_razon_social,\n"
                    + "     usuarios.`idusuarios` AS datos_gasolinera_razon_social\n"
                    + "FROM\n"
                    + "     `pagare_lubricantes` pagare_lubricantes INNER JOIN `pagare_lubricantes_detalle` pagare_lubricantes_detalle ON pagare_lubricantes.`idpagare_lubricantes` = pagare_lubricantes_detalle.`pagare_lubricantes_idpagare_lubricantes`\n"
                    + "     INNER JOIN `usuarios` usuarios ON pagare_lubricantes.`usuarios_idusuarios` = usuarios.`idusuarios`\n"
                    + "     INNER JOIN `cliente` cliente ON pagare_lubricantes.`cliente_idcliente` = cliente.`idcliente`,\n"
                    + "     `datos_gasolinera` datos_gasolinera where idpagare_lubricantes=" + npagare + "");

            while (ridim1.next()) {

                nombred = ridim1.getString(1);
                idusuarios = ridim1.getInt(6);

                ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 24};
                ps.write(ESC_CUT_PAPER);

                ps.println(rz.replace("ñ", "n"));

                ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 1};
                ps.write(ESC_CUT_PAPER);
                ps.println("Pagare Numero: " + npagare);
                ps.println("Despachador : " + nombred);
                ps.println("FECHA: " + fecha() + " HORA: " + hora());
                ps.println("RUC/CED: " + ridim1.getString(4));

                Statement st_im2 = n.createStatement();
                ResultSet ridim2 = st_im2.executeQuery("SELECT\n"
                        + "     pagare_lubricantes_detalle.`cantidad` AS pagare_lubricantes_detalle_cantidad,\n"
                        + "     pagare_lubricantes_detalle.`total` AS pagare_lubricantes_detalle_total,\n"
                        + "     pagare_lubricantes_detalle.`punit` AS pagare_lubricantes_detalle_punit,\n"
                        + "     producto.`nombre` AS producto_nombre\n"
                        + "FROM\n"
                        + "     `producto` producto INNER JOIN `pagare_lubricantes_detalle` pagare_lubricantes_detalle ON producto.`idproducto` = pagare_lubricantes_detalle.`producto_idproducto` where `pagare_lubricantes_idpagare_lubricantes`=" + npagare);

                while (ridim2.next()) {

                    ps.println("---------------------------------");
                    ps.println("CANTIDAD:  " + ridim2.getString(1));
                    ps.println("PRODUCTO:  " + ridim2.getString(4));
                    ps.println("PUNIT   :  " + ridim2.getString(3));
                    ps.println("VALOR   :  " + ridim2.getString(2));

                }

                ps.println("---------------------------------------------");
                ps.println("VALOR TOTAL: " + ridim1.getString(2) + " DOLARES");
                Dibuja_Linea(ps);
                ps.println("YO:" + ridim1.getString(3));
                ps.println("DEBO Y PAGARE  ");
                ps.println("En esta ciudad a la orden de:");
                ps.println(ridim1.getString(5));
                ps.println("la cantidad de " + ridim1.getString(2) + "USD");
                ps.println("Renuncio a fuero y domicilio y ala via ");
                ps.println("ejecutiva o verbal sumaria y me someto ");
                ps.println("a los jueces o tribunales competentes");
                ps.println("de la ciudad ");
                ps.println("                      ");
                ps.println("                      ");
                ps.println("----------------------");
                ps.println("Firma Cliente");
                ps.println("                      ");
                ps.println("----------------------");
                ps.println("                       ");

                correr(5, ps);

                cortar(ps);
                ps.close();

                Statement st_im = n.createStatement();
                ResultSet ridim = st_im.executeQuery("SELECT impresora FROM adv_facturacion.configuracion where usuarios_idusuarios=" + idusuarios);

                while (ridim.next()) {

                    impresora = ridim.getString(1);

                }





            }

            FileInputStream inputStream = new FileInputStream("ticket" + manguera + ".txt");

            //Formato de Documento
            DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
            //Lectura de Documento
            Doc document = new SimpleDoc(inputStream, docFormat, null);

            //Inclusion del nombre de impresora y sus atributos
            AttributeSet attributeSet = new HashAttributeSet();
            attributeSet.add(new PrinterName(impresora, null));
            attributeSet = new HashAttributeSet();
            //Soporte de color o no
            attributeSet.add(ColorSupported.NOT_SUPPORTED);

            //Busqueda de la impresora por el nombre asignado en attributeSet
            PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);

            for (int i = 0; i < services.length; i++) {

                if (services[i].getName().equals(impresora)) {

                    DocPrintJob printJob = services[i].createPrintJob();
                    //Envio a la impresora
                    try {

                        System.out.println("Entro credito");

                        printJob.print(document, new HashPrintRequestAttributeSet());

                        FileInputStream inputStream2 = new FileInputStream("ticket" + manguera + ".txt");

                        //Formato de Documento
                        DocFlavor docFormat2 = DocFlavor.INPUT_STREAM.AUTOSENSE;
                        //Lectura de Documento
                        Doc document2 = new SimpleDoc(inputStream2, docFormat2, null);

                        DocPrintJob printJob2 = services[i].createPrintJob();
                        printJob2.print(document2, new HashPrintRequestAttributeSet());

                        //  imprimir_ticket(rz, r, ncliente, manguera, fecha(), total, producto, email, tp);
                    } catch (PrintException ex) {

                        loge.log(Priority.ERROR, getStackTrace(ex));

                    }

                    System.out.println("Imprimiendo en : " + services[i].getName());

                    i = services.length;

                }

            }

            buffer.close();
            ps.close();
            inputStream.close();


        } catch (IOException ex) {
            Logger.getLogger(Surtidores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Surtidores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ResultSet consulta(String consulta) throws RemoteException {
        ResultSet ridim1 = null;
        try {

            n = data.getConnection();

            Statement st_im1 = n.createStatement();
            ridim1 = st_im1.executeQuery(consulta);

        } catch (SQLException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ridim1;

    }

    @Override
    public int nota(int punto, String s1, String s2, String s3, int idcliente, String metodo_pago, int idusuario, Double subtotal, Double total, Double iva, Double[] cantidadp, Double[] subtotalp, Double[] totalp, Double[] ivap, String[] idproducto, String contraseña, String nusuario, Double[] punit, String[] producto, String fechaFactura, String nfactura, String motivo) throws RemoteException {
        int validar;
        try {
            int notaCredito;

            int nn = 0;

            n = data.getConnection("adv", "advgp2014");

            Statement st_in = n.createStatement();
            ResultSet ri = st_in.executeQuery("SELECT iddatos_gasolinera,razon_social,ruc,direccion,email_estacion,secuencia1_factura,s2,despachadores_turno,tipo_ambiente,obligado_llevar_contabilidad,nombre_comercial,contribuyente_especial,certificado_digital,contraseña_certificado,tipo_cierre_turnos,contraseña_mail,AES_DECRYPT(contraseña_certificado,'thekey'),AES_DECRYPT(contraseña_mail,'thekey') FROM adv_facturacion.datos_gasolinera , adv_facturacion.punto_emision where datos_gasolinera_iddatos_gasolinera=iddatos_gasolinera and idpunto_emision=" + punto + ";");

            while (ri.next()) {

                rz = ri.getString(2);
                np = ri.getString(5);
                d = ri.getString(4);
                r = ri.getString(3);
                s1 = ri.getString(6);
                s2 = ri.getString(7);
                ta = ri.getString(9);
                ocont = ri.getString(10);
                nc = ri.getString(11);
                cespecial = ri.getInt(12);
                Blob archivo = ri.getBlob(13);

                PKCS12_RESOURCE = archivo.getBinaryStream();
                PKCS12_PASSWORD = ri.getString(17);
                maile = ri.getString(5);
                contramail = ri.getString(18);
            }

            Statement clien = n.createStatement();
            ResultSet rclien = clien.executeQuery("SELECT nombre,idcliente,cedula_ruc,tipo_identificacion,email,cupo_cliente FROM adv_facturacion.cliente where idcliente=" + idcliente + ";");

            while (rclien.next()) {

                ncliente = rclien.getString(1).replace("ñ", "n");

                idcliente = rclien.getInt(2);

                clientr = rclien.getString(3);

                tcliente = rclien.getString(4);

                email = rclien.getString(5);

                cupo = rclien.getDouble(6);

            }

            if (tcliente.equalsIgnoreCase("cedula")) {

                ti = "05";

            }

            if (tcliente.equalsIgnoreCase("ruc")) {

                ti = "04";

            }
            if (tcliente.equalsIgnoreCase("placa")) {

                ti = "09";

            }
            if (tcliente.equalsIgnoreCase("pasaporte")) {

                ti = "06";

            }

            String fechaEmision = String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance());
            String cn = "12345678";
            String te = "1";

            String nuevo[] = fechaFactura.split("-");

            fechaFactura = nuevo[2] + "/" + nuevo[1] + "/" + nuevo[0];

            s2 = "050";

            System.out.println("S2 " + s2);

            String clave = new crear_clave_acceso().crear_clave_acceso(fechaEmision.replace("/", ""), "04", r, ta, s1 + s2, s3, "12345678", "1");

            notaCredito = new crear_nota_credito_normal1().crear_nota_credito_normal(idproducto, nc, ocont, cespecial, ta, rz, r, clave, s1, s2, s3, d, fechaEmision, ti, ncliente, clientr, subtotalp, ivap, totalp, producto, cantidadp, punit, fechaFactura, nfactura, motivo, total, subtotal, iva);

            if (notaCredito == 1) {

                FirmaDigital fd = new FirmaDigital("notas_generadas\\nc" + s1 + "-" + s2 + "-" + s3 + ".xml", "notas_firmadas\\nota_firmada" + s1 + "-" + s2 + "-" + s3 + ".xml");
                System.out.println("nc" + s1 + "-" + s2 + "-" + s3 + ".xml");

                fd.setPKCS12_RESOURCE(PKCS12_RESOURCE);
                fd.setPKCS12_PASSWORD(PKCS12_PASSWORD);

                fd.firmarDocumentoXML();

                File temp = new File("notas_firmadas\\nota_firmada" + s1 + "-" + s2 + "-" + s3 + ".xml");

                Statement st4 = n.createStatement();
                st4.executeUpdate("INSERT INTO clave_acceso (clave_acceso, fecha, estado, tipo) VALUES ('" + clave + "', CURDATE(), 1, 'normal')");

                Statement rclave = n.createStatement();
                ResultSet reclave = rclave.executeQuery("SELECT idclave_acceso from clave_acceso where clave_acceso='" + clave + "'");

                int idClave = 0;
                while (reclave.next()) {

                    idClave = reclave.getInt(1);

                }

                int idf = 0;
                Statement idfactura = n.createStatement();
                ResultSet rfactura = idfactura.executeQuery("SELECT idfactura FROM factura  WHERE numero = '" + nfactura + "'");

                while (rfactura.next()) {

                    idf = rfactura.getInt(1);

                }

                int pnota = 1;

                System.out.println("id nota de credito " + nn);

                System.out.println("id factura " + idf);
                
                Statement st3 = n.createStatement();
                st3.executeUpdate("INSERT INTO nota_credito (motivo, factura_idfactura, factura_cliente_idcliente, usuarios_idusuarios,clave_acceso_idclave_acceso,estado,numero,fecha,hora,subtotal,valor_modificacion,iva,punto_emision_idpunto_emision) "
                        + "VALUES ('" + motivo + "', '" + idf + "', '" + idcliente + "', '" + idusuario + "', " + idClave + ",'Contingencia', '" + s1 + "-" + s2 + "-" + s3 + "','" + String.format("%tF", Calendar.getInstance()) + "', '" + String.format("%tT", Calendar.getInstance()) + "', " + subtotal + ", " + total + "," + iva + "," + pnota + ")");

                Statement idnota = n.createStatement();
                ResultSet rdnota = idnota.executeQuery("SELECT idnota_credito FROM nota_credito  WHERE numero = '" + s1 + "-" + s2 + "-" + s3 + "'");

                while (rdnota.next()) {

                    nn = rdnota.getInt(1);

                }

                String idp = null;

                for (int i = 0; i < producto.length; i++) {

                    Statement idpr = n.createStatement();
                    ResultSet rs1 = idpr.executeQuery("SELECT idproducto FROM producto where nombre='" + producto[i] + "'");
                    if (rs1.next()) {
                        idp = rs1.getString(1);
                    }

                    System.out.println(cantidadp[i]);

                    PreparedStatement nota = n.prepareStatement("INSERT INTO nota_credito_detalle(cantidad, subtotal, iva, total,nota_credito_idnota_credito, producto_idproducto) "
                            + "VALUES (" + cantidadp[i] + ", " + subtotalp[i] + ", " + ivap[i] + ", " + totalp[i] + ", " + nn + ", " + idp + ")");
                    nota.execute();

                }

                FileInputStream in;

                PreparedStatement contingencia = n.prepareStatement("INSERT INTO adv_xml.xml_contingencia_notas (xml, xml_notas,motivo) "
                        + "VALUES(?, '" + nn + "', 'Indisponibilidad del Sistema')");

                in = new FileInputStream(temp);
                contingencia.setBinaryStream(1, in, (int) temp.length());
                contingencia.executeUpdate();
                contingencia.close();

                in.close();
                //validar = 1;

                impresion a4 = new impresion();
                a4.generarNota(s1 + "-" + s2 + "-" + s3, "0", nusuario, contraseña, 0);

                PreparedStatement facturan = n.prepareStatement("UPDATE factura SET Estado_factura = 'ANULADA'"
                        + "WHERE idfactura = " + idf);
                facturan.execute();

            }

            validar = 1;

        } catch (Exception ex) {
            validar = 0;
            loge.log(Priority.ERROR, getStackTrace(ex));

            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);

        }
        return validar;
    }

    @Override
    public int pagare_lubricantes(int idcliente, String[] detalle, Double[] cantidad, Double[] subtotalp, Double[] totalp, Double[] ivap, Double total, Double iva, int idusuario, String[] producto) throws RemoteException {
        String f, h, idp = "", punit = "";

        f = fecha();
        h = hora();
        int idpagare = 0;

        try {
            n = data.getConnection("adv", "advgp2014");

            Statement st_n = n.createStatement();
            ResultSet rid_n = st_n.executeQuery("SHOW TABLE STATUS FROM adv_facturacion LIKE 'pagare_lubricantes'");

            while (rid_n.next()) {

                idpagare = rid_n.getInt(11);
            }

            PreparedStatement nota = n.prepareStatement("INSERT INTO `adv_facturacion`.`pagare_lubricantes` (`cliente_idcliente`, `usuarios_idusuarios`, `estado`, `factura_idfactura`, `fecha`, `hora`, `total`, `subtotal`, `iva`) VALUES ('" + idcliente + "', '" + idusuario + "', '0', '0', '" + f + "', '" + h + "', '" + total + "', '" + total / 1.12 + "', '" + iva + "');");
            nota.execute();

            for (int i = 0; i < producto.length; i++) {

                System.out.println("SELECT idproducto FROM producto where nombre='" + producto[i] + "'");

                Statement idpr = n.createStatement();
                ResultSet rs1 = idpr.executeQuery("SELECT idproducto,punit FROM producto where idproducto='" + producto[i] + "'");
                if (rs1.next()) {
                    idp = rs1.getString(1);
                    punit = rs1.getString(2);

                }

                System.out.println("INSERT INTO `adv_facturacion`.`pagare_lubricantes_detalle` (`cantidad`, `total`, `subtotal`, `iva`, `producto_idproducto`, `punit`, `pagare_lubricantes_idpagare_lubricantes`) VALUES ('" + cantidad[i] + "', '" + totalp[i] + "', '" + subtotalp[i] + "', '" + ivap[i] + "', '" + idp + "', '" + punit + "', '" + idpagare + "');");

                PreparedStatement detallep = n.prepareStatement("INSERT INTO `adv_facturacion`.`pagare_lubricantes_detalle` (`cantidad`, `total`, `subtotal`, `iva`, `producto_idproducto`, `punit`, `pagare_lubricantes_idpagare_lubricantes`) VALUES ('" + cantidad[i] + "', '" + totalp[i] + "', '" + subtotalp[i] + "', '" + ivap[i] + "', '" + idp + "', '" + punit + "', '" + idpagare + "');");
                detallep.execute();

            }


            imprimir_pagare_lubricantes(idpagare);


        } catch (SQLException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            idpagare = 0;

        }
        return idpagare;
    }

    @Override
    public void impresion_nota(String numero) throws RemoteException {
        try {
            impresion a4 = new impresion();
            a4.generarNota(numero, "0", "adv", "advgp2014", 0);
        } catch (SQLException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
}
