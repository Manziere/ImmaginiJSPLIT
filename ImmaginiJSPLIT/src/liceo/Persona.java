package liceo;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Persona implements Serializable {
	private String cognome;
	private String nome;
	private ImageIcon immagine;
	
	public Persona(String cognome, String nome, ImageIcon immagine) {
		super();
		this.setCognome(cognome);
		this.setNome(nome);
		this.setImmagine(immagine);
		
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ImageIcon getImmagine() {
		return immagine;
	}

	public void setImmagine(ImageIcon immagine) {
		this.immagine = immagine;
	}
	
	public String toString() {
		return cognome+","+nome;
	}
}
