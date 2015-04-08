package vue;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import javax.media.*;
import java.io.*;

public class JLecteur extends JDialog implements ControllerListener{ // Classe qui créé un lecteur de vidéos

	File fichier = new File("C:/Documents and Settings/pmad.siege/King of Pop/vidéos/Jackson 5.flv");
	String chemin = fichier.getPath(); // chemin de la vidéo
	URL url; // chemin
	Player lecteur; // lecteur vidéo
	JPanel panel; // panneau
	Component video; // composants vidéos
	Component controles; // controles

	public JLecteur(JFrame frame) { // Constructeur

		setLayout(new BorderLayout()); // disposition

		try{ // exceptions

			url = new URL("http://localhost:5555/"); // chemin de la vidéo
			//lecteur = Managerchemin.createRealizedPlayer(url); // on créé le lecteur
		}
		catch(Exception e){}

		panel = new JPanel(); // panneau

		// Composants

		if(lecteur != null){

			video = lecteur.getVisualComponent(); // composants vidéos 
			controles = lecteur.getControlPanelComponent(); // contrôles
			panel.add(video, BorderLayout.CENTER); // on ajoute l'écran video
			panel.add(controles, BorderLayout.SOUTH); // on ajoute l'écran video
		}

		else{

			System.out.println("Le lecteur est nul");
		}

		this.getContentPane().add(panel); // on ajoute le panel à la fenêtre
		this.setTitle(chemin); // on change le titre de la fenêtre
		this.setBounds(0, 0, 300, 300);
		this.setLocationRelativeTo(frame); // on la met au milieu
		this.setModal(true); // La fenêtre est modale 
		this.setVisible(true); // la fenêtre est visible
		lecteur.addControllerListener(this); // on ajoute le lecteur aux actions
	}

	public void controllerUpdate(ControllerEvent e){
	}
}