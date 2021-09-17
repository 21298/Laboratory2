/*
Universidad del Valle de Guatemala
Programación Orientada a objetos
Autor: Angel Gabriel Perez Figueroa
Carné: 21298
Maestro: Tomás Gálvez

*/ 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

//------------------------------------------------------------------------------------------------

public class Controlador{
    
    //------------------------------------------------------------------------------------------------
    public static void main(String args[]) {
    
        BufferedReader BR = null;
        Vista vista = new Vista(); 
        memoriaRAM ram = null; 
        boolean permitir = false; //deja ingresar un nuevo programa en la simulación
        
        while(!permitir){
        
            //------------Try/Catch en caso de que no se haya ingresado correctamente un dato----------------------------------------
            try{
                String[] datos = vista.memoriaRAMDatos(); 
                ram = new memoriaRAM(Integer.valueOf(datos[1]), datos[0]); 
                permitir = true; 
            }
            
            catch (Exception e){
                vista.print("ERROR: dato incorrecto, por favor intente de nuevo");
                vista.mismatch();
            }
        }
        
        String line;  
        
        //----------------Try/Catch para observar si el archivo fue creado con éxito no----------------------------------------
        try{
            BR = new BufferedReader(new FileReader(vista.directorio()+"Simulacion.txt"));
             
        }
        catch(FileNotFoundException nt){
            System.out.println("El archivo no fue encontrado, por favor verifique sus datos");
            System.exit(0);
             
        }
        
        //----------------Try/Catch en caso de que no se pueda ingresar un nuevo programa----------------------------------------
        try{ 
        
            while((line = BR.readLine()) != null){
              String[] lineto = line.split(",");
              Programa c = new Programa(lineto);
              ram.addPrograma(c);
            }
                   
        }
        catch(IOException io){
            System.out.println("ERROR: acción no permitida");
            System.exit(0);
             
        }           
        
        //--------------Try/Catch en caso de que no se ingrese la información solicitada--------------------
        try{
             
            int opcion = 0;
            while(opcion != 6){
                opcion = vista.menu();
                switch(opcion){
                    
                    //realiza un ciclo de reloj
                    case 1 -> {
                        vista.print(ram.period());
                    }
 
                    //disponibilidad en la RAM
                    case 2 -> {
                        ArrayList<String> compilar = ram.getStatus();
                        for(String c: compilar){
                            if(c == null){
                                vista.print("Disponibilidad: Acción Permitida");
                            }
                            else{
                                vista.print(c);
                            }
                        }
                         
                         
                    }
                    //datos de en la memoria RAM
                    case 3-> {
                        String[] Vista = ram.getdatos();
                        vista.print(Vista[2]);
                        vista.print(Vista[3]);
                         
                         
                    }
                    //Agregar Programa
                    case 4 -> {
                        try{
                            String[] datos = vista.ProgramaDatos();
                            Programa c = new Programa(datos);
                            boolean stats = ram.addPrograma(c);
                            if (stats){
                                vista.print("Programa agregado existosamente");
                            }
                            else{
                                vista.print("El programa ha sido colocado en la cola de espera");
                            }
                        }
                        
                        catch(InputMismatchException ei){
                            vista.print("ERROR: se le ha solicitado un dato numérico");
                        }catch(Exception e){
                            vista.print("ERROR INESPERADO, por favor intente de nuevo");
                        }
                    }
                    
                    //buscar un programa
                    case 5-> {
                        String nombre = vista.buscarprograma();
                        String[] datos = ram.buscarprograma(nombre);
                        if(datos == null){
                            vista.print("ERROR: El programa no está en ejecución");
                        }
                        else{
                            vista.print("Nombre: " + datos[0]);
                            vista.print("Tamaño en 'mbs': " + datos[1] );
                            vista.print("ciclos del programa: " + datos[2]);
                            vista.print("Espacios ocupados: " + datos[3]);
                        }
                    }
                }
            }
        
        }
        
        catch (InputMismatchException ie){
            vista.print("ERROR: dato no permitido");
        }
    
    }

}