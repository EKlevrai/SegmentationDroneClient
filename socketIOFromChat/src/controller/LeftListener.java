package controller;

import org.eclipse.swt.widgets.Event;
import org.json.JSONObject;

import socket.SocketMessage;


public class LeftListener implements org.eclipse.swt.widgets.Listener {

	@Override
	public void handleEvent(Event arg0) {
		JSONObject jobj=new JSONObject();	
    	SocketMessage.send("hello",jobj);
	}

}
