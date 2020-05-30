
package Parameters;

public enum CRUD_Estudiante {
    INSERT("{call  insert_estudiante(?,?,?,?)}"),
    UPDATE("{call  update_estudiante(?,?,?,?,?)}"),
    DELETE("{call  delete_estudiante(?)}"),
    LIST("{?=call  list_estudiante()}"),
    QUERY("{?=call  query_estudiante(?)}"),
    MATCHING("{call  insert_curso_x_estudiante(?)}"),
    MISMATCHING("{call  delete_curso_x_estudiante(?)}");

    private final String value;

    /**
     * Método para asignar el valor de cada uno de los parámetros
     * @param envUrl 
     */
    CRUD_Estudiante(String envUrl) {
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
