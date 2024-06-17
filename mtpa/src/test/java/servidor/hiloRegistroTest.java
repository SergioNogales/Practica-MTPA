/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package servidor;

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
public class hiloRegistroTest {
    
    public hiloRegistroTest() {
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
     * Test of crearFichero method, of class hiloRegistro.
     */
    @Test
    public void testCrearFichero() {
        System.out.println("crearFichero");
        hiloRegistro.crearFichero();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarUsuario method, of class hiloRegistro.
     */
    @Test
    public void testBuscarUsuario() throws Exception {
        System.out.println("buscarUsuario");
        String username = "";
        hiloRegistro instance = null;
        String expResult = "";
        String result = instance.buscarUsuario(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarUsuario method, of class hiloRegistro.
     */
    @Test
    public void testRegistrarUsuario() throws Exception {
        System.out.println("registrarUsuario");
        String username = "";
        String password = "";
        hiloRegistro instance = null;
        instance.registrarUsuario(username, password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class hiloRegistro.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        hiloRegistro instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
