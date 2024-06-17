package servidor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase jugador.
 */
public class jugadorTest {
    
    private Socket socketMock;
    private jugador instance;
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        // Crear un socket falso usando Mockito
        socketMock = mock(Socket.class);
        
        // Configurar el comportamiento del socket para devolver flujos de entrada y salida falsos
        when(socketMock.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));
        when(socketMock.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        
        // Crear una instancia de jugador con el socket falso
        instance = new jugador(socketMock, "testUser");
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetSocket() {
        System.out.println("getSocket");
        Socket result = instance.getSocket();
        assertEquals(socketMock, result);
    }

    @Test
    public void testGetOs() throws Exception {
        System.out.println("getOs");
        OutputStream result = instance.getOs();
        assertNotNull(result);
        assertTrue(result instanceof ByteArrayOutputStream);
    }

    @Test
    public void testGetOOs() throws Exception {
        System.out.println("getOOs");
        ObjectOutputStream result = instance.getOOs();
        assertNotNull(result);
    }

    @Test
    public void testGetOIs() throws Exception {
        System.out.println("getOIs");
        ObjectInputStream result = instance.getOIs();
        assertNotNull(result);
    }

    @Test
    public void testGetIs() throws Exception {
        System.out.println("getIs");
        InputStream result = instance.getIs();
        assertNotNull(result);
        assertTrue(result instanceof ByteArrayInputStream);
    }

    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        String expResult = "testUser";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }
}