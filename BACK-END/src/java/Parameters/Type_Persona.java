
package Parameters;

public enum Type_Persona {
    PROFESOR("P"),
    ALUMNO("A");

    private final String value;
    
    /**
     * Método para asignar el valor de cada uno de los parámetros
     * @param envUrl 
     */
    Type_Persona(String envUrl) {
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
