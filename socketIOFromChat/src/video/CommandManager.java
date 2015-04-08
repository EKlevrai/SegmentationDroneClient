package video;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;


public class CommandManager extends AbstractManager{

	private static final String CR="\r";

	private static int seq=1;

	private FloatBuffer fb=null;
	private IntBuffer ib=null;

	private boolean landing=true;
	private boolean continuance=false;
	private String command=null;
	
	/** speed */
	private float speed=0.05f;//0.01f - 1.0f
		
	public CommandManager(InetAddress inetaddr){
		super(inetaddr);
		initialize();
	}
	
	
	public void setHorizontalCamera() {
		//command="AT*ZAP="+(seq++)+",0";
		command="AT*CONFIG="+(seq++)+",\"video:video_channel\",\"0\"";
		continuance=false;
		//setCommand("AT*ZAP="+(seq++)+",0", false);
	}

	
	public void setVerticalCamera() {
		//command="AT*ZAP="+(seq++)+",1";
		command="AT*CONFIG="+(seq++)+",\"video:video_channel\",\"1\"";
		continuance=false;
		//setCommand("AT*ZAP="+(seq++)+",1", false);
	}

	
	public void setHorizontalCameraWithVertical() {
		//command="AT*ZAP="+(seq++)+",2";
		command="AT*CONFIG="+(seq++)+",\"video:video_channel\",\"2\"";
		continuance=false;
		//setCommand("AT*ZAP="+(seq++)+",2", false);
	}

	
	public void setVerticalCameraWithHorizontal() {
		//command="AT*ZAP="+(seq++)+",3";
		command="AT*CONFIG="+(seq++)+",\"video:video_channel\",\"3\"";
		continuance=false;
		//setCommand("AT*ZAP="+(seq++)+",3", false);
	}

	
	public void toggleCamera() {
		//command="AT*ZAP="+(seq++)+",4";
		command="AT*CONFIG="+(seq++)+",\"video:video_channel\",\"4\"";
		continuance=false;
		//setCommand("AT*ZAP="+(seq++)+",4", false);
	}

	
	public void landing() {
		System.out.println("*** Landing");
		command="AT*REF=" + (seq++) + ",290717696";
		continuance=false;
		//setCommand("AT*REF=" + (seq++) + ",290717696", false);
		landing=true;		
	}

	
	public void takeOff() {
		System.out.println("*** Take off");
		sendCommand("AT*FTRIM="+(seq++));
		command="AT*REF=" + (seq++) + ",290718208";
		continuance=false;
		//setCommand("AT*REF=" + (seq++) + ",290718208", false);
		landing=false;		
	}

	
	public void reset() {
		System.out.println("*** Reset");
		command="AT*REF="+(seq++)+",290717952";
		continuance=true;
		//setCommand("AT*REF="+(seq++)+",290717952", true);
		landing=true;		
	}

	
	public void forward() {
		command="AT*PCMD="+(seq++)+",1,0,"+intOfFloat(-speed)+",0,0"+"\r"+"AT*REF=" + (seq++) + ",290718208";
		continuance=true;
		//setCommand("AT*PCMD="+(seq++)+",1,0,"+intOfFloat(-speed)+",0,0"+"\r"+"AT*REF=" + (seq++) + ",290718208", true);
	}

	
	public void forward(int speed) {
		setSpeed(speed);
		forward();
	}

	
	public void backward() {
		command="AT*PCMD="+(seq++)+",1,0,"+intOfFloat(speed)+",0,0"+"\r"+"AT*REF=" + (seq++) + ",290718208";
		continuance=true;
	}

	
	public void backward(int speed) {
		setSpeed(speed);
		backward();
	}

	
	public void spinRight() {
		command="AT*PCMD=" + (seq++) + ",1,0,0,0," + intOfFloat(speed)+"\r"+"AT*REF=" + (seq++) + ",290718208";
		continuance=true;
	}

	
	public void spinRight(int speed) {
		setSpeed(speed);
		spinRight();
	}

	
	public void spinLeft() {
		command="AT*PCMD=" + (seq++) + ",1,0,0,0," + intOfFloat(-speed)+"\r"+"AT*REF=" + (seq++) + ",290718208";
		continuance=true;
	}

	
	public void spinLeft(int speed) {
		setSpeed(speed);
		spinLeft();
	}

	
	public void up() {
		System.out.println("*** Up");
		command="AT*PCMD="+(seq++)+",1,"+intOfFloat(0)+","+intOfFloat(0)+","+intOfFloat(speed)+","+intOfFloat(0)+"\r"+"AT*REF="+(seq++)+",290718208";
		continuance=true;
	}

	
	public void up(int speed) {
		setSpeed(speed);
		up();
	}

	
	public void down() {
		System.out.println("*** Down");
		command="AT*PCMD="+(seq++)+",1,"+intOfFloat(0)+","+intOfFloat(0)+","+intOfFloat(-speed)+","+intOfFloat(0)+"\r"+"AT*REF="+(seq++)+",290718208";
		continuance=true;
	}

	
	public void down(int speed) {
		setSpeed(speed);
		down();
	}

	
	public void goRight() {
		command="AT*PCMD="+(seq++)+",1,"+intOfFloat(speed)+",0,0,0"+"\r"+"AT*REF=" + (seq++) + ",290718208";
		continuance=true;
	}

	
	public void goRight(int speed) {
		setSpeed(speed);
		goRight();
	}

	
	public void goLeft() {
		command="AT*PCMD="+(seq++)+",1,"+intOfFloat(-speed)+",0,0,0"+"\r"+"AT*REF=" + (seq++) + ",290718208";
		continuance=true;
	}

	
	public void goLeft(int speed) {
		setSpeed(speed);
		goLeft();
	}

	
	
	public void stop() {
		System.out.println("*** Stop (Hover)");
		command="AT*PCMD="+(seq++)+",1,0,0,0,0";
		continuance=true;
	}

	
	public void setSpeed(int speed) {
		if(speed>100)
			speed=100;
		else if(speed<1)
			speed=1;

		this.speed=(float) (speed/100.0);
	}
	

	public void enableVideoData(){
		command="AT*CONFIG="+(seq++)+",\"general:video_enable\",\"TRUE\""+CR+"AT*FTRIM="+(seq++);
		continuance=false;
		//setCommand("AT*CONFIG="+(seq++)+",\"general:video_enable\",\"TRUE\""+CR+"AT*FTRIM="+(seq++), false);
	}
	
	public void enableDemoData(){
		command="AT*CONFIG="+(seq++)+",\"general:navdata_demo\",\"TRUE\""+CR+"AT*FTRIM="+(seq++);
		continuance=false;
		//setCommand("AT*CONFIG="+(seq++)+",\"general:navdata_demo\",\"TRUE\""+CR+"AT*FTRIM="+(seq++), false);
	}

	public void sendControlAck(){
		command="AT*CTRL="+(seq++)+",0";
		continuance=false;
		//setCommand("AT*CTRL="+(seq++)+",0", false);
	}
	
	public int getSpeed(){
		return (int) (speed*100);
	}
	
	public void disableAutomaticVideoBitrate(){
		command="AT*CONFIG="+(seq++)+",\"video:bitrate_ctrl_mode\",\"0\"";
		continuance=false;
	}

	public void setMaxAltitude(int altitude){
		command="AT*CONFIG="+(seq++)+",\"control:altitude_max\",\""+altitude+"\"";
		continuance=false;
	}
	
	public void setMinAltitude(int altitude){
		command="AT*CONFIG="+(seq++)+",\"control:altitude_min\",\""+altitude+"\"";
		continuance=false;
	}

	/*
	 * Thanks, Tarquínio.
	 */
	public void move3D(int speedX, int speedY, int speedZ, int speedSpin) {
		if(speedX>100)
			speedX=100;
		else if(speedX<-100)
			speedX=-100;
		if(speedY>100)
			speedY=100;
		else if(speedY<-100)
			speedY=-100;
		if(speedZ>100)
			speedZ=100;
		else if(speedZ<-100)
			speedZ=-100;
		
		command="AT*PCMD="+(seq++)+",1,"+intOfFloat(-speedY/100.0f)+","+intOfFloat(-speedX/100.0f)+","+intOfFloat(-speedZ/100.0f)+","+intOfFloat(-speedSpin/100.0f)+"\r"+"AT*REF="+(seq++)+",290718208";
		continuance=true;
	}
	
	@Override
	public void run() {
		initARDrone();
		while(true){
			if(this.command!=null){
				sendCommand();
				if(!continuance){
					command=null;
				}
			}else{
				if(landing){
					sendCommand("AT*PCMD="+(seq++)+",1,0,0,0,0"+CR+"AT*REF="+(seq++)+",290717696");
				}else{
					sendCommand("AT*PCMD="+(seq++)+",1,0,0,0,0"+CR+"AT*REF="+(seq++)+",290718208");
				}
			}
			if(seq%5==0){//<2000ms
				sendCommand("AT*COMWDG="+(seq++));
			}
		}
	}
	

	private void initialize(){
		ByteBuffer bb=ByteBuffer.allocate(4);
		fb=bb.asFloatBuffer();
		ib=bb.asIntBuffer();
	}
	
	private void initARDrone(){
		sendCommand("AT*CONFIG="+(seq++)+",\"general:navdata_demo\",\"TRUE\""+CR+"AT*FTRIM="+(seq++));//1
		sendCommand("AT*PMODE="+(seq++)+",2"+CR+"AT*MISC="+(seq++)+",2,20,2000,3000"+CR+"AT*FTRIM="+(seq++)+CR+"AT*REF="+(seq++)+",290717696");//2-5
		sendCommand("AT*PCMD="+(seq++)+",1,0,0,0,0"+CR+"AT*REF="+(seq++)+",290717696"+CR+"AT*COMWDG="+(seq++));//6-8
		sendCommand("AT*PCMD="+(seq++)+",1,0,0,0,0"+CR+"AT*REF="+(seq++)+",290717696"+CR+"AT*COMWDG="+(seq++));//6-8
		sendCommand("AT*FTRIM="+(seq++));
		System.out.println("Initialize completed!");
	}
	
	/*private void setCommand(String command, boolean continuance){
		this.command=command;
		this.continuance=continuance;
	}*/

	
	private void sendCommand(){
		sendCommand(this.command);
	}
	
	private synchronized void sendCommand(String command){
		byte[] buffer=(command+CR).getBytes();
		//System.out.println(command);
		DatagramPacket packet=new DatagramPacket(buffer, buffer.length, inetaddr, 5556);
		try {
			socket.send(packet);
			Thread.sleep(20);//<50ms			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private int intOfFloat(float f) {
		fb.put(0, f);
		return ib.get(0);
	}
}