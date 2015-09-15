/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import Principal.guardar_datos;
import Principal.Surtidores;
import conexion.Conecciones;
import ec.gob.sri.comprobantes.ws.Comprobante;
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;
import ec.gob.sri.comprobantes.ws.aut.Autorizacion;
import ec.gob.sri.comprobantes.ws.aut.Mensaje;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
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
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.PrinterName;
import javax.sql.DataSource;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Priority;
import org.apache.log4j.RollingFileAppender;
import reportes.reporte_factura;
import seguridad.CertificadosSSL;
import seguridad.FirmaDigital;
import sockets.servidor;
import util.ArchivosUtil;
import util.XMLUtil;
import ws.AutorizacionComprobantesWS;
import ws.EnvioComprobantesWS;

/**
 *
 * @author r
 */
public class facturacion_electronica extends Thread {

    String usuario, contra;
    JTextArea mensajesR;
    Date fechalogs = new Date();
    JLabel f, h2;
    String placa;
    int nsurtidor, manguera;
    String producto, cang, punit;
    Double iva, subtotal, total;
    Double cupo = 0.00;
    String tp = "";
    Boolean mantenimiento;
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
    Conecciones he;
    String cadenaC = "";
    int idcl = 0, idusuario = 0;
    String cadena1 = "";
    InputStream PKCS12_RESOURCE;
    String PKCS12_PASSWORD;
    org.apache.log4j.Logger loge = org.apache.log4j.Logger.getLogger(facturacion_electronica.class);

    public void guardar() {

        System.out.println("coemso guaradar");
        try {
            //

            n = data.getConnection(usuario, contra);

            Statement st_im = n.createStatement();
            ResultSet ridim = st_im.executeQuery("SELECT usuarios_idusuarios FROM adv_facturacion.configuracion where nmanguera=" + manguera);

            while (ridim.next()) {

                Statement st_im1 = n.createStatement();
                ResultSet ridim1 = st_im1.executeQuery("SELECT usuario, AES_DECRYPT(contraseña,'thekey') FROM adv_facturacion.usuarios where idusuarios=" + ridim.getInt(1));

                while (ridim1.next()) {

                    usuario = ridim1.getString(1);
                    contra = ridim1.getString(2);

                }

            }

            System.out.println("Tipo de pago");

            if (tp.equalsIgnoreCase("Pagare")) {

                Statement st_credp = n.createStatement();
                ResultSet ridcredp = st_credp.executeQuery("SELECT * FROM adv_facturacion.credito_cliente, adv_facturacion.cliente where cliente_idcliente=idcliente and idcliente='" + idcliente + "';");

                // System.out.println("SELECT tipo_cliente FROM adv_facturacion.credito_cliente,adv_facturacion.cliente where cliente_idcliente=idcliente and cliente_idcliente=" + idcliente);
                if (ridcredp.next()) {

                    Statement st_credito = n.createStatement();
                    ResultSet ridcredito = st_credito.executeQuery("SELECT tipo_cliente FROM adv_facturacion.credito_cliente,adv_facturacion.cliente where cliente_idcliente=idcliente and cliente_idcliente=" + idcliente);

                    // System.out.println("SELECT tipo_cliente FROM adv_facturacion.credito_cliente,adv_facturacion.cliente where cliente_idcliente=idcliente and cliente_idcliente=" + idcliente);
                    while (ridcredito.next()) {

                        System.out.println("tipo de cliente " + ridcredito.getInt(1));

                        if (ridcredito.getInt(1) == 2) {

                            s3 = 0;

                            imprimir_ticket(rz, r, ncliente, manguera, fecha(1), total, producto, email, tp, clientr, cang, cupo - total);

                            PreparedStatement clave = n.prepareStatement("UPDATE `adv_facturacion`.`clave_acceso` SET `estado`='0' WHERE `idclave_acceso`='" + idcl + "';");

                            clave.execute();

                            PreparedStatement cupoC = n.prepareStatement("UPDATE `adv_facturacion`.`cliente` SET `cupo_cliente`='" + (cupo - total) + "' WHERE `idcliente`='" + idcliente + "';");

                            cupoC.execute();

                        } else {

                            guardar_datos g = new guardar_datos(tp, 0, 1, axml, "", "", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                            g.usuarios(usuario, contra);
                            g.grabar();
                            //imprimir_ticket(rz, r, ncliente, manguera, fecha(1), total, producto, email, tp);
                            this.start();

                        }

                    }

                } else {

                    imprimir_ticket(rz, r, ncliente, manguera, fecha(1), total, producto, email, "Prepago", clientr, cang, cupo - total);

                    PreparedStatement clave = n.prepareStatement("UPDATE `adv_facturacion`.`clave_acceso` SET `estado`='0' WHERE `idclave_acceso`='" + idcl + "';");

                    clave.execute();

                    PreparedStatement cupoC = n.prepareStatement("UPDATE `adv_facturacion`.`cliente` SET `cupo_cliente`='" + (cupo - total) + "' WHERE `idcliente`='" + idcliente + "';");

                    cupoC.execute();

                }

            } else {

                if (tp.equalsIgnoreCase("Prepago")) {

                    s3 = 0;

                    imprimir_ticket(rz, r, ncliente, manguera, fecha(1), total, producto, email, tp, clientr, cang, cupo - total);

                    PreparedStatement clave = n.prepareStatement("UPDATE `adv_facturacion`.`clave_acceso` SET `estado`='0' WHERE `idclave_acceso`='" + idcl + "';");

                    clave.execute();

                    PreparedStatement cupoC = n.prepareStatement("UPDATE `adv_facturacion`.`cliente` SET `cupo_cliente`='" + (cupo - total) + "' WHERE `idcliente`='" + idcliente + "';");

                    cupoC.execute();

                }

                if (tp.equalsIgnoreCase("Contado") || tp.equalsIgnoreCase("Credito") || tp.equalsIgnoreCase("TC")) {

                    guardar_datos g = new guardar_datos(tp, 0, 1, axml, "", "", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                    g.usuarios(usuario, contra);
                    g.grabar();
                    //imprimir_ticket(rz, r, ncliente, manguera, fecha(1), total, producto, email, tp);
                    this.start();

                }

            }

            n.close();

            System.out.println("termino guardar");

        } catch (SQLException ex) {
            loge.log(Priority.ERROR, getStackTrace(ex));
        }

    }

    @Override
    public void run() {

        try {

            System.out.println("comenzo el hilo");

            String Ewsri;
            String Awsri;

            n = data.getConnection(usuario, contra);

            if (ta.equalsIgnoreCase("1")) {

                Ewsri = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl";
                Awsri = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";

            } else {

                Ewsri = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl";
                Awsri = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";

            }

            if (ncliente.length() == 0) {

                ncliente = "Consumidor Final";
                clientr = "0000000000000";
                ti = "07";
                tcliente = "";
                Statement con = n.createStatement();
                ResultSet rcon = con.executeQuery("SELECT idcliente FROM adv_facturacion.cliente where nombre='" + ncliente + "';");

                while (rcon.next()) {

                    idcliente = rcon.getInt(1);

                }

            }

            System.out.println(tp);

            if (tp.equalsIgnoreCase("Credito") || tp.equalsIgnoreCase("prepago")) {

                mensajesR.append("\n Cliente de credito");

                PreparedStatement cupoC = n.prepareStatement("UPDATE `adv_facturacion`.`cliente` SET `cupo_cliente`='" + (cupo - total) + "' WHERE `idcliente`='" + idcliente + "';");

                cupoC.execute();

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

            String fecha = f.getText().replace("-", "");
            String hora = h2.getText().replace(":", "");

            String cn = "12345678";
            String te = "1";

////////////////////////////////////////////////////////////////////////////////
            ArchivosUtil au = new ArchivosUtil();

            // System.out.println(au.existeConexion());
            System.out.println(fecha);
            System.out.println(r);
            System.out.println(ta);
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(ss3);
            System.out.println(cn);
            System.out.println(te);

            if (au.existeConexion(Ewsri, Awsri, mantenimiento)) {

                System.out.println("sri con conexion");

                PreparedStatement clave = n.prepareStatement("UPDATE `adv_facturacion`.`clave_acceso` SET `estado`='0' WHERE `idclave_acceso`='" + idcl + "';");

                clave.execute();

                crear_clave_acceso n1 = new crear_clave_acceso();

                System.out.println(fecha);

                cadena = n1.crear_clave_acceso(fecha, "01", r, ta, s1 + s2, ss3, cn, te);

                XMLUtil xml = new XMLUtil();

                crear_factura_normal cr = new crear_factura_normal();

                int x = cr.crear_factura_normal(tp, codigop, nc, ocont, cespecial, ta, rz, r, cadena, s1, s2, ss3, d, f.getText(), ti, ncliente, clientr, subtotal, iva, total, producto, cantidad, pu);

                Date date = new Date();
                DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

                if (ncliente.equalsIgnoreCase("Consumidor Final")) {

                    PreparedStatement gf = n.prepareStatement("INSERT INTO `adv_facturacion`.`clave_acceso` (`clave_acceso`, `fecha`, `estado`,`tipo`) VALUES ('" + cadena + "',curdate(), '1','normal');");

                    gf.execute();

                    axml = new File("consumidores_finales\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                    guardar_datos g = new guardar_datos(tp, 0, 1, axml, "Consumidor Final", "", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                    g.usuarios(usuario, contra);
                    g.actualizar("", cadena, s1 + "-" + s2 + "-" + ss3, ss3);

                    mensajesR.append("\n Factura Consumidor Final");

                } else if (x == 1) {

                    PreparedStatement gf = n.prepareStatement("INSERT INTO `adv_facturacion`.`clave_acceso` (`clave_acceso`, `fecha`, `estado`,`tipo`) VALUES ('" + cadena + "',curdate(), '1','normal');");

                    gf.execute();

                    mensajesR.append("\n Factura de Manguera " + manguera + "  xml creado " + s1 + "-" + s2 + "-" + ss3 + ".xml  hora: " + hora());

                    FirmaDigital fd = new FirmaDigital("generados\\" + s1 + "-" + s2 + "-" + ss3 + ".xml", "firmados\\firmado" + s1 + "-" + s2 + "-" + ss3 + ".xml");
                    fd.setPKCS12_RESOURCE(PKCS12_RESOURCE);
                    fd.setPKCS12_PASSWORD(PKCS12_PASSWORD);

                    fd.firmarDocumentoXML();

                    mensajesR.append("\n Factura de Manguera " + manguera + "  xml firmado " + s1 + "-" + s2 + "-" + ss3 + ".xml hora: " + hora());

                    axml = new File("firmados\\firmado" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                    String motivo = "";

                    CertificadosSSL.instalarCertificados();
                    RespuestaSolicitud response = null;
                    try {

                        EnvioComprobantesWS ws = new EnvioComprobantesWS(Ewsri);
                        response = ws.enviarComprobante(axml);

                    } catch (Exception ex) {

                        guardar_datos g = new guardar_datos(tp, 0, 1, axml, "Contingencia", "indisponibilidad del sistema", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                        g.usuarios(usuario, contra);
                        g.actualizar("", cadena, s1 + "-" + s2 + "-" + ss3, ss3);
                        //impresion(s1 + "-" + s2 + "-" + ss3, String.valueOf(manguera));

                    }

                    //RespuestaSolicitud response = ws.enviarComprobante(new File("D:\\ejemplos\\Firmados\\pruebaguia.xml"));
                    //System.out.println(response.getEstado());
                    System.out.println("---");

                    RespuestaSolicitud.Comprobantes comprobantes = response.getComprobantes();
                    if (comprobantes != null) {

                        List<Comprobante> listaComprobantes = comprobantes.getComprobante();
                        if (!listaComprobantes.isEmpty()) {
                            for (Comprobante comprobante : listaComprobantes) {
                                List<ec.gob.sri.comprobantes.ws.Mensaje> mensajes = comprobante.getMensajes().getMensaje();
                                for (ec.gob.sri.comprobantes.ws.Mensaje mensaje : mensajes) {
                                    System.out.println(mensaje.getIdentificador() + "\t" + mensaje.getMensaje() + "\t" + mensaje.getInformacionAdicional());
                                }
                            }
                        }
                    }
                    mensajesR.append("\n Factura de Manguera " + manguera + "  xml enviado hora: " + hora());

                    //System.out.println(envioRes);
                    //System.out.println(respuesta(envioRes, "estado"));
                    mensajesR.append("\n Factura de Manguera " + manguera + " " + response.getEstado() + " hora:" + hora());

                    mensajesR.append("\n Clave de acceso " + cadena);

                    System.out.println("autorizando factura " + ss3);

                    if (response.getEstado().equalsIgnoreCase("RECIBIDA")) {

                        List<Autorizacion> listaAutorizaciones = null;
                        ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante respuesta = null;
                        int c = 0;
                        try {

                            respuesta = (new AutorizacionComprobantesWS(Awsri)).llamadaWSAutorizacionInd(cadena);
                            listaAutorizaciones = respuesta.getAutorizaciones().getAutorizacion();

                            while (listaAutorizaciones.size() == 0) {

                                respuesta = (new AutorizacionComprobantesWS(Awsri)).llamadaWSAutorizacionInd(cadena);
                                listaAutorizaciones = respuesta.getAutorizaciones().getAutorizacion();

                                c++;
                                if (c == 15) {

                                    guardar_datos g = new guardar_datos(tp, 0, 1, axml, "Contingencia", "indisponibilidad del sistema", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                                    g.usuarios(usuario, contra);
                                    g.actualizar("", cadena, s1 + "-" + s2 + "-" + ss3, ss3);
                                    impresion(s1 + "-" + s2 + "-" + ss3, String.valueOf(manguera), 1);

                                    break;

                                }

                            }

                        } catch (Exception ex) {

                            guardar_datos g = new guardar_datos(tp, 0, 1, axml, "Contingencia", "indisponibilidad del sistema", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                            g.usuarios(usuario, contra);
                            g.actualizar("", cadena, s1 + "-" + s2 + "-" + ss3, ss3);
                            // impresion(s1 + "-" + s2 + "-" + ss3, String.valueOf(manguera));

                        }
                        System.out.println("Intentos " + c);

                        System.out.println(listaAutorizaciones.size());
                        for (Autorizacion autorizacion : listaAutorizaciones) {
                            System.out.println("entro a autorizacion factura " + ss3);
                            //Lógica de acceso a datos
                            String nAuto = autorizacion.getNumeroAutorizacion();
                            XMLGregorianCalendar fAuto = autorizacion.getFechaAutorizacion();
                            //--

                            String estado = autorizacion.getEstado().toUpperCase();
                            mensajesR.append("\n Factura de Manguera " + manguera + " " + estado + "Hora :" + hora());

                            if (estado.compareTo("AUTORIZADO") == 0) {
                                //msgAutorizacion.append(estado);

                                ArchivosUtil.guardarDocumentoXMLAutorizado(autorizacion, "autorizados\\autorizado" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                                if (ta.equalsIgnoreCase("1")) {

                                    ta = "Pruebas";

                                } else {

                                    ta = "Producccion";

                                }

                                File auxml = new File("autorizados\\autorizado" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                                guardar_datos g = new guardar_datos(tp, 0, 1, auxml, estado, "", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                                g.usuarios(usuario, contra);
                                g.actualizar(nAuto, cadena, s1 + "-" + s2 + "-" + ss3, ss3);

                                impresion(s1 + "-" + s2 + "-" + ss3, String.valueOf(manguera), 0);

                                reporte_factura rf = new reporte_factura();
                                rf.reporte_factura(s1 + "-" + s2 + "-" + ss3, r, d, d, ocont, String.valueOf(cespecial), nAuto, String.valueOf(fAuto), cadena, "Normal", ta, rz, nc, ncliente, clientr, f.getText(), "", String.valueOf(subtotal), String.valueOf(iva), String.valueOf(total), "0", codigop, producto, String.valueOf(cantidad), producto, String.valueOf(pu), "0");
                                rf.enviar_email(contramail, maile, "autorizados\\autorizado" + s1 + "-" + s2 + "-" + ss3 + ".xml", "pdf\\" + s1 + "-" + s2 + "-" + ss3 + ".pdf", email, ncliente, rz, s1 + "-" + s2 + "-" + ss3 + ".pdf", s1 + "-" + s2 + "-" + ss3 + ".xml");
                                rf.run();

                                n.close();

                            } else {
                                int con = 0;
                                String mensajeE = null;
                                List<Mensaje> mensajes = autorizacion.getMensajes().getMensaje();
                                File auxml = null;
                                for (Mensaje mensaje : mensajes) {

                                    ArchivosUtil.guardarDocumentoXMLAutorizado(autorizacion, "no_autorizados\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                                    auxml = new File("no_autorizados\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                                    con++;

                                    if (con == 1) {

                                        mensajeE = mensaje.getMensaje();

                                    }

                                    //msgAutorizacion.append("NO AUTORIZADO").append(".").append(mensaje.getTipo()).append(".").append(mensaje.getIdentificador()).append(".").append(mensaje.getMensaje()).append(".").append(mensaje.getInformacionAdicional()).append("\n");
                                }

                                guardar_datos g = new guardar_datos(tp, 0, 1, auxml, estado, mensajeE, nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                                g.usuarios(usuario, contra);
                                g.actualizar("", cadena, s1 + "-" + s2 + "-" + ss3, ss3);

                            }
                        }

                    } else if (response.getEstado().equalsIgnoreCase("DEVUELTA")) {

                        guardar_datos g = new guardar_datos(tp, 0, 1, axml, "Contingencia", "indisponibilidad del sistema", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                        g.usuarios(usuario, contra);
                        g.actualizar("", cadena, s1 + "-" + s2 + "-" + ss3, ss3);
                        impresion(s1 + "-" + s2 + "-" + ss3, String.valueOf(manguera), 1);

                    } else {

                        guardar_datos g = new guardar_datos(tp, 0, 1, axml, "Contingencia", "indisponibilidad del sistema", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                        g.usuarios(usuario, contra);
                        g.actualizar("", cadena, s1 + "-" + s2 + "-" + ss3, ss3);
                        impresion(s1 + "-" + s2 + "-" + ss3, String.valueOf(manguera), 1);

                    }

                }

            } else {

                /*
                 System.out.println(cadena);
                 cadena1 = cadena1.substring(14, 37);
                 System.out.println(cadena1.length());

                 String ss1 = cadena1.substring(0, 5);

                 String ss2 = cadena1.substring(5, 14);
                 System.out.println(ss2);
                 String sss3 = cadena1.substring(14);
                 System.out.println(sss3);
                 te = "2";

                 System.out.println(ss1 + ss2 + sss3);
                 crear_clave_acceso n1 = new crear_clave_acceso();

                 cadena1 = n1.crear_clave_acceso(fecha, "01", r, ta, ss1, ss2, sss3, te);

                 System.out.println(cadena1);

                 PreparedStatement clave = n.prepareStatement("UPDATE `adv_facturacion`.`clave_acceso` SET `clave_acceso`='" + cadena1 + "' WHERE `idclave_acceso`='" + idcl + "';");

                 clave.execute();

                
                 */
                PreparedStatement clave = n.prepareStatement("UPDATE `adv_facturacion`.`clave_acceso` SET `estado`='0' WHERE `idclave_acceso`='" + idcl + "';");

                clave.execute();

                crear_clave_acceso n1 = new crear_clave_acceso();

                System.out.println(fecha);

                cadena = n1.crear_clave_acceso(fecha, "01", r, ta, s1 + s2, ss3, cn, te);

                XMLUtil xml = new XMLUtil();

                crear_factura_normal cr = new crear_factura_normal();

                int x = cr.crear_factura_normal(tp, codigop, nc, ocont, cespecial, ta, rz, r, cadena, s1, s2, ss3, d, f.getText(), ti, ncliente, clientr, subtotal, iva, total, producto, cantidad, pu);

                // crear_factura_contingencia cr = new crear_factura_contingencia();
                //  int x = cr.crear_factura_contingencia(tp, codigop, nc, ocont, cespecial, ta, rz, r, cadena1, s1, s2, ss3, d, f.getText(), ti, ncliente, clientr, subtotal, iva, total, producto, cantidad, pu);
                if (ncliente.equalsIgnoreCase("Consumidor Final")) {

                    axml = new File("consumidores_finales\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                    guardar_datos g = new guardar_datos(tp, 0, 1, axml, "Consumidor Final", "", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                    g.usuarios(usuario, contra);
                    g.actualizar(" ", cadena1, s1 + "-" + s2 + "-" + ss3, ss3);
                    mensajesR.append("\n Factura Consumidor Final");

                } else if (x == 1) {

                    PreparedStatement gf = n.prepareStatement("INSERT INTO `adv_facturacion`.`clave_acceso` (`clave_acceso`, `fecha`, `estado`,`tipo`) VALUES ('" + cadena + "',curdate(), '1','normal');");

                    gf.execute();

                    mensajesR.append("\n Factura de Contingencia Manguera " + manguera + "  xml creado " + s1 + "-" + s2 + "-" + ss3 + ".xml");

                    FirmaDigital fd = new FirmaDigital("generados\\" + s1 + "-" + s2 + "-" + ss3 + ".xml", "firmados\\firmado" + s1 + "-" + s2 + "-" + ss3 + ".xml");
                    fd.setPKCS12_RESOURCE(PKCS12_RESOURCE);
                    fd.setPKCS12_PASSWORD(PKCS12_PASSWORD);

                    fd.firmarDocumentoXML();

                    mensajesR.append("\n Factura de Manguera " + manguera + "  xml firmado " + s1 + "-" + s2 + "-" + ss3 + ".xml");

                    File axml = new File("firmados\\firmado" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                    guardar_datos g = new guardar_datos(tp, idcl, 1, axml, "Contingencia", "indisponibilidad del sistema", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                    g.usuarios(usuario, contra);
                    g.actualizar("", cadena, s1 + "-" + s2 + "-" + ss3, ss3);

                    int idfactu = 0;

                    Statement st_idfac = n.createStatement();
                    ResultSet idfac = st_idfac.executeQuery("SELECT idfactura  FROM adv_facturacion.factura where numero='" + s1 + "-" + s2 + "-" + ss3 + "';");

                    if (idfac.first()) {

                        idfactu = idfac.getInt(1);
                    }

                    FutureTask task = new FutureTask(new HiloHijo(data, Ewsri, Awsri, axml, idfactu, cadena, s1 + "-" + s2 + "-" + ss3));

                    ExecutorService es = Executors.newSingleThreadExecutor();

                    es.submit(task);
                    String result;

                    try {

                        result = (String) task.get(3, TimeUnit.SECONDS);

                        es.shutdownNow();

                    } catch (Exception e) {

                        es.shutdown();

                        result = "error";

                    }

                    // imprimir_ticket(rz, r, ncliente, manguera, fecha(1), total, producto, email,tp, clientr, cang, cupo - total);
                    impresion(s1 + "-" + s2 + "-" + ss3, String.valueOf(manguera), 0);

                    /*
                     guardar_datos g = new guardar_datos(idcl, 2, axml, "Contingencia", "indisponibilidad del sistema", nsurtidor, s1 + "-" + s2 + "-" + ss3, f.getText(), h2.getText(), subtotal, total, iva, manguera, idcliente, usuario, producto, cang);
                     g.usuarios(usuario, contra);
                     g.grabar();
                     */
                    n.close();

                }

            }

            System.out.println("termino el hilo");

        } catch (Exception ex) {

            loge.log(Priority.ERROR, getStackTrace(ex));

        }

    }

    public int impresion(String arg, String arg1, int t) {

        System.out.println("entro impresion");

        FileWriter file1 = null;
        String impresora = null;
        Font fuente;

        try {

            n = data.getConnection(usuario, contra);

            file1 = new FileWriter("ride" + arg1 + ".txt");
            BufferedWriter buffer = new BufferedWriter(file1);
            PrintWriter ps = new PrintWriter(buffer);

            String nombre = null;

            Statement st_im1 = n.createStatement();
            ResultSet ridim1 = st_im1.executeQuery("SELECT nombre FROM adv_facturacion.usuarios where usuario='" + usuario + "';");

            while (ridim1.next()) {

                nombre = ridim1.getString(1);

            }

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
                    + "     factura_detalle.`cantidad` AS factura_detalle_cantidad,\n"
                    + "     factura_detalle.`subtotal` AS factura_detalle_subtotal,\n"
                    + "     factura_detalle.`total` AS factura_detalle_total,\n"
                    + "     factura_detalle.`iva` AS factura_detalle_iva,\n"
                    + "     producto.`nombre` AS producto_nombre,\n"
                    + "     producto.punit as producto_punit,\n"
                    + "     cliente.`nombre` AS cliente_nombre,\n"
                    + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                    + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso,\n"
                    + "     clave_acceso.`tipo` AS clave_acceso_tipo,\n"
                    + "     producto.`idproducto` AS producto_idproducto\n,"
                    + "     datos_gasolinera.nombre_comercial,   "
                    + "     factura_detalle.`configuracion_nmanguera` AS nmanguera,"
                    + "     factura.metodo_pago AS nmanguera,    "
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
                ps.println("Despachador: " + nombre);
                ps.println("Forma Pago: " + ridf.getString(25));
                ps.println("Cliente: " + ridf.getString(18).replace("ñ", "|"));
                ps.println("Ruc/CI: " + ridf.getString(19) + "  Codigo:" + ridf.getString(27));
                ps.println("Fecha: " + ridf.getString(9) + " Hora Emision: " + ridf.getString(10));
                Dibuja_Linea(ps);
                ps.println("Cod Principal:  " + ridf.getString(22) + " Manguera: " + ridf.getString(24));
                ps.println("Cantidad:       " + ridf.getString(12) + "gls");
                ps.println("Descripcion:    " + ridf.getString(16));
                Double punit = ridf.getDouble(17);
                DecimalFormat df = new DecimalFormat("#.##");
                ps.println("Precio Unitario:" + df.format(punit / 1.12).replace(",", "."));
                Dibuja_Linea(ps);
                ps.println("Subtotal 12%: " + ridf.getString(13));
                //ps.println("Subtotal 0%:  0");
                //ps.println("Subtotal No objeto de Iva:0");
                //ps.println("Subtotal sin impuestos:" + ridf.getString(13));
                //ps.println("Descuento:    0");
                //ps.println("ICE           0");
                ps.println("IVA 12%:      " + ridf.getString(15));
                //ps.println("Propina:      0");
                ps.println("Valor Total:  " + ridf.getString(14));
                Dibuja_Linea(ps);
                ps.println("CLAVE DE ACCESO: " + ridf.getString(20));

                if (ridf.getString(11).length() == 0) {
                } else {



                    ps.println("AUTORIZACION S.R.I.: ");
                    ps.println(ridf.getString(11));
                    ps.println("Fecha y Hora" + ridf.getString(9) + " " + ridf.getString(10));
                }
                String ambiente = "";
                if (ridf.getString(11).length() == 0) {

                    ambiente = "CONTINGENCIA";

                } else {

                    ambiente = "NORMAL";


                }

                ps.println("AMBIENTE:" + ridf.getString(6).toUpperCase() + " EMISION:" + ambiente);
                ps.println();
                Dibuja_Linea(ps);

                ps.println("    SISTEMA ADV-BOX -ADVANTECH");
                ps.println("       2829421 - 0999064457");
                if (ambiente.equalsIgnoreCase("CONTINGENCIA")) {
                    ps.println("DOCUMENTO SIN VALIDEZ TRIBUTARIA");
                    ps.println("DESCARGAR FACTURA AUTORIZADA ");
                    ps.println("EN LAS PROXIMAS 24 HORAS ");
                    ps.println("DE " + ridf.getString(26) + "");
                    ps.println("CONSULTE SU VALIDEZ EN ");
                    ps.println("http://www.tinyurl.com/sridocs");
                } else {
                    ps.println("DESCARGAR FACTURA AUTORIZADA ");
                    ps.println("EN LAS PROXIMAS 24 HORAS ");
                    ps.println("DE " + ridf.getString(26) + "");
                    ps.println("CONSULTE SU VALIDEZ EN ");
                    ps.println("http://www.tinyurl.com/sridocs");

                }

                ps.println("                                     ");
                ps.println("                                     ");
                correr(5, ps);
                cortar(ps);
                ps.close();

            }

            System.out.println(impresora);

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

            n.close();

        } catch (SQLException ex) {

            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            return 0;

        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } catch (PrintException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            return 0;

        }

        return 1;
    }

    public void imprimir_ticket(String rz, String r, String nombre, int manguera, String fecha, Double Total, String producto, String correo, String tp, String ruc, String cantidad, Double cupo) {
        try {
            int idpagare = 0;
            String impresora = "";
            Font fuente;
            FileWriter file = null;
            file = new FileWriter("ticket" + manguera + ".txt");

            BufferedWriter buffer = new BufferedWriter(file);
            PrintWriter ps = new PrintWriter(buffer);

            char[] ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 0};
            ps.write(ESC_CUT_PAPER);

            if (tp.equalsIgnoreCase("Contado") || tp.equalsIgnoreCase("TC")) {

                ps.println(" " + rz);
                ps.println("RUC:" + r);
                Dibuja_Linea(ps);

                ps.println("Ticket de Venta   ");
                ps.println(s1 + "-" + s2 + "-" + ss3);
                ps.println("Cliente : " + nombre);
                ps.println("Fecha : " + fecha);
                ps.println("Producto: " + producto);
                ps.println("Total venta: " + Total);
                if (correo.length() > 0) {
                    ps.println("su factura sera enviada al correo: ");
                    ps.println(correo);
                }
                ps.println("Su factura la podra encontrar en 24 horas ");
                ps.println(" en la siguiente pagina: www.advangas.com");
                correr(5, ps);

            } else if (tp.equalsIgnoreCase("Pagare") || tp.equalsIgnoreCase("Prepago")) {

                String nombred = "";
                Statement st_im1 = n.createStatement();
                ResultSet ridim1 = st_im1.executeQuery("SELECT nombre FROM adv_facturacion.usuarios where usuario='" + usuario + "';");

                while (ridim1.next()) {

                    nombred = ridim1.getString(1);

                }

                Statement st_n = n.createStatement();
                ResultSet rid_n = st_n.executeQuery("SHOW TABLE STATUS FROM adv_facturacion LIKE 'pagare'");

                while (rid_n.next()) {

                    idpagare = rid_n.getInt(11);
                }

                if (tp.equalsIgnoreCase("Pagare")) {

                    ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 24};
                    ps.write(ESC_CUT_PAPER);

                    ps.println(rz.replace("ñ", "n"));

                    ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 1};
                    ps.write(ESC_CUT_PAPER);
                    ps.println("Pagare Numero:" + idpagare);
                    ps.println("Despachador : " + nombred);
                    ps.println("FECHA: " + fecha + " HORA: " + h2.getText());
                    ps.println("RUC/CED: " + ruc);
                    ps.println("MANGUERA: " + manguera + " CANTIDAD: " + cantidad);
                    ps.println("PRODUCTO:  " + producto);
                    ps.println("VALOR TOTAL: " + Total + " DOLARES");
                    Dibuja_Linea(ps);
                    ps.println("YO:" + nombre);
                    ps.println("DEBO Y PAGARE  ");
                    ps.println("En esta ciudad a la orden de:");
                    ps.println(rz);
                    ps.println("la cantidad de " + Total + "USD");
                    ps.println("Por concepto de Venta de Combustible:");
                    ps.println(producto);
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
                    ps.println("Cedula Cliente");
                    ps.println("Placa Vehiculo " + placa);
                    ps.println("                      ");
                    ps.println("----------------------");
                    ps.println("Nombre Chofer");
                    ps.println("                       ");

                    correr(5, ps);

                } else {

                    ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 24};
                    ps.write(ESC_CUT_PAPER);

                    ps.println(rz.replace("ñ", "n"));

                    ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 1};
                    ps.write(ESC_CUT_PAPER);
                    ps.println("Despacho Numero:" + idpagare);
                    ps.println("Despacho de Combustible");
                    ps.println("Nombre Cliente: " + nombre);
                    ps.println("FECHA: " + fecha + " HORA: " + h2.getText());
                    ps.println("Ruc/Ced:");
                    ps.println(ruc);
                    ps.println("Manguera: " + manguera);
                    ps.println("Cantidad : " + cantidad);
                    ps.println("Producto:  " + producto);
                    ps.println("Valor total: " + Total);
                    ps.println("Saldo Cliente: " + cupo);
                    ps.println("                      ");
                    ps.println("                      ");
                    ps.println("----------------------");
                    ps.println("Firma Cliente");
                    ps.println("                      ");
                    ps.println("----------------------");
                    ps.println("Cedula Cliente");
                    ps.println("                       ");
                    ps.println("                       ");
                    correr(5, ps);

                }

            }

            cortar(ps);
            ps.close();

            Statement st_im = n.createStatement();
            ResultSet ridim = st_im.executeQuery("SELECT impresora FROM adv_facturacion.configuracion where nmanguera=" + manguera);

            while (ridim.next()) {

                impresora = ridim.getString(1);

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

                        if (tp.equalsIgnoreCase("Pagare") || tp.equalsIgnoreCase("Prepago")) {

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
                        } else {
                            printJob.print(document, new HashPrintRequestAttributeSet());
                        }

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

            FileInputStream inputStream1 = new FileInputStream("ticket" + manguera + ".txt");

            if (tp.equalsIgnoreCase("Pagare")) {

                //System.out.println("INSERT INTO `adv_facturacion`.`pagare` (`pagare`,`facturado`, `factura_idfactura`, `factura_cliente_idcliente`, `factura_usuarios_idusuarios`) VALUES(?,1,'" + s3 + "','" + idcliente + "','" + idusuario + "')");
                System.out.println(s3);
                String fechap = "";

                Statement st_p = n.createStatement();
                ResultSet idpro = st_p.executeQuery("SELECT idproducto,punit from producto where nombre='" + producto + "'");

                Double punit = null;

                System.out.println("pro" + producto);

                while (idpro.next()) {

                    punit = idpro.getDouble(2);

                }

                if (s3 == 0) {

                    try {


                        if(idusuario==0){
                        
                        
                         PreparedStatement myStatement = n.prepareStatement("INSERT INTO `adv_facturacion`.`pagare` (`idpagare`,`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`,`hora`,`placa`) VALUES(" + idpagare + ",'eror recuperado',0,'0','" + idcliente + "','3','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + fechap + "','" + hora() + "','" + placa + "')");
                         myStatement.executeUpdate();
                            
                        }else{


                        fechap = fecha(2);

                        System.out.println("INSERT INTO `adv_facturacion`.`pagare` (`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`,`hora`,punit) VALUES(?,0,'" + s3 + "','" + idcliente + "','" + idusuario + "','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + fechap + "','" + hora() + "','" + punit + "')");
                        PreparedStatement myStatement = n.prepareStatement("INSERT INTO `adv_facturacion`.`pagare` (`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`,`hora`,`placa`,punit) VALUES(?,0,0,'" + idcliente + "','" + idusuario + "','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + fechap + "','" + hora() + "','" + placa + "'," + punit + ")");



                        myStatement.setBinaryStream(1, inputStream1, inputStream1.available());
                        myStatement.executeUpdate();

                        }

                    } catch (Exception ex) {

                        PreparedStatement myStatement = n.prepareStatement("INSERT INTO `adv_facturacion`.`pagare` (`idpagare`,`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`,`hora`,`placa`) VALUES(" + idpagare + ",'" + ex.getMessage() + "',0,'0','" + idcliente + "','3','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + fechap + "','" + hora() + "','" + placa + "')");
                        myStatement.executeUpdate();
                        loge.log(Priority.ERROR, getStackTrace(ex));

                    }

                } else {
                    Statement st_f = n.createStatement();
                    ResultSet rif = st_f.executeQuery("select curdate(),curtime();;");

                    while (rif.next()) {

                        System.out.println("INSERT INTO `adv_facturacion`.`pagare` (`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`) VALUES(?,0,'" + s3 + "','" + idcliente + "','" + idusuario + "','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + rif.getString(1) + "')");
                        PreparedStatement myStatement = n.prepareStatement("INSERT INTO `adv_facturacion`.`pagare` (`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`,`hora`,`placa`,punit) VALUES(?,0,0,'" + idcliente + "','" + idusuario + "','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + rif.getString(1) + "','" + hora() + "','" + placa + "'," + punit + ")");

                        myStatement.setBinaryStream(1, inputStream1, inputStream1.available());
                        myStatement.executeUpdate();

                    }
                }
            } else if (tp.equalsIgnoreCase("Prepago")){
                
                 System.out.println(s3);
                String fechap = "";

                Statement st_p = n.createStatement();
                ResultSet idpro = st_p.executeQuery("SELECT idproducto,punit from producto where nombre='" + producto + "'");

                Double punit = null;

                System.out.println("pro" + producto);

                while (idpro.next()) {

                    punit = idpro.getDouble(2);

                }

                if (s3 == 0) {

                    try {





                        fechap = fecha(2);

                        System.out.println("INSERT INTO `adv_facturacion`.`pagare` (`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`,`hora`,punit) VALUES(?,0,'" + s3 + "','" + idcliente + "','" + idusuario + "','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + fechap + "','" + hora() + "','" + punit + "')");
                        PreparedStatement myStatement = n.prepareStatement("INSERT INTO `adv_facturacion`.`pagare` (`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`,`hora`,`placa`,punit) VALUES(?,1,0,'" + idcliente + "','" + idusuario + "','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + fechap + "','" + hora() + "','" + placa + "'," + punit + ")");



                        myStatement.setBinaryStream(1, inputStream1, inputStream1.available());
                        myStatement.executeUpdate();



                    } catch (Exception ex) {

                        PreparedStatement myStatement = n.prepareStatement("INSERT INTO `adv_facturacion`.`pagare` (`idpagare`,`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`,`hora`,`placa`) VALUES(" + idpagare + ",'" + ex.getMessage() + "',0,'" + s3 + "','" + idcliente + "','" + idusuario + "','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + fechap + "','" + hora() + "','" + placa + "')");
                        myStatement.executeUpdate();
                        loge.log(Priority.ERROR, getStackTrace(ex));

                    }

                } else {
                    Statement st_f = n.createStatement();
                    ResultSet rif = st_f.executeQuery("select curdate(),curtime();;");

                    while (rif.next()) {

                        System.out.println("INSERT INTO `adv_facturacion`.`pagare` (`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`) VALUES(?,0,'" + s3 + "','" + idcliente + "','" + idusuario + "','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + rif.getString(1) + "')");
                        PreparedStatement myStatement = n.prepareStatement("INSERT INTO `adv_facturacion`.`pagare` (`pagare`,`facturado`, `factura_idfactura`, `cliente_idcliente`, `usuarios_idusuarios`,`configuracion_nmanguera`,`total`,`subtotal`,`iva`,`cantidad`,`fecha`,`hora`,`placa`,punit) VALUES(?,1,0,'" + idcliente + "','" + idusuario + "','" + manguera + "','" + total + "','" + subtotal + "','" + iva + "','" + cantidad + "','" + rif.getString(1) + "','" + hora() + "','" + placa + "'," + punit + ")");

                        myStatement.setBinaryStream(1, inputStream1, inputStream1.available());
                        myStatement.executeUpdate();

                    }
                }
                
                
            }
            

            inputStream1.close();

        } catch (IOException ex) {
            Logger.getLogger(Surtidores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Surtidores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loge() {

        try {

            SimpleDateFormat formato = new SimpleDateFormat("dd.MM.yyyy");
            String fechaAc = formato.format(fechalogs);

            System.out.println(fechaAc);
            PatternLayout defaultLayout = new PatternLayout("%p %c,line %L,%d{dd.MM.yyyy/HH:mm:ss},%m%n");
            RollingFileAppender rollingFileAppendere = new RollingFileAppender();
            rollingFileAppendere.setFile("Logs\\Logs_errores\\logerrores" + fechaAc + ".log", true, false, 0);
            //rollingFileAppender.setMaxFileSize("10MB");
            //rollingFileAppender.setMaxBackupIndex(5);
            rollingFileAppendere.setLayout(defaultLayout);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Surtidores.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }
    Connection n = null;
    DataSource data;

    public facturacion_electronica(DataSource data, String usu, String contra, JTextArea mensajesR, JLabel f, JLabel h2, int nsurtidor, int manguera, String producto, String cang, String punit, Double iva, Double subtotal, Double total) {

        System.out.println("entro al hilo");

        this.usuario = usu;
        this.contra = contra;
        this.mensajesR = mensajesR;
        this.f = f;
        this.h2 = h2;
        this.nsurtidor = nsurtidor;
        this.manguera = manguera;
        this.producto = producto;
        this.cang = cang;
        this.punit = punit;
        this.total = total;
        this.iva = iva;
        this.subtotal = subtotal;
        this.data = data;
        galones = Double.parseDouble(cang);
        precio = Double.parseDouble(punit);
        cantidad = Double.valueOf(cang);
        pu = Double.valueOf(punit);

        try {

            n = data.getConnection(usuario, this.contra);






            Statement st_d = n.createStatement();
            ResultSet rid = st_d.executeQuery("SELECT tanques_idtanques,tipo_pago,usuarios_idusuarios FROM adv_facturacion.configuracion where nmanguera=" + manguera + ";");
            while (rid.next()) {

                tanque = rid.getInt(1);
                tp = rid.getString(2);
                idusuario = rid.getInt(3);
            }

            Statement st_cp = n.createStatement();
            ResultSet ricp = st_cp.executeQuery("SELECT idproducto FROM adv_facturacion.producto where nombre='" + producto + "';");
            while (ricp.next()) {

                codigop = ricp.getString(1);

            }

            System.out.println("Comenzo buscar");

            Statement st_in = n.createStatement();
            ResultSet ri = st_in.executeQuery("SELECT iddatos_gasolinera,razon_social,ruc,direccion,email_estacion,secuencia1_factura,s2,despachadores_turno,tipo_ambiente,obligado_llevar_contabilidad,nombre_comercial,contribuyente_especial,certificado_digital,contraseña_certificado,tipo_cierre_turnos,contraseña_mail,AES_DECRYPT(contraseña_certificado,'thekey'),AES_DECRYPT(contraseña_mail,'thekey'),mantenimiento FROM adv_facturacion.datos_gasolinera , adv_facturacion.punto_emision where datos_gasolinera_iddatos_gasolinera=iddatos_gasolinera and idpunto_emision=1;");

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
                System.out.println();
                mantenimiento = ri.getBoolean(19);

                PKCS12_RESOURCE = archivo.getBinaryStream();
                PKCS12_PASSWORD = ri.getString(17);
                maile = ri.getString(5);
                contramail = ri.getString(18);
            }

            System.out.println("temino buscar");

            int ra = 0;
            // System.out.println("icliente "+ra);

            if (ta.equalsIgnoreCase("1")) {

                int i = 0;

                while (i == 0) {

                    ra = (int) (Math.random() * (1000000) + 1);

                    Statement clienA = n.createStatement();
                    ResultSet rclienA = clienA.executeQuery("SELECT idcliente FROM adv_facturacion.cliente where idcliente='" + ra + "' LIMIT 1;");
                    while (rclienA.next()) {

                        i = rclienA.getInt(1);

                    }

                }

                Statement clien = n.createStatement();
                ResultSet rclien = clien.executeQuery("SELECT nombre,idcliente,cedula_ruc,tipo_identificacion,email,cupo_cliente FROM adv_facturacion.cliente where idcliente='" + ra + "' LIMIT 1;");

                while (rclien.next()) {

                    ncliente = rclien.getString(1).replace("ñ", "n").replace("Ñ", "N");

                    idcliente = rclien.getInt(2);

                    clientr = rclien.getString(3);

                    tcliente = rclien.getString(4);

                    email = rclien.getString(5);

                    cupo = rclien.getDouble(6);

                    tp = "contado";

                    if (rclien.getString(4).equalsIgnoreCase("")) {

                        String ti = null;

                        if (clientr.length() == 10) {

                            ti = "cedula";

                        }
                        if (clientr.length() == 13) {

                            ti = "ruc";

                        }

                        PreparedStatement cli = n.prepareStatement("UPDATE `adv_facturacion`.`cliente` SET `tipo_identificacion`='" + ti + "' WHERE `idcliente`='" + idcliente + "';");

                        cli.execute();

                    }

                }

            } else {

                Statement clien = n.createStatement();
                ResultSet rclien = clien.executeQuery("SELECT nombre,idcliente,cedula_ruc,tipo_identificacion,email,cupo_cliente,configuracion.placa FROM adv_facturacion.configuracion,adv_facturacion.cliente where cliente_idcliente=idcliente and nmanguera=" + manguera + ";");

                while (rclien.next()) {

                    ncliente = rclien.getString(1).replace("ñ", "n").replace("ñ", "n").replace("Ñ", "N");

                    idcliente = rclien.getInt(2);

                    clientr = rclien.getString(3);

                    tcliente = rclien.getString(4);

                    email = rclien.getString(5);

                    cupo = rclien.getDouble(6);

                    placa = rclien.getString(7);

                }
            }

            Statement st_n = n.createStatement();
            ResultSet rid_n = st_n.executeQuery("SELECT\n"
                    + "    numero\n"
                    + "FROM\n"
                    + "   `punto_emision` punto_emision INNER JOIN `factura` factura ON punto_emision.`idpunto_emision` = factura.`punto_emision_idpunto_emision`\n"
                    + "    AND punto_emision.`datos_gasolinera_iddatos_gasolinera` = factura.`datos_gasolinera_iddatos_gasolinera`\n"
                    + "	where punto_emision.idpunto_emision=1 order by numero desc limit 1 ");
            String numero;

            if (rid_n.first()) {

                numero = rid_n.getString(1).substring(8);
                System.out.println(numero);

                s3 = Integer.parseInt(numero) + 1;

            } else {

                s3 = 1;
            }




            Formatter fmt = new Formatter();
            fmt.format("%09d", s3);

            ss3 = fmt.toString();

            System.out.println(ss3);


            if (ncliente.length() == 0) {

                ncliente = "Consumidor Final";
                clientr = "0000000000000";
                tp = "Contado";
                ti = "07";
                tcliente = "";
                Statement con = n.createStatement();
                ResultSet rcon = con.executeQuery("SELECT idcliente FROM adv_facturacion.cliente where nombre='" + ncliente + "';");

                while (rcon.next()) {

                    idcliente = rcon.getInt(1);

                }
            }

            Statement st_clave = n.createStatement();
            ResultSet idclave = st_clave.executeQuery("SELECT clave_acceso,idclave_acceso from clave_acceso where estado=0 and tipo='contingencia' limit 1");

            if (idclave.first()) {

                cadena1 = idclave.getString(1);
                idcl = idclave.getInt(2);
            }

            PreparedStatement clave = n.prepareStatement("UPDATE `adv_facturacion`.`clave_acceso` SET `estado`='1' WHERE `idclave_acceso`='" + idcl + "';");

            clave.execute();

            Statement st_surtidor = n.createStatement();
            ResultSet idsurtidor = st_surtidor.executeQuery("SELECT surtidor from configuracion WHERE `nmanguera`='" + manguera + "';");

            if (idsurtidor.first()) {

                PreparedStatement cli = n.prepareStatement("UPDATE `adv_facturacion`.`configuracion` SET `cliente_idcliente`='0' ,`tipo_pago`='' WHERE `surtidor`='" + idsurtidor.getInt(1) + "';");

                cli.execute();

            }

            DecimalFormat df = new DecimalFormat("#.##");

            total = Double.valueOf(df.format(total).replace(",", "."));

            subtotal = Double.valueOf(df.format(total / 1.12).replace(",", "."));

            iva = Double.valueOf(df.format(total - subtotal).replace(",", "."));

            pu = Double.valueOf(df.format(subtotal / cantidad).replace(",", "."));

            System.out.println("termino constructor");

            n.close();







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
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'm'};
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

    public String hora() {
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        return hourFormat.format(date);

    }

    public String fecha(int n) {
        String dia = "";
        if (n == 1) {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            f.setText(dateFormat.format(date));
            dia = dateFormat.format(date);
        } else {

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            f.setText(dateFormat.format(date));
            dia = dateFormat.format(date);
        }
        return dia;

    }
}
