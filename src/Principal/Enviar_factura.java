/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import conexion.ConnectionTableDB;
import conexion.conexion_facturacion;
import facturacion.crear_clave_acceso;
import facturacion.crear_factura_credito;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import seguridad.FirmaDigital;

public class Enviar_factura extends Thread {

    private ConnectionTableDB model;
    private final String DB = "adv_facturacion";
    public String ta, razonSocial, nombreComercial, ruc, s2, s1, secuencial, direccion, emailEstacion;
    public String fechaEmision, tipo, nCliente, idC, contribuyente, contabilidad, factura, fechaFactura, passwEmail;
    private String claveAcceso, Ewsri, Awsri, email;
    private String prod, pago;
    private Double cantidadT, ppuT, subtotalT, ivaT, totalT;
    private Double cantidadE = 0.000, ppuE = 0.000, subtotalE = 0.00, ivaE = 0.000, totalE = 0.00;
    private Double cantidadS = 0.000, ppuS = 0.000, subtotalS = 0.00, ivaS = 0.000, totalS = 0.00;
    private Double cantidadD = 0.000, ppuD = 0.000, subtotalD = 0.00, ivaD = 0.000, totalD = 0.00;
    private String[] productos,ids;
    private Double[] cantidades, ppus, subtotales, ivas, totales;
    String usuario, contra;
    JTextArea mensajesR;
    int idClave;
    Date fechalogs = new Date();
    JLabel f, h2;
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
    String  tel = "";
    String ss3 = "";
    int s3 = 0, idcliente = 0;
    String ti = "", maile = "", contramail = "",ncliente="";
    int tanque = 0;
    int turno = 0;
    String cadena;
    File axml;
    conexion_facturacion he;
    String cadenaC = "";
    int idcl = 0, idusuario = 0;
    String cadena1 = "";
    InputStream PKCS12_RESOURCE;
    String PKCS12_PASSWORD;
    private int idFactura;
    String Cedula;
    private String idUser;

    public Enviar_factura(String usu, String contra, String[] producto, Double[] cangp, Double[] punitp, Double[] ivap, Double[] subtotalp, Double[] totalp, Double total, Double subtotal, Double iva,String ruc,String [] ids) {



        


        ppuT = 0.00;
        cantidadT = 0.00;
        subtotalT = 0.00;
        totalT = 0.00;
        ppuE = 0.00;
        ppuS = 0.00;
        ppuD = 0.00;
        cantidadE = 0.00;
        cantidadS = 0.00;
        cantidadD = 0.00;
        subtotalE = 0.00;
        subtotalS = 0.00;
        subtotalD = 0.00;
        ivaE = 0.00;
        ivaS = 0.00;
        ivaD = 0.00;
        totalE = 0.00;
        totalS = 0.00;
        totalD = 0.00;

        

               
        this.usuario=usu;
        this.contra=contra;
        this.ids=ids;    
        this.productos=producto;
        this.cantidades=cangp;
        this.ppus=punitp;
        this.ivas=ivap;
        this.subtotales=subtotalp;
        this.totales=totalp;
        this.total=total;
        this.subtotal=subtotal;
        this.iva=iva;
        this.Cedula=ruc;
        
        
        
        
     

        try {
            
            model = new ConnectionTableDB(usuario,contra, DB,"", false);
            he = new conexion_facturacion(usuario, contra);
            he.conectar();




            
            Statement st_in = he.coneccion.createStatement();
            ResultSet ri = st_in.executeQuery("SELECT iddatos_gasolinera,razon_social,ruc,direccion,email_estacion,secuencia1_factura,s2,despachadores_turno,tipo_ambiente,obligado_llevar_contabilidad,nombre_comercial,contribuyente_especial,certificado_digital,contraseña_certificado,tipo_cierre_turnos,contraseña_mail,AES_DECRYPT(contraseña_certificado,'thekey'),AES_DECRYPT(contraseña_mail,'thekey') FROM adv_facturacion.datos_gasolinera , adv_facturacion.punto_emision where datos_gasolinera_iddatos_gasolinera=iddatos_gasolinera and idpunto_emision=2;");

            while (ri.next()) {

                razonSocial = ri.getString(2);
                np = ri.getString(5);
                direccion = ri.getString(4);
                r = ri.getString(3);
                s1 = ri.getString(6);
                s2 = ri.getString(7);
                ta = ri.getString(9);
               contabilidad = ri.getString(10);
               nombreComercial = ri.getString(11);
                contribuyente = ri.getString(12);
                Blob archivo = ri.getBlob(13);


                PKCS12_RESOURCE = archivo.getBinaryStream();
                PKCS12_PASSWORD = ri.getString(17);
                maile = ri.getString(5);
                contramail = ri.getString(18);
            }
     
            
            
               Statement user = he.coneccion.createStatement();
                ResultSet ruser = user.executeQuery("select idusuarios from usuarios where usuario='"+usuario+"';");


                while (ruser.next()) {
                
                
                idUser=ruser.getString(1);
                
                }

            
            
                Statement clien = he.coneccion.createStatement();
                ResultSet rclien = clien.executeQuery("SELECT nombre,idcliente,cedula_ruc,tipo_identificacion,email,cupo_cliente FROM adv_facturacion.configuracion,adv_facturacion.cliente where cliente_idcliente=idcliente and cedula_ruc='" + Cedula+ "';");


                while (rclien.next()) {

                    nCliente= rclien.getString(1).replace("ñ","n").replace("ñ","n").replace("Ñ","N");


                    idcliente = rclien.getInt(2);

                    clientr = rclien.getString(3);

                    tcliente = rclien.getString(4);

                    email = rclien.getString(5);

                    cupo = rclien.getDouble(6);







                }

            Statement st_n = he.coneccion.createStatement();
            ResultSet rid_n = st_n.executeQuery("SELECT\n"
                    + "    numero\n"
                    + "FROM\n"
                    + "   `punto_emision` punto_emision INNER JOIN `factura` factura ON punto_emision.`idpunto_emision` = factura.`punto_emision_idpunto_emision`\n"
                    + "    AND punto_emision.`datos_gasolinera_iddatos_gasolinera` = factura.`datos_gasolinera_iddatos_gasolinera`\n"
                    + "	where punto_emision.idpunto_emision=2 order by numero desc");
            String numero;

            if (rid_n.first()) {

                numero = rid_n.getString(1).substring(8);
                System.out.println(numero);
                s3 = Integer.parseInt(numero) + 1;


            }
            
           if (s3 >= 9) {


                ss3 = "00000000" + s3;
            }
            if (s3 <= 99 & s3 > 9) {


                ss3 = "0000000" + s3;
            }
            if (s3 <= 999 & s3 > 99) {


                ss3 = "000000" + s3;
            }

            if (s3 <= 9999 & s3 > 999) {


                ss3 = "00000" + s3;
            }
            if (s3 <= 99999 & s3 > 9999) {


                ss3 = "0000" + s3;
            }

            if (s3 <= 999999 & s3 > 99999) {


                ss3 = "000" + s3;
            }
            if (s3 <= 9999999 & s3 > 999999) {


                ss3 = "00" + s3;
            }
            if (s3 <= 99999999 & s3 > 9999999) {


                ss3 = "0" + s3;
            }
            if (s3 <= 9) {


                ss3 = "00000000" + s3;
            }
            if (s3 <= 99 & s3 > 9) {


                ss3 = "0000000" + s3;
            }
            if (s3 <= 999 & s3 > 99) {


                ss3 = "000000" + s3;
            }

            if (s3 <= 9999 & s3 > 999) {


                ss3 = "00000" + s3;
            }
            if (s3 <= 99999 & s3 > 9999) {


                ss3 = "0000" + s3;
            }

            if (s3 <= 999999 & s3 > 99999) {


                ss3 = "000" + s3;
            }
            if (s3 <= 9999999 & s3 > 999999) {


                ss3 = "00" + s3;
            }
            if (s3 <= 99999999 & s3 > 9999999) {


                ss3 = "0" + s3;
            }   


                



            
            
            
            
            he.coneccion.close();

          
            
            

        } catch (SQLException ex) {
            Logger.getLogger(Enviar_factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Enviar_factura.class.getName()).log(Level.SEVERE, null, ex);
        }









    }

    
    
    
    
    public void ejecutar(){
    
    
      this.start();
    
    
    
    
    }
    
    
    
    
    @Override
    public void run() {
        try {
            String Ewsri;
            String Awsri;
            
            
            System.out.println("Ejecutando Hilo");
                                if (ta.equalsIgnoreCase("1")) {


                Ewsri = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl";
                Awsri = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";

            } else {

                Ewsri = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl";
                Awsri = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";




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
            
           fechaEmision=String.format("%tF", Calendar.getInstance());
           System.out.println(fechaEmision.replace("-", "")+ "01"+ r+ta+s1 + s2+ ss3+ "12345678"+ "1"); 
           
             String clave = new crear_clave_acceso().crear_clave_acceso(fechaEmision.replace("-", ""), "01", r, ta,s1 + s2, ss3, "12345678", "1");
             System.out.println("Clave de acceso: " + clave + " generada.\n");
        
        
             System.out.println(Integer.parseInt(contribuyente));
             System.out.println(nCliente);
             
             System.out.println("01"+ ids+ nombreComercial+ contabilidad+ Integer.parseInt(contribuyente)+ ta+ razonSocial+ r+ clave+ s1+ s2+ ss3+ direccion+ fechaEmision+ ti+ nCliente.replace("ñ", "n").replace("Ñ","N")+ ti+ subtotal+ iva+ total+ productos+ cantidades+ ppus+ ivas+ totales+ subtotales);
             
             
            int Factura = new crear_factura_credito().crear_factura_credito("01", ids, nombreComercial, contabilidad, Integer.parseInt(contribuyente), ta,
                        razonSocial, r, clave, s1, s2, ss3, direccion, fechaEmision, ti, nCliente.replace("ñ", "n").replace("Ñ", "N"), clientr,
                        subtotal, iva, total, productos, cantidades, ppus, ivas, totales, subtotales);

              
                              
                    System.out.println("Factura de Credito: " + s1 + "-" + s2 + "-" + ss3 + " creada correctamente.\n");



                    FirmaDigital fd = new FirmaDigital("generados\\" + s1 + "-" + s2 + "-" + ss3 + ".xml", "firmados\\firmado" + s1 + "-" + s2 + "-" + ss3 + ".xml");
                    fd.setPKCS12_RESOURCE(PKCS12_RESOURCE);
                    fd.setPKCS12_PASSWORD(PKCS12_PASSWORD);
                    fd.firmarDocumentoXML();
                    System.out.println("Factura de Credito: " + s1 + "-" + s2 + "-" + ss3 + " firmada correctamente.\n");

                    //File xsd = new File("factura_v1.0.0.xsd");
                    File temp = new File("firmados\\firmado" + s1 + "-" + s2 + "-" + ss3 + ".xml");


                                    model.ejecutar("INSERT INTO factura (numero, fecha, hora, metodo_pago, punto_emision_idpunto_emision, "
                                            + "cliente_idcliente, usuarios_idusuarios, datos_gasolinera_iddatos_gasolinera, "
                                            + "subtotal, iva, total) "
                                            + "VALUES ('" + s1 + "-" + s2 + "-" + ss3 + "', '" + String.format("%tF", Calendar.getInstance()) + "', '" + String.format("%tT", Calendar.getInstance())
                                            + "', 'Credito', 2, " + idcliente + ", " + idUser + ", 1, " + subtotal + ", " + iva + ", " + total + ")");

                                    model.ejecutar("INSERT INTO clave_acceso (clave_acceso, fecha, estado, tipo) VALUES ('" + clave + "', CURDATE(), 1, 'normal')");
                                    System.out.println("Clave de acceso: " + clave + " grabada correctamente.\n");

                                    try (java.sql.ResultSet rsIdClave = model.stSentencias.executeQuery("SELECT idclave_acceso FROM clave_acceso WHERE clave_acceso = '" + clave + "'")) {
                                        if (rsIdClave.first()) {
                                            idClave = rsIdClave.getInt(1);
                                        }
                                    }



                                    try (java.sql.ResultSet rsFact = model.stSentencias.executeQuery("SELECT idfactura FROM factura "
                                            + "WHERE numero = '" + s1 + "-" + s2 + "-" + ss3 + "'")) {
                                        if (rsFact.first()) {
                                            idFactura = rsFact.getInt(1);
                                        }
                                    }
                                    String idp = null;
                                    for (int i = 0; i < productos.length; i++) {

                                        java.sql.ResultSet rs1 = model.stSentencias.executeQuery("SELECT idproducto FROM producto where nombre='" + productos[i] + "'");
                                        if (rs1.next()) {
                                            idp = rs1.getString(1);
                                        }


                                        model.ejecutar("INSERT INTO factura_detalle (cantidad, subtotal, iva, total, factura_idfactura, producto_idproducto) "
                                                + "VALUES (" + cantidades[i] + ", " + subtotales[i] + ", " + ivas[i] + ", " + totales[i] + ", " + idFactura + ", " + idp + ")");
                                    }



                                    model.ejecutar("UPDATE factura SET Estado_factura = 'CONTINGENCIA', "
                                            + "clave_acceso_idclave_acceso = " + idClave + " "
                                            + "WHERE idfactura = " + idFactura);
                               
                                    
                                    
                                    
                                    model.psPrepararSentencias = model.coneccion.prepareStatement("INSERT INTO adv_xml.xml_contingencia (xml_contingencia, xml_factura,motivo) "
                                            + "VALUES(?, '" + idFactura + "', 'Indisponibilidad del Sistema')");
                                    FileInputStream in = new FileInputStream(temp);
                                    model.psPrepararSentencias.setBinaryStream(1, in, (int) temp.length());
                                    model.psPrepararSentencias.executeUpdate();
                                    model.psPrepararSentencias.close();
                                    
                                   
                                in.close();
        } catch (SQLException ex) {
            Logger.getLogger(Enviar_factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Enviar_factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Enviar_factura.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
            
        
        
        
        
        


    }
}
