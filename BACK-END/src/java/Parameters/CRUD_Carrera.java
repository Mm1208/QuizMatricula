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
public enum CRUD_Carrera {
    INSERT("{call  insert_carrera(?,?,?)}"),
    UPDATE("{call  update_carrera(?,?,?,?)}"),
    DELETE("{call  delete_carrera(?)}"),
    LIST("{?=call  list_carrera()}"),
    QUERYCODIGO("{?=call  query_carrera_codigo(?)}"),
    QUERYNOMBRE("{?=call  query_carrera_nombre(?)}"),
    QUERY("{?=call  query_carrera(?)}");

    private final String value;

    /**
     * Método para asignar el valor de cada uno de los parámetros
     *
     * @param envUrl
     */
    CRUD_Carrera(String envUrl) {
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
