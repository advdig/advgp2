/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion;

import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class xml {

    String fecha, hora, producto, usuario;
    double subtotal, total, ppu;
    String cliente;
    String raz, r, nau, se1, se2, se3;
    String cantidad;
    int manguera;
    public String usu, contra, numerof, dir,numa;

    public xml(String rz, String ruc, String na, String s1, String s2, String dire, String s3, String numerofa, String f, Double sub, Double tot, Double pu, int man, String cli, String prod, String cantida) {


        fecha = f;
        subtotal = sub;
        total = tot;
        ppu = pu;
        manguera = man;
        cliente = cli;
        usuario = usu;
        producto = prod;
        cantidad = cantida;
        numerof = numerofa;
        raz = rz;
        r = ruc;
        nau = na;
        se1 = s1;
        se2 = s2;
        se3 = s3;
        dir = dire;
        numa=na;
        cantidad=cantida;
       
    }

    public void crearxml() {
        try {

            Element te = new Element("factura");
            te.setAttribute(new Attribute("version", "\"2_00\""));
            Document doc = new Document(te);
            doc.setRootElement(te);

            Element it = new Element("infoTributaria");


            it.addContent(new Element("razonSocial").setText(raz));
            it.addContent(new Element("ruc").setText(r));
            it.addContent(new Element("numAut").setText(numa));
            it.addContent(new Element("codDoc").setText("1"));
            it.addContent(new Element("estab").setText(se2));
            it.addContent(new Element("ptoEmi").setText(se1));
            it.addContent(new Element("secuencial").setText(se3));
            it.addContent(new Element("fechaAutorizacion").setText(""));
            it.addContent(new Element("caducidad").setText(""));
            it.addContent(new Element("fechaEmision").setText(fecha));
            it.addContent(new Element("dirMatriz").setText(dir));
            it.addContent(new Element("razonSocialComprador").setText(cliente));
            it.addContent(new Element("totalConImpuestos").setText(String.valueOf(total)));
            it.addContent(new Element("moneda").setText(String.valueOf("Dolares Americanos")));



            doc.getRootElement().addContent(it);

            Element des = new Element("detalles");

           
            Element de = new Element("detalle");

           

            de.addContent(new Element("concepto").setText("Venta de combustible"));
            de.addContent(new Element("producto").setText(producto));
            de.addContent(new Element("cantidad").setText(cantidad));
            de.addContent(new Element("precioUnitario").setText(String.valueOf(ppu)));
            de.addContent(new Element("precioTotal").setText(String.valueOf(subtotal)));

            des.addContent(de);
            doc.getRootElement().addContent(des);
            Element fi = new Element("infoAdicional");
            doc.getRootElement().addContent(fi);

            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("xml\\"+numerof+".xml"));

            
            System.out.println("xml\\"+numerof+".xml");
            System.out.println("File Saved!");
        
        
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}
