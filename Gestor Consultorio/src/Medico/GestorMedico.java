package Medico;

import Modelo.Direccion;
import Modelo.Especialidad;
import Modelo.GestorInformacion;
import Modelo.Json;
import Secretaria.Secretaria;
import Turno.Turno;
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GestorMedico extends GestorInformacion {
    HashMap<Especialidad,HashSet<Medico>>listadoMedicos;
    //

    public GestorMedico() {
        listadoMedicos=new HashMap<>();
    }

    public GestorMedico(HashSet<Medico> listadoMedicos) {
        this.listadoMedicos=new HashMap<>();
    }

    public String agregarMedico(Medico medico) {
        HashSet<Medico> listado;
        if (listadoMedicos.containsKey(medico.getEspecialidad())) {
            listado = listadoMedicos.get(medico.getEspecialidad());
        } else {
            listado = new HashSet<>();
            listadoMedicos.put(medico.getEspecialidad(), listado);
        }

        boolean agregado = listado.add(medico);
        return agregado ? "Se creó el médico correctamente!" : "El médico ya existe en la lista.";
    }

    public HashMap<Especialidad,HashSet<Medico>> getListadoMedicos() {
        return listadoMedicos;
    }

    public boolean validacionMatricula(int matricula){   //Devuelve verdadero o falso dependiendo de si ya existe en el sistema
        Iterator<Map.Entry<Especialidad,HashSet<Medico>>> entryIterator=listadoMedicos.entrySet().iterator();
        boolean rta=false;
        while (entryIterator.hasNext() && !rta){
            Map.Entry<Especialidad,HashSet<Medico>> medicosMapa=entryIterator.next();
            HashSet<Medico>medicoHashSet=medicosMapa.getValue();
            Iterator<Medico>medicoIterator= medicoHashSet.iterator();
            while (medicoIterator.hasNext()){
                Medico aux=medicoIterator.next();
                if(aux.getMatricula()==matricula){
                    rta=true;
                }
            }
        }
        return rta;
    }

    public Medico buscarMedico(int matricula){   //Devuelve el medico
        Medico rta=new Medico();
        Iterator<Map.Entry<Especialidad,HashSet<Medico>>> entryIterator=listadoMedicos.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Especialidad, HashSet<Medico>> medicosMapa = entryIterator.next();
            HashSet<Medico> medicoHashSet = medicosMapa.getValue();
            Iterator<Medico> medicoIterator = medicoHashSet.iterator();
            while (medicoIterator.hasNext()) {
                Medico aux = medicoIterator.next();
                if (aux.getMatricula() == matricula) {
                    rta = aux;
                }
            }
        }
        return rta;
    }

    public String mostrarMedicos(){
        String rta="";
        Iterator<Map.Entry<Especialidad,HashSet<Medico>>> entryIterator=listadoMedicos.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<Especialidad,HashSet<Medico>> medicosMapa=entryIterator.next();
            HashSet<Medico>medicoHashSet=medicosMapa.getValue();
            Iterator<Medico>medicoIterator= medicoHashSet.iterator();
            while (medicoIterator.hasNext()) {
                Medico aux = medicoIterator.next();
                rta += aux.toString();
            }
        }
        return rta;
    }
    //
    public void guardarListado() {
        JSONArray jsonArray = new JSONArray();
        try {
            Iterator<Map.Entry<Especialidad,HashSet<Medico>>> entryIterator=listadoMedicos.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<Especialidad,HashSet<Medico>> medicosMapa=entryIterator.next();
                HashSet<Medico>medicoHashSet=medicosMapa.getValue();
                Iterator<Medico>iterator= medicoHashSet.iterator();
                while (iterator.hasNext()) {
                    Medico aux = iterator.next();
                    JSONObject jo = new JSONObject();
                    jo.put("apellido", aux.getApellido());
                    jo.put("nombre", aux.getNombre());
                    String fechaNacimiento = aux.getFechaNacimiento().format(DateTimeFormatter.ISO_LOCAL_DATE);
                    jo.put("fechaNacimiento", fechaNacimiento);
                    jo.put("dni", aux.getDni());
                    JSONObject joDireccion = new JSONObject();
                    joDireccion.put("calle", aux.getDireccion().getCalle());
                    joDireccion.put("numero", aux.getDireccion().getNumero());
                    joDireccion.put("ciudad", aux.getDireccion().getCiudad());
                    jo.put("direccion", joDireccion);
                    jo.put("matricula", aux.getMatricula());
                    String especialidad = aux.getEspecialidad().toString();
                    jo.put("especialidad", especialidad);
                    JSONArray agendaJson = new JSONArray();
                    HashMap<DayOfWeek,  HashSet<Turno>> turnos = aux.getAgenda().getTurnos();
                    Iterator<Map.Entry<DayOfWeek,  HashSet<Turno>>> iteratorTurnos = turnos.entrySet().iterator();
                    while (iteratorTurnos.hasNext()) {
                        Map.Entry<DayOfWeek,  HashSet<Turno>> entradaMapa = iteratorTurnos.next();
                        JSONArray turnosDia = new JSONArray();
                        DayOfWeek diaSemana = entradaMapa.getKey();
                        HashSet<Turno> turnosDelDia = entradaMapa.getValue();
                        Iterator<Turno>turnoIterator=turnosDelDia.iterator();
                        while (turnoIterator.hasNext()) {
                            Turno turno=turnoIterator.next();
                            JSONObject turnoJson = new JSONObject();
                            if (turno.getPaciente() != 0) {
                                turnoJson.put("dniPaciente", turno.getPaciente());
                            }
                            turnoJson.put("matriculaMedico", turno.getMatriculaMedico());
                            String fechaHora = turno.getFechaHora().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                            turnoJson.put("fechaHora", fechaHora);
                            turnoJson.put("disponibilidad", turno.isDisponible());
                            turnosDia.put(turnoJson);
                        }
                        JSONObject diaSemanaJson = new JSONObject();
                        diaSemanaJson.put("diaSemana", diaSemana.toString());
                        diaSemanaJson.put("turnos", turnosDia);
                        agendaJson.put(diaSemanaJson);
                    }
                    jo.put("agenda", agendaJson);
                    jsonArray.put(jo);
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Medicos", jsonArray);
            Json.grabar(jsonObject, "medicos");
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    public void leerListado(){
        try {
            JSONObject object  = new JSONObject(Json.leer("medicos"));
            JSONArray jo_array = object.getJSONArray("Medicos");

            for (int i=0;i<jo_array.length();i++)
            {
                JSONObject jo=jo_array.getJSONObject(i);
                String apellido= jo.getString("apellido");
                String nombre= jo.getString("nombre");
                String fechaNacimientoString= jo.getString("fechaNacimiento");
                LocalDate fechaNacimiento= LocalDate.parse(fechaNacimientoString,DateTimeFormatter.ISO_LOCAL_DATE);
                int dni=jo.getInt("dni");

                JSONObject direccionJson=jo.getJSONObject("direccion");
                String calle=direccionJson.getString("calle");
                int numero=direccionJson.getInt("numero");
                String ciudad=direccionJson.getString("ciudad");
                Direccion direccion=new Direccion(calle,numero,ciudad);
                int matricula= jo.getInt("matricula");
                Especialidad especialidad=Especialidad.valueOf(jo.getString("especialidad"));

                HashMap<DayOfWeek, HashSet<Turno>>agenda=new HashMap<>();
                JSONArray agendaJson=jo.getJSONArray("agenda");
                for(int j=0;j<agendaJson.length();j++){
                    Turno turno=new Turno();
                    JSONObject diaJson=agendaJson.getJSONObject(j);
                    DayOfWeek diaSemana=DayOfWeek.valueOf(diaJson.getString("diaSemana"));
                    JSONArray turnosJson=diaJson.getJSONArray("turnos");
                    HashSet<Turno>turnosDelDia=new HashSet<>();
                    for(int k=0;k<turnosJson.length();k++){
                        JSONObject tJson=turnosJson.getJSONObject(k);
                        String fechaHoraString = tJson.getString("fechaHora");
                        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        boolean disponibilidad = tJson.getBoolean("disponibilidad");
                        if (tJson.has("matriculaMedico")) {
                            int nroMatricula=tJson.getInt("matriculaMedico");
                            if (tJson.has("dniPaciente")) {
                                int dniPaciente = tJson.getInt("dniPaciente");
                                turno = new Turno(dniPaciente, nroMatricula, fechaHora, disponibilidad);
                            } else {
                                turno = new Turno(fechaHora, nroMatricula, disponibilidad);
                            }
                        }else {
                            turno=new Turno();
                        }
                        turnosDelDia.add(turno);
                    }
                    agenda.put(diaSemana,turnosDelDia);
            }
                Medico medico=new Medico(nombre,apellido,fechaNacimiento,dni,direccion,matricula,especialidad);
                medico.setAgenda(new Agenda(agenda));
                agregarMedico(medico);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    ///

    public boolean eliminar(Object obj) {
        Medico medico=(Medico) obj;
        Iterator<Map.Entry<Especialidad,HashSet<Medico>>>entryIterator=listadoMedicos.entrySet().iterator();
        boolean flag=false;
        while (entryIterator.hasNext()){
            Map.Entry<Especialidad,HashSet<Medico>>entry=entryIterator.next();
            HashSet<Medico>medicoHashSet=entry.getValue();
            Iterator<Medico>iterator=medicoHashSet.iterator();
            while (iterator.hasNext()){
                Medico aux=iterator.next();
                if(aux.equals(medico)){
                    iterator.remove();
                    flag=true;
                }
            }
        }
        return flag;
    }


}
