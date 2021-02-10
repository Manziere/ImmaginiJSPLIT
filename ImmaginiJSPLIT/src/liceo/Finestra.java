package liceo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Finestra extends JFrame implements ActionListener, MouseListener, Serializable{

	private JMenuBar bar;
	private JMenu fileMenu;
	private JMenuItem nuovo;
	private JMenuItem salva;
	private JMenuItem serializza;
	private JMenuItem deserializza;
	
	private JSplitPane sp;
	private JScrollPane s1;
	private JPanel s2;
	private JTable table;
	private DefaultTableModel tableModel;
	
	
	private JLabel iFoto;
	
	private JTextField t1;
	
	private JTextField t2;
	
	
	private PersonaDialog pd;
	private ElencoPersone elenco;
	
	
	public Finestra() {
		super("PERSONE");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initMenu();
		initComponent();
		
	}
	
	public void initMenu() {
		bar = new JMenuBar();
		fileMenu = new JMenu("FILE");
		nuovo = new JMenuItem("nuovo");
		nuovo.addActionListener(this);
		fileMenu.add(nuovo);
		salva = new JMenuItem("salva");
		salva.addActionListener(this);
		fileMenu.add(salva);
		serializza = new JMenuItem("serializza");
		serializza.addActionListener(this);
		fileMenu.add(serializza);
		deserializza = new JMenuItem("deserializza");
		deserializza.addActionListener(this);
		fileMenu.add(deserializza);
		bar.add(fileMenu);
		setJMenuBar(bar);		
	}
	
	public void initComponent() {
		
		elenco = new ElencoPersone();
		
		tableModel = new DefaultTableModel(new String[] {"COGNOME", "NOME"}, 0);
		table = new JTable(tableModel);
		table.addMouseListener(this);
		s1 = new JScrollPane(table);
		
		s2 = new JPanel();
		s2.setLayout(new BorderLayout());
		
		
		
		
		iFoto = new JLabel();
		s2.add(iFoto, BorderLayout.CENTER);
				
		
		
		sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, s1, s2);
        add(sp);
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(nuovo)) {
			pd = new PersonaDialog(this, true);
			pd.setVisible(true); 
			if(pd.getRisultato() != null) {
				elenco.add(pd.getRisultato());
				tableModel.addRow(new String[] {pd.getRisultato().getCognome(),pd.getRisultato().getNome()});	
			}			
		}
		if(e.getSource().equals(salva)) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("File di testo", "csv");
			chooser.setFileFilter(filter);
			int retVal = chooser.showSaveDialog(this);
			if(retVal == chooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile();
			    try {
					f.createNewFile();
					this.convertCsv(f, elenco.elencoStringhe());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			  
			}
			
		}
		
		if(e.getSource().equals(serializza)) {
			try {
				
				elenco.serializza(this);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(deserializza)) {
			try{
				elenco.deserializza(this);
				int tot=tableModel.getRowCount();
				for(int i=tot-1;i>=0;i--) {
					tableModel.removeRow(i);
				}
				for(int i=0;i<elenco.size();i++) {
					tableModel.addRow(new String[] {elenco.get(i).getCognome(),elenco.get(i).getNome()});	
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
	}
	public void mouseClicked(MouseEvent m) {
		if(m.getSource().equals(table)) {
			if(table.getSelectedRow() != -1) {
				int i = table.getSelectedRow();
				if(elenco.get(i) != null) {
					Persona p = elenco.get(i);
					iFoto.setIcon(p.getImmagine());
				
					
				}
			}
		}	
	}
	
	public void convertCsv(File f, ArrayList<String> a){
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
			for(String line : a) {
				fw.write(line+"\n");
				
			}			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String args[]) {
		Finestra f = new Finestra();
		f.setVisible(true);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
