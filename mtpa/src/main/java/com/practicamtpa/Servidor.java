package com.practicamtpa;

import java.net.ServerSocket;
import java.net.Socket;
public class Servidor {
	
    private static final int PORT_SERVICE = 7894;
    private ServerSocket socket;
    
    public Servidor(){
        
    }
    
    public void initServer()
    {
        try {
            socket = new ServerSocket(PORT_SERVICE);
            System.out.println("Escuchando Clientes...");
            Socket unCliente = socket.accept();
            System.out.println("Alguien se conecta...");
            
        } catch (Exception e) 
        {
            
        }
    }

}