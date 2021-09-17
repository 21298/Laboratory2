/*
Universidad del Valle de Guatemala
Programación Orientada mem objetos
Autor: Angel Gabriel Perez Figueroa
Carné: 21298
Maestro: Tomás Gálvez
Programa: Visual Studio Code
*/ 

import java.util.ArrayList;

//------------------------------------------------------------------------------------------------
public class memoriaRAM{
    private ArrayList<Programa> compile = new ArrayList<>();
    private ArrayList<Programa> queue= new ArrayList<>();
    private ArrayList<String> Spaces = new ArrayList<>();
    private int Espacios;
    private int disponibilidad; 
    private int ciclo = 0; 
    private String tipo; 
    
    memoriaRAM(int size, String type){
        tipo = type;
        Espacios = size;
        disponibilidad = size; 
        
        for (int i = 0; i < Espacios; i++){//Muestra los espacios libres en la memoria
        Spaces.add(null);
        }
    }
    
    //----------------------------Clase para poder agregar un programa nuevo-------------------------------------
    //Agregar Programa
    
    public boolean addPrograma(Programa period){//Programa en un nuevo ciclo
        
        String[] datos = period.getdatos();
        boolean datosss = false; 
        if(Integer.valueOf(datos[3]) <=  disponibilidad){
            
            period = new Programa(ciclo, datos); //nuevo ciclo en el programa
            datosss = true; 
            compile.add(period); 
            int x = 0;
            while(x<Integer.valueOf(datos[3])){
                
                for(String i: Spaces){
                    
                    if(i == null){
                        int indice = Spaces.indexOf(i); 
                        Spaces.set(indice, datos[0]);
                        break;
                    }
                }
                x++;
                disponibilidad--; 
            
            } 
        }
        else{
            if(queue.contains(period)){// mientras contenga el ciclo
            }
            else {
                queue.add(period);//agregar un nuevo ciclo
            }
        }
        return datosss;
    }
    
    
    //----------------------------Obtener nuevos espacios----------------------------------------------------------
    public ArrayList<String> getStatus(){ 
        return Spaces; 
    }
    
    
    //-------------------------------Funcioón para realizar los cilcos de reloj----------------------------------------------
    public String period() {
        
        //Incremento del cilco    
        ciclo++;
        String msj = "Ciclo de reloj No: " + String.valueOf(ciclo) + "\n";
        
        //Quitar programas que hayan excedido su tiempo
        for (int i = 0; i < compile.size() - 1; i++) //Pbservar los programar recorridos
            {
            Programa period = compile.get(i);
            if(period!= null){
                String[] datos = period.getdatos();
                
                if (period.getExit() == ciclo){
                    
                    //Observar la lista con los espacios ocupados y disponibles
                    for(int x = 0; x < Spaces.size() -1 ; x++){
                        
                        if(datos[0].equals(Spaces.get(x))){
                            
                            msj = msj + "El programa : "+ datos[0] +" terminó " + "\n"; //el programa se remueve al exceder su tienmpo
                            Spaces.set(x,null);
                            
                        }
                    }
                    compile.remove(i); //Quitar el arralist
                    i--; // contador para poder remover el arraylist
                    disponibilidad = disponibilidad + Integer.valueOf(datos[3]);
                    
                }
            }
            }

        //remover programas de cola 
        if(!queue.isEmpty()){ //Lista vacía
            
            for (int i = 0; i < queue.size(); i++){
                
                Programa period = queue.get(i);
                if(period != null){
                    
                    String[] datos = period.getdatos();
                    boolean addPrograma = addPrograma(period);
                
                //Si se pudo detener la ejecucion de cierto programa, quitarlo de la cola
                if(addPrograma){
                    
                    msj = msj + "El programa : "+ datos[0] +" ha sido inicializado " + "\n";
                    queue.remove(i);
                    i--; 
                } 
                else{
                    if ("DDR".equals(this.tipo)){
                        
                        //Agrandar y agregar de nuevo el programa
                        String add =var(0);
                        boolean try2 = addPrograma(period);
                        if(try2){
                            msj = msj + "El programa : "+datos[0] +" ha sido inicializado " + "\n";
                            queue.remove(i);
                            i--; 
                        } 
                        else{
                            //aumentar en tamaño para permitir el acceso del programa
                            while(add != "Máximo" ){
                                add =var(0);
                                try2 = addPrograma(period);
                                
                                if(try2){
                                    
                                    msj = msj + "El programa : "+ datos[0] +" ha sido inicializado " + "\n";
                                    queue.remove(i);
                                    i--; 
                                break;
                                }

                            }
                        }

                    }

                }
                }
            }
        }
        
        //En caso de ser memoria tipo DDR, verificar que se pueda hacer mas pequena
        if("DDR".equals(this.tipo)){
            var(1);  
        }
        
        return msj; 
    }
     
    //------------------------------Obedetener todos los datos------------------------------------------------
    public String[] getdatos(){
        
        String datos[] = new String[5];
        int GB = (Espacios * 64)/1024; 
        datos[0] = "Tamaño de memoria en GB: " + String.valueOf(GB) + "\n"; 
        datos[0] = datos[0] + "Espacios de RAM: " + String.valueOf(this.Espacios) + "\n";
        datos[0] = datos[0] + "Espacios utilizados" + String.valueOf(Espacios - disponibilidad); 
        datos[1]= "Disponibilidad de espacios: " + String.valueOf(this.disponibilidad) + "\n";
        
        
        String compilacion = "Programas usados actualmente: \n"; //Programas que estan siendo ejecutados actualmente
        for(Programa period: compile){
           
            String[] compdatos = period.getdatos();
           compilacion = compilacion + compdatos[0] + " Espacios usados: " + String.valueOf(compdatos[3]) + "\n"; 
        }
        
        datos[2]  = compilacion;
        
        
        String cola = "Programas en cola \n";//Programas en cola
        for(Programa i :queue){
            String[] compdatos = i.getdatos();
            cola = cola + compdatos[0] + " espacios por emplear: " + String.valueOf(compdatos[3]) + "\n";
        }
        datos[3] = cola;
        
        
        return datos; 
    }
    
    //------------------------------------------------------------------------------------------------
    //Info sobre determinado programa
    public String[] buscarprograma(String nombre){
        
        String[] datos = null;
        for(Programa mem: compile){
            
            String[] datoss = mem.getdatos();
            if(nombre.equals(datoss[0])){
                
                datos = datoss; 
            }
        }
        return datos; 
    }

    //-------------------------------Modificar datos de memoria-----------------------------------------------
    private String var(int mem){
        
        String msj = null; 
        switch(mem){
            
            case 1-> {//Agrandar memoria
                if(this.Espacios < 1024){//Si está en el rango maximo de GB, es posible aun
                    
                    //para agarndar se duplica la cantidad de espacio disponible
                    int extamano = this.Espacios;
                    this.Espacios = this.Espacios * 2;
                    int fase = Espacios - extamano; 
                    this.disponibilidad = disponibilidad + fase ; //nuevos espacios con disponibilidad
                    for(int i = 0; i < fase ; i++){//dato de nuevos espacios
                        
                        Spaces.add(null);
                    }
                } 
                
                else{
                    msj = "Máximo"; 
                }
                
            }
            
            //Reducir su tamano
            case 2-> {
                
                //espacio utilizado
                int usado = 0; 
                for (int i = 0 ; i < Spaces.size() -1; i++){
                    
                    if(Spaces.get(i) != null){
                        
                        usado++;
                    }
                }
                
                int ant = this.Espacios; //guardar el tamano anterior
                
                //Marcar RAM seleccionada
                //-------------------------------------------------------------------------------------------------------------------
                if(usado <= 64 || usado == 0){//opcion1 = 4GB
                    this.Espacios = 64;
                    
                    //Disponibilidad de espacios
                    this.disponibilidad = Espacios - usado; 
                    } 
                    //-------------------------------------------------------------------------------------------------------------------
                    else if(usado <= 128 && usado > 64){//opcion2 = 8GB
                        this.Espacios = 128;
                        //Disponibilidad de espacios
                        this.disponibilidad = Espacios - usado; 
                    }
                    //------------------------------------------------------------------------------------------------------------------- 
                    else if(usado <= 256 && usado > 128){//opcion3 = 16GB
                        this.Espacios = 256;
                        //Disponibilidad de espacios
                        this.disponibilidad = Espacios - usado; 
                    }
                    //-------------------------------------------------------------------------------------------------------------------
                    else if(usado <= 512 && usado > 256){//opcion4 = 32GB
                        this.Espacios = 512;
                    }

                    //Disponibilidad de espacios para que el arryalist tenga la nueva capacidad de almacenamiento
                    this.disponibilidad = Espacios - usado; 
                    
                    
                    int fase = ant - Espacios; //quitar espacios que no fueron utilizados
                    int contador = 0; 
                    for(int i = 0; i < Spaces.size() - 1; i++){
                        
                        if(contador == fase){
                           
                            break; //terminar
                        }
                        else{
                            
                            if (Spaces.get(i) == null){ //completar con datos sin información
                                
                                Spaces.remove(i);
                                i--;
                                contador++;
                            }
                        }
                    }
            }
        }
    
        return msj; 
    
    }
    
    //-------------------------Información reciente-----------------------------------------------
    public int reciente(){
        
        return ciclo;
    }

}