package socket;

import model.CallbackAdapter;

import org.eclipse.swt.widgets.Display;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vue.Fenetre;

public class EventCallback implements CallbackAdapter{	
	Fenetre F;
	public EventCallback(Fenetre _F){F=_F;}
	@Override
	public void callback(JSONArray data) throws JSONException {
		//TODO 
	}


	@Override
	public void on(String event, JSONObject data) {
		if (event.equals("hello")) {
			Display.getDefault().syncExec(new Runnable() {
			    public void run() {
					F.repaint();
					System.out.println("MERCI CANU");
			    }
			});
		}		
	}


	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onMessage(JSONObject json) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onConnect() {
		System.out.println("Connected");
	}


	@Override
	public void onDisconnect() {
		System.out.println("DisConnected");
	}


	@Override
	public void onConnectFailure() {
		System.out.println("Connection Failed");
	}	


}
