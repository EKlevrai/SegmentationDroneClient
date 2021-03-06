package socket;

import io.socket.SocketIO;

import java.net.MalformedURLException;

import main.Constantes;
import model.Callback;
import model.CallbackAdapter;

import org.json.JSONObject;


public class SocketMessage {
	static SocketIO socket;
	static Callback callback;

    public SocketMessage() {}
    
    /**
     * definit les fonctions de callback propre � socket.io;
     * @param eventCallback : fonctions de callback propre � socket.io
     */
    public static void start(CallbackAdapter eventCallback){
    	callback=new Callback(eventCallback);
	    try {
	    	socket = new SocketIO(Constantes.SocketURL);
	   	} catch (MalformedURLException e1) {
	   		e1.printStackTrace();
	   	}
	    System.out.println("url passed");
	   	socket.connect(callback);
    }
    /**
     * envoie de message au serveur
     * @param ev : string caracterisant la nature de l'event
     * @param data : json composant les donn�es du message
     */
    public static void send(String ev,JSONObject data) {
        socket.emit(ev, data);
    }
    public static void send(String ev) {
        socket.emit(ev);
    }
    

    public static void end() {
    	socket.disconnect();
    }

    
}
