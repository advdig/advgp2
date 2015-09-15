/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import conexion.conexion_facturacion;
import conexion.conexion_sis_contable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author r
 */ 
public class Tarea_contable implements Job {

    String contraseña, usuario;
    conexion_facturacion cf;
    conexion_sis_contable cc;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {

            org.quartz.JobDataMap properties = jec.getJobDetail().getJobDataMap();

            usuario = properties.getString("usuarios");
            contraseña = properties.getString("contrasena");

            cf = new conexion_facturacion(usuario, contraseña);
            cc = new conexion_sis_contable("useradv","adv111");

            cf.conectar();
            cc.conectar();

            crear_clientes();
            enviar_facturas();
          
            enviar_notas();

            


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tarea_contable.class.getName()).log(Level.SEVERE, null, ex);
        }






    }

    public void enviar_facturas() {
        String mp = "";

        try {


            Statement st_in = cf.coneccion.createStatement();
            ResultSet ri = st_in.executeQuery("SELECT\n"
                    + "     factura.idfactura,\n"
                    + "	    factura.`Estado_factura` AS factura_Estado_factura,\n"
                    + "     SUBSTRING(factura.`numero`,9) AS factura_numero,\n"
                    + "     factura.`fecha` AS factura_fecha,\n"
                    + "     factura.`hora` AS factura_hora,\n"
                    + "     datos_gasolinera.`secuencia1_factura` AS datos_gasolinera_ruc,\n"
                    + "     punto_emision.`s2` AS punto_emision_s2,\n"
                    + "     factura.`metodo_pago` AS factura_metodo_pago,\n"
                    + "     factura.`usuarios_idusuarios` AS factura_usuarios_idusuarios,\n"
                    + "     factura.`numero_autorizacion` AS factura_numero_autorizacion,\n"
                    + "     factura.`subtotal` AS factura_subtotal,\n"
                    + "     factura.`total` AS factura_total,\n"
                    + "     factura.`iva` AS factura_iva,\n"
                    + "     datos_gasolinera.`ruc` AS datos_gasolinera_ruc,\n"
                    + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso,\n"
                    + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                    + "     usuarios.`usuario` AS usuarios_usuario,\n"
                    + "     cliente.`idcliente` AS cliente_id\n"
                    + "FROM\n"
                    + "     `punto_emision` punto_emision INNER JOIN `factura` factura ON punto_emision.`idpunto_emision` = factura.`punto_emision_idpunto_emision`\n"
                    + "     AND punto_emision.`datos_gasolinera_iddatos_gasolinera` = factura.`datos_gasolinera_iddatos_gasolinera`\n"
                    + "     INNER JOIN `datos_gasolinera` datos_gasolinera ON punto_emision.`datos_gasolinera_iddatos_gasolinera` = datos_gasolinera.`iddatos_gasolinera`\n"
                    + "     INNER JOIN `clave_acceso` clave_acceso ON factura.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                    + "     INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                    + "     INNER JOIN `usuarios` usuarios ON factura.`usuarios_idusuarios` = usuarios.`idusuarios`\n"
                    + "where  punto_emision_idpunto_emision=idpunto_emision and enviado_contable=0 and (factura.Estado_factura='AUTORIZADO' or factura.Estado_factura='ANULADO') and numero_autorizacion > 0");


            System.out.println("SELECT\n"
                    + "     factura.idfactura,\n"
                    + "	    factura.`Estado_factura` AS factura_Estado_factura,\n"
                    + "     SUBSTRING(factura.`numero`,9) AS factura_numero,\n"
                    + "     factura.`fecha` AS factura_fecha,\n"
                    + "     factura.`hora` AS factura_hora,\n"
                    + "     datos_gasolinera.`secuencia1_factura` AS datos_gasolinera_ruc,\n"
                    + "     punto_emision.`s2` AS punto_emision_s2,\n"
                    + "     factura.`metodo_pago` AS factura_metodo_pago,\n"
                    + "     factura.`usuarios_idusuarios` AS factura_usuarios_idusuarios,\n"
                    + "     factura.`numero_autorizacion` AS factura_numero_autorizacion,\n"
                    + "     factura.`subtotal` AS factura_subtotal,\n"
                    + "     factura.`total` AS factura_total,\n"
                    + "     factura.`iva` AS factura_iva,\n"
                    + "     datos_gasolinera.`ruc` AS datos_gasolinera_ruc,\n"
                    + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso,\n"
                    + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                    + "     usuarios.`usuario` AS usuarios_usuario,\n"
                    + "     cliente.`idcliente` AS cliente_id\n"
                    + "FROM\n"
                    + "     `punto_emision` punto_emision INNER JOIN `factura` factura ON punto_emision.`idpunto_emision` = factura.`punto_emision_idpunto_emision`\n"
                    + "     AND punto_emision.`datos_gasolinera_iddatos_gasolinera` = factura.`datos_gasolinera_iddatos_gasolinera`\n"
                    + "     INNER JOIN `datos_gasolinera` datos_gasolinera ON punto_emision.`datos_gasolinera_iddatos_gasolinera` = datos_gasolinera.`iddatos_gasolinera`\n"
                    + "     INNER JOIN `clave_acceso` clave_acceso ON factura.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                    + "     INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                    + "     INNER JOIN `usuarios` usuarios ON factura.`usuarios_idusuarios` = usuarios.`idusuarios`\n"
                    + "where  punto_emision_idpunto_emision=idpunto_emision and enviado_contable=0 and (factura.Estado_factura='AUTORIZADO' or factura.Estado_factura='ANULADO') and numero_autorizacion > 0");



            String idcliente;


            while (ri.next()) {
                int ndetalle = 0;

                Statement st_fdc = cf.coneccion.createStatement();
                ResultSet ri_fdc = st_fdc.executeQuery("SELECT * FROM adv_facturacion.factura_detalle where factura_idfactura=" + ri.getInt(1) + " group by factura_detalle.producto_idproducto ;");
                while (ri_fdc.next()) {
                    ndetalle++;


                }


                Statement st_fd = cf.coneccion.createStatement();

                ResultSet ri_fd = st_fd.executeQuery("SELECT\n"
                        + "     sum(factura_detalle.`cantidad`) AS factura_detalle_cantidad,\n"
                        + "     sum(factura_detalle.`subtotal`) AS factura_detalle_subtotal,\n"
                        + "     sum(factura_detalle.`total`) AS factura_detalle_total,\n"
                        + "     factura_detalle.`configuracion_nmanguera` AS factura_detalle_configuracion_nmanguera,\n"
                        + "     sum(factura_detalle.`iva`) AS factura_detalle_iva,\n"
                        + "     producto.`nombre` AS producto_nombre,\n"
                        + "     configuracion.`surtidor` AS configuracion_surtidor,\n"
                        + "     configuracion.`tanques_idtanques` AS configuracion_tanques_idtanques,\n"
                        + "     producto.`punit` AS producto_punit\n"
                        + "FROM\n"
                        + "     `producto` producto INNER JOIN `factura_detalle` factura_detalle ON producto.`idproducto` = factura_detalle.`producto_idproducto`\n"
                        + "     INNER JOIN `configuracion` configuracion ON factura_detalle.`configuracion_nmanguera` = configuracion.`nmanguera`\n"
                        + "where factura_idfactura=" + ri.getInt(1) + " group by factura_detalle.producto_idproducto");





                System.out.println(ndetalle);

                //  idcliente = ri.getString(1);
                //System.out.println(idcliente);
                if (ri.getString(8).equalsIgnoreCase("contado")) {


                    mp = "EF";



                } else if (ri.getString(8).equalsIgnoreCase("TC")) {

                    mp = "TC";


                } else if (ri.getString(8).equalsIgnoreCase("Credito")) {

                    mp = "CR";


                }









                ResultSet ris = cc.consulta("select RUC from ESCliente where RUC='" + ri.getString(16) + "'");



                if (ris.first()) {

                    System.out.println("Cliente si existe");

                    Date fecha = ri.getDate(4);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");

                    String fechaConFormato = sdf.format(fecha);

                    System.out.println(ndetalle);


                    if (ndetalle == 1) {

                        String detalle = "";

                        while (ri_fd.next()) {
                            //System.out.println("INSERT INTO ESFCElectronica (Fecha,Hora,Establecimiento,PtoEmision,Secuencial,CodCliente,RUC,CodUsuario,Manguera,Dispensador,Tanque,Turno,Producto,Galones,Precio_de_Galon,Total,FormaCobro,ClaveAccesoSRI,AutorizacionSRI,FechaAutorizacionSRI,Estado,NumDocCobro,MsgResultado) VALUES ( CAST ( '" + fechaConFormato + "' AS DATETIME ),'" + ri.getString(5) + "','" + ri.getString(6) + "','" + ri.getString(7) + "'," + ri.getString(3) + ",'" + ri.getString(16) + "','" + ri.getString(14) + "','" + ri.getString(17) + "'," + ri_fd.getInt(4) + "," + ri_fd.getInt(7) + "," + ri_fd.getInt(8) + ",1,'" + ri_fd.getString(6) + "'," + ri_fd.getDouble(1) + "," + ri_fd.getDouble(9) + "," + ri_fd.getDouble(3) + ",'" + mp + "','" + ri.getString(15) + "','" + ri.getString(10) + "',CAST ( '" + fechaConFormato + "' AS DATETIME ),0,'0','grabado');");
                            PreparedStatement ic = cc.coneccion.prepareStatement("INSERT INTO ESFCElectronica (CodCliente,Fecha,Hora,Establecimiento,PtoEmision,Secuencial,RUC,CodUsuario,Manguera,Dispensador,Tanque,Turno,Producto,Galones,Precio_de_Galon,Total,FormaCobro,ClaveAccesoSRI,AutorizacionSRI,FechaAutorizacionSRI,Estado,NumDocCobro,MsgResultado,IVDetalle,SerieFuente) VALUES ('',CAST ( '" + fechaConFormato + "' AS DATETIME ),'" + ri.getString(5) + "','" + ri.getString(6) + "','" + ri.getString(7) + "'," + ri.getString(3) + ",'" + ris.getString(1) + "','" + ri.getString(17) + "'," + ri_fd.getInt(4) + "," + ri_fd.getInt(7) + "," + ri_fd.getInt(8) + ",1,'" + ri_fd.getString(6) + "'," + ri_fd.getDouble(1) + "," + ri_fd.getDouble(9) + "," + ri.getDouble(12) + ",'" + mp + "','" + ri.getString(15) + "','" + ri.getString(10) + "',CAST ( '" + fechaConFormato + "' AS DATETIME ),0,'0','','','');");

                            System.out.println("INSERT INTO ESFCElectronica (CodCliente,Fecha,Hora,Establecimiento,PtoEmision,Secuencial,RUC,CodUsuario,Manguera,Dispensador,Tanque,Turno,Producto,Galones,Precio_de_Galon,Total,FormaCobro,ClaveAccesoSRI,AutorizacionSRI,FechaAutorizacionSRI,Estado,NumDocCobro,MsgResultado,IVDetalle,SerieFuente) VALUES ('',CAST ( '" + fechaConFormato + "' AS DATETIME ),'" + ri.getString(5) + "','" + ri.getString(6) + "','" + ri.getString(7) + "'," + ri.getString(3) + ",'" + ris.getString(1) + "','" + ri.getString(17) + "'," + ri_fd.getInt(4) + "," + ri_fd.getInt(7) + "," + ri_fd.getInt(8) + ",1,'" + ri_fd.getString(6) + "'," + ri_fd.getDouble(1) + "," + ri_fd.getDouble(9) + "," + ri.getDouble(12) + ",'" + mp + "','" + ri.getString(15) + "','" + ri.getString(10) + "',CAST ( '" + fechaConFormato + "' AS DATETIME ),0,'0','','','');");
                            ic.execute();
                            PreparedStatement estadoF = cf.coneccion.prepareStatement("UPDATE `adv_facturacion`.`factura` SET enviado_contable=1 WHERE idfactura='" + ri.getInt(1) + "'");
                            estadoF.execute();



                        }
                    } else {

                        String detalle = "";
                        while (ri_fd.next()) {

                            
                            
                            detalle = detalle + ri_fd.getString(6) + "-" + ri_fd.getDouble(1) + "-" + ri_fd.getDouble(2) + ";";




                        }

                        
                        detalle=detalle.substring(0,detalle.length()-1); 

                        if (ri_fd.first()) {


                            //  System.out.println("INSERT INTO ESFCElectronica (Fecha,Hora,Establecimiento,PtoEmision,Secuencial,CodCliente,RUC,CodUsuario,Manguera,Dispensador,Tanque,Turno,Producto,Galones,Precio_de_Galon,Total,FormaCobro,ClaveAccesoSRI,AutorizacionSRI,FechaAutorizacionSRI,Estado,NumDocCobro,MsgResultado,IVDetalle) VALUES ( CAST ( '" + fechaConFormato + "' AS DATETIME ),'" + ri.getString(5) + "','" + ri.getString(6) + "','" + ri.getString(7) + "'," + ri.getString(3) + ",'" + ri.getString(16) + "','" + ri.getString(14) + "','" + ri.getString(17) + "'," + ri_fd.getInt(4) + "," + ri_fd.getInt(7) + "," + ri_fd.getInt(8) + ",1,' '," + ri_fd.getDouble(1) + "," + ri_fd.getDouble(9) + "," + ri_fd.getDouble(3) + ",'" + mp + "','" + ri.getString(15) + "','" + ri.getString(10) + "',CAST ( '" + fechaConFormato + "' AS DATETIME ),0,'0','grabado');");
                            PreparedStatement ic = cc.coneccion.prepareStatement("INSERT INTO ESFCElectronica (CodCliente,Fecha,Hora,Establecimiento,PtoEmision,Secuencial,RUC,CodUsuario,Manguera,Dispensador,Tanque,Turno,Producto,Galones,Precio_de_Galon,Total,FormaCobro,ClaveAccesoSRI,AutorizacionSRI,FechaAutorizacionSRI,Estado,NumDocCobro,MsgResultado,IVDetalle,SerieFuente) VALUES ('',CAST ( '" + fechaConFormato + "' AS DATETIME ),'" + ri.getString(5) + "','" + ri.getString(6) + "','" + ri.getString(7) + "'," + ri.getString(3) + ",'" + ris.getString(1) + "','" + ri.getString(17) + "'," + ri_fd.getInt(4) + "," + ri_fd.getInt(7) + "," + ri_fd.getInt(8) + ",1,''," + ri_fd.getDouble(1) + "," + ri_fd.getDouble(9) + "," + ri.getDouble(12) + ",'" + mp + "','" + ri.getString(15) + "','" + ri.getString(10) + "',CAST ( '" + fechaConFormato + "' AS DATETIME ),0,'0','','" + detalle + "','');");

                            System.out.println("INSERT INTO ESFCElectronica (CodCliente,Fecha,Hora,Establecimiento,PtoEmision,Secuencial,RUC,CodUsuario,Manguera,Dispensador,Tanque,Turno,Producto,Galones,Precio_de_Galon,Total,FormaCobro,ClaveAccesoSRI,AutorizacionSRI,FechaAutorizacionSRI,Estado,NumDocCobro,MsgResultado,IVDetalle,SerieFuente) VALUES ('',CAST ( '" + fechaConFormato + "' AS DATETIME ),'" + ri.getString(5) + "','" + ri.getString(6) + "','" + ri.getString(7) + "'," + ri.getString(3) + ",'" + ris.getString(1) + "','" + ri.getString(17) + "'," + ri_fd.getInt(4) + "," + ri_fd.getInt(7) + "," + ri_fd.getInt(8) + ",1,' '," + ri_fd.getDouble(1) + "," + ri_fd.getDouble(9) + "," + ri.getDouble(12) + ",'" + mp + "','" + ri.getString(15) + "','" + ri.getString(10) + "',CAST ( '" + fechaConFormato + "' AS DATETIME ),0,'0',' ','" + detalle + "','');");


                            ic.execute();
                            PreparedStatement estadoF = cf.coneccion.prepareStatement("UPDATE `adv_facturacion`.`factura` SET enviado_contable=1 WHERE idfactura='" + ri.getInt(1) + "'");
                            estadoF.execute();

                        }


                    }






                }







            }






        } catch (SQLException ex) {
            Logger.getLogger(enviar_facturas_sofi.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void enviar_notas() {


        String mp = null;

        System.out.println("Enviando Notas");

        try {
            Statement st_fd = cf.coneccion.createStatement();

            ResultSet ri_fd = st_fd.executeQuery("SELECT\n"
                    + "SUBSTRING(numero,1,3),"
                    + "SUBSTRING(numero,5,3),"
                    + "SUBSTRING(numero,9),"
                    + "nota_credito.`factura_idfactura` AS nota_credito_factura_idfactura,\n"
                    + "     usuarios.`usuario` AS usuarios_usuario,\n"
                    + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso,\n"
                    + "     nota_credito.`autorizacion` AS nota_credito_autorizacion\n"
                    + "FROM\n"
                    + "     `usuarios` usuarios INNER JOIN `nota_credito` nota_credito ON usuarios.`idusuarios` = nota_credito.`usuarios_idusuarios`\n"
                    + "     INNER JOIN `clave_acceso` clave_acceso ON nota_credito.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                    + "where   enviado_contable=0 and nota_credito.estado='AUTORIZADO' and autorizacion > 0");

            while (ri_fd.next()) {

                System.out.println(ri_fd.getString(4));

                Statement st_f = cf.coneccion.createStatement();

                ResultSet ri_f = st_f.executeQuery("SELECT\n"
                        + "     factura.idfactura,\n"
                        + "	factura.`Estado_factura` AS factura_Estado_factura,\n"
                        + "     SUBSTRING(factura.`numero`,9) AS factura_numero,\n"
                        + "     SUBSTRING(factura.`numero`,1,7) AS factura_numero,\n"
                        + "     factura.`fecha` AS factura_fecha,\n"
                        + "     factura.`hora` AS factura_hora,\n"
                        + "     factura.`metodo_pago` AS factura_metodo_pago,\n"
                        + "     factura.`usuarios_idusuarios` AS factura_usuarios_idusuarios,\n"
                        + "     factura.`numero_autorizacion` AS factura_numero_autorizacion,\n"
                        + "     factura.`subtotal` AS factura_subtotal,\n"
                        + "     factura.`total` AS factura_total,\n"
                        + "     factura.`iva` AS factura_iva,\n"
                        + "     datos_gasolinera.`ruc` AS datos_gasolinera_ruc,\n"
                        + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso,\n"
                        + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                        + "     usuarios.`usuario` AS usuarios_usuario,\n"
                        + "     cliente.`idcliente` AS cliente_id\n"
                        + "FROM\n"
                        + "     `punto_emision` punto_emision INNER JOIN `factura` factura ON punto_emision.`idpunto_emision` = factura.`punto_emision_idpunto_emision`\n"
                        + "     AND punto_emision.`datos_gasolinera_iddatos_gasolinera` = factura.`datos_gasolinera_iddatos_gasolinera`\n"
                        + "     INNER JOIN `datos_gasolinera` datos_gasolinera ON punto_emision.`datos_gasolinera_iddatos_gasolinera` = datos_gasolinera.`iddatos_gasolinera`\n"
                        + "     INNER JOIN `clave_acceso` clave_acceso ON factura.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                        + "     INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                        + "     INNER JOIN `usuarios` usuarios ON factura.`usuarios_idusuarios` = usuarios.`idusuarios`\n"
                        + "where  factura.idfactura=" + ri_fd.getInt(4) + " ");
                while (ri_f.next()) {


                    System.out.println(ri_f.getString(2));
                    Statement st_fde = cf.coneccion.createStatement();

                    ResultSet ri_fde = st_fde.executeQuery("SELECT\n"
                            + "     factura_detalle.`cantidad` AS factura_detalle_cantidad,\n"
                            + "     factura_detalle.`subtotal` AS factura_detalle_subtotal,\n"
                            + "     factura_detalle.`total` AS factura_detalle_total,\n"
                            + "     factura_detalle.`configuracion_nmanguera` AS factura_detalle_configuracion_nmanguera,\n"
                            + "     factura_detalle.`iva` AS factura_detalle_iva,\n"
                            + "     producto.`nombre` AS producto_nombre,\n"
                            + "     configuracion.`surtidor` AS configuracion_surtidor,\n"
                            + "     configuracion.`tanques_idtanques` AS configuracion_tanques_idtanques,\n"
                            + "     producto.`punit` AS producto_punit\n"
                            + "FROM\n"
                            + "     `producto` producto INNER JOIN `factura_detalle` factura_detalle ON producto.`idproducto` = factura_detalle.`producto_idproducto`\n"
                            + "     INNER JOIN `configuracion` configuracion ON factura_detalle.`configuracion_nmanguera` = configuracion.`nmanguera`\n"
                            + "where factura_idfactura=" + ri_fd.getInt(4) + "");


                    Date fecha = ri_f.getDate(5);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");

                    String fechaConFormato = sdf.format(fecha);





                    if (ri_f.getString(7).equalsIgnoreCase("contado")) {


                        mp = "EF";



                    } else if (ri_f.getString(7).equalsIgnoreCase("TC")) {

                        mp = "TC";


                    } else if (ri_f.getString(7).equalsIgnoreCase("Credito")) {

                        mp = "CR";


                    }

                    ResultSet ris = cc.consulta("select RUC from ESCliente where RUC='" + ri_f.getString(15) + "'");

                    System.out.println("select CodCliente from ESCliente where CodCliente='" + ri_f.getString(15) + "'");

                    if (ris.first()) {

                        System.out.println("cliente existe");

                        if (ri_fde.first()) {

                            System.out.println(ri_fde.getString(2));
                            //                                            System.out.println("INSERT INTO ESFCElectronica (Fecha,Hora,Establecimiento,PtoEmision,Secuencial,CodCliente,RUC,CodUsuario,Manguera,Dispensador,Tanque,Turno,Producto,Galones,Precio_de_Galon,Total,FormaCobro,ClaveAccesoSRI,AutorizacionSRI,FechaAutorizacionSRI,Estado,NumDocCobro,MsgResultado,SerieFuente,SecuencialFuente) VALUES ( CAST ( '" + fechaConFormato + "' AS DATETIME ),'" + ri_f.getString(6) + "','" + ri_fd.getString(1) + "','" + ri_fd.getString(2) + "'," + ri_fd.getString(3) + ",'" + ri_f.getString(15) + "','" + ri_f.getString(13) + "','" + ri_f.getString(16) + "'," + ri_fde.getInt(4) + "," + ri_fde.getInt(7) + "," + ri_fde.getInt(8) + ",1,'"+ri_fde.getString(6)+"'," + ri_fde.getDouble(1) + "," + ri_fde.getDouble(9) + "," + ri_fde.getDouble(3) + ",'" + mp + "','" + ri_fd.getString(6) + "','" + ri_fd.getString(7) + "',CAST ( '" + fechaConFormato + "' AS DATETIME ),0,'0','grabado','" + ri_f.getString(4) + "'," + ri_f.getString(3) + ");");
                            PreparedStatement ic = cc.coneccion.prepareStatement("INSERT INTO ESFCElectronica (CodCliente,Fecha,Hora,Establecimiento,PtoEmision,Secuencial,RUC,CodUsuario,Manguera,Dispensador,Tanque,Turno,Producto,Galones,Precio_de_Galon,Total,FormaCobro,ClaveAccesoSRI,AutorizacionSRI,FechaAutorizacionSRI,Estado,NumDocCobro,MsgResultado,SerieFuente,SecuencialFuente,IVDetalle) VALUES (' ',CAST ( '" + fechaConFormato + "' AS DATETIME ),'" + ri_f.getString(6) + "','" + ri_fd.getString(1) + "','" + ri_fd.getString(2) + "'," + ri_fd.getString(3) + ",'" + ris.getString(1) + "','" + ri_f.getString(16) + "'," + ri_fde.getInt(4) + "," + ri_fde.getInt(7) + "," + ri_fde.getInt(8) + ",1,'" + ri_fde.getString(6) + "'," + ri_fde.getDouble(1) + "," + ri_fde.getDouble(9) + "," + ri_fde.getDouble(3) + ",'" + mp + "','" + ri_fd.getString(6) + "','" + ri_fd.getString(7) + "',CAST ( '" + fechaConFormato + "' AS DATETIME ),0,'0',' ','" + ri_f.getString(4).replace("-", "") + "'," + ri_f.getString(3) + ",' ');");

                            System.out.println("nota INSERT INTO ESFCElectronica (CodCliente,Fecha,Hora,Establecimiento,PtoEmision,Secuencial,RUC,CodUsuario,Manguera,Dispensador,Tanque,Turno,Producto,Galones,Precio_de_Galon,Total,FormaCobro,ClaveAccesoSRI,AutorizacionSRI,FechaAutorizacionSRI,Estado,NumDocCobro,MsgResultado,SerieFuente,SecuencialFuente,IVDetalle) VALUES (' ',CAST ( '" + fechaConFormato + "' AS DATETIME ),'" + ri_f.getString(6) + "','" + ri_fd.getString(1) + "','" + ri_fd.getString(2) + "'," + ri_fd.getString(3) + ",'" + ris.getString(1) + "','" + ri_f.getString(16) + "'," + ri_fde.getInt(4) + "," + ri_fde.getInt(7) + "," + ri_fde.getInt(8) + ",1,'" + ri_fde.getString(6) + "'," + ri_fde.getDouble(1) + "," + ri_fde.getDouble(9) + "," + ri_fde.getDouble(3) + ",'" + mp + "','" + ri_fd.getString(6) + "','" + ri_fd.getString(7) + "',CAST ( '" + fechaConFormato + "' AS DATETIME ),0,'0',' ','" + ri_f.getString(4).replace("-", "") + "'," + ri_f.getString(3) + "),' ';");
                            ic.execute();
                            PreparedStatement estadoF = cf.coneccion.prepareStatement("UPDATE `adv_facturacion`.`nota_credito` SET enviado_contable=1 WHERE factura_idfactura='" + ri_fd.getInt(4) + "'");
                            estadoF.execute();


                        }








                    } else {




                        String codigo = "";
                        Statement st_c = cf.coneccion.createStatement();
                        ResultSet rc = st_c.executeQuery("SELECT nombre,cedula_ruc,direccion,telefono,email,tipo_identificacion,(select codigo from codigos where cliente_idcliente=idcliente and  idcliente ='" + ri_f.getInt(17) + "') FROM adv_facturacion.cliente,adv_facturacion.codigos  WHERE  cedula_ruc ='" + ri_f.getString(15) + "' group by cedula_ruc");

                        while (rc.next()) {
                            System.out.println("Cliente no existe");

                            System.out.println(rc.getString(2));
                            System.out.println(rc.getString(6).toUpperCase().substring(0, 1));
                            System.out.println(ri_f.getString(5));
                            System.out.println(rc.getString(1));
                            System.out.println(rc.getString(3));
                            System.out.println(rc.getString(4));
                            System.out.println(rc.getString(5));
                            System.out.println(rc.getString(7));

                            if (rc.getString(7) == null) {

                                codigo = "";

                            }

                            System.out.println("INSERT INTO ESCliente (CodCliente,TipoDoc,Ruc,Nombre,Direccion,Telefono,Email,CodigoAdhesivo,CodigoTag)VALUES ('" + rc.getString(2) + "','" + rc.getString(6).toUpperCase().substring(0, 1) + "','" + rc.getString(2) + "','" + rc.getString(1) + "','" + rc.getString(3) + "','" + rc.getString(4) + "','" + rc.getString(5) + "','" + codigo + "','" + codigo + "');");

                            codigo = rc.getString(7);

                            PreparedStatement ic = cc.coneccion.prepareStatement("INSERT INTO ESCliente (CodCliente,TipoDoc,Ruc,Nombre,Direccion,Telefono,Email,CodigoAdhesivo,CodigoTag)VALUES ('" + rc.getString(2) + "','" + rc.getString(6).toUpperCase().substring(0, 1) + "','" + rc.getString(13) + "','" + rc.getString(1) + "','" + rc.getString(3) + "','" + rc.getString(4) + "','" + rc.getString(5) + "','" + codigo + "','" + codigo + "');");


                            ic.execute();

                           // enviar_notas();





                        }









                    }





                }

            }




            cf.coneccion.close();
            cc.coneccion.close();

        } catch (SQLException ex) {
            Logger.getLogger(Tarea_contable.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

    public void crear_clientes() {
        try {
    

            Statement st_in = cf.coneccion.createStatement();
            ResultSet ri = st_in.executeQuery("SELECT\n"
                    + "     factura.idfactura,\n"
                    + "	    factura.`Estado_factura` AS factura_Estado_factura,\n"
                    + "     SUBSTRING(factura.`numero`,9) AS factura_numero,\n"
                    + "     factura.`fecha` AS factura_fecha,\n"
                    + "     factura.`hora` AS factura_hora,\n"
                    + "     datos_gasolinera.`secuencia1_factura` AS datos_gasolinera_ruc,\n"
                    + "     punto_emision.`s2` AS punto_emision_s2,\n"
                    + "     factura.`metodo_pago` AS factura_metodo_pago,\n"
                    + "     factura.`usuarios_idusuarios` AS factura_usuarios_idusuarios,\n"
                    + "     factura.`numero_autorizacion` AS factura_numero_autorizacion,\n"
                    + "     factura.`subtotal` AS factura_subtotal,\n"
                    + "     factura.`total` AS factura_total,\n"
                    + "     factura.`iva` AS factura_iva,\n"
                    + "     datos_gasolinera.`ruc` AS datos_gasolinera_ruc,\n"
                    + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso,\n"
                    + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                    + "     usuarios.`usuario` AS usuarios_usuario,\n"
                    + "     cliente.`idcliente` AS cliente_id\n"
                    + "FROM\n"
                    + "     `punto_emision` punto_emision INNER JOIN `factura` factura ON punto_emision.`idpunto_emision` = factura.`punto_emision_idpunto_emision`\n"
                    + "     AND punto_emision.`datos_gasolinera_iddatos_gasolinera` = factura.`datos_gasolinera_iddatos_gasolinera`\n"
                    + "     INNER JOIN `datos_gasolinera` datos_gasolinera ON punto_emision.`datos_gasolinera_iddatos_gasolinera` = datos_gasolinera.`iddatos_gasolinera`\n"
                    + "     INNER JOIN `clave_acceso` clave_acceso ON factura.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                    + "     INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                    + "     INNER JOIN `usuarios` usuarios ON factura.`usuarios_idusuarios` = usuarios.`idusuarios`\n"
                    + "where  punto_emision_idpunto_emision=idpunto_emision and enviado_contable=0 and (factura.Estado_factura='AUTORIZADO' or factura.Estado_factura='ANULADO') and numero_autorizacion > 0");
              while (ri.next()) {


            
            ResultSet ris = cc.consulta("select RUC from ESCliente where RUC='" + ri.getString(16) + "'");

            if (ris.first()) {
           
            }
            
            
            else{




                String codigo = "";
                Statement st_c = cf.coneccion.createStatement();
                ResultSet rc = st_c.executeQuery("SELECT nombre,cedula_ruc,direccion,telefono,email,tipo_identificacion,(select codigo from codigos where cliente_idcliente=idcliente and  idcliente ='" + ri.getInt(18) + "' limit 1) FROM adv_facturacion.cliente,adv_facturacion.codigos  WHERE  cedula_ruc ='" + ri.getString(16) + "' group by cedula_ruc");

                while (rc.next()) {
                    System.out.println("Cliente no existe");

                    System.out.println(rc.getString(2));
                    System.out.println(rc.getString(6).toUpperCase().substring(0, 1));
                    System.out.println(ri.getString(6));
                    System.out.println(rc.getString(1));
                    System.out.println(rc.getString(3));
                    System.out.println(rc.getString(4));
                    System.out.println(rc.getString(5));
                    System.out.println(rc.getString(7));
                    codigo = rc.getString(7);
                    if (codigo == null || codigo.length() == 0) {

                        codigo = "";

                    }

                    System.out.println("INSERT INTO ESCliente (CodCliente,TipoDoc,Ruc,Nombre,Direccion,Telefono,Email,CodigoAdhesivo,CodigoTag)VALUES ('" + rc.getString(2) + "','" + rc.getString(6).toUpperCase().substring(0, 1) + "','" + ri.getString(14) + "','" + rc.getString(1) + "','" + rc.getString(3) + "','" + rc.getString(4) + "','" + rc.getString(5) + "','" + codigo + "','" + codigo + "');");



                    PreparedStatement ic = cc.coneccion.prepareStatement("INSERT INTO ESCliente (CodCliente,TipoDoc,Ruc,Nombre,Direccion,Telefono,Email,CodigoAdhesivo,CodigoTag)VALUES ('" + rc.getString(2) + "','" + rc.getString(6).toUpperCase().substring(0, 1) + "','" + rc.getString(2) + "','" + rc.getString(1) + "','" + rc.getString(3) + "','" + rc.getString(4) + "','" + rc.getString(5) + "','" + codigo + "','" + codigo + "');");


                    ic.execute();




                }
                
                
                
            }
           
        }
        } catch (SQLException ex) {
            Logger.getLogger(Tarea_contable.class.getName()).log(Level.SEVERE, null, ex);
        }




    }
}

