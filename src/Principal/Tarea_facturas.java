/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import conexion.conexion_facturacion;
import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author r
 */
public class Tarea_facturas implements Job {

    String[] producto, ids;
    Double[] totalp, subtotalp, cantidadp, ivap, punit;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {


        int cp = 0;




        String usu, contra;
        Double total = null, subtotal = null, iva = null, cantidad;






        // JOptionPane.showMessageDialog(null, "Tarea programada exitosamente con la expresion: ");
        org.quartz.JobDataMap properties = jec.getJobDetail().getJobDataMap();



        usu = properties.getString("usuarios");
        contra = properties.getString("contrasena");


        try {

            System.out.println("Enviando Pagares");

            conexion_facturacion conf = new conexion_facturacion(usu, contra);
            conf.conectar();



            Statement clien = conf.coneccion.createStatement();
            ResultSet rclien = clien.executeQuery("SELECT pagare.`cliente_idcliente` AS pagare_cliente_idcliente,cliente.`cedula_ruc` AS cliente_cedula_ruc FROM `cliente` cliente INNER JOIN `pagare` pagare ON cliente.`idcliente` = pagare.`cliente_idcliente` where facturado=0 group by cliente_idcliente");


            while (rclien.next()) {


                cp=0;
                System.out.println("rclien " + rclien.getString(1));

                Statement spa = conf.coneccion.createStatement();
                ResultSet rcpa = spa.executeQuery("SELECT Nombre FROM adv_facturacion.pagare,adv_facturacion.configuracion,adv_facturacion.producto where configuracion_nmanguera=nmanguera and producto_idproducto=idproducto and facturado=0 and pagare.cliente_idcliente=" + rclien.getInt(1) + " group by nombre;");
                while (rcpa.next()) {

                    cp++;


                }
                System.out.println("cp " + cp);

                producto = new String[cp];
                ids = new String[cp];
                totalp = new Double[cp];
                subtotalp = new Double[cp];
                cantidadp = new Double[cp];
                ivap = new Double[cp];
                punit = new Double[cp];


                System.out.println("2");

                int i = 0;
                Statement spa1 = conf.coneccion.createStatement();
                ResultSet rcpa1 = spa1.executeQuery("SELECT sum(subtotal),sum(total),sum(cantidad),sum(iva),nombre,punit,idproducto FROM adv_facturacion.pagare,adv_facturacion.configuracion,adv_facturacion.producto where configuracion_nmanguera=nmanguera and producto_idproducto=idproducto and facturado=0 and pagare.cliente_idcliente=" + rclien.getInt(1) + " group by nombre;");
                while (rcpa1.next()) {

                    System.out.println("entro");
                    producto[i] = rcpa1.getString(5);
                    totalp[i] = rcpa1.getDouble(2);
                    subtotalp[i] = rcpa1.getDouble(1);
                    cantidadp[i] = rcpa1.getDouble(3);
                    ivap[i] = rcpa1.getDouble(4);
                    punit[i] = rcpa1.getDouble(6);
                    ids[i] = rcpa1.getString(7);
                    
                    System.out.println("salio");

                  
                    i++;
                }

                System.out.println("1");


                Statement stot = conf.coneccion.createStatement();
                ResultSet rctot = stot.executeQuery("SELECT sum(subtotal),sum(total),sum(cantidad),sum(iva) FROM adv_facturacion.pagare,adv_facturacion.configuracion,adv_facturacion.producto where configuracion_nmanguera=nmanguera and producto_idproducto=idproducto and facturado=0 and pagare.cliente_idcliente=" + rclien.getInt(1) + " group by pagare.cliente_idcliente;");
                while (rctot.next()) {

                    total = rctot.getDouble(2);
                    subtotal = rctot.getDouble(1);
                    cantidad = rctot.getDouble(3);
                    iva = rctot.getDouble(4);

                }







                System.out.println("enviando");

                Enviar_factura ef = new Enviar_factura(usu, contra, producto, cantidadp, punit, ivap, subtotalp, totalp, total, subtotal, iva, rclien.getString(2), ids);
                ef.ejecutar();



            }





            //   Enviar_factura ef=new Enviar_factura(usu,contra,producto,cantidadp,punit,ivap,subtotalp,);

            conf.coneccion.close();



        } catch (Exception ex) {
            Logger.getLogger(Surtidores.class.getName()).log(Level.SEVERE, null, ex);
        } 






    }
}
