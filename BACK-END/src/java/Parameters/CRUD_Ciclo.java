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
public enum CRUD_Ciclo {
    INSERT("{call  insert_ciclo(?,?,?)}"),
    UPDATE("{call  update_ciclo(?,?,?,?)}"),
    DELETE("{call  delete_ciclo(?)}"),
    LIST("{?=call  list_ciclo()}"),
    QUERY("{?=call  query_ciclo(?)}");

    private final String value;

    /**
     * Método para asignar el valor de cada uno de los parámetros
     * @param envUrl 
     */
    CRUD_Ciclo(String envUrl) {
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
