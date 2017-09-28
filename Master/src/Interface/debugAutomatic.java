package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Channels.MasterInstance;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class debugAutomatic extends JFrame  {

	private JPanel contentPane;
    private String debug="";
    private MasterInstance CurrentInstance=null;
    private JLabel lblNewLabel_1=null;
    private JScrollPane scrollPane_1=null;
   
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					debugAutomatic frame = new debugAutomatic(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
	
	
	
	
	public void Setdebug(String text) {
		this.debug=text;
	}

	
	public void SetScroolPane(JLabel label) {
		this.scrollPane_1.setViewportView(label);
	}
	
	
	
	public JLabel SetLabelScrenn(String input) {
		
		//System.out.println("previo input"+lblNewLabel_1.getText());
		String before=lblNewLabel_1.getText();
		//el before 
		before=before.replace("<html>","");
		before=before.replace("</html>","");
		//System.out.println("el before change"+ before);
		//el input 
		input=input.replace("<html>","");
		input=input.replace("</html>","");
		//System.out.println("esot es el input"+input);
		String h="<html>"+ before +"<BR>" +input+ "</html>";
		//System.out.println("final string"+h);
		lblNewLabel_1 = new JLabel("");
	    lblNewLabel_1.setForeground(Color.GREEN);
	    lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setOpaque(true);
		this.lblNewLabel_1.setText(h);
		return lblNewLabel_1;
	}
   
	
	/**
	 * Create the frame.
	 */
	public debugAutomatic(MasterInstance master) {
		this.CurrentInstance=master;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 438);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Automatic Debug");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(60, 11, 135, 26);
		contentPane.add(lblNewLabel);
		
	    scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 128), new Color(0, 0, 0)));
		scrollPane_1.setBounds(60, 48, 427, 316);
		contentPane.add(scrollPane_1);
		
	    lblNewLabel_1 = new JLabel("");
	    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
	    lblNewLabel_1.setForeground(Color.GREEN);
	    lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setOpaque(true);
		scrollPane_1.setViewportView(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 139), new Color(0, 0, 0)));
		btnNewButton.setBackground(new Color(169, 169, 169));
		btnNewButton.setIcon(new ImageIcon(debugAutomatic.class.getResource("/Resources/escoba-de-limpieza-para-suelos.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel_1 = new JLabel("");
			    lblNewLabel_1.setForeground(Color.GREEN);
			    lblNewLabel_1.setBackground(Color.BLACK);
				lblNewLabel_1.setVerticalTextPosition(SwingConstants.TOP);
				lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
				lblNewLabel_1.setOpaque(true);
				scrollPane_1.setViewportView(lblNewLabel_1);
				
			}
		});
		btnNewButton.setBounds(405, 11, 47, 33);
		contentPane.add(btnNewButton);
		
		 
	}  
}
