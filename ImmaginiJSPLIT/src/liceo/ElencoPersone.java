package liceo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ElencoPersone extends ArrayList<Persona> implements Serializable  {

	public void serializza(JFrame j) throws Exception{	
	FileOutputStream f = null;
	ObjectOutputStream o = null;
	try {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("generico", "dat");
		chooser.setFileFilter(filter);
		int retVal = chooser.showSaveDialog(j);
		if(retVal == chooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			file.createNewFile();
			f = new FileOutputStream(file);
			o = new ObjectOutputStream(f);
			o.writeObject(this);
			o.flush();
		}		
	}
	catch(Exception exc) {
		throw new Exception();
	}
	finally {
		if(o != null) {
			try {
				o.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


public void deserializza(JFrame j ) {
	FileInputStream f = null;
	ObjectInputStream o = null;
	try {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("generico", "dat");
		chooser.setFileFilter(filter);
		int retVal = chooser.showOpenDialog(j);
		if(retVal == chooser.APPROVE_OPTION) {
			File f1 = chooser.getSelectedFile();
			if(f1.exists()) {
				f = new FileInputStream(f1);
				o = new ObjectInputStream(f);
				ElencoPersone ep = (ElencoPersone)o.readObject();
				
				int i;
				for(i=0; i<ep.size(); i++) {
					this.add(ep.get(i));
				}					
			}
		}
	}
	catch(Exception exc) {
		JOptionPane.showMessageDialog(null, exc.getMessage(), "ERRORE", JOptionPane.ERROR_MESSAGE);
	}
	finally {
		if(o != null) {
			try {
				o.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

	public ArrayList<String> elencoStringhe(){
		ArrayList<String> l=new ArrayList<String>();
		for(int i=0;i<this.size();i++) {
			l.add(this.get(i).toString());
		}
		return l;
	}
}
