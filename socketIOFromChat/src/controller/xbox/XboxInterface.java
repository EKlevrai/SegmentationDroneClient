package controller.xbox;
//RumbleDemo.java

import ch.aplu.xboxcontroller.*;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.json.JSONException;
import org.json.JSONObject;

import socket.SocketMessage;
import vue.Fenetre;

public class XboxInterface
{
	private XboxController xc;
	private Fenetre f;
	private boolean manual;


	public XboxInterface(Fenetre fenetre)
	{ 
		manual=true;
		f = fenetre;
		xc = new XboxController();

		if (!xc.isConnected())
		{
			//emergency message(disconnecting manette)
			System.out.println("disconnected");
			xc.release();
			return;
		}

		xc.addXboxControllerListener(new XboxListenerManual());//TODO go to auto



		//xc.release();
		//System.exit(0);
	}

	public void auto() {
		xc.vibrate(0, 0);
		xc.addXboxControllerListener(new XboxListenerAuto());
	}
	public void manual(){xc.vibrate(65535, 65535);
		xc.addXboxControllerListener(new XboxListenerManual());
	}



}

