/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parameters;

/**
 *
 * @author Jostin
 */
public enum CRUD_Curso {
    INSERT("{call  insert_curso(?,?,?,?,?,?,?)}"),
    UPDATE("{call  update_curso(?,?,?,?,?,?,?,?)}"),
    DELETE("{call  delete_curso(?)}"),
    LIST("{?=call  list_curso()}"),
    QUERYCODIGO("{?=call  query_curso_codigo(?)}"),
    QUERYNOMBRE("{?=call  query_curso_nombre(?)}"),
    QUERYCARRERA("{?=call  query_curso_carrera(?)}"),
    QUERY("{?=call  query_curso(?)}"),
    LIST_TO_ESTUDIANTE("{?=call query_curso_x_estudiante(?)}");

    private final String value;

    /**
     * Método para asignar el valor de cada uno de los parámetros
     *
     * @param envUrl
     */
    CRUD_Curso(String envUrl) {
        this.value = envUrl;
    }

    /**
     * Método para retornar el valor de cada uno de los parámetros
     *
     * @return String
     */
    public String getValue() {
        return value;
    }
}
