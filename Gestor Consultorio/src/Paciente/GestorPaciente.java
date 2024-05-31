package Paciente;

import Modelo.Direccion;
import Usuario.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class GestorPaciente{
    private HashSet<Paciente>listadoPacientes;
    private Scanner scanner;
    //
    public GestorPaciente() {
        this.listadoPacientes = new HashSet<>();
        this.scanner=new Scanner(System.in);
    }
    public void crearPaciente(){
        boolean flag=true;
        int dni=0;
        while (flag){
            System.out.println("Ingrese D.N.I: ");
            dni=scanner.nextInt();
            boolean rta=buscarDNI(dni);
            if (!rta){
                flag=false;
            }else {
                System.out.println("Ya existe un paciente con ese numero de documento. Intente nuevamente");
            }
        }
        System.out.println("Ingrese Nombre: ");
        String nombre=scanner.next();
        System.out.println("Ingrese Apellido: ");
        String apellido=scanner.next();
        System.out.println("Ingrese fecha de nacimiento.\nDia:  ");
        int dia=scanner.nextInt();
        System.out.println("Mes:");
        int mes=scanner.nextInt();
        System.out.println("Año:  ");
        int año=scanner.nextInt();
        System.out.println("Ingrese Calle: ");
        String calle=scanner.next();
        System.out.println("Ingrese Numero: ");
        int nro=scanner.nextInt();
        System.out.println("Ingrese Ciudad: ");
        String ciudad=scanner.next();
        listadoPacientes.add((new Paciente(nombre,apellido, LocalDate.of(año,mes,dia),dni,new Direccion(calle,nro,ciudad))));
        scanner.close();
    }
    public boolean buscarDNI(int DNI){   //Devuelve verdadero o falso dependiendo de si ya existe en el sistema
        Iterator<Paciente>iterator=listadoPacientes.iterator();
        boolean rta=false;
        while (iterator.hasNext()){
            Paciente aux=iterator.next();
            if(aux.getDni()==DNI){
                rta=true;
            }
        }
        return rta;
    }
    public void leerPaciente() { //Trae del archivo los pacientes a un HashSet
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("Pacientes.bin");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Paciente aux;
            while (true) {
                try {
                    aux = (Paciente) objectInputStream.readObject();
                    listadoPacientes.add(aux);
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void guardarPacientes(){
        ObjectOutputStream objectOutputStream=null;
        Iterator<Paciente> iterator = listadoPacientes.iterator();
        try{
            FileOutputStream fileOutputStream=new FileOutputStream("Pacientes.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            while (iterator.hasNext())
            {
                Paciente aux = iterator.next();
                objectOutputStream.writeObject(aux);
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            try
            {
                objectOutputStream.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

        }
    }
    private int leerEntero(String campo) {
        while (true) {
            System.out.println("Ingrese " + campo + ": ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Entrada no válida. Por favor ingrese un número entero.");
                scanner.next(); // Limpiar la entrada no válida
            }
        }
    }
    public void mostrarPacientes(){
        Iterator<Paciente>iterator= listadoPacientes.iterator();
        while (iterator.hasNext()){
            Paciente aux=iterator.next();
            System.out.println(aux.toString());
        }
    }
}
