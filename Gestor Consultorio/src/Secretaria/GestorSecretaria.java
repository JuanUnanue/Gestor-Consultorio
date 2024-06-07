package Secretaria;

import Modelo.GestorInformacion;
import Paciente.Paciente;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class GestorSecretaria extends GestorInformacion {
    private final String ARCHIVO_SECRETARIA="Secretarias.bin";
    private HashSet<Secretaria> listadoSecretarias;

    public GestorSecretaria(HashSet<Secretaria> listadoSecretarias) {
        this.listadoSecretarias = listadoSecretarias;
    }

    public GestorSecretaria() {
        listadoSecretarias=new HashSet<>();
    }
    ///
    public void agregarSecretaria(Secretaria secretaria){
        listadoSecretarias.add(secretaria);
    }
    public Secretaria buscarSecretaria(int dni){
        Iterator<Secretaria>iterator=listadoSecretarias.iterator();
        Secretaria rta=new Secretaria();
        while (iterator.hasNext()){
            Secretaria aux=iterator.next();
            if(aux.getDni()==dni){
                rta=aux;
            }
        }
        return rta;
    }
    public void leerListado() {
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(ARCHIVO_SECRETARIA);
            objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Secretaria aux = (Secretaria) objectInputStream.readObject();
                    listadoSecretarias.add(aux);
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
    public void guardarListado(){
        ObjectOutputStream objectOutputStream=null;
        Iterator<Secretaria> iterator = listadoSecretarias.iterator();
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(ARCHIVO_SECRETARIA);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            while (iterator.hasNext())
            {
                Secretaria aux = iterator.next();
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
    public void mostrarSecretarias(){
        Iterator<Secretaria>iterator= listadoSecretarias.iterator();
        while (iterator.hasNext()){
            Secretaria aux=iterator.next();
            System.out.println(aux.toString());
        }
    }
}
