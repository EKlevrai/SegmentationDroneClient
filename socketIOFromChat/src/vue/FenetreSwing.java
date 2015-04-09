package vue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreSwing extends JFrame{
	
	public FenetreSwing(){
		this.setSize(640, 640);
		this.add(new ImagePanel());
		this.setVisible(true);
		

		
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
	}
	

}
