/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

/**
 *
 * @author r
 */
import Principal.PDF;
import Principal.Enviar_email;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class reporte_factura extends Thread{

    String nf;
    Map pars; 
    Properties props;
    Session session;
    String correo, xml, pdf, correocliente, rz, npdf, nxml;
    MimeMessage message;
    String ncliente, contraseña;
    LinkedList<producto> listaproducto;
    String u,c;
    
    
    public void reporte_factura(String nf, String ruc, String dm, String ds, String ce, String nce, String numAut, String fechahora, String clave, String temision, String ambiente, String rz, String nc, String rzc, String ruc_comp, String f_emision, String guia, String subtotal, String iva, String total, String descuento, String cp, String producto, String cantidad, String detalle, String pu, String descuentop) {


      

            this.nf = nf;

            //1. Se compila el reporte
            System.out.println("Numero aut"+numAut);
    
             PDF p=new PDF();
            p.crear_pdf(nf,"adv","advgp2014",numAut);
            
            System.out.println("Done!");

            this.start();



       
    }

    public void reporte_factura(String nf, String ruc, String dm, String ds, String ce, String nce, String numAut, String fechahora, String clave, String temision, String ambiente, String rz, String nc, String rzc, String ruc_comp, String f_emision, String guia, String subtotal, String iva, String total, String descuento, String cp, String[] producto, Double[] cantidad, String[] detalle, Double[] pu, String descuentop, Double[] subtotalp) {


     

            this.nf = nf;

            PDF p=new PDF();
            p.crear_pdf(nf,"adv","advgp2014",numAut);
            //1. Se compila el reporte
            this.start();

       
    }

    public void enviar_email(String contraseña, String correo, String xml, String pdf, String correocliente, String ncliente, String rz, String npdf, String nxml) {

        this.correo = correo;
        this.xml = xml;
        this.pdf = pdf;
        this.correocliente = correocliente;
        this.rz = rz;
        this.npdf = npdf;
        this.nxml = nxml;
        this.ncliente = ncliente;
        this.contraseña = contraseña;





    }

    public void run() {
        try {



            // se obtiene el objeto Session. La configuración es para
            // una cuenta de gmail.
            props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", correo);
            props.setProperty("mail.smtp.auth", "true");

            session = Session.getDefaultInstance(props, null);
            // session.setDebug(true);

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();

            message = new MimeMessage(session);


            message.setSubject(rz + "Factura Electronica N° " + nxml);

            texto.setText("Estimado(a) " + ncliente + "\n"
                    + "Presente.\n"
                    + "\n"
                    + "Adjunto sírvase encontrar Factura Electrónica Nº" + nxml + " ¹ y el detalle² de dicho comprobante que hemos emitido en nuestra empresa.\n"
                    + "Descargue sus documentos electrónicos de la siguinte página web www.advangas.com\n"
                    + "Gracias por preferirnos.\n"
                    + "\n"
                    + "Atentamente,\n"
                    + "" + rz + "\n"
                    + "--------------------------------------------------------------------------------\n"
                    + "¹ El comprobante electrónico es el archivo XML adjunto, le socilitamos que lo almacene de manera segura puesto que tiene validez tributaria.\n");
                  

            // Se compone el adjunto con la imagen
            BodyPart adjuntopdf = new MimeBodyPart();
            adjuntopdf.setDataHandler(new DataHandler(new FileDataSource(pdf)));
            adjuntopdf.setFileName(npdf);

            BodyPart adjuntoxml = new MimeBodyPart();
            adjuntoxml.setDataHandler(new DataHandler(new FileDataSource(xml)));
            adjuntoxml.setFileName(nxml);


            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjuntopdf);
            multiParte.addBodyPart(adjuntoxml);

            // Se compone el correo, dando to, from, subject y el
            // contenido.

          
              
                
                message.setFrom(new InternetAddress("yo@yo.com"));
                message.addRecipient( Message.RecipientType.TO,
                        new InternetAddress(correocliente));

                message.setContent(multiParte);




                // Se envia el correo.



                Transport t = session.getTransport("smtp");
                t.connect(correo, contraseña);
                t.sendMessage(message, message.getAllRecipients());
                t.close();
                
                
                System.out.println("Correo Enviado Correctamente");
                
            

        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Enviar_email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Enviar_email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}