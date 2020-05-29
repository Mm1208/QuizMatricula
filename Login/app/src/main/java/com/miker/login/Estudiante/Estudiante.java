package com.miker.login.Estudiante;

import com.miker.login.curso.Curso;
import com.miker.login.curso.Instancia;

import org.json.JSONObject;

import java.util.ArrayList;

public class Estudiante extends Instancia {
    private String id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private ArrayList<Curso> cursos = new ArrayList<>();


    public Estudiante(String id, String nombre, String apellido1, String apellido2, ArrayList<Curso> cursos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.cursos = cursos;
    }

    public Estudiante() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public void insertarCurso(Curso curso){
        cursos.add(curso);
    }


    @Override
    public String toString() {
        return "Estudiante:\n\tId: " + id +  "\n\tNombre: " + nombre + "\n\t Primer Apellido: " + apellido1 + "\n\tSegundo Apellido: " + apellido2 + "\n\tCursos: " + cursos;
    }

    @Override
    public JSONObject getJSON() throws Exception{
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("nombre", nombre);
        json.put("apellido1", apellido1);
        json.put("apellido2", apellido2);
        json.put("Cursos", cursos);
        return json;
    }
}
