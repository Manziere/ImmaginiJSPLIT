package liceo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PersonaDialog extends JDialog implements ActionListener {
	private JLabel l1;
	private JLabel l2;
	private JTextField t1;
	private JTextField t2;
	private JButton f;
	private JButton btnSalva;
	private ImageIcon image;
	private Persona p;
	public JLabel getL1() {
		return l1;
	}
	public void setL1(JLabel l1) {
		this.l1 = l1;
	}
	public JLabel getL2() {
		return l2;
	}
	public void setL2(JLabel l2) {
		this.l2 = l2;
	}
	public JTextField getT1() {
		return t1;
	}
	public void setT1(JTextField t1) {
		this.t1 = t1;
	}
	public JTextField getT2() {
		return t2;
	}
	public void setT2(JTextField t2) {
		this.t2 = t2;
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public PersonaDialog(JFrame owner, boolean modale) {
		super(owner, modale);
		setSize(800,600);
		setLayout(new GridLayout(5,2));
		initComponents();
	}
public void  initComponents() {
		
		l1 = new JLabel("COGNOME:  ");
		add(l1);
		t1 = new JTextField(10);
		add(t1);
		
		l2 = new JLabel("NOME:  ");
		add(l2);
		t2 = new JTextField(10);
		add(t2);
		
		f = new JButton("AGGIUNGERE FOTO");
		f.addActionListener(this);
		add(f);
		

		btnSalva=new JButton ("SALVA");
		btnSalva.addActionListener(this);
		add(btnSalva);
}

@Override
public void actionPerformed(ActionEvent e) {
	
	if(e.getSource().equals(f)) {
		JFileChooser c = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("immagine", "jpg");
		c.setFileFilter(filter);
		int retVal = c.showOpenDialog(this);
		if(retVal == c.APPROVE_OPTION) {
			File file = c.getSelectedFile();
			if(file.exists()) {
				image = new ImageIcon(file.getPath());
			}
		}
	}
	
	try {
		if(e.getSource().equals(btnSalva)) {
			if(p == null) {
				p = new Persona(t1.getText(), t2.getText(),  image);
				dispose();
			}
		}		
	}
	catch(Exception exc) {
		JOptionPane.showMessageDialog(null, exc.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
	}
	}

	public Persona getRisultato() {
		return p;
	}
}
