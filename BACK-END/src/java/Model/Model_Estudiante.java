package Model;

import DAO.DAO_Estudiante;
import Logic.Estudiante;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.List;

public class Model_Estudiante {

    private final DAO_Estudiante dao = DAO_Estudiante.getDAO();
    private final Gson gson = new Gson();
    private final static Model_Estudiante model = new Model_Estudiante();

    public static Model_Estudiante getModel() {
        return model;
    }

    //El rango de edad permitido esde 150 años a 0
    //No se permite la inserción de un registro sin ningún curso
    public Estudiante verify(String json) throws Exception {
        Estudiante object;
        try {
            if(json == null){
                throw new Exception("¡Debe ingresar un estudiante en formato JSON!");
            }
            object = gson.fromJson(json, Estudiante.class);
        } catch (JsonSyntaxException ex) {
            throw ex;
        }
        if (object.getEdad() > 150 && object.getEdad() < 0) {
            throw new Exception("¡La edad ingresada es inválida!");
        }
        if (object.getCursos().isEmpty()) {
            throw new Exception("¡Debe ingresar al menos un curso!");
        }
        return object;
    }

    public void insert(String json) throws Exception {
        dao.insert(verify(json));
    }

    public void update(String json) throws Exception {
        dao.update(verify(json));
    }

    public void delete(String json) throws Exception {
        dao.delete(verify(json));
    }

    public Estudiante query(String json) throws Exception {
        return dao.query(verify(json));
    }

    public List<Estudiante> list() throws Exception {
        return dao.list();
    }
}
