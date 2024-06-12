package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements Runnable {
    
    private static final int PORT_SERVICE = 7894;
    private ServerSocket servidor;
    private Thread t;
    private OutputStream output;
    private InputStream input;
    //NO IMPLEMENTAR ArrayList<hiloRegistro> hilosRegister = new ArrayList<>();
    
    public Servidor() throws IOException {
        servidor = new ServerSocket(PORT_SERVICE);
        //hilosRegister = new ArrayList<>();
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
            System.out.println("Escuchando Clientes...");
            while (true) {
                Socket sck = servidor.accept();
                System.out.println("Alguien se conecta...");
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
                    //hilosRegister.add(new hiloRegistro(sck));
                    hiloRegistro hR = new hiloRegistro(sck);
                    hR.run();
                    break;
                case "login":
                    hiloLogin hL = new hiloLogin(sck);
                    hL.run();
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
    
 public void crearFichero() 
 {
    File carpeta = new File("./TresEnRaya");
    carpeta.mkdirs();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("./TresEnRaya/registro.txt")))
    {
        bw.write("A partir de aquí se escribirán todos los usuarios registrados, siguiendo el siguiente formato. login;password\n");
    } 
    catch (IOException e)
    {
        e.printStackTrace();
    }
}
    
    public String buscarUsuario(String username)throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("./TresEnRaya/registro.txt"));
        String line;
        while ((line = br.readLine()) != null) 
        {
            if (line.contains(";"))
            {
                if (line.split(";")[0].equals(username)) 
                {
                    return line;
                }
            }
        }
        return null;
    }
    
    public void registrarUsuario(String username, String password) throws FileNotFoundException, IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./TresEnRaya/registro.txt"));
        bw.write(username + ";" + password);
        bw.newLine();
    }
    
    public hiloRegistro(Socket _socket) throws IOException 
    {
        socket = _socket;
        os = socket.getOutputStream();
        is = socket.getInputStream();
        crearFichero();
    }
    
    @Override
    public void run()
    {
        try 
        {
            byte[] buffer = new byte[1024];
            //Recibimos del socket el usuario y contraseña
            is.read(buffer);
            String login = new String(buffer).trim();
            System.out.println(login);
            byte[]buffer2 = new byte[1024];
            is.read(buffer2);
            String password = new String(buffer2).trim();
            //Si encontramos un usuario ya registrado con ese nombre le denegamos la petición de registro
            if(buscarUsuario(login) == null)
            {
                registrarUsuario(login,password);
                os.write("okR".getBytes(), 0, "okR".getBytes().length);
            }
            else
            {
                os.write("denied".getBytes(), 0, "denied".getBytes().length);
                return;
            }
        } 
        catch (IOException ex) 
        {
            
        }
    }
}

class hiloLogin extends Thread {
    private Socket socket;
    private OutputStream os;
    private InputStream is;
    
    
    public String buscarUsuario(String username) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("./TresEnRaya/registro.txt"));
        String line;
        while ((line = br.readLine()) != null) 
        {
            if(line.contains(";"))
            {
                if (line.split(";")[0].equals(username)) 
                {
                    return line;
                }
            }
        }
        return null;
    }
    
    
    public hiloLogin(Socket _socket) throws IOException 
    {
        socket = _socket;
        os = socket.getOutputStream();
        is = socket.getInputStream();
    }
    
    @Override
    public void run()
    {
        try {
            byte[] buffer = new byte[1024];
            //Recibimos del socket el usuario y contraseña
            is.read(buffer);
            String login = new String(buffer).trim();
            is.read(buffer);
            String password = new String(buffer).trim();
            //Si encontramos que no hay un usuario registrado con ese login denegamos la entrada
            if(buscarUsuario(login).equals(null))
            {
                os.write("denied".getBytes(), 0, "denied".getBytes().length);
                return;
            }
            if (buscarUsuario(login).split(";")[1].equals(password))
            {
            	os.write("acepted".getBytes(), 0, "acepted".getBytes().length);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(hiloRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class hiloJugar extends Thread {
    private Socket socket;
    private OutputStream os;
    private InputStream is;
    
    public hiloJugar(Socket _socket) throws IOException 
    {
        socket = _socket;
        os = socket.getOutputStream();
        is = socket.getInputStream();
    }
    
    @Override
    public void run()
    {
        
    }
}

class hiloPartida extends Thread {
    private Socket Cli1;
    private Socket Cli2;
    private OutputStream os1;
    private InputStream is1;
    private OutputStream os2;
    private InputStream is2;
    
    public hiloPartida(Socket _socket, Socket __socket) throws IOException 
    {
        Cli1 = _socket;
        os1 = Cli1.getOutputStream();
        is1 = Cli1.getInputStream();
        Cli2 = __socket;
        os2 = Cli2.getOutputStream();
        is2 = Cli2.getInputStream();
    }
    
    @Override
    public void run()
    {
        
    }
}