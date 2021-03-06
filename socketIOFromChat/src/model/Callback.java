package model;

import java.util.Arrays;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * 
 * @author Olivier
 *
 */
public class Callback implements IOCallback, IOAcknowledge {
    
    /**
     * Constructeur: on utilise une classe qui impl�mente CallbackAdapter
     * et donc qui definis les fontions de callback n�cessaire � socket.io
     * @param callback
     */
	private CallbackAdapter callback;
    public Callback(CallbackAdapter callback) {
        this.callback = callback;
    }
    /**
     * 
     */
	@Override
	public void ack(Object... data) {
        try {
			callback.callback(new JSONArray(Arrays.asList(data)));
		} catch (JSONException e) {
			e.printStackTrace();
		}
    }
	/*
	 * en cas d'evenement de socket.io, on fait ce qui a �t� defini dans la classe implementant CallbackAdapter
	 */
    @Override
    public void on(String event, IOAcknowledge ack, Object... data) {
    	System.out.println(event);
    	System.out.println(data.toString());
    	try{
    		callback.on(event, (JSONObject) data[0]);
        }
    	catch(SocketIOException e){
			e.printStackTrace();
    	}
    }

    @Override
    public void onMessage(String message, IOAcknowledge ack) {
        callback.onMessage(message);
    }

    @Override
    public void onMessage(JSONObject json, IOAcknowledge ack) {
        try {
			callback.onMessage(json);
		} catch (SocketIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onConnect() {
        callback.onConnect();
    }

    @Override
    public void onDisconnect() {
        callback.onDisconnect();
    }

	@Override
	public void onError(SocketIOException socketIOException) {
		socketIOException.printStackTrace();
        callback.onConnectFailure();
	}

    
}
