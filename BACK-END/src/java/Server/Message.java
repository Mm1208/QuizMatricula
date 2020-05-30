package Server;

import Logic.Instancia;
import java.io.Serializable;
public class Message implements Serializable{
     
    private static final long serialVersionUID = 11111;

    private String instance;
    private String type_process;
    private String opcion;
    private Instancia object;

    public Message(String instance, String type_process, String opcion, Instancia object) {
        this.instance = instance;
        this.type_process = type_process;
        this.opcion = opcion;
        this.object = object;
    }

    public Message() {
        this(null, null, null, null);
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getType_process() {
        return type_process;
    }

    public void setType_process(String type_process) {
        this.type_process = type_process;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Instancia getObject() {
        return object;
    }

    public void setObject(Instancia object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Message{" + "instance=" + instance + ", type_process=" + type_process + ", opcion=" + opcion + ", object=" + object + '}';
    }
}
