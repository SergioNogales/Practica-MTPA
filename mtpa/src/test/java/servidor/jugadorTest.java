/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package servidor;

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

/**
 *
 * @author Sergio
 */
public class jugadorTest {
    
    public jugadorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getSocket method, of class jugador.
     */
    @Test
    public void testGetSocket() {
        System.out.println("getSocket");
        jugador instance = null;
        Socket expResult = null;
        Socket result = null;
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOs method, of class jugador.
     */
    @Test
    public void testGetOs() {
        System.out.println("getOs");
        jugador instance = null;
        OutputStream expResult = null;
        OutputStream result = instance.getOs();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOOs method, of class jugador.
     */
    @Test
    public void testGetOOs() throws Exception {
        System.out.println("getOOs");
        jugador instance = null;
        ObjectOutputStream expResult = null;
        ObjectOutputStream result = instance.getOOs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOIs method, of class jugador.
     */
    @Test
    public void testGetOIs() throws Exception {
        System.out.println("getOIs");
        jugador instance = null;
        ObjectInputStream expResult = null;
        ObjectInputStream result = instance.getOIs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIs method, of class jugador.
     */
    @Test
    public void testGetIs() {
        System.out.println("getIs");
        jugador instance = null;
        InputStream expResult = null;
        InputStream result = instance.getIs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class jugador.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        jugador instance = null;
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
