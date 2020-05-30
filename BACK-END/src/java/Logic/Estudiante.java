package Logic;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class Estudiante extends Instancia {

    private int id;
    private String nombre;
    private String ape_1;
    private String ape_2;
    private int edad;
    private List<Curso> cursos;

    public Estudiante(int id, String nombre, String ape_1, String ape_2, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.ape_1 = ape_1;
        this.ape_2 = ape_2;
        this.edad = edad;
        this.cursos = new ArrayList();
    }

    public Estudiante() {
        this(-1, null, null, null, -1);
    }

    public Estudiante(int id) {
        this();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe_1() {
        return ape_1;
    }

    public void setApe_1(String ape_1) {
        this.ape_1 = ape_1;
    }

    public String getApe_2() {
        return ape_2;
    }

    public void setApe_2(String ape_2) {
        this.ape_2 = ape_2;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public JSONObject getJSON() throws Exception {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("nombre", nombre);
        json.put("ape1", ape_1);
        json.put("ape2", ape_2);
        json.put("edad", edad);
        json.put("cursos", cursos);
        return json;
    }
}
