package Medico;

import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;

public class JsonMedicos {
    public void grabar(JSONArray jsonArray,String archivo){
        try {
            FileWriter file = new FileWriter(archivo+".json");
            file.write(jsonArray.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void leer(){

    }
}
