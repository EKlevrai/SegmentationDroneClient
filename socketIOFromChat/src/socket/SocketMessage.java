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
    
    
    
    public static void start(CallbackAdapter eventCallback){
    	callback=new Callback(eventCallback);
       try {
		socket = new SocketIO("http://localhost:3000",callback);
	} catch (MalformedURLException e1) {
		e1.printStackTrace();
	}
    }
    public static void send(String message) {
        try {
            JSONObject json = new JSONObject();
            json.putOpt("message", message);
            socket.emit("hello", json);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    public static void end() {
    	socket.disconnect();
    }

    
}
