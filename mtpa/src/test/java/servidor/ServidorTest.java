/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package servidor;

import java.io.IOException;
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
public class ServidorTest {
    
    public ServidorTest() {
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
     * Test of run method, of class Servidor.
     */
    @Test
    public void testRun() throws IOException {
        System.out.println("run");
        Servidor instance = new Servidor();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinish method, of class Servidor.
     */
    @Test
    public void testGetFinish() throws IOException {
        System.out.println("getFinish");
        Servidor instance = new Servidor();
        int expResult = 0;
        int result = instance.getFinish();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of initserver method, of class Servidor.
     */
    @Test
    public void testInitserver() throws IOException {
        System.out.println("initserver");
        Servidor instance = new Servidor();
        instance.initserver();
        fail("The test case is a prototype.");
    }


    /**
     * Test of buscarJugador method, of class Servidor.
     */
    @Test
    public void testBuscarJugador() throws Exception {
        System.out.println("buscarJugador");
        String username = "";
        Servidor instance = new Servidor();
        jugador expResult = null;
        jugador result = instance.buscarJugador(username);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
