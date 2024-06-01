package Medico;

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;

public class GestorMedico {
    private HashSet<Medico>listadoMedicos;
    //
    public GestorMedico(HashSet<Medico> listadoMedicos) {
        this.listadoMedicos = new HashSet<>();
    }
    //
    public void guardarListado(){
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        Iterator<Medico>iterator= listadoMedicos.iterator();;
        try {
            while (iterator.hasNext()){
                Medico aux=iterator.next();
                JSONObject jo=new JSONObject();
                jo.put(aux.getApellido(),aux);
                jsonArray.put(jo);
            }
            jsonObject.put("Medicos",jsonArray);


        }catch (JSONException exception){
            exception.printStackTrace();
        }
    }
    public void leerListado(){

    }
}
