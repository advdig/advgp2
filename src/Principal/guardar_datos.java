/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import conexion.Conecciones;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.log4j.Priority;

/**
 *
 * @author r
 */
public class guardar_datos {

    
     org.apache.log4j.Logger loge = org.apache.log4j.Logger.getLogger(guardar_datos.class);
    
    String fecha, hora, producto, usuario;
    double subtotal, total, iva;
    String cliente, tp;
    int idcliente, ns;
    String cantidad, estadox, motivox;
    File archivox;
    int manguera;
    public String usu, contra, numerof;
    int ifactu = 0;
    int temi;
    int idcla;
    int clavea = 0;
    Conecciones he;
     Connection n = null;
    DataSource data;

    public guardar_datos(String tp, int idc, int te, File archivo, String estado, String motivo, int nsurtidor, String numerofa, String f, String h, double sub, double tot, double iv, int man, int cli, String usu, String prod, String cantida) throws SQLException {


        this.tp = tp;
        idcla = idc;
        temi = te;
        estadox = estado;
        motivox = motivo;
        archivox = archivo;
        ns = nsurtidor;
        fecha = f;
        hora = h;
        subtotal = sub;
        total = tot;
        iva = iv;
        manguera = man;
        idcliente = cli;
        usuario = usu;
        producto = prod;
        cantidad = cantida;
        numerof = numerofa;

        
            
         he = new Conecciones(usuario, contra);
        try {
            data=he.ConectarMysql();
           //cantidad=Double.parseDouble(cantida);
        } catch (ClassNotFoundException ex) {
            loge.log(Priority.ERROR,getStackTrace(ex));
        }



    }

    public static String getStackTrace(final Throwable throwable) {
     final StringWriter sw = new StringWriter();
     final PrintWriter pw = new PrintWriter(sw, true);
     throwable.printStackTrace(pw);
     return sw.getBuffer().toString();
}
    
    
    public void grabar() {
        try {

           

            
         
              
            n = data.getConnection(usuario, contra);
            
            
            int idu = 0, idp = 0, idc, idf = 0;


           
            Statement st_d = n.createStatement();
            ResultSet rid = st_d.executeQuery("SELECT idusuarios from usuarios where usuario='" + usuario + "'");




            Statement st_f = n.createStatement();
            ResultSet rif = st_f.executeQuery("select curdate();");


            while (rif.next()) {

                fecha = rif.getString(1);
            }


            while (rid.next()) {

                idu = rid.getInt(1);
            }




            Statement st_p = n.createStatement();
            ResultSet idpro = st_p.executeQuery("SELECT idproducto,punit from producto where nombre='" + producto + "'");


            Double punit = null;
            
            System.out.println("pro" + producto);

            while (idpro.next()) {

                idp = idpro.getInt(1);

                punit=idpro.getDouble(2);




            }

            System.out.println("Cliente " + idcliente);





            PreparedStatement factura = n.prepareStatement("INSERT INTO `adv_facturacion`.`factura` (`Estado_factura`,`numero`, `fecha`, `hora`,`metodo_pago`, `cliente_idcliente`, `usuarios_idusuarios`, `datos_gasolinera_iddatos_gasolinera`,`subtotal`,`total`,`iva`,`punto_emision_idpunto_emision`) VALUES ('','" + numerof + "', '" + fecha + "', '" + hora + "','" + tp + "', '" + idcliente + "', '" + idu + "','1','" + subtotal + "','" + total + "','" + iva + "',1);");

            factura.execute();




            Statement st_idfac = n.createStatement();
            ResultSet idfac = st_idfac.executeQuery("SELECT idfactura  FROM adv_facturacion.factura where numero='" + numerof + "';");


            if (idfac.first()) {

                ifactu = idfac.getInt(1);
            }


            PreparedStatement factura_detalle = n.prepareStatement("INSERT INTO `adv_facturacion`.`factura_detalle` (`cantidad`, `subtotal`, `total`,`iva` ,`factura_idfactura`, `configuracion_nmanguera`,`producto_idproducto`,p_unit,detalle_adicional) VALUES ('" + cantidad + "', '" + subtotal + "', '" + total + "','" + iva + "', '" + ifactu + "','" + manguera + "',"+idp+","+punit+",'Manguera "+ manguera +"');");

            factura_detalle.execute();









            n.close();
            System.out.println("factura grabada correctamente");
        } catch (SQLException ex) {
            loge.log(Priority.ERROR,getStackTrace(ex));
        }



    }

     public void actualizar(String nAuto, String cadena, String nfactura, String idfact) throws SQLException, FileNotFoundException {
        try {

            
            n = data.getConnection(usuario, contra);
           


            if (temi == 1) {

                Statement st_clave = n.createStatement();
                ResultSet idclave = st_clave.executeQuery("SELECT idclave_acceso from clave_acceso where clave_acceso='" + cadena + "'");





                if (idclave.first()) {

                    clavea = idclave.getInt(1);

                }

                
                
                
                
                
            } else {






                clavea = idcla;
            }


            System.out.println(clavea);
            
            
              Statement st_id = n.createStatement();
              ResultSet id = st_id.executeQuery("SELECT idfactura from factura where numero='" + nfactura + "'");





                if (id.first()) {

                    idfact = id.getString(1);

                   
                    
                }

                
                 System.out.println("idfactura"+idfact);
                
                
            PreparedStatement cli = n.prepareStatement("UPDATE `adv_facturacion`.`factura` SET `Estado_factura`='" + estadox + "',`clave_acceso_idclave_acceso`='" + clavea + "',`numero_autorizacion`='" + nAuto + "' WHERE `numero`='" + nfactura + "';");

            cli.execute();



            if (estadox.equalsIgnoreCase("NO AUTORIZADO")) {

                FileInputStream in = new FileInputStream(archivox);
                long datos = archivox.lastModified();


                PreparedStatement myStatement = n.prepareStatement("INSERT INTO adv_xml.xml_no_autorizados(doc_xml, xml_factura,motivo_no_autorizado)VALUES(?, '" + idfact + "','" + motivox + "')");

                myStatement.setBinaryStream(1, in, (int) archivox.length());
                myStatement.executeUpdate();

                in.close();



            }

            if (estadox.equalsIgnoreCase("AUTORIZADO")) {

                FileInputStream in = new FileInputStream(archivox);



                PreparedStatement myStatement = n.prepareStatement("INSERT INTO adv_xml.xml_enviados_autorizados(doc_xml, xml_factura,tipo_doc) VALUES(?, '" + idfact + "','factura')");

                myStatement.setBinaryStream(1, in, (int) archivox.length());
                myStatement.executeUpdate();


                in.close();


            }
            if (estadox.equalsIgnoreCase("Contingencia")) {




                FileInputStream in = new FileInputStream(archivox);
                long datos = archivox.lastModified();


                PreparedStatement myStatement = n.prepareStatement("INSERT INTO adv_xml.xml_contingencia(xml_contingencia, xml_factura,motivo)VALUES(?, '" + idfact + "','" + motivox + "')");

                myStatement.setBinaryStream(1, in, (int) archivox.length());
                myStatement.executeUpdate();


                in.close();




            }
            
            
            
            if (estadox.equalsIgnoreCase("Consumidor Final")) {
            
                FileInputStream in = new FileInputStream(archivox);
                long datos = archivox.lastModified();


                PreparedStatement myStatement = n.prepareStatement("INSERT INTO adv_xml.consumidores_finales(xml,idfactura)VALUES(?, '" + idfact + "')");

                myStatement.setBinaryStream(1, in, (int) archivox.length());
                myStatement.executeUpdate();


                in.close();
            
            
            }
       } catch (IOException ex) {
            loge.log(Priority.ERROR,getStackTrace(ex));
        }


    }

    public void usuarios(String usuario, String contraseña) {

        contra = contraseña;
        usu = usuario;
        he = new Conecciones(usuario, contra);
    }
}
