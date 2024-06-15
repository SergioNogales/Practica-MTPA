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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
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
        initserver();  
    }
    
    public void run() {
        try {
            boolean salir = false;
            Scanner scanner = new Scanner(System.in);

            while (!salir) {
                System.out.println("MENU SERVIDOR");
                System.out.println("-------------");
                System.out.println("1. Lista de Usuarios Conectados");
                System.out.println("2. Parar el servidor");
                System.out.print("Seleccione una opcion: ");

                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Lista de Usuarios Conectados (a implementar)");
                        break;
                    case 2:
                        System.out.println("Parando servidor...\n\n");
                        servidor.close();
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
                        break;
                }
            }  
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void initserver() {
        try {
            System.out.println("Servidor encendido");
            while (true) {
                Socket sck = servidor.accept();
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
            ArrayList<jugador> jugadores = new ArrayList<jugador>();
            
            input.read(buffer);
            mensajeEntrante = new String(buffer).trim();
            // Asignamos el
            switch (mensajeEntrante) {
                case "register":
                    hiloRegistro hR = new hiloRegistro(sck);
                    hR.run();
                    break;
                case "login":
                    hiloLogin hL = new hiloLogin(sck);
                    hL.run();
                    break;
                case "jugar":
                    //Cogemos como mensaje entrante el username del usuario
                    buffer = new byte[1024];
                    input.read(buffer);
                    mensajeEntrante = new String(buffer).trim();
                    //Creamos el objeto jugador
                    //jugadores.add(new jugador(sck, mensajeEntrante));
                    String[] arrayJugadores = new String[10];
                    //for (int i = 0; i < 10; i++) {
                        arrayJugadores[0] = mensajeEntrante;
                        arrayJugadores[1] = "Raquel";
                        String arrayJugadoresString = String.join(",", arrayJugadores);
                        output.write(arrayJugadoresString.getBytes());
                        //System.out.println(arrayJugadores[i]);
                    //}
                    break;
                case "meboi":
                    //cogemos el usuario a quitar de la lista
                    buffer = new byte[1024];
                    input.read(buffer);
                    mensajeEntrante = new String(buffer).trim();
                    //Eliminamos el objeto
                    for(int i = 0; i<=jugadores.size(); i++)
                    {
                        jugador tmp = (jugador) jugadores.toArray()[i];
                        if(tmp.getUsername() == mensajeEntrante)
                        {
                            jugadores.remove(jugadores.toArray()[i]);
                        }
                    }
                    break;
                default:
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
    
    public static void crearFichero() {
        File carpeta = new File("./TresEnRaya");
        carpeta.mkdirs();

        File archivo = new File("./TresEnRaya/registro.txt");
        if (!archivo.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                bw.write("A partir de aquí se escribirán todos los usuarios registrados, siguiendo el siguiente formato. login;password\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String buscarUsuario(String username)throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("./TresEnRaya/registro.txt"));
        String line;
        while ((line = br.readLine()) != null) 
        {
            if(line.contains(username))
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
        BufferedWriter bw = new BufferedWriter(new FileWriter("./TresEnRaya/registro.txt", true));
        bw.write(username + ";" + password);
        bw.newLine();
        bw.close();
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
            while(true)
            {
                byte[] buffer = new byte[1024];
                //Recibimos del socket el usuario y contraseña
                is.read(buffer);
                String login = new String(buffer).trim();
                byte[]buffer2 = new byte[1024];
                is.read(buffer2);
                String password = new String(buffer2).trim();
                //Si encontramos un usuario ya registrado con ese nombre le denegamos la petición de registro
                if(buscarUsuario(login) == null)
                {
                    registrarUsuario(login, password);
                    os.write("okR".getBytes(), 0, "okR".getBytes().length);
                    break;
                }
                else
                {
                    os.write("denied".getBytes(), 0, "denied".getBytes().length);
                }
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
    
    
    public String buscarUsuario(String username) 
            throws FileNotFoundException, IOException 
    {
        BufferedReader br = new BufferedReader(new FileReader("./TresEnRaya/registro.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.split(";")[0].equals(username)) {
                br.close();
                return line;
            }
        }
        br.close();
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
            while(true)
            {
                byte[] buffer = new byte[1024];
                //Recibimos del socket el usuario y contraseña
                is.read(buffer);
                String login = new String(buffer).trim();
                byte[]buffer2 = new byte[1024];
                is.read(buffer2);
                String password = new String(buffer2).trim();
                //Si encontramos que no hay un usuario registrado con ese login denegamos la entrada
                String usuario = buscarUsuario(login);
                if (usuario != null) {
                    String[] parts = usuario.split(";");
                    if (parts[1].equals(password)) {
                        os.write("okL".getBytes());
                    } else {
                        os.write("denied".getBytes());
                    }
                } else {
                    os.write("denied".getBytes());
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(hiloRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class jugador{
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private OutputStream os;
    private InputStream is;
    private String username;
    
    public jugador(Socket _socket, String _username) throws IOException 
    {
        socket = _socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        username = _username;
        
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOOs() {
        return out;
    }

    public ObjectInputStream getOIs() {
        return in;
    }
    
    public OutputStream getOs() {
        return os;
    }

    public InputStream getIs() {
        return is;
    }

    public String getUsername() {
        return username;
    }
}

class hiloPartida extends Thread {

    private jugador p1;
    private jugador p2;
    private int[][] tablero = new int[3][3];
    
    public hiloPartida(jugador _p1, jugador _p2) throws IOException 
    {
        p1 = _p1;
        p2 = _p2;
    }
    
    @Override
    public void run()
    {
        inicializarTablero();
        while(true)
        {
            try {
                turno(p1);
                if(comprobador() == true)
                {
                    return;
                }
                turno(p2);
                if(comprobador() == true)
                {
                    return;
                }
            } catch (IOException ex) {
                Logger.getLogger(hiloPartida.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(hiloPartida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean comprobador() throws IOException
    {
        if(comprobador(tablero)!= "no")
        {
            switch (comprobador(tablero)){
                case "x":{
                    p1.getOs().write("x".getBytes());
                    p2.getOs().write("x".getBytes());
                }
                case "o":{
                    p1.getOs().write("o".getBytes());
                    p2.getOs().write("o".getBytes());
                }
            }
            p1.getOOs().writeObject(tablero);
            p2.getOOs().writeObject(tablero);
            return true;
        }
        return false;
    }
    
    public void turno (jugador player) throws IOException, ClassNotFoundException
    {
        player.getOs().write("mueve".getBytes());
        player.getOOs().writeObject(tablero);
        tablero = (int[][])player.getOIs().readObject();
    }
    
    public void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = 0;
            }
        }
    }
    
    public String comprobador(int[][] tablero)
    {
        
        for(int i = 0; i<3; i++)
        {
            //horizontal
            if(tablero[i][0] == tablero[i][1] && tablero[i][0] == tablero[i][2] && tablero[i][0] != 0)
            {
                if(tablero[i][0] == 1)
                {
                    return "x";
                }
                else{
                    return "o";
                }
            }
            //vertical
            if(tablero[0][i] == tablero[1][i] && tablero[0][i] == tablero[2][i] && tablero[0][i] != 0)
            {
                if(tablero[0][i] == 1)
                {
                    return "x";
                }
                else{
                    return "o";
                }
            }
            //diagonal
            if(tablero[0][0] == tablero[1][1] && tablero[0][0] == tablero[2][2] && tablero[2][2] != 0)
            {
                if(tablero[0][i] == 1)
                {
                    return "x";
                }
                else{
                    return "o";
                }
            }
            //diagonal opuesta
            if(tablero[0][2] == tablero[1][1] && tablero[0][2] == tablero[2][0] && tablero[0][2] != 0)
            {
                if(tablero[0][i] == 1)
                {
                    return "x";
                }
                else{
                    return "o";
                }
            }
        }
        return "no";
    }
}