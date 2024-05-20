package com.practicamtpa;


import java.net.*;
import java.io.*;
public class TCPClient {
    public static void main(String[] args) 
        throws Exception{
        
        Socket cliente = new Socket("localhost", 7894); 
        if(cliente == null){
            System.out.println("Error al iniciar el Socket");
        }
        String peticion = null;
        boolean menu = true;
        Scanner sc = new Scanner(System.in);
        while(menu)
        {
            System.out.println("Bienvenido a la calculadora online.");
            System.out.println("Introduzca el primer operando: ");
            int op1 = sc.nextInt();
            System.out.println("Introduzca el segundo operando: ");
            int op2 = sc.nextInt();
            peticion = op1 + "@" + op2 + "@";
            System.out.println("MENU");
            System.out.println("------");
            System.out.println("1.Sumar");
            System.out.println("2.Restar");
            System.out.println("3.Multiplicar");
            System.out.println("4.Dividir");
            System.out.println("0.Salir");
            
            System.out.println("\nSeleccion: ");
            int seleccion = sc.nextInt();
            
            switch(seleccion){
                case 1:{
                    peticion = peticion + "@SUM@";
                    break;
                }
                case 2:{
                    peticion = peticion + "@RES@";
                    break;
                }
                case 3:{
                    peticion = peticion + "@MUL@";
                    break;
                }
                case 4:{
                    peticion = peticion + "@DIV@";
                    break;
                }
                case 0:{
                    menu = false;
                    break;
                }
                default :{
                    System.out.println("No se encuentra tal opción en el menú");
                }
            }
        }
        byte[] peticionBytes = peticion.getBytes();
        DataInputStream in = new DataInputStream(cliente.getInputStream());
        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
        out.write(peticionBytes);
        System.out.println("Petición Realizada...");
        byte respuesta[] = new byte[64];
        int nb = in.read(respuesta);
        System.out.println("Me Dice: " + new String(respuesta, 0, nb));
    }
}