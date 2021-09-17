
/*
Universidad del Valle de Guatemala
Programación Orientada a objetos
Autor: Angel Gabriel Perez Figueroa
Carné: 21298
Maestro: Tomás Gálvez
Programa: Visual Studio Code
*/ 

import java.util.Scanner;

//------------------------------------------------------------------------------------------------
public class Vista {
    private Scanner sc = new Scanner(System.in); 
    private Scanner string = new Scanner(System.in); 
    
    public int menu(){//menu de opciones
        System.out.println("(1). Ciclo de Reloj");
        System.out.println("(2). Estado de Ram (espacios)");
        System.out.println("(3). Informacion");
        System.out.println("(4). Agregar Programa");
        System.out.println("(5). Datos de programa");
        System.out.println("(6). Salir");
        
        int opcion = sc.nextInt();
        return opcion;
    }
    
    //------------------------------------------------------------------------------------------------
    public String[] ProgramaDatos(){//Se le piden los datos al usuario para el funcionamiento del programa
        
        String[] datos = new String[3];
        System.out.println("Nombre del programa: ");
        datos[0] = string.nextLine();
        
        System.out.println("Tamano del programa en MBs de la RAM: ");
        datos[1] = String.valueOf(sc.nextInt());
        
        System.out.println("Ciclos activos del programa: ");
        datos[2] = String.valueOf(sc.nextInt());
        return datos;
    }
    
    //------------------------------------------------------------------------------------------------
    public void print(String mensaje){//mostrar mensaje al usuario
        
        System.out.println(mensaje);
    }

    //------------------------------------------------------------------------------------------------
    public String[] memoriaRAMDatos(){//datos para la memoria RAM elegida
        String[] datos = new String[2]; 
        boolean pedirtipo = true; 
        
        System.out.println("¿Que tipo de memoria RAM desea implementar?");
        System.out.println("(1). SDR");
        System.out.println("(2). DDR");
        int tipo = sc.nextInt();
        
        switch(tipo){
            
            case 1 ->{
                datos[0] ="SDR";
                boolean pedirtamano = true;
                
                
                while(pedirtamano){
                    System.out.println("Ingrese el tamaño: ");
                    System.out.println("(1). 4GB ");
                    System.out.println("(2). 8GB ");
                    System.out.println("(3). 16GB ");
                    System.out.println("(4). 32GB ");
                    System.out.println("(5). 64GB ");
                    int size = sc.nextInt();
                    
                    switch(size){
                        case 1-> {
                            datos[1] = String.valueOf(64);
                            pedirtamano = false;
                            break; 
                        }
                        
                        case 2-> {
                            datos[1] = String.valueOf(128);
                            pedirtamano = false;
                            break; 

                        }
                        
                        case 3-> {
                            datos[1] = String.valueOf(256);
                            pedirtamano = false;
                            break; 

                        }
                        
                        case 4-> {
                            datos[1] = String.valueOf(512);
                            pedirtamano = false;
                            break; 

                        }
                        
                        case 5-> {
                            datos[1] = String.valueOf(1024);
                            pedirtamano = false;
                            break; 

                        }
                        
                        default ->{ //en caso de que el usuario ingrese un valor que no cuadra con los solicitados
                            System.out.println("tamano no permitido");
                            break; 
                        }
                    }
                }
                
                
                pedirtipo = false;
                break; 
                
            }
            
            
            case 2-> {
                datos[0] ="DDR";
                datos[1] = String.valueOf(64);
                pedirtipo = false;
                break; 
                
            }
            
             
            
            default -> {
                
                System.out.println("ERROR: RAM no valida, intente de nuevo");
                
            }
        }
        
        return datos;  
    
    }

    //------------------------------------------------------------------------------------------------
    public String buscarprograma(){//pedir nombre del programa que se desea buscar
        
        System.out.println("Ingrese el nombre del programa que desea buscar");
        String nombre = string.nextLine();
        return nombre.toUpperCase(); 
    }

    //------------------------------------------------------------------------------------------------
    public void mismatch(){//usando next, se le pedira al usuario que vuelva a ingresar un dato correcto del menu, en caso de haber cometido un error.
        
        sc.next(); 
    
    }

    //------------------------------------------------------------------------------------------------
    public String directorio(){//Se solicita la ubicacion del archivo donde se mostra el análisis
        
        System.out.println("Ingrese la ubicación del Archivo 'Simulacion.txt' ");
        System.out.println("Al ingresar la ubicación, agregar 'Diagonal invertida(\\)' al final de esta ");
        System.out.println("Ejemplo: C:\\Users\\angel\\OneDrive\\Documentos\\POO\\Laboratorio2");
        String directorio = string.nextLine();
        return directorio; 
    }   
}