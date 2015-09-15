package java_splash;

import Principal.Surtidores;
import conexion.Conecciones;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Mouse
 */
public class Main {

  

    public static void main(String[] args) {


        
       // PropertyConfigurator.configure("log4j.properties");
      



        try {
            Surtidores n = new Surtidores("adv", "advgp2014");




            n.setVisible(true);
            n.setLocation(0, 0);

            n.usuario = "adv";
            n.usu = "adv";
            n.contra = "advgp2014";
            n.usuarios("adv", "advgp2014");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.ALL, null, ex);
        }

    }
}
