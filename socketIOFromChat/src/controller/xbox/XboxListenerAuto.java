package controller.xbox;

import org.json.JSONException;
import org.json.JSONObject;

import socket.SocketMessage;
import ch.aplu.xboxcontroller.XboxControllerAdapter;

public class XboxListenerAuto extends XboxControllerAdapter {
	public XboxListenerAuto(){}

	public void start(boolean pressed){
		if (pressed)SocketMessage.send("switch");
	}
}

