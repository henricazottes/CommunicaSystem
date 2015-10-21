/**
 * Example of a Fenetre (JFrame) using awt and swing
 * @author your_name
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.swing.*;

 public class Communica extends JFrame implements ActionListener, WindowListener{

	 private CommunicaTCP ni;
	 private BufferedWriter writer;
	 private BufferedReader reader;
	 private ListenSocket ls;
	 private JButton bSend;
	 private JLabel lmessrec;
	 private JTextArea textRec;
	 private JTextField textToSend;
	 private JTextField tfpseudo;
	 private JLabel lpseudo;
	 private JPanel recPart;
	 private JPanel pseudoPanel;
	 private JPanel ip;
	 private JPanel port;
	 private JScrollPane scrollRec;
	 private String recPath;
	 private String sendPath;
	 private JMenuBar menu;

 

 /** Creates a Fenetre */
 public Communica(OutputStreamWriter myWriter, InputStreamReader myReader, CommunicaTCP networkInterface) {
	 
	 this.writer = new BufferedWriter(myWriter);
	 this.reader = new BufferedReader(myReader);
	 this.ni = networkInterface;
	 
	 initGUI();
	 
	 this.ls = new ListenSocket(this.reader);
	 this.ls.addObserver(this);
	 this.ls.start();
 }

 /** Initializes the Fenetre components */

 private void initGUI() {
	 
	this.addWindowListener(this);
	
	// Menu bar
	/*
	 JMenuBar menubar = new JMenuBar();
     JMenu file = new JMenu("File");
     file.setMnemonic(KeyEvent.VK_F);

     JMenuItem configMenuItem = new JMenuItem("Config");
     JMenuItem exitMenuItem = new JMenuItem("Exit");
     
     configMenuItem.setMnemonic(KeyEvent.VK_E);
     configMenuItem.setToolTipText("Open configuration page");
     configMenuItem.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent event) {
             
         }
     });
     
     exitMenuItem.setMnemonic(KeyEvent.VK_E);
     exitMenuItem.setToolTipText("Exit application");
     exitMenuItem.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent event) {
             System.exit(0);
         }
     });

     file.add(exitMenuItem);
     menubar.add(file);

     setJMenuBar(menubar);*/
     
	 lpseudo = new JLabel("Pseudo: ");
	 tfpseudo = new JTextField(10); 
	 tfpseudo.addActionListener(this);
	 
	 textToSend = new JTextField(20);
	 textToSend.addActionListener(this);
	 
	 
	 lmessrec = new JLabel("Received:");
	 textRec = new JTextArea(20,45);
	 scrollRec = new JScrollPane(textRec);
	 scrollRec.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	 
	 	 
	 pseudoPanel = new JPanel();
	 pseudoPanel.add("West",lpseudo);
	 pseudoPanel.add("East", tfpseudo);
	 
	 
	 recPart = new JPanel();
	 recPart.add(scrollRec,"Center");
	 
	 ip = new JPanel();
	 ip.add("West", new JLabel("IP:"));
	 ip.add("Est", new JTextField(20));
	 
	 port = new JPanel();
	 port.add("West", new JLabel("Port:"));
	 port.add("Est", new JTextField(20));
	 
	 // configures the JFrame layout using a border layout
	 this.setLayout(new BorderLayout());
	 // places the components in the layout
	 this.displayChat();
	 
	 // packs the fenetre: size is calculated
	 // regarding the added components
	 this.pack();
	 // the JFrame is visible now
	 this.setVisible(true);
 }
 
 public void displayChat(){
	 this.add("North", pseudoPanel);
	 this.add("Center", textRec);
	 this.add("South", textToSend);
 }
 
 public void displayConfig(){
	 this.add("North", ip);
	 this.add("South", port);
 }
 
 
 public void updateReceive(){	 
	 System.out.println("Je reçois !");
	 this.textRec.setText(this.textRec.getText() + "\n" + this.ls.getLastMessage());	
 }

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	//this.chatArea.setText(this.chatArea.getText()+"\n"+this.text.getText());
	//this.text.setText("");
	if (arg0.getSource() == textToSend){
		
		System.out.println("J'envoie !");
		
		try {			
			writer.write(this.tfpseudo.getText() + ": " + this.textToSend.getText());			
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.textRec.setText(this.textRec.getText()+ "\n" + this.tfpseudo.getText() + ": " + this.textToSend.getText());
		this.textToSend.setText("");	
	}else if(arg0.getSource() == tfpseudo){
		System.out.println("Focus asked.");
		this.textToSend.requestFocus();
	}else{
		System.out.println("Soucis...");
	}
	System.out.flush();
}



@Override
public void windowClosing(WindowEvent arg0) {
	// TODO Auto-generated method stub
	this.ls.stopReader();
	this.ni.close();
	System.exit(0);
}

@Override
public void windowActivated(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosed(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeactivated(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeiconified(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowIconified(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowOpened(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}	
}
 
 
 //BufferedWriter -> write and newline() , and flush sometimes, and BufferedReader, 