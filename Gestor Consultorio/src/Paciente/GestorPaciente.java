package Paciente;

import Modelo.Direccion;
import Usuario.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class GestorPaciente{
    private final String ARCHIVO_PACIENTE="Pacientes.bin";
    private HashSet<Paciente>listadoPacientes;
    //
    public GestorPaciente() {
        this.listadoPacientes = new HashSet<>();
    }
    public String agregarPaciente(Paciente paciente){
        this.listadoPacientes.add(paciente);
        return "Se creo el paciente correctamente!";
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
    public void leerPaciente() {
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(ARCHIVO_PACIENTE);
            objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Paciente aux = (Paciente) objectInputStream.readObject();
                    listadoPacientes.add(aux);
                } catch (EOFException ex) {
                    // Hemos llegado al final del archivo, salir del bucle
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
            FileOutputStream fileOutputStream=new FileOutputStream(ARCHIVO_PACIENTE);
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
    public void mostrarPacientes(){
        Iterator<Paciente>iterator= listadoPacientes.iterator();
        while (iterator.hasNext()){
            Paciente aux=iterator.next();
            System.out.println(aux.toString());
        }
    }
}
