package Medico;

import Turno.Turno;
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GestorMedico {
    private HashSet<Medico>listadoMedicos;
    //
    public GestorMedico(HashSet<Medico> listadoMedicos) {
        this.listadoMedicos = new HashSet<>();
    }
    //
    public void guardarListado() {
        JSONArray jsonArray = new JSONArray();
        try {
            Iterator<Medico> iterator = listadoMedicos.iterator();
            while (iterator.hasNext()) {
                Medico aux = iterator.next();
                JSONObject jo = new JSONObject();
                jo.put("Apellido", aux.getApellido());
                jo.put("Nombre", aux.getNombre());
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
                        turnoJson.put("dniPaciente",turno.getPaciente());
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
            JSONObject object  = new JSONObject(JsonMedicos.leer("datos"));
            JSONArray jo_array = object.getJSONArray("Medicos");

            for (int i=0;i<jo_array.length();i++)
            {
                JSONObject jsonObject1 = jo_array.getJSONObject(i);
                Iterator<String>keys= jsonObject1.keys();
                while (keys.hasNext()){
                    String key= keys.next();
                    JSONObject aux=jsonObject1.getJSONObject(key);
                    Medico m;


                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
