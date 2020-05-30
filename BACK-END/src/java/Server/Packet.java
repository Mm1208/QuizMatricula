
package Server;

import java.io.Serializable;

public class Packet implements Serializable{
     
    private static final long serialVersionUID = 11112;
    private Object response;
    private boolean status;

    public Packet(Object object, boolean status) {
        this.response = object;
        this.status = status;
    }

    public Packet() {
        this(new Object(), false);
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Packet{" + "response=" + response + ", status=" + status + '}';
    }
}
