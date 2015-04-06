package controller.xbox;
//RumbleDemo.java

import ch.aplu.xboxcontroller.*;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;

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
			public void buttonA(boolean ispressed)
			{
				if(ispressed){
					System.out.println("  GO UP");
				}
				else{Display.getDefault().syncExec(new Runnable() {
						public void run() {
							f.repaint();
							System.out.println("STOP UP");
						}
					});
				}
			}

		});



		//xc.release();
		//System.exit(0);
	}


}

