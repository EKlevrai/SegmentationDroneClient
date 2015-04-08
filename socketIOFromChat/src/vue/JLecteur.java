package vue;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import javax.media.*;
import java.io.*;

public class JLecteur extends JDialog implements ControllerListener{ // Classe qui cr�� un lecteur de vid�os

	File fichier = new File("C:/Documents and Settings/pmad.siege/King of Pop/vid�os/Jackson 5.flv");
	String chemin = fichier.getPath(); // chemin de la vid�o
	URL url; // chemin
	Player lecteur; // lecteur vid�o
	JPanel panel; // panneau
	Component video; // composants vid�os
	Component controles; // controles

	public JLecteur(JFrame frame) { // Constructeur

		setLayout(new BorderLayout()); // disposition

		try{ // exceptions

			url = new URL("http://localhost:5555/"); // chemin de la vid�o
			//lecteur = Managerchemin.createRealizedPlayer(url); // on cr�� le lecteur
		}
		catch(Exception e){}

		panel = new JPanel(); // panneau

		// Composants

		if(lecteur != null){

			video = lecteur.getVisualComponent(); // composants vid�os 
			controles = lecteur.getControlPanelComponent(); // contr�les
			panel.add(video, BorderLayout.CENTER); // on ajoute l'�cran video
			panel.add(controles, BorderLayout.SOUTH); // on ajoute l'�cran video
		}

		else{

			System.out.println("Le lecteur est nul");
		}

		this.getContentPane().add(panel); // on ajoute le panel � la fen�tre
		this.setTitle(chemin); // on change le titre de la fen�tre
		this.setBounds(0, 0, 300, 300);
		this.setLocationRelativeTo(frame); // on la met au milieu
		this.setModal(true); // La fen�tre est modale 
		this.setVisible(true); // la fen�tre est visible
		lecteur.addControllerListener(this); // on ajoute le lecteur aux actions
	}

	public void controllerUpdate(ControllerEvent e){
	}
}