package controller.xbox;

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
	}
	public void buttonA(boolean ispressed){
		if(ispressed)alt=1;
		else alt=0;
		actualize();
	}
	
	public void buttonB(boolean ispressed){
		if(ispressed)alt=-1;
		else alt=0;
		actualize();
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
	public void leftTrigger(double value){
		if(value>0.5)clockwise=-1;
		else clockwise=0;
		actualize();
	}
	public void rightTrigger(double value){
		if(value>0.5)clockwise=1;
		else clockwise=0;
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

