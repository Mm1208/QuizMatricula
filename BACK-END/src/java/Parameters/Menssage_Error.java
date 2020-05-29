
package Parameters;

/**
 *
 * @author Miker
 */
public enum Menssage_Error {
    
    GLOBAL_EXEPTION("No se ha localizado el driver"),
    NO_DATA_EXEPTION("La base de datos no se encuentra disponible"),
    KEY_DUPLICATED("Llave duplicada"),
    INVALID_STATUTES("Estatutos invalidos o nulos"),
    METHODS_CLASS_NOT_ESTABLISHED("Clase de método no establecida"),
    NOT_EXIST_PREPARED_STATEMENT("No existe una declaración preparada"),
    OBJECT_NOT_INSERTED("No se realizo la insercion"),
    OBJECT_NOT_UPDATED("No se modificó"),
    OBJECT_NOT_FOUND("No se encontró"),
    OBJECT_NOT_DELETED("No se eliminó"),
    LOGIN_NOT_ESTABLISHED("Usuario ó Contraseña no reconocida."),
    MUST_HAVE_INPUT("Debe ingresar "),
    ERROR_TO_PARSER("Error al obtener ");

    private final String value;
    
    /**
     * Método para asignar el valor de cada uno de los parámetros
     * @param envUrl 
     */
    Menssage_Error(String envUrl) {
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
