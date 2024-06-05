package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor implements Runnable {
    private static final int PORT_SERVICE = 7894;
    private ServerSocket servidor;
    private Thread t;
    private OutputStream output;
    private InputStream input;
    ArrayList<hiloRegistro> hilosRegister = new ArrayList<>();
    
    public Servidor() throws IOException {
        servidor = new ServerSocket(PORT_SERVICE);
        hilosRegister = new ArrayList<>();
        t = new Thread(this);
        t.start();
    }
    
    public void run() {
        try {
            initserver();  
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void initserver() {
        try {
            // Recibir llamada de cliente
            System.out.println("Escuchando Clientes...");
            while (true) {
                Socket sck = servidor.accept();
                System.out.println("Alguien se conecta...");
                // Handle the client in a separate thread
                new Thread(() -> handleClient(sck)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void handleClient(Socket sck) {
        try {
            // Sistema de mensajes mediante el socket
            String mensajeEntrante = null;
            byte[] buffer = new byte[1024];
            input = sck.getInputStream();
            output = sck.getOutputStream();
            
            input.read(buffer);
            mensajeEntrante = new String(buffer).trim();
            //

            // Asignamos el
            switch (mensajeEntrante) {
                case "register":
                    // hilosRegister.add(new hiloRegistro(sck));
                    output.write("okR".getBytes(), 0, "okR".getBytes().length);
                    break;
                case "login":
                	output.write("okL".getBytes(), 0, "okL".getBytes().length);
                    break;
                case "jugar":
                	output.write("okJ".getBytes(), 0, "okJ".getBytes().length);
                    break;
                default:
                    System.out.println("Pasa Registern't");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] readSocket(Socket cliente) {
        byte[] socketMessage = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(cliente.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[8];
            int nb = 0;
            do {
                nb = bis.read(buffer);
                baos.write(buffer, 0, nb);
            } while (bis.available() > 0);
            socketMessage = baos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return socketMessage;
    }
}

class hiloRegistro extends Thread {
    private Socket socket;
    private OutputStream os;
    private InputStream is;
    
    public hiloRegistro(Socket _socket) throws IOException {
        socket = _socket;
        os = socket.getOutputStream();
        is = socket.getInputStream();
    }
}