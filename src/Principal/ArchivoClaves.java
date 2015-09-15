/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import conexion.conexion_facturacion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Advantech Digital
 */
public class ArchivoClaves {
    String USUARIO = "";
    String PASSWORD = "";
    
    public ArchivoClaves(File claves,String usuario,String contraseña){
       
        USUARIO=usuario;
        PASSWORD=contraseña;
        
        
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(claves));
            while ((line = br.readLine()) != null){
                setClaves(line, String.format("%tF", Calendar.getInstance()), 0, "contingencia");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    public void setClaves(String clave, String date, int estado, String tipo) {
        conexion_facturacion con = new conexion_facturacion(USUARIO, PASSWORD);
        try {
            con.conectar();
            //ResultSet rs = con.consulta("SELECT DATE_FORMAT(CURDATE(),'%d'\"-\"'%m'\"-\"'%Y');");
            //rs.next();
            con.ejecutar("INSERT INTO clave_acceso (clave_acceso, fecha, estado, tipo) "
                    + "VALUES('" + clave + "', '" + date + "', " + estado + ", '" + tipo + "');");
            con.coneccion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
