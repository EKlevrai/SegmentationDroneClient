package socket;

import io.socket.SocketIO;

import java.net.MalformedURLException;

import model.Callback;
import model.CallbackAdapter;

import org.json.JSONException;
import org.json.JSONObject;


public class SocketMessage {
	static SocketIO socket;
	static Callback callback;

    public SocketMessage() {}
    
    /**
     * definit les fonctions de callback propre à socket.io;
     * @param eventCallback : fonctions de callback propre à socket.io
     */
    public static void start(CallbackAdapter eventCallback){
    	callback=new Callback(eventCallback);
       try {
		socket = new SocketIO("http://localhost:3000",callback);
	} catch (MalformedURLException e1) {
		e1.printStackTrace();
	}
    }
    /**
     * envoie de message au serveur
     * @param ev : string caracterisant la nature de l'event
     * @param data : json composant les données du message
     */
    public static void send(String ev,JSONObject data) {
        socket.emit(ev, data);
    }

    public static void end() {
    	socket.disconnect();
    }

    
}
