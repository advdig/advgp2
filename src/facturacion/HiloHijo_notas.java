/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import ec.gob.sri.comprobantes.ws.Comprobante;
import ec.gob.sri.comprobantes.ws.RecepcionComprobantesService;
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;
import ec.gob.sri.comprobantes.ws.aut.Autorizacion;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.xml.datatype.XMLGregorianCalendar;
import reportes.reporte_factura;
import seguridad.CertificadosSSL;
import util.ArchivosUtil;
import util.XMLUtil;
import ws.AutorizacionComprobantesWS;
import ws.EnvioComprobantesWS;

/**
 *
 * @author Juan Gonzalez
 */
public class HiloHijo_notas implements Callable<String> {

    String Ewsri, Awsri, clave;
    File factura;
    int idfactura;
    Connection n;
    String numero;
    int id;
    String fecha, rcliente, cliente;
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
    String fechaE = "";
    Double total = null, subtotal = null, iva = null;
    String producto = null;
    int cp = 0;
    DataSource data;

    public HiloHijo_notas(DataSource data, String Ewsri, String Awsri, File factura, int idfactura, String clave, String numero) {
        this.Ewsri = Ewsri;
        this.Awsri = Awsri;
        this.factura = factura;
        this.idfactura = idfactura;
        this.clave = clave;

        this.numero = numero;
        this.data=data;
        
    }

    private static RecepcionComprobantesService service;

    @Override
    public String call() throws Exception {

        n = data.getConnection("adv","advgp2014");

        RespuestaSolicitud response = null;
        try {

            Statement st_in = n.createStatement();
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
                mail = ri.getString(4);
                contra = ri.getString(11);

            }

            Statement st_im1 = n.createStatement();
            ResultSet rs = st_im1.executeQuery("SELECT xml_contingencia.xml_factura,factura.fecha,xml_contingencia,clave_acceso,email,cantidad,punit,producto.nombre,producto.idproducto,factura.iva,factura.total,factura.recibida FROM adv_xml.xml_contingencia, adv_facturacion.factura,adv_facturacion.cliente,adv_facturacion.clave_acceso,adv_facturacion.factura_detalle,adv_facturacion.configuracion,adv_facturacion.producto where xml_factura=idfactura and factura.cliente_idcliente=idcliente and clave_acceso_idclave_acceso=idclave_acceso and factura_idfactura=idfactura and configuracion_nmanguera=nmanguera and factura_detalle.producto_idproducto=idproducto and  idfactura='" + idfactura + "' ;");

            while (rs.next()) {

                System.out.println(rs.getInt(1));

                fechaE = rs.getString(2);
                cadena = rs.getString(4);
                email = rs.getString(5);
                cantidad = rs.getDouble(6);
                pu = rs.getDouble(7);
                producto = rs.getString(8);
                cp = rs.getInt(9);
                iva = rs.getDouble(10);
                total = rs.getDouble(11);

            }

            System.out.println("Soy el hilo hijo y he iniciado mi ejecución.");

            ArchivosUtil au = new ArchivosUtil();

            CertificadosSSL.instalarCertificados();

            EnvioComprobantesWS ws = new EnvioComprobantesWS(Ewsri);
            response = ws.enviarComprobante(factura);

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

            System.out.println(response.getEstado());

            if (response.getEstado().equalsIgnoreCase("RECIBIDA")) {

                PreparedStatement estador = n.prepareStatement("UPDATE `adv_facturacion`.`factura` SET `recibida`=1 WHERE idfactura='" + idfactura + "'");

                estador.execute();

                List<Autorizacion> listaAutorizaciones = null;
                ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante respuesta = null;
                int c = 0;
                try {

                    respuesta = (new AutorizacionComprobantesWS(Awsri)).llamadaWSAutorizacionInd(clave);
                    listaAutorizaciones = respuesta.getAutorizaciones().getAutorizacion();

                    while (listaAutorizaciones.size() == 0) {

                        respuesta = (new AutorizacionComprobantesWS(Awsri)).llamadaWSAutorizacionInd(clave);
                        listaAutorizaciones = respuesta.getAutorizaciones().getAutorizacion();

                        c++;
                        if (c == 15) {

                            break;

                        }

                    }

                } catch (Exception ex) {

                }
                System.out.println("Intentos " + c);

                System.out.println(listaAutorizaciones.size());
                for (Autorizacion autorizacion : listaAutorizaciones) {

                    //Lógica de acceso a datos
                    String nAuto = autorizacion.getNumeroAutorizacion();
                    XMLGregorianCalendar fAuto = autorizacion.getFechaAutorizacion();
                    //--

                    String estado = autorizacion.getEstado().toUpperCase();

                    if (estado.compareTo("AUTORIZADO") == 0) {
                        //msgAutorizacion.append(estado);

                        ArchivosUtil.guardarDocumentoXMLAutorizado(autorizacion, "autorizados\\autorizado" + numero + ".xml");

                        System.out.println("Factura Autorizada");

                        File archivox = new File("autorizados\\autorizado" + numero + ".xml");

                        PreparedStatement xmlE = n.prepareStatement("DELETE FROM `adv_xml`.`xml_contingencia` WHERE `xml_factura`='" + idfactura + "';");

                        xmlE.execute();

                        
                        
                        
                        
                        PreparedStatement estadoF = n.prepareStatement("UPDATE `adv_facturacion`.`factura` SET `Estado_factura`='AUTORIZADO',`numero_autorizacion`='" + String.valueOf(nAuto) + "' WHERE numero='" + numero + "'");

                        estadoF.execute();
                        
                        
                         reporte_factura rf = new reporte_factura();
                         rf.enviar_email(contra, mail, "autorizados\\autorizado" + numero + ".xml", "pdf\\" + numero + ".pdf", email, ncliente, rz, numero + ".pdf", "" + numero + ".xml");
                         rf.reporte_factura(numero, r, d, d, ocont, String.valueOf(cespecial), nAuto, String.valueOf(fAuto), cadena, "Contingencia", ta, rz, nc, ncliente, clientr, fechaE, "", String.valueOf(subtotal), String.valueOf(iva), String.valueOf(total), "0", codigop, producto, String.valueOf(cantidad), "Gasolina " + producto, String.valueOf(pu), "0");


                        FileInputStream in = new FileInputStream(archivox);
                        long datos = archivox.lastModified();

                        PreparedStatement myStatement = n.prepareStatement("INSERT INTO adv_xml.xml_enviados_autorizados(doc_xml, xml_factura) VALUES(?, '" + idfactura + "')");

                        myStatement.setBinaryStream(1, in, (int) archivox.length());
                        myStatement.executeUpdate();

                        PreparedStatement xmlNA = n.prepareStatement("DELETE FROM `adv_xml`.`xml_no_autorizados` WHERE `xml_factura`='" + idfactura + "';");

                        xmlNA.execute();

                        System.out.println("Factura Autorizada");

                    } else {

                    }
                }

            }

        } catch (Exception ex) {

            Logger.getLogger(HiloHijo_notas.class.getName()).log(Level.SEVERE, null, ex);

            return "error";

        }
        return response.getEstado();

    }
}
