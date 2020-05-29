/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parameters;

/**
 *
 * @author Miker
 */
public enum CRUD_Usuario {
    INSERT("{call  insert_usuario(?,?,?,?,?,?,?)}"),
    UPDATE("{call  update_usuario(?,?,?,?,?,?,?,?)}"),
    DELETE("{call  delete_usuario(?)}"),
    LIST("{?=call  list_usuario()}"),
    QUERY("{?=call  query_usuario(?)}"),
    LOGIN("{?=call  login_usuario(?,?)}");

    private final String value;
    
    /**
     * Método para asignar el valor de cada uno de los parámetros
     * @param envUrl 
     */
    CRUD_Usuario(String envUrl) {
        this.value = envUrl;
    }
    
    /**
     * Método para retornar el valor de cada uno de los parámetros
     * @return String 
     */
    public String getValue() {
        return value;
    }
}
