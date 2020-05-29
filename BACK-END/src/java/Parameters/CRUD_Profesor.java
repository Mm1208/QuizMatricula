
package Parameters;

public enum CRUD_Profesor {
    INSERT("{call  insert_profesor(?,?,?,?,?,?)}"),
    UPDATE("{call  update_profesor(?,?,?,?,?,?,?)}"),
    DELETE("{call  delete_profesor(?)}"),
    LIST("{?=call  list_profesor()}"),
    QUERY("{?=call  query_profesor(?)}");

    private final String value;

    /**
     * Método para asignar el valor de cada uno de los parámetros
     * @param envUrl 
     */
    CRUD_Profesor(String envUrl) {
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
