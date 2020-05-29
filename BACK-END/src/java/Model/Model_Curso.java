package Model;

import DAO.DAO_Curso;
import Logic.Curso;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.List;

public class Model_Curso {

    private final DAO_Curso dao = DAO_Curso.getDAO();
    private final Gson gson = new Gson();
    private final static Model_Curso model = new Model_Curso();

    public static Model_Curso getModel() {
        return model;
    }

    //Sin restricciones logicas
    public Curso verify(String json) throws Exception {
        Curso object;
        try {
            if(json == null){
                throw new Exception("¡Debe ingresar un curso en formato JSON!");
            }
            object = gson.fromJson(json, Curso.class);
        } catch (JsonSyntaxException ex) {
            throw ex;
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

    public Curso query(String json) throws Exception {
        return dao.query(verify(json));
    }

    public List<Curso> list() throws Exception {
        return dao.list();
    }
}
