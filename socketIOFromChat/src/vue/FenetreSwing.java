package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreSwing extends JFrame{
	public FenetreSwing(){
		this.setSize(640, 640);
		this.add(new JLecteur(this));
		this.setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
	}
	

}
