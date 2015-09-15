/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import Principal.Enviar_facturas_contingencia;
import conexion.conexion_facturacion;
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;
import ec.gob.sri.comprobantes.ws.aut.Autorizacion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import reportes.reporte_factura;
import seguridad.CertificadosSSL;
import util.ArchivosUtil;
import util.XMLUtil;
import ws.AutorizacionComprobantesWS;
import ws.EnvioComprobantesWS;

/**
 *
 * @author r
 */
public class contingencia_automatica extends Thread {

    int id;
    String numero, fecha, rcliente, cliente;
    String codigop = "";
    Double cantidad, pu;
    String ocont = "";
    int cespecial = 0;
    String nc = "";
    String rz = "", np = "", d = "", r = "", na = "", clientr = "", tcliente = "";
    String s1 = "", s2 = "", tel = "";
    String ss3 = "", ta = "";
    int s3 = 0, idcliente = 0;
    String ti = "";
    String ncliente = "", email = "", cadena = "";
    ArchivosUtil au = new ArchivosUtil();
    XMLUtil Cxml = new XMLUtil();
    String mail, contra;
    
    public void run() {
        
        try {
            
            String Ewsri;
            String Awsri;
            
            int xml_factura = 0;
            String fechaE = "";
            Double total = null, subtotal = null, iva = null;
            String producto = null;
            int cp = 0;
            conexion_facturacion n = new conexion_facturacion("root", "manager");
            n.conectar();
            
           Statement st_in = n.coneccion.createStatement();
            ResultSet ri = st_in.executeQuery("SELECT razon_social,ruc,direccion,email_estacion,secuencia1_factura,s2,tipo_ambiente,obligado_llevar_contabilidad,nombre_comercial,contribuyente_especial,AES_DECRYPT(contraseña_mail,'thekey') FROM adv_facturacion.datos_gasolinera , adv_facturacion.punto_emision where datos_gasolinera_iddatos_gasolinera=iddatos_gasolinera and idpunto_emision=1");


            while (ri.next()) {

                rz = ri.getString(1);
                d = ri.getString(3);
                r = ri.getString(2);
                s1 = ri.getString(5);
                s2 = ri.getString(6);
                ta = ri.getString(7);
                ocont = ri.getString(8);
                nc = ri.getString(9);
                cespecial = ri.getInt(10);
                mail=ri.getString(4);
                contra=ri.getString(11);

            }
            
            
            if (ta.equalsIgnoreCase("1")) {
                
                
                Ewsri = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl";
                Awsri = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes";
                
            } else {
                
                Ewsri = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl";
                Awsri = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";
                
                
                
                
            }
            
            
            
            
            
            
            
            
            
            
            
            
            FileOutputStream outpu = null;
            
            
            
           
            
            Statement st_im1 = n.coneccion.createStatement();
            ResultSet ridim1 = st_im1.executeQuery("SELECT xml_contingencia.xml_factura,factura.fecha,xml_contingencia,clave_acceso,email,cantidad,punit,producto.nombre,idproducto,iva,total FROM adv_xml.xml_contingencia, adv_facturacion.factura,adv_facturacion.cliente,adv_facturacion.clave_acceso,adv_facturacion.factura_detalle,adv_facturacion.configuracion,adv_facturacion.producto where xml_factura=idfactura and factura.cliente_idcliente=idcliente and clave_acceso_idclave_acceso=idclave_acceso and factura_idfactura=idfactura and configuracion_nmanguera=nmanguera and producto_idproducto=idproducto and  idxml_contingencia='" + id + "';");

            
           
            while (ridim1.next()) {
                
                xml_factura = ridim1.getInt(1);
                fechaE = ridim1.getString(2);
                cadena = ridim1.getString(4);
                email = ridim1.getString(5);
                cantidad = ridim1.getDouble(6);
                pu = ridim1.getDouble(7);
                producto = ridim1.getString(8);
                cp = ridim1.getInt(9);
                iva = ridim1.getDouble(10);
                total = ridim1.getDouble(11);
                
                String pathname = "firmados\\firmado" + numero + ".xml";
                File file = new File(pathname);
                outpu = new FileOutputStream(file);
                Blob archivo = ridim1.getBlob(3);
                InputStream inStream = archivo.getBinaryStream();
                int size = (int) archivo.length();
                byte[] buffer = new byte[size];
                int length = -1;
                while ((length = inStream.read(buffer)) != -1) {
                    outpu.write(buffer, 0, length);
                }
                outpu.close();
                
                
                
                
            }
            
            
            
            
            
            
            
            
            
            
            System.out.println(au.existeConexion(Ewsri, Awsri,false));
            
            if (au.existeConexion(Ewsri, Awsri,false)) {
                
                
                
                System.out.println("\n Factura de " + numero + "  xml creado " + s1 + "-" + s2 + "-" + ss3 + ".xml");
                
                File fichero = new File(numero + ".xml");
                fichero.delete();
                
                
                
                
                
                Date datef = new Date();
                DateFormat hourFormatf = new SimpleDateFormat("HH:mm:ss");
                
                System.out.println("\n Factura " + numero + "  xml firmado " + s1 + "-" + s2 + "-" + ss3 + "");
                
                File xsd = new File("factura_v1.0.0.xsd");
                File axml = new File("firmados\\firmado" + numero + ".xml");
                
                
                
                
                
                
                String motivo = "";
                
                CertificadosSSL ssl = new CertificadosSSL();
                ssl.instalarCertificados();
                
                EnvioComprobantesWS ws = new EnvioComprobantesWS(Ewsri);
                RespuestaSolicitud response = ws.enviarComprobante(axml);
                
                
                
            
                System.out.println("\n Factura de Manguera " + numero + "  xml enviado hora: ");

             
                System.out.println("\n Clave de acceso " + cadena);
                
                if (response.getEstado().equalsIgnoreCase("RECIBIDA")) {
                    ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante respuesta = null;
                    respuesta = (new AutorizacionComprobantesWS(Awsri)).llamadaWSAutorizacionInd(cadena);
                    List<Autorizacion> listaAutorizaciones = respuesta.getAutorizaciones().getAutorizacion();
                    int c = 0;
                    
                    while (listaAutorizaciones.size() == 0) {
                        
                        respuesta = (new AutorizacionComprobantesWS(Awsri)).llamadaWSAutorizacionInd(cadena);
                        listaAutorizaciones = respuesta.getAutorizaciones().getAutorizacion();
                        System.out.println();
                        c++;
                    }
                    
                    System.out.println("Intentos " + c);
                    
                    
                    for (Autorizacion autorizacion : listaAutorizaciones) {
                        //Lógica de acceso a datos
                        String nAuto = autorizacion.getNumeroAutorizacion();
                        XMLGregorianCalendar fAuto = autorizacion.getFechaAutorizacion();
                        //--

                        
                        String estado = autorizacion.getEstado().toUpperCase();
                        
                        System.out.println("\n Factura " + numero + " " + estado + "");
                        
                        if (estado.compareTo("AUTORIZADO") == 0) {
                            //msgAutorizacion.append(estado);

                            ArchivosUtil.guardarDocumentoXMLAutorizado(autorizacion, "autorizados\\autorizado" + numero + ".xml");
                            
                            File archivox = new File("autorizados\\autorizado" + numero + ".xml");
                            
                            
                            if (ta.equalsIgnoreCase("1")) {
                                
                                ta = "Pruebas";
                                
                            } else {
                                
                                ta = "Producccion";
                                
                            }
                            
                            System.out.println(xml_factura);
                            PreparedStatement xmlE = n.coneccion.prepareStatement("DELETE FROM `adv_xml`.`xml_contingencia` WHERE `xml_factura`='" + xml_factura + "';");
                            
                            xmlE.execute();
                            
                            reporte_factura rf = new reporte_factura();
                            rf.reporte_factura(numero, r, d, d, ocont, String.valueOf(cespecial), nAuto, String.valueOf(fAuto), cadena, "Contingencia", ta, rz, nc, ncliente, clientr, fechaE, "", String.valueOf(subtotal), String.valueOf(iva), String.valueOf(total), "0", codigop, producto, String.valueOf(cantidad), "Gasolina " + producto, String.valueOf(pu), "0");
                            rf.enviar_email(contra, mail, "autorizados\\autorizado" + numero + ".xml", "pdf\\" + numero + ".pdf", email, ncliente, rz, numero + ".pdf", "" + numero + ".xml");
                            
                            PreparedStatement estadoF = n.coneccion.prepareStatement("UPDATE `adv_facturacion`.`factura` SET `Estado_factura`='AUTORIZADO',`numero_autorizacion`='"+String.valueOf(fAuto)+"' WHERE numero='" + numero + "'");
                            
                            estadoF.execute();
                            
                            FileInputStream in = new FileInputStream(archivox);
                            long datos = archivox.lastModified();
                            
                            
                            PreparedStatement myStatement = n.coneccion.prepareStatement("INSERT INTO adv_xml.xml_enviados_autorizados(doc_xml, xml_factura) VALUES(?, '" + xml_factura + "')");
                            
                            myStatement.setBinaryStream(1, in, (int) archivox.length());
                            myStatement.executeUpdate();
                            
                            
                            
                            
                            
                        } else {
                            
                            
                            
                            
                            String mensajeE = null;
                            int con = 0;
                            List<ec.gob.sri.comprobantes.ws.aut.Mensaje> mensajes = autorizacion.getMensajes().getMensaje();
                            for (ec.gob.sri.comprobantes.ws.aut.Mensaje mensaje : mensajes) {
                                
                                ArchivosUtil.guardarDocumentoXMLAutorizado(autorizacion, "no_autorizados\\" + numero + ".xml");
                                
                                con++;
                                
                                if (con == 1) {
                                    
                                    mensajeE = mensaje.getMensaje();
                                    
                                }


                                // mens.append("NO AUTORIZADO").append(".").append(mensaje.getTipo()).append(".").append(mensaje.getIdentificador()).append(".").append(mensaje.getMensaje()).append(".").append(mensaje.getInformacionAdicional()).append("\n");
                            }
                            
                            File archivox = new File("no_autorizados\\" + numero + ".xml");
                            PreparedStatement estadoF = n.coneccion.prepareStatement("UPDATE `adv_facturacion`.`factura` SET `Estado_factura`='NO AUTORIZADO' WHERE numero='" + numero + "'");
                            
                            estadoF.execute();
                            
                            FileInputStream in = new FileInputStream(archivox);
                            long datos = archivox.lastModified();
                            PreparedStatement myStatement = n.coneccion.prepareStatement("INSERT INTO adv_xml.xml_no_autorizados(doc_xml, xml_factura,motivo_no_autorizado) VALUES(?, '" + xml_factura + "', '" + mensajeE + "')");
                            
                            myStatement.setBinaryStream(1, in, (int) archivox.length());
                            myStatement.executeUpdate();
                            
                            PreparedStatement xmlE = n.coneccion.prepareStatement("DELETE FROM `adv_xml`.`xml_contingencia` WHERE `xml_factura`='" + xml_factura + "';");
                            
                            xmlE.execute();
                            
                        }
                        
                    }
                    
                    
                    
                    
                    
                    
                }
                
                
                
                
            }
            
            n.coneccion.close();
            
            
            
            
            
            
        } catch (JAXBException ex) {
            Logger.getLogger(Enviar_facturas_contingencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Enviar_facturas_contingencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Enviar_facturas_contingencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Enviar_facturas_contingencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public contingencia_automatica(int id, String numero, String fecha, String rcliente, String cliente) {
        this.id = id;
        this.numero = numero;
        this.fecha = fecha;
        this.rcliente = rcliente;
        this.cliente = cliente;
        
        
        
        
        
        
        
        
    }
}
