/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import Principal.Surtidores;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CampoAdicional;
import modelo.Impuesto;
import modelo.InfoTributaria;
import modelo.factura.Factura;
import modelo.factura.FacturaDetalle;
import modelo.factura.InfoFactura;
import modelo.factura.TotalImpuesto;
import util.XMLUtil;

/**
 *
 * @author r
 */
public class crear_factura_credito_1 {

     public static String remove1(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }
    
    
    public int crear_factura_credito(String cp[], String nc, String oconta, int cest, String ambiente, String rz, String r, String cadena, String s1, String s2, String ss3, String d, String fecha, String ti, String ncliente, String clientr, Double subtotalF, Double ivaF, Double totalF, String[] producto, Double[] cantidad, Double[] pu, Double ivaP[], Double[] totalP, Double[] subtotalP,int tipo) {

        try {

            DecimalFormat dc;

            DecimalFormat df = new DecimalFormat("#.##");
          
            dc = new DecimalFormat("#.######");
            
            
            DecimalFormat dpu = new DecimalFormat("#.###");
          
            

            totalF = Double.valueOf(df.format(totalF).replace(",", "."));

            if (ivaF > 0) {

                subtotalF = Double.valueOf(df.format(totalF / 1.12).replace(",", "."));

                System.out.println("entro");
                
                
                
                ivaF = Double.valueOf(df.format(totalF - subtotalF).replace(",", "."));
                String cant[] = new String[cantidad.length];
                String punit[] = new String[pu.length];

                for (int i = 0; i < cantidad.length; i++) {
                    cant[i] = dc.format(cantidad[i]).replace(",", ".");
                    
                    pu[i]=Double.valueOf(dc.format(pu[i]).replace(",", "."));
                    
                    punit[i] = dc.format(pu[i]).replace(",", ".");
                    
                    
                    ivaP[i] = Double.parseDouble(df.format(ivaP[i]).replace(",", "."));
                    subtotalP[i] = Double.parseDouble(df.format(subtotalP[i]).replace(",", "."));

                }

            } else {

            }

        //  pu = Double.valueOf(df.format(subtotalF / cantidad).replace(",", "."));
            Factura fac = new Factura();

            fac.setId("comprobante");
            
            
            fac.setVersion("1.1.0");
            

            InfoTributaria it = new InfoTributaria();
            it.setAmbiente(ambiente);
           
            
           
                
            if(tipo==1){
            
            it.setTipoEmision("1");
            
            }else{
            
              it.setTipoEmision("2");
            
            }
            
            
            
            
            it.setRazonSocial(rz);
            it.setNombreComercial(nc);
            it.setRuc(r);
            it.setClaveAcceso(cadena);
            it.setCodDoc("01");
            it.setPtoEmi(s2);
            it.setEstab(s1);
            it.setSecuencial(ss3);
            it.setDirMatriz(d);
            fac.setInfoTributaria(it);
            InfoFactura inf = new InfoFactura();
            inf.setFechaEmision(fecha.replace("-", "/"));
            inf.setDirEstablecimiento(d);
            if (cest == 0) {
            } else {
                inf.setContribuyenteEspecial(BigDecimal.valueOf(cest));

            }
            inf.setObligadoContabilidad(oconta);
            inf.setTipoIdentificacionComprador(ti);
            inf.setRazonSocialComprador(remove1(ncliente));
            inf.setIdentificacionComprador(clientr);
            inf.setTotalSinImpuestos(BigDecimal.valueOf(subtotalF));
            inf.setTotalDescuento(BigDecimal.valueOf(0.00));

            TotalImpuesto tim = new TotalImpuesto();

            System.out.println("iva " + ivaF);

            if (ivaF > 0.00) {
                tim.setCodigo("2");
                tim.setCodigoPorcentaje("2");
                tim.setBaseImponible(BigDecimal.valueOf(subtotalF));
                tim.setValor(BigDecimal.valueOf(ivaF));
            } else {
                tim.setCodigo("2");
                tim.setCodigoPorcentaje("0");
                tim.setBaseImponible(BigDecimal.valueOf(subtotalF));
                tim.setValor(BigDecimal.valueOf(ivaF));

            }

            List<TotalImpuesto> listim = new ArrayList<TotalImpuesto>();
            listim.add(tim);
            inf.setTotalImpuesto(listim);
            inf.setPropina(BigDecimal.valueOf(0.00));
            inf.setImporteTotal(BigDecimal.valueOf(totalF));
            inf.setMoneda("Dolar");

            fac.setInfoFactura(inf);

            List<FacturaDetalle> detallef = new ArrayList<FacturaDetalle>();

            for (int i = 0; i < cp.length; i++) {

                FacturaDetalle detalle = new FacturaDetalle();

                detalle.setCodigoPrincipal(cp[i]);
                detalle.setCodigoAuxiliar(cp[i]);
                detalle.setDescripcion(producto[i]);
                detalle.setCantidad(BigDecimal.valueOf(cantidad[i]));
                System.out.println(pu[i]);
                detalle.setPrecioUnitario(BigDecimal.valueOf(pu[i]));
                detalle.setDescuento(BigDecimal.valueOf(0.00));
                detalle.setPrecioTotalSinImpuesto(BigDecimal.valueOf(Double.valueOf(df.format(subtotalP[i]).replace(",", "."))));

                List<Impuesto> impuesto = new ArrayList<Impuesto>();

                Impuesto imp = new Impuesto();

                if (ivaF > 0.00) {
                    imp.setCodigo("2");
                    imp.setCodigoPorcentaje("2");
                    imp.setTarifa(BigDecimal.valueOf(12));
                    imp.setBaseImponible(BigDecimal.valueOf(Double.valueOf(df.format(subtotalP[i]).replace(",", "."))));
                    imp.setValor(BigDecimal.valueOf(ivaP[i]));
                } else {

                    imp.setCodigo("2");
                    imp.setCodigoPorcentaje("0");
                    imp.setTarifa(BigDecimal.valueOf(0));
                    imp.setBaseImponible(BigDecimal.valueOf(Double.valueOf(df.format(subtotalP[i]).replace(",", "."))));
                    imp.setValor(BigDecimal.valueOf(ivaP[i]));

                }

                impuesto.add(imp);

                detalle.setImpuesto(impuesto);

                detallef.add(detalle);

            }

            fac.setDetalle(detallef);

            CampoAdicional adicional = new CampoAdicional();

            adicional.setNombre("Imprime");
            adicional.setValue("Advadvantech");

            List<CampoAdicional> camp = new ArrayList<CampoAdicional>();

            camp.add(adicional);

            fac.setCampoAdicional(camp);

            XMLUtil xml = new XMLUtil();

            if (ncliente.equalsIgnoreCase("Consumidor Final")) {

                System.out.println(s2);

                if (s2.equalsIgnoreCase("052")) {

                    xml.doMarshall(fac, "generados\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                    System.out.println("contingencia\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                } else {

                    xml.doMarshall(fac, "consumidores_finales\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");
                    System.out.println("consumdores finales\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                }

            } else {

                xml.doMarshall(fac, "generados\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                System.out.println("generados\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

            }
            return 1;

        } catch (Exception ex) {
            Logger.getLogger(Surtidores.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 1;

    }

    public int crear_factura_contingencia(String tp, String cp[], String nc, String oconta, int cest, String ambiente, String rz, String r, String cadena, String s1, String s2, String ss3, String d, String fecha, String ti, String ncliente, String clientr, Double subtotalF, Double ivaF, Double totalF, String[] producto, Double[] cantidad, Double[] pu, Double ivaP[], Double[] totalP, Double[] subtotalP) {

        DecimalFormat df = new DecimalFormat("#.##");

        totalF = Double.valueOf(df.format(totalF).replace(",", "."));

        subtotalF = Double.valueOf(df.format(totalF / 1.12).replace(",", "."));

        ivaF = Double.valueOf(df.format(totalF - subtotalF).replace(",", "."));

        //  pu = Double.valueOf(df.format(subtotalF / cantidad).replace(",", "."));
        Factura fac = new Factura();

        fac.setId("comprobante");
        fac.setVersion("1.0.0");
        InfoTributaria it = new InfoTributaria();
        it.setAmbiente(ambiente);
        it.setTipoEmision("1");
        it.setRazonSocial(rz);
        it.setNombreComercial(nc);
        it.setRuc(r);
        it.setClaveAcceso(cadena);
        it.setCodDoc("01");
        it.setPtoEmi(s2);
        it.setEstab(s1);
        it.setSecuencial(ss3);
        it.setDirMatriz(d);
        fac.setInfoTributaria(it);
        InfoFactura inf = new InfoFactura();
        inf.setFechaEmision(fecha.replace("-", "/"));
        inf.setDirEstablecimiento(d);
        if (cest == 0) {
        } else {
            inf.setContribuyenteEspecial(BigDecimal.valueOf(cest));

        }
        inf.setObligadoContabilidad(oconta);
        inf.setTipoIdentificacionComprador(ti);
        inf.setRazonSocialComprador(ncliente);
        inf.setIdentificacionComprador(clientr);
        inf.setTotalSinImpuestos(BigDecimal.valueOf(subtotalF));
        inf.setTotalDescuento(BigDecimal.valueOf(0.00));

        TotalImpuesto tim = new TotalImpuesto();
        tim.setCodigo("2");
        tim.setCodigoPorcentaje("2");
        tim.setBaseImponible(BigDecimal.valueOf(subtotalF));
        tim.setValor(BigDecimal.valueOf(ivaF));

        List<TotalImpuesto> listim = new ArrayList<TotalImpuesto>();
        listim.add(tim);
        inf.setTotalImpuesto(listim);
        inf.setPropina(BigDecimal.valueOf(0.00));
        inf.setImporteTotal(BigDecimal.valueOf(totalF));
        inf.setMoneda("Dolar");

        fac.setInfoFactura(inf);

        List<FacturaDetalle> detallef = new ArrayList<FacturaDetalle>();

        for (int i = 0; i < cp.length; i++) {

            FacturaDetalle detalle = new FacturaDetalle();

            detalle.setCodigoPrincipal(cp[i]);
            detalle.setCodigoAuxiliar(cp[i]);
            detalle.setDescripcion(producto[i]);
            detalle.setCantidad(BigDecimal.valueOf(cantidad[i]));
            detalle.setPrecioUnitario(BigDecimal.valueOf(pu[i]));
            detalle.setDescuento(BigDecimal.valueOf(0.00));
            detalle.setPrecioTotalSinImpuesto(BigDecimal.valueOf(subtotalP[i]));

            List<Impuesto> impuesto = new ArrayList<Impuesto>();

            Impuesto imp = new Impuesto();
            imp.setCodigo("2");
            imp.setCodigoPorcentaje("2");
            imp.setTarifa(BigDecimal.valueOf(12));
            imp.setBaseImponible(BigDecimal.valueOf(subtotalP[i]));
            imp.setValor(BigDecimal.valueOf(ivaP[i]));

            impuesto.add(imp);

            detalle.setImpuesto(impuesto);

            detallef.add(detalle);

        }

        fac.setDetalle(detallef);

        CampoAdicional adicional = new CampoAdicional();

        adicional.setNombre("Imprime");
        adicional.setValue("Advadvantech");
        CampoAdicional adicional1 = new CampoAdicional();
        adicional1.setNombre("Tipo de Pago");
        adicional1.setValue(tp);

        List<CampoAdicional> camp = new ArrayList<CampoAdicional>();

        camp.add(adicional);
        camp.add(adicional1);

        fac.setCampoAdicional(camp);

        XMLUtil xml = new XMLUtil();

        if (ncliente.equalsIgnoreCase("Consumidor Final")) {

            System.out.println(s2);

            if (s2.equalsIgnoreCase("052")) {

                xml.doMarshall(fac, "generados\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

                System.out.println("contingencia\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

            } else {

                xml.doMarshall(fac, "consumidores_finales\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");
                System.out.println("consumdores finales\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

            }

        } else {

            xml.doMarshall(fac, "contingencia\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

            System.out.println("contingencia\\" + s1 + "-" + s2 + "-" + ss3 + ".xml");

        }

        return 1;

    }
}
