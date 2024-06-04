package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor 
{
	
    private static final int PORT_SERVICE = 7894;
    private ServerSocket socket;
    ArrayList<hiloRegistro> hilosRegister;
    
    public Servidor()
    {
        hilosRegister = new ArrayList();
    }
    
    public void initServer()
    {
        try {
            //Recibir llamada de cliente
            socket = new ServerSocket(PORT_SERVICE);
            System.out.println("Escuchando Clientes...");
            Socket unCliente = socket.accept();
            System.out.println("Alguien se conecta...");
            //
            
            //Sistema de mensajes mediante el socket
            String mensaje = new String(readSocket(unCliente));
            byte[] buffer;
            BufferedOutputStream output = new BufferedOutputStream(unCliente.getOutputStream());
            //

            switch(mensaje)
            {
                case "register":
                {
                    buffer = "okR".getBytes();
                    output.write(buffer, 0, buffer.length);
                    break;
                }
                case "login":
                {
                    buffer = "okL".getBytes();
                    output.write(buffer, 0, buffer.length);
                    break;
                }
                default:
                {
                    break;
                }
            }
            /*
            double result = doOperation(request); //Se genera la respuesta
            ResponseProtocol respuesta = responseBuilder.buildResponse(result);
            System.out.println("Generando respuesta");
            sendResponse(cliente, respuesta);
            */
        } 
        catch (Exception e) 
        {
        }
    }
    
    private byte[] readSocket(Socket cliente)
    {
        byte[] socketMessage = null;
        try{
            BufferedInputStream bis = new BufferedInputStream(cliente.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[8];
            int nb = 0;
            do{
                nb = bis.read(buffer);
                baos.write(buffer, 0, nb);
            }while (bis.available()>0);
            socketMessage = baos.toByteArray();
        }
        catch (Exception ex){
        }
        return socketMessage;
    }
}

class hiloRegistro
extends Thread
{
    private ServerSocket socket;
    public hiloRegistro(ServerSocket _socket)
    {
        socket = _socket;
    }
}