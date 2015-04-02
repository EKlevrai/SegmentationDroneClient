package controller.xbox;
//RumbleDemo.java

import ch.aplu.xboxcontroller.*;

import javax.swing.JOptionPane;

import vue.Fenetre;

public class XboxInterface
{
private XboxController xc;
private Fenetre f;

public XboxInterface(Fenetre fenetre)
{ 
	f = fenetre;
	xc = new XboxController();
 
 if (!xc.isConnected())
 {
//emergency message(disconnecting manette)
	System.out.println("disconnected");
	xc.release();
   return;
 }
 
 xc.addXboxControllerListener(new XboxControllerAdapter()
 {

 });
 

 
 xc.release();
 System.exit(0);
}
 

}

