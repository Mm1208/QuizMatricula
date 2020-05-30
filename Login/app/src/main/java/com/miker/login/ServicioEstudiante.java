package com.miker.login;

import com.google.gson.Gson;
import com.miker.login.Estudiante.Estudiante;
import com.miker.login.curso.Curso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicioEstudiante {
    public final static String LIST_ESTUDIANTES_URL = "http://10.0.2.2:8080/BACK-END/estudiante?opcion=list";
    public final static String INSERT_ESTUDIANTE_URL = "http://10.0.2.2:8080/BACK-END/estudiante?opcion=insert";
    public final static String UPDATE_ESTUDIANTE_URL = "http://10.0.2.2:8080/BACK-END/estudiante?opcion=update";
    public final static String DELETE_ESTUDIANTE_URL = "http://10.0.2.2:8080/BACK-END/estudiante?opcion=delete";
    private final static Gson gson = new Gson();
    private final static ServicioEstudiante servicioEstudiante = new ServicioEstudiante();

    public static ServicioEstudiante getServicioEstudiante() {
        return servicioEstudiante;
    }

    public static String insert(Estudiante object) throws Exception {
        return object.getJSON().toString();
    }

    public static String update(Estudiante object) {
        return gson.toJson(object);
    }

    public static String delete(Estudiante object) {
        return gson.toJson(object);
    }

    public static Estudiante query(String json_format) throws Exception {
        return gson.fromJson(json_format, Estudiante.class);
    }

    public static List<Estudiante> list(String json_format) throws Exception {
        List<Estudiante> list = new ArrayList<>();
        try {
            JSONArray JSONList = new JSONArray(json_format);
            for (int i = 0; i < JSONList.length(); i++) {
                JSONObject dataDetail = JSONList.getJSONObject(i);
                list.add(
                        gson.fromJson(dataDetail.toString(), Estudiante.class)
                );
            }
        } catch (JSONException e) {
            throw e;
        }
        return list;
    }
}
