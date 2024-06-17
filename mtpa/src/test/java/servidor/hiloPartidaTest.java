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
public class hiloPartidaTest {
    
    public hiloPartidaTest() {
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
     * Test of run method, of class hiloPartida.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        hiloPartida instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of comprobador method, of class hiloPartida.
     */
    @Test
    public void testComprobador_0args() throws Exception {
        System.out.println("comprobador");
        hiloPartida instance = null;
        boolean expResult = false;
        boolean result = instance.comprobador();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of turno method, of class hiloPartida.
     */
    @Test
    public void testTurno() throws Exception {
        System.out.println("turno");
        jugador player = null;
        hiloPartida instance = null;
        instance.turno(player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inicializarTablero method, of class hiloPartida.
     */
    @Test
    public void testInicializarTablero() {
        System.out.println("inicializarTablero");
        hiloPartida instance = null;
        instance.inicializarTablero();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of comprobador method, of class hiloPartida.
     */
    @Test
    public void testComprobador_intArrArr() {
        System.out.println("comprobador");
        int[][] tablero = null;
        hiloPartida instance = null;
        String expResult = "";
        String result = instance.comprobador(tablero);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class hiloPartida.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        hiloPartida instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarPartida method, of class hiloPartida.
     */
    @Test
    public void testRegistrarPartida() {
        System.out.println("registrarPartida");
        hiloPartida hp = null;
        hiloPartida instance = null;
        instance.registrarPartida(hp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarResultado method, of class hiloPartida.
     */
    @Test
    public void testRegistrarResultado() {
        System.out.println("registrarResultado");
        String username = "";
        char resultado = ' ';
        hiloPartida instance = null;
        instance.registrarResultado(username, resultado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
