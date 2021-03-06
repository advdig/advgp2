/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Adv
 */
package Principal;

import conexion.ConnectionTableDB;
import conexion.conexion_facturacion;
import java.io.BufferedWriter;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.PrinterName;

public class impresion {

    conexion_facturacion he = new conexion_facturacion("root", "manager");
    ConnectionTableDB model;
    DecimalFormat df = new DecimalFormat("#.##");

    public impresion() {
    }

    public void generar(String num, String mang, String usu, String contra, int t) throws SQLException, IOException {
        PrintWriter ps = null;


        model = new ConnectionTableDB(usu, contra, "adv_facturacion", " ", false);

        model.consulta("SELECT\n"
                + "                         datos_gasolinera.`razon_social` AS datos_gasolinera_razon_social,\n"
                + "                         datos_gasolinera.`ruc` AS datos_gasolinera_ruc,\n"
                + "                         datos_gasolinera.`direccion` AS datos_gasolinera_direccion,\n"
                + "                         datos_gasolinera.`nombre_comercial` AS datos_gasolinera_nombre_comercial,\n"
                + "                         datos_gasolinera.`contribuyente_especial` AS datos_gasolinera_contribuyente_especial,\n"
                + "                         IF(datos_gasolinera.`tipo_ambiente`=1,'Pruebas','Produccion' )AS 	datos_gasolinera_tipo_ambiente,\n"
                + "                         datos_gasolinera.`obligado_llevar_contabilidad` AS datos_gasolinera_obligado_llevar_contabilidad,\n"
                + "                         factura.`numero` AS factura_numero,\n"
                + "                         factura.`fecha` AS factura_fecha,\n"
                + "                         factura.`hora` AS factura_hora,\n"
                + "                         factura.`numero_autorizacion` AS factura_numero_autorizacion,\n"
                + "                         factura_detalle.`cantidad` AS factura_detalle_cantidad,\n"
                + "                         factura_detalle.`subtotal` AS factura_detalle_subtotal,\n"
                + "                         factura_detalle.`total` AS factura_detalle_total,\n"
                + "                         factura_detalle.`iva` AS factura_detalle_iva,\n"
                + "                         producto.`nombre` AS producto_nombre,\n"
                + "                         format(factura_detalle.`subtotal`/ factura_detalle.`cantidad`,3) as producto_punit,\n"
                + "                         cliente.`nombre` AS cliente_nombre,\n"
                + "                         cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                + "                         clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso,\n"
                + "                         clave_acceso.`tipo` AS clave_acceso_tipo,\n"
                + "                         producto.`idproducto` AS producto_idproducto,\n"
                + "                         datos_gasolinera.nombre_comercial,   \n"
                + "                         factura_detalle.`configuracion_nmanguera` AS nmanguera    \n"
                + "                    FROM\n"
                + "                         `datos_gasolinera` datos_gasolinera INNER JOIN `factura` factura ON datos_gasolinera.`iddatos_gasolinera` = factura.`datos_gasolinera_iddatos_gasolinera`\n"
                + "                         INNER JOIN `factura_detalle` factura_detalle ON factura.`idfactura` = factura_detalle.`factura_idfactura`\n"
                + "                         INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                + "                         INNER JOIN `clave_acceso` clave_acceso ON factura.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                + "                         INNER JOIN `configuracion` configuracion ON factura_detalle.`configuracion_nmanguera` = configuracion.`nmanguera`\n"
                + "                         INNER JOIN `producto` producto ON factura_detalle.`producto_idproducto` = producto.`idproducto`\n"
                + "                    where factura.numero= '" + num + "'");
        FileWriter fichero = null;
        try {

            fichero = new FileWriter("rides_manuales\\" + num + ".txt");
            BufferedWriter buffer = new BufferedWriter(fichero);
            ps = new PrintWriter(buffer);

// System.out.println(ridf.getString(2));
            setFormato(2, ps);

            char[] ESC_CUT_PAPER = new char[]{0x1B, 'R', (char) 7};
            ps.write(ESC_CUT_PAPER);

            ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 24};
            ps.write(ESC_CUT_PAPER);

            ps.println(model.getValueAt(0, 22).toString().replace("ñ", "|"));

            ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 1};
            ps.write(ESC_CUT_PAPER);


            ps.println("RUC:          " + model.getValueAt(0, 1).toString());
            ps.println("RAZON SOCIAL: " + model.getValueAt(0, 0).toString().replace("ñ", "|"));
            ps.println("DIRECCION:    " + model.getValueAt(0, 2).toString());
            // ps.println("Contribuyente Especial #: " + ridf.getString(5));
            ps.println("Obligado a llevar Contabilidad:" + model.getValueAt(0, 6).toString());
            ps.println("Factura #:    " + model.getValueAt(0, 7).toString());
            ps.println("Razon Social/Nombres y Apellidos:");
            ps.println(model.getValueAt(0, 17).toString().replace("ñ", "|"));
            ps.println("Ruc/CI: " + model.getValueAt(0, 18).toString());
            ps.println("Fecha Emision: " + model.getValueAt(0, 8).toString());


            double subt = 0;
            double tot = 0;
            ps.println("-------------------");

            for (int i = 0; i < model.getRowCount(); i++) {
                ps.println("Cod Principal: " + model.getValueAt(i, 21).toString());
                ps.println("Cantidad:      " + model.getValueAt(i, 11).toString());
                ps.println("Descripcion:   " + model.getValueAt(i, 15).toString());
                ps.println("Precio Unitario:" + model.getValueAt(i, 16).toString());
                ps.println("Precio Total:  " + model.getValueAt(i, 12).toString());
                ps.println("-------------------");
                subt = subt + Double.parseDouble(model.getValueAt(i, 12).toString());
                tot = tot + Double.parseDouble(model.getValueAt(i, 12).toString());

            }
            ps.println("Subtotal 12%: " + df.format(subt).replace(",", "."));

            ps.println("IVA 12%:      " + df.format(subt * 0.12).replace(",", "."));
            ps.println("Valor Total:  " + df.format(tot + (tot * 0.12)).replace(",", "."));



            ps.println("-------------------");
            ps.println("CLAVE DE ACCESO: ");
            ps.println(model.getValueAt(0, 19).toString());
            ps.println("AUTORIZACION S.R.I.: ");
            ps.println(model.getValueAt(0, 10).toString());
            ps.println("FECHA Y HORA AUTORIZACION:");
            if (model.getValueAt(0, 10).toString().length() == 0) {
            } else {
                ps.println(model.getValueAt(0, 8) + " " + model.getValueAt(0, 9).toString());
            }
            String ambiente = "";
            if (t == 0) {

                ambiente = "NORMAL";

            } else if (t == 1) {

                ambiente = "CONTINGENCIA";

            }

            ps.println("AMBIENTE:" + model.getValueAt(0, 5).toString() + " EMISION: " + ambiente);
            ps.println("-------------------");



            ps.println(" SISTEMA ADV-BOX -ADVANTECH");
            ps.println("  2829421 - 0999064457");
            if (ambiente.equalsIgnoreCase("CONTINGENCIA")) {
                ps.println("ESTA FACTURA FUE EMITIDA EN CONTINGENCIA");
                ps.println("USTED PODRA CONSULTAR SU FACTURA");
                ps.println("AUTORIZADA EN 24 HORAS EN LA ");
                ps.println("PAGINA WEB DEL SRI");
                ps.println("/COMPROBANTES ELECTRONICOS");
                ps.println("http://www.sri.gob.ec");
            }
            ps.println("                                     ");
            ps.println("                                     ");
            ps.println("                                     ");
            correr(5, ps);
            cortar(ps);
            ps.close();



            imprimir(num, mang, "factura");
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public void generarNota(String num, String mang, String usu, String contra, int s) throws SQLException, IOException {
        PrintWriter ps = null;


        model = new ConnectionTableDB(usu, contra, "adv_facturacion", " ", false);

        model.consulta("select * from datos_gasolinera  where iddatos_gasolinera=1");
        FileWriter fichero = null;
        try {

            fichero = new FileWriter("rides_manuales\\nc" + num + ".txt");
            BufferedWriter buffer = new BufferedWriter(fichero);
            ps = new PrintWriter(buffer);

// System.out.println(ridf.getString(2));
            setFormato(2, ps);

            char[] ESC_CUT_PAPER = new char[]{0x1B, 'R', (char) 7};
            ps.write(ESC_CUT_PAPER);

            ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 24};
            ps.write(ESC_CUT_PAPER);

            ps.println(model.getValueAt(0, 9).toString().replace("ñ", "|"));

            ESC_CUT_PAPER = new char[]{0x1B, '!', (char) 1};
            ps.write(ESC_CUT_PAPER);



            ps.println("RUC:          " + model.getValueAt(0, 2).toString());
            ps.println("RAZON SOCIAL: " + model.getValueAt(0, 1).toString());
            ps.println("DIRECCION:    " + model.getValueAt(0, 3).toString());
            if (!model.getValueAt(0, 10).toString().equals("0")) {
                ps.println("Contribuyente Especial #: SI");

            }

            ps.println("Obligado a llevar Contabilidad:" + model.getValueAt(0, 8).toString());


            System.out.println("ride de la nota" + num);





            he.conectar();



            System.out.println("SELECT\n"
                    + "     nota_credito.`motivo` AS nota_credito_motivo,\n"
                    + "     nota_credito.`estado` AS nota_credito_estado,\n"
                    + "     nota_credito.`autorizacion` AS nota_credito_autorizacion,\n"
                    + "     nota_credito.`numero` AS nota_credito_numero,\n"
                    + "     nota_credito.`fecha` AS nota_credito_fecha,\n"
                    + "     nota_credito.`hora` AS nota_credito_hora,\n"
                    + "     nota_credito.`subtotal` AS nota_credito_subtotal,\n"
                    + "     nota_credito.`valor_modificacion` AS nota_credito_valor_modificacion,\n"
                    + "     nota_credito.`iva` AS nota_credito_iva,\n"
                    + "     factura.`numero` AS factura_numero,\n"
                    + "     cliente.`nombre` AS cliente_nombre,\n"
                    + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                    + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso\n"
                    + "FROM\n"
                    + "     `factura` factura INNER JOIN `nota_credito` nota_credito ON factura.`idfactura` = nota_credito.`factura_idfactura`\n"
                    + "     AND factura.`usuarios_idusuarios` = nota_credito.`usuarios_idusuarios`\n"
                    + "     AND factura.`cliente_idcliente` = nota_credito.`factura_cliente_idcliente`\n"
                    + "     INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                    + "     INNER JOIN `clave_acceso` clave_acceso ON factura.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                    + "     AND clave_acceso.`idclave_acceso` = nota_credito.`clave_acceso_idclave_acceso`   "
                    + " where nota_credito.`numero`='" + num + "'");


            Statement nota = he.coneccion.createStatement();
            ResultSet rnota = nota.executeQuery("SELECT\n"
                    + "     nota_credito.`motivo` AS nota_credito_motivo,\n"
                    + "     nota_credito.`estado` AS nota_credito_estado,\n"
                    + "     nota_credito.`autorizacion` AS nota_credito_autorizacion,\n"
                    + "     nota_credito.`numero` AS nota_credito_numero,\n"
                    + "     nota_credito.`fecha` AS nota_credito_fecha,\n"
                    + "     nota_credito.`hora` AS nota_credito_hora,\n"
                    + "     nota_credito.`subtotal` AS nota_credito_subtotal,\n"
                    + "     nota_credito.`valor_modificacion` AS nota_credito_valor_modificacion,\n"
                    + "     nota_credito.`iva` AS nota_credito_iva,\n"
                    + "     factura.`numero` AS factura_numero,\n"
                    + "     cliente.`nombre` AS cliente_nombre,\n"
                    + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                    + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso\n"
                    + "FROM\n"
                    + "`nota_credito` nota_credito INNER JOIN `nota_credito_detalle` nota_credito_detalle ON nota_credito.`idnota_credito` = nota_credito_detalle.`nota_credito_idnota_credito`\n"
                    + "     INNER JOIN `factura` factura ON nota_credito.`factura_idfactura` = factura.`idfactura`\n"
                    + "     AND factura.`usuarios_idusuarios` = nota_credito.`usuarios_idusuarios`\n"
                    + "     AND factura.`cliente_idcliente` = nota_credito.`factura_cliente_idcliente`\n"
                    + "     INNER JOIN `clave_acceso` clave_acceso ON nota_credito.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                    + "     INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                    + "     INNER JOIN `producto` producto ON nota_credito_detalle.`producto_idproducto` = producto.`idproducto` where nota_credito.`numero`='" + num + "'");


            while (rnota.next()) {


                ps.println("Nota de Credito #:    " + rnota.getString(4));

                //System.out.println(rnota.getInt(3));

                if (rnota.getString(3) == null) {
                } else {
                    ps.println("# de Autorizacion: ");
                    ps.println(rnota.getString(3));
                    ps.println("Fecha  Autorizacion: " + rnota.getString(5));
                }




                //ps.println("Motivo: "+model.getValueAt(0, 1).toString());
                ps.println("Clave de Acceso:");
                ps.println(rnota.getString(13));

                ps.println("Razon Social/Nombres y Apellidos:");
                ps.println(rnota.getString(11).replace("ñ", "|"));
                ps.println("Ruc/CI: " + rnota.getString(12));
                ps.println("Fecha Emision: " + rnota.getString(5));
                ps.println("-------------------");




                Statement clien = he.coneccion.createStatement();
                ResultSet rclien = clien.executeQuery("SELECT\n"
                        + "     nota_credito_detalle.`cantidad` AS nota_credito_detalle_cantidad,\n"
                        + "     nota_credito_detalle.`subtotal` AS nota_credito_detalle_subtotal,\n"
                        + "     nota_credito_detalle.`total` AS nota_credito_detalle_total,\n"
                        + "     nota_credito_detalle.`iva` AS nota_credito_detalle_iva,\n"
                        + "     producto.`nombre` AS producto_nombre,\n"
                        + "     producto.`punit` AS producto_punit,\n"
                        + "     producto.`idproducto` AS producto_punit\n"
                        + "FROM\n"
                        + "     `producto` producto INNER JOIN `nota_credito_detalle` nota_credito_detalle ON producto.`idproducto` = nota_credito_detalle.`producto_idproducto`\n"
                        + "     INNER JOIN `nota_credito` nota_credito ON nota_credito_detalle.`nota_credito_idnota_credito` = nota_credito.`idnota_credito` where numero='" + num + "';");


                System.out.println("SELECT\n"
                        + "   \n"
                        + "     nota_credito.`motivo` AS nota_credito_motivo,\n"
                        + "     nota_credito.`estado` AS nota_credito_estado,\n"
                        + "     nota_credito.`autorizacion` AS nota_credito_autorizacion,\n"
                        + "     nota_credito.`numero` AS nota_credito_numero,\n"
                        + "     nota_credito.`fecha` AS nota_credito_fecha,\n"
                        + "     nota_credito.`hora` AS nota_credito_hora,\n"
                        + "     nota_credito.`subtotal` AS nota_credito_subtotal,\n"
                        + "     nota_credito.`valor_modificacion` AS nota_credito_valor_modificacion,\n"
                        + "     nota_credito.`iva` AS nota_credito_iva,\n"
                        + "     factura.`numero` AS factura_numero,\n"
                        + "     cliente.`nombre` AS cliente_nombre,\n"
                        + "     cliente.`cedula_ruc` AS cliente_cedula_ruc,\n"
                        + "     clave_acceso.`clave_acceso` AS clave_acceso_clave_acceso\n"
                        + "FROM\n"
                        + "     `factura` factura INNER JOIN `nota_credito` nota_credito ON factura.`idfactura` = nota_credito.`factura_idfactura`\n"
                        + "     AND factura.`usuarios_idusuarios` = nota_credito.`usuarios_idusuarios`\n"
                        + "     AND factura.`cliente_idcliente` = nota_credito.`factura_cliente_idcliente`\n"
                        + "     INNER JOIN `cliente` cliente ON factura.`cliente_idcliente` = cliente.`idcliente`\n"
                        + "     INNER JOIN `clave_acceso` clave_acceso ON factura.`clave_acceso_idclave_acceso` = clave_acceso.`idclave_acceso`\n"
                        + "     AND clave_acceso.`idclave_acceso` = nota_credito.`clave_acceso_idclave_acceso`   "
                        + " where nota_credito.`numero`='" + num + "'");

                while (rclien.next()) {

                    ps.println("Cod Principal: " + rclien.getString(7));
                    ps.println("Descripcion: " + rclien.getString(5));
                    double pr = (Double.parseDouble(rclien.getString(6))) / 1.12;
                    ps.println("Precio Unitario: " + df.format(pr).replace(",", "."));
                    ps.println("Cantidad: " + rclien.getString(1));
                    ps.println("Subtotal: " + rclien.getString(2));
                    ps.println("IVA: " + rclien.getString(4));
                    ps.println("Total: " + rclien.getString(3));

                    ps.println("-------------------");
                }

                ps.println("Iva: " + rnota.getString(9));
                ps.println("Subtotal: " + rnota.getString(7));
                ps.println("Total " + rnota.getString(8));




                ps.println("Nota de Credito a la factura #: ");

                ps.println(rnota.getString(10));





                ps.println("-------------------");


                ps.println("       SISTEMA ADV-BOX -ADVANTECH     ");
                ps.println("           2829421 - 0999064457      ");
                correr(8, ps);
                cortar(ps);
                ps.close();

            }
            he.coneccion.close();


            imprimir(num, mang, "nota");
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
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

    public void imprimir(String num, String mang, String tipo) throws IOException {
        String impresora = null;
        try {
            System.out.println("entron a impresion local");
            he.conectar();
            Statement st_im = he.coneccion.createStatement();
            System.out.println("entro a impresion");
            ResultSet ridim = st_im.executeQuery("SELECT impresora FROM adv_facturacion.configuracion where nmanguera=" + mang);
            while (ridim.next()) {

                impresora = ridim.getString(1);

            }



            System.out.println(impresora);

            FileInputStream inputStream = null;
            if (tipo.equalsIgnoreCase("factura")) {
                inputStream = new FileInputStream("rides_manuales\\" + num + ".txt");
            } else {
                inputStream = new FileInputStream("rides_manuales\\nc" + num + ".txt");

            }

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

            inputStream.close();

            System.out.println("Documento impreso " + "rides_manuales\\" + num + ".txt");

        } catch (SQLException ex) {
            Logger.getLogger(impresion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrintException ex) {
            Logger.getLogger(impresion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(impresion.class.getName()).log(Level.SEVERE, null, ex);
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

    private void cortar(PrintWriter ps) {

        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'i'};
            ps.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
