package Server;

import java.net.*;
import java.io.*;

public class Client {

    private final String SERVER_IP;
    private Socket socket;
    private ObjectInputStream bufferObjectIn = null;
    private ObjectOutputStream bufferObjectOut = null;
    private Packet packet;
    private static final Client client = new Client();

    public Client(String ip) {
        SERVER_IP = ip;
        packet = new Packet();
    }

    public Client() {
        this("localhost");
    }

    public static Client getClient() {
        return client;
    }

    public void levantarConexion(int port) throws Exception {
        try {
            socket = new Socket(SERVER_IP, port);
        } catch (IOException ex) {
            throw new Exception("No se logró estableser una conexión con el servidor lógico.");
        }
    }

    public static void mostrarTexto(String s) {
        System.out.println(s);
    }

    public void abrirFlujos() throws Exception {
        try {
            bufferObjectIn = new ObjectInputStream(socket.getInputStream());
            bufferObjectOut = new ObjectOutputStream(socket.getOutputStream());
            bufferObjectOut.flush();
        } catch (IOException e) {
            throw new Exception("Error en la apertura de flujos.");
        }
    }

    public void enviar(Object object) throws Exception {
        try {
            bufferObjectOut.writeObject(object);
            bufferObjectOut.flush();
        } catch (IOException ex) {
            throw new Exception("Falló el envío de [" + object + "].");
        }
    }

    public void cerrarConexion() throws Exception {
        try {
            if (bufferObjectIn != null) {
                bufferObjectIn.close();
            }
            if (bufferObjectOut != null) {
                bufferObjectOut.close();
            }
            if (socket != null) {
                socket.close();
            }
            bufferObjectIn = null;
            bufferObjectOut = null;
            socket = null;
        } catch (IOException e) {
            throw new Exception("Falló al intentar cerrar la conexión.");
        }
    }

    public Packet ejecutarConexion(Message message, int port) throws Exception {
        try {
            levantarConexion(port);
            abrirFlujos();
            escribirDatos(message);
            recibirDatos();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            cerrarConexion();
        }
        return packet;
    }

    public void recibirDatos() throws Exception {
        try {
            packet = (Packet) bufferObjectIn.readObject();
            if(!packet.isStatus()){
                throw new Exception(packet.getResponse().toString());
            }
        } catch (IOException ex) {
            throw new Exception("Falló el recibir la respuesta.<br>" + ex.getMessage());
        }
    }

    public void escribirDatos(Message message) throws Exception {
        try {
            enviar(message);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public String type_proccess(String opcion) throws Exception {
        String result;
        switch (opcion) {
            case "insert": {
            }
            case "delete": {
            }
            case "update": {
                result = "procedure";
            }
            break;
            default: {
                result = "function";
            }
        }
        return result;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}
