package Modelo;

import Paciente.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class GestorPacientePresente {
    private static final String ARCHIVO_PRESENTES = "Presentes.dat";

    public GestorPacientePresente() {
    }

    public void guardarPresentes(HashMap<String, ArrayList<Paciente>> presentes) {
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ARCHIVO_PRESENTES);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(presentes);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public HashMap<String, ArrayList<Paciente>> leerPresentes() {
        HashMap<String, ArrayList<Paciente>> presentes = null;
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(ARCHIVO_PRESENTES);
            objectInputStream = new ObjectInputStream(fileInputStream);
            presentes = (HashMap<String, ArrayList<Paciente>>) objectInputStream.readObject();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return presentes;
    }
}
