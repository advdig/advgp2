/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import conexion.conexion_facturacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author r
 */
public class Tarea_Credito implements Job  {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
       
        String usu,contra;
         
        
            
           
           // JOptionPane.showMessageDialog(null, "Tarea programada exitosamente con la expresion: ");
            org.quartz.JobDataMap   properties  = jec.getJobDetail().getJobDataMap();  
          
        
            
            usu= properties.getString("usuarios");  
            contra= properties.getString("contrasena");
            
           
            try {
                
                System.out.println("Primer dia del Mes");
                
                conexion_facturacion conf = new conexion_facturacion(usu, contra);
                conf.conectar();

                Statement st_m15b = conf.coneccion.createStatement();
                ResultSet ridm15b = st_m15b.executeQuery("SELECT cupo_cliente,cliente_idcliente FROM adv_facturacion.credito_cliente;");

                while (ridm15b.next()) {

                    Statement st1 = conf.coneccion.createStatement();

                    String consulta = "UPDATE `adv_facturacion`.`cliente` SET `cupo_cliente`='" + ridm15b.getDouble(1) + "' WHERE `idcliente`='" + ridm15b.getInt(2) + "';";


                    st1.executeUpdate(consulta);
                    st1.close();

                }


                conf.coneccion.close();



            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Surtidores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Surtidores.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
    
        
        
    
    }
    
    
    
    
}
