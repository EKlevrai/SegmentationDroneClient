package controller.xbox;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import main.Constantes;

import org.json.JSONException;
import org.json.JSONObject;

import socket.SocketMessage;
import ch.aplu.xboxcontroller.XboxControllerAdapter;

public class XboxListenerManual extends XboxControllerAdapter {
	private double directionMagnitude;
	private int forward;
	private int left;
	private int alt;
	private int clockwise;
	
	
	public XboxListenerManual(){
		directionMagnitude=0;
		forward=0;
		left=0;
		alt=0;
		clockwise=0;
	}
	public void actualize(){
		JSONObject jobj=new JSONObject();
		try {
			jobj.accumulate("forward", forward);
			jobj.accumulate("left", left);
			jobj.accumulate("alt", alt);
			jobj.accumulate("clockwise", clockwise);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SocketMessage.send("new_direction", jobj);
		//if(clockwise==1 || clockwise==-1 ){clockwise=0;}
		//if(alt==1 || alt==1 ){alt=0;}
	}
	public void buttonA(boolean ispressed){
		if(ispressed)alt=2;
		else alt=-0;
		actualize();
	}
	
	public void buttonB(boolean ispressed){
		if(ispressed)alt=-2;
		else alt=0;
		actualize();
	}
	public void buttonX(boolean ispressed){
//		public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		InputStream is = null;
		FileOutputStream os = null;
		Calendar cal=Calendar.getInstance();
			try {
				URL url = new URL(Constantes.StreamURL);
				 is = url.openStream();
				 os = new FileOutputStream("photos/photo_"+cal.get(Calendar.HOUR_OF_DAY)+"_"+cal.get(Calendar.MINUTE)+"_"+cal.get(Calendar.SECOND)+".png");
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			byte[] b = new byte[2048];
			int length;

			try {
				while ((length = is.read(b)) != -1) {
					try {
						os.write(b, 0, length);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * set the magnitude to 0 if negligeable, or to its value
	 * @param magnitude
	 */
	public void leftThumbMagnitude(double magnitude){
		if(magnitude<0.20)directionMagnitude=0;
		else directionMagnitude=magnitude;

	}
	public void leftThumbDirection(double direction) {
		forward = (int) Math.round(Math.cos(Math.toRadians(direction))*100*directionMagnitude);
		left = (int) Math.round(-Math.sin(Math.toRadians(direction))*100*directionMagnitude);
		actualize();
	}
	/**
	 * unclockwise
	 */
	public void leftTrigger(double magnitude){
		if(magnitude>0.50)clockwise=-2;
		else clockwise=0;
		actualize();
	}
	public void rightShoulder(double magnitude){
		if(magnitude>0.50)clockwise=2;
		else clockwise=-0;
		actualize();
	}
	public void start(boolean pressed){
		if (pressed)SocketMessage.send("switch");
	}
	/*public void dpad(int direction, boolean pressed) {
		JSONObject jobj=new JSONObject();//ici, le json comporte les directions
		if (pressed){
			try {
				jobj.accumulate("forward", Math.round(Math.cos(Math.toRadians(direction*45))*100));
				jobj.accumulate("left", Math.round(-Math.sin(Math.toRadians(direction*45))*100));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				jobj.accumulate("forward", 0);
				jobj.accumulate("left", 0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SocketMessage.send("new_direction", jobj);
		System.out.println(jobj.toString());
	}*/

}

