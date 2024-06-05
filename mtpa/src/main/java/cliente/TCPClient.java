package cliente;

import java.net.*;
import java.io.*;
public class TCPClient {
	
    public static void main(String[] args) 
        throws Exception{
        
        Socket cliente = new Socket("localhost", 7894); 
        if(cliente == null){
            System.out.println("Error al iniciar el Socket");
        }
        byte[] peticionBytes = "jugar".getBytes();
        DataInputStream in = new DataInputStream(cliente.getInputStream());
        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
        out.write(peticionBytes);
        System.out.println("Petición Realizada...");
        byte respuesta[] = new byte[1024];
        in.read(respuesta);
        System.out.println("Me Dice: " + (new String(respuesta).trim()));
    }
    
}