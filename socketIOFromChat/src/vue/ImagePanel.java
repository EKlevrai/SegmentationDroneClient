package vue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private BufferedImage bImage;
	public ImagePanel(){
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		try {
			bImage = ImageIO.read(new URL("http://localhost:8080"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(bImage,0,0,null);
	}

}
