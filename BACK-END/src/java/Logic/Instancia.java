
package Logic;

import java.io.Serializable;
import org.json.JSONObject;

public abstract class Instancia implements Serializable{
    private static final long serialVersionUID = 1111111111L;
    
    public abstract JSONObject getJSON() throws Exception;
}
