package Medico;

import Modelo.Direccion;
import Modelo.Especialidad;
import Turno.Turno;
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GestorMedico {
    private HashSet<Medico>listadoMedicos;
    //

    public GestorMedico() {
        listadoMedicos=new HashSet<>();
    }

    public GestorMedico(HashSet<Medico> listadoMedicos) {
        this.listadoMedicos = new HashSet<>();
    }
    public void agregarMedico(Medico medico){
        listadoMedicos.add(medico);
    }
    public String mostrarMedicos(){
        Iterator<Medico>iterator=listadoMedicos.iterator();
        String rta="";
        while (iterator.hasNext()){
            Medico aux=iterator.next();
            rta+=aux.toString();
        }
        return rta;
    }
    //
    public void guardarListado() {
        JSONArray jsonArray = new JSONArray();
        try {
            Iterator<Medico> iterator = listadoMedicos.iterator();
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
                HashMap<DayOfWeek, ArrayList<Turno>> turnos = aux.getAgenda().getTurnos();
                Iterator<Map.Entry<DayOfWeek, ArrayList<Turno>>> iteratorTurnos = turnos.entrySet().iterator();
                while (iteratorTurnos.hasNext()) {
                    Map.Entry<DayOfWeek, ArrayList<Turno>> entradaMapa = iteratorTurnos.next();
                    DayOfWeek diaSemana = entradaMapa.getKey();
                    JSONArray turnosDia = new JSONArray();
                    for (Turno turno : entradaMapa.getValue()) {
                        JSONObject turnoJson = new JSONObject();
                        if (turno.getPaciente()!=0){
                            turnoJson.put("dniPaciente",turno.getPaciente());
                        }
                        turnoJson.put("matriculaMedico",turno.getMatriculaMedico());
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
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Medicos", jsonArray);
            JsonMedicos.grabar(jsonObject, "medicos");
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }
    public void leerListado(){
        try {
            JSONObject object  = new JSONObject(JsonMedicos.leer("medicos"));
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

                HashMap<DayOfWeek,ArrayList<Turno>>agenda=new HashMap<>();
                JSONArray agendaJson=jo.getJSONArray("agenda");
                ArrayList<Turno>turnosDelDia=new ArrayList<>();
                for(int j=0;j<agendaJson.length();j++){
                    Turno turno=new Turno();
                    JSONObject diaJson=agendaJson.getJSONObject(j);
                    DayOfWeek diaSemana=DayOfWeek.valueOf(diaJson.getString("diaSemana"));
                    JSONArray turnosJson=diaJson.getJSONArray("turnos");
                    for(int k=0;k<turnosJson.length();k++){
                        JSONObject tJson=turnosJson.getJSONObject(k);
                        int nroMatricula= tJson.getInt("matriculaMedico");
                        String fechaHoraString = tJson.getString("fechaHora");
                        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        boolean disponibilidad = tJson.getBoolean("disponibilidad");
                        if (tJson.has("dniPaciente")) {
                            int dniPaciente = tJson.getInt("dniPaciente");
                            turno = new Turno(dniPaciente, nroMatricula, fechaHora, disponibilidad);
                        } else {
                            turno = new Turno(fechaHora, nroMatricula, disponibilidad);
                        }
                        turnosDelDia.add(turno);
                    }
                    agenda.put(diaSemana,turnosDelDia);
            }
                Medico medico=new Medico(nombre,apellido,fechaNacimiento,dni,direccion,matricula,especialidad);
                medico.setAgenda(new Agenda(agenda));
                listadoMedicos.add(medico);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}