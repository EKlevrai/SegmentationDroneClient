package vue;


import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.json.JSONObject;




import controller.xbox.XboxInterface;
import socket.SocketMessage;
 
public class Fenetre implements java.lang.Runnable{
	int numColor;
	Color cols[];
	Display display ;
	Shell shell ;
	Canvas streamArea;
	Label buttonCommands;
	Button altUp;
	Button altDown;
	Button rotateClock;
	Button rotateUnclock;
	Button orientFront;
	Button orientBack;
	Button orientLeft;
	Button orientRight;
	Button takeOffLand;
	XboxInterface xInterf;
	
	public Fenetre(){
		xInterf = new XboxInterface(this);
		System.out.println("mabite");
		display = new Display();
		cols=new Color[]{Display.getCurrent().getSystemColor(SWT.COLOR_BLACK),Display.getCurrent().getSystemColor(SWT.COLOR_BLUE),Display.getCurrent().getSystemColor(SWT.COLOR_CYAN),Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA),Display.getCurrent().getSystemColor(SWT.COLOR_GRAY)};
		numColor=0;
		shell= new Shell(display, SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shell.setText("Drone Control");
		shell.setSize(FenetreConstantes.TailleX,FenetreConstantes.TailleY);
		shell.addListener(SWT.Close, new Listener() {public void handleEvent(Event event) {System.exit(0);}});
		GridLayout gl = new GridLayout(9, true);
	    gl.horizontalSpacing = 0;
	    gl.verticalSpacing = 0;
	    gl.marginBottom = 0;
	    gl.marginTop = 0;
        shell.setLayout(gl);
        SocketMessage.start(new socket.EventCallback(this));

		initComponents();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
 
		display.dispose();
	}
	
	
private void initComponents() {
	
	
	streamArea = new Canvas(shell,SWT.CENTER);
	streamArea.setBackground(cols[numColor]);

   
	altUp = new Button(shell,SWT.CENTER);
	altUp.setText("Up");
	altDown = new Button(shell,SWT.CENTER);
	altDown.setText("Down");
	rotateClock = new Button(shell,SWT.CENTER);
	rotateClock.setText("Clock");
	rotateUnclock = new Button(shell,SWT.CENTER);
	rotateUnclock.setText("Unclock");
	orientFront = new Button(shell,SWT.CENTER);
	orientFront.setText("Front");
	orientBack = new Button(shell,SWT.CENTER);
	orientBack.setText("Back");
	orientLeft = new Button(shell,SWT.CENTER);
	orientLeft.setText("Left");
	orientRight = new Button(shell,SWT.CENTER);
	orientRight.setText("Right");
	takeOffLand = new Button(shell,SWT.CENTER);
	takeOffLand.setText("TakeOff/Land");
	refresh();

	altUp.addListener(SWT.Selection, new controller.UpListener());
	altDown.addListener(SWT.Selection, new controller.DownListener());
	rotateClock.addListener(SWT.Selection, new controller.ClockListener());
	rotateUnclock.addListener(SWT.Selection, new controller.UnclockListener());
	orientFront.addListener(SWT.Selection, new controller.FrontListener());
	orientBack.addListener(SWT.Selection, new controller.BackListener());
	orientLeft.addListener(SWT.Selection, new controller.LeftListener());
	orientRight.addListener(SWT.Selection, new controller.RightListener());
	takeOffLand.addListener(SWT.Selection, new controller.TakeOffLandListener());

shell.pack();
shell.open();



    }
public void refresh(){
    GridData gridData = new GridData(SWT.FILL, SWT.FILL, false, false);
    gridData.horizontalSpan = 9;
    gridData.heightHint=shell.getSize().y*15/16;
    gridData.horizontalAlignment = GridData.FILL;
    streamArea.setLayoutData(gridData);
    gridData = new GridData(SWT.FILL, SWT.FILL, false, false);
    gridData.horizontalSpan = 1;
    gridData.heightHint=shell.getSize().y*1/16;
    gridData.widthHint=shell.getSize().x*1/9;
    gridData.horizontalAlignment = GridData.FILL; 	altUp.setLayoutData(gridData);
	altDown.setLayoutData(gridData);
	rotateClock.setLayoutData(gridData);
	rotateUnclock.setLayoutData(gridData);
	orientFront.setLayoutData(gridData);
	orientBack.setLayoutData(gridData);
	orientLeft.setLayoutData(gridData);
	orientRight.setLayoutData(gridData);
	takeOffLand.setLayoutData(gridData);
	
}	
	/**
	 * message a string on the interface
	 * @param msg to print
	 * @param code (0 normal, 1 emergency)
	 */
	public void message(String msg, int code ){
		/*TODO*/
	}

	public void repaint() {
		numColor=(numColor+1) % cols.length;
		streamArea.setBackground(cols[numColor]);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}