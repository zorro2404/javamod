package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class ConfigurationPageTcp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurationPageTcp frame = new ConfigurationPageTcp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	

	
	
	public String getAdress(){
	 return	textField_1.getText();
	  
	}
	
	
	public String getPort(){
		
		return textField.getText();
	}
	
	
	
	public void SetAdreess(String value) {
		this.textField.setText(value);
	}
	
	
	public void SetPort(String value) {
		this.textField_1.setText(value);
	}
	
	public void SetDevice(String value) {
		this.textField_2.setText(value);
	}
	
	
	public ConfigurationPageTcp() {
		final ConfigurationPageTcp obj= this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblPort.setForeground(new Color(0, 0, 139));
		lblPort.setBackground(new Color(0, 0, 139));
		lblPort.setBounds(30, 36, 46, 14);
		contentPane.add(lblPort);
		
		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblAdress.setForeground(new Color(0, 0, 139));
		lblAdress.setBounds(30, 168, 46, 14);
		contentPane.add(lblAdress);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 255));
		textField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		textField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 128)));
		textField.setBackground(new Color(128, 128, 128));
		textField.setBounds(129, 33, 152, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(0, 0, 255));
		textField_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		textField_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 128)));
		textField_1.setBackground(new Color(128, 128, 128));
		textField_1.setBounds(129, 166, 152, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setIcon(new ImageIcon(ConfigurationPageTcp.class.getResource("/Resources/confirmar.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 128)));
		btnNewButton.setForeground(new Color(0, 0, 205));
		btnNewButton.setBackground(new Color(105, 105, 105));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obj.setVisible(false);
				
			}
		});
		btnNewButton.setBounds(315, 205, 97, 45);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Device");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(30, 91, 61, 14);
		contentPane.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 139), new Color(0, 0, 0)));
		textField_2.setBackground(new Color(128, 128, 128));
		textField_2.setBounds(129, 88, 152, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
	}
}
