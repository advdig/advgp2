/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import conexion.conexion_facturacion;
import facturacion.contingencia_automatica;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author r
 */
public class Tarea_contingencia implements Job {
 
    public static final String usuarios="";
    public static final String contraseña="";
    
   
    
    

    @Override
    public void execute(JobExecutionContext arg) throws JobExecutionException {
     
          String usu,contra;
         
          try {
            
             System.out.println("ENVIANDO CONTINGENCIA");
           // JOptionPane.showMessageDialog(null, "Tarea programada exitosamente con la expresion: ");
            org.quartz.JobDataMap   properties  = arg.getJobDetail().getJobDataMap();  
          
        
            
            usu= properties.getString("usuarios");  
            contra= properties.getString("contrasena");
            
            System.out.println("usuario "+usu);
            System.out.println("contraseña "+contra);
            
            
            conexion_facturacion n = new conexion_facturacion(usu, contra);
            n.conectar();
           
            
           
           
            String nombre;


            nombre = "SELECT idxml_contingencia,numero,fecha,cedula_ruc,nombre,motivo,xml_contingencia FROM adv_xml.xml_contingencia, adv_facturacion.factura,adv_facturacion.cliente where xml_factura=idfactura and cliente_idcliente=idcliente;";


            ResultSet consultan = n.consulta(nombre);



            while (consultan.next()) {


                contingencia_automatica ca = new contingencia_automatica(consultan.getInt(1), consultan.getString(2), consultan.getString(3), consultan.getString(4), consultan.getString(5));
                ca.start();


            }




            n.coneccion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Surtidores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
    }
}