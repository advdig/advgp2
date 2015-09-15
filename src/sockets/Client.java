package sockets;

import java.rmi.*;
import java.rmi.registry.*;

public class Client{
    
    public void Client(String surtidor){
	metodosServidor estado;
      
	try {
	    estado=(metodosServidor)Naming.lookup("rmi://localhost/surtidor1");
         
	    System.out.println();
	    System.out.println( "   "+surtidor+"! = "+estado.estadosurtidor(surtidor));
	    System.out.println();
	}
	catch (Exception e){
	    e.printStackTrace();
	}
}
}

