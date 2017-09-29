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
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import javax.swing.JMenuBar;
import java.awt.Choice;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;

public class ConfigurationPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField_4;
	private JTextField textField_5;
	private Choice choicebaud;
	private Choice choiceParity;
	private Choice choiceData;
	private Choice choiceStop;
	private Choice choiceParit;
	private Choice choice_1;
	private Choice choice_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurationPage frame = new ConfigurationPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public String GetBaud (){
		return choicebaud.getSelectedItem(); 
	}
	
	
	public String GetParity(){
		return choiceParity.getSelectedItem();
	}
	
	public String GetDatabits(){
		return choiceData.getSelectedItem();
	}
	
	public String GetStopBits(){
		return choiceStop.getSelectedItem();}
	
	public String GetPort(){
		return this.textField_4.getText().toUpperCase(); ///paso puerto a mayuscula   
	}
	
	public String GetDevice(){
		return this.textField_5.getText();
	}
	
	/**
	 * Create the frame.
	 */
	public ConfigurationPage() {
		
		final ConfigurationPage obj=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBaud = new JLabel("Baud ");
		lblBaud.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblBaud.setForeground(new Color(0, 0, 139));
		lblBaud.setBounds(31, 29, 46, 14);
		contentPane.add(lblBaud);
		
		JLabel lblParity = new JLabel("Parity");
		lblParity.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblParity.setForeground(new Color(0, 0, 139));
		lblParity.setBounds(31, 66, 46, 14);
		contentPane.add(lblParity);
		
		JLabel lblNewLabel = new JLabel("Databits");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setBounds(31, 101, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Stopbit");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setForeground(new Color(0, 0, 139));
		lblNewLabel_1.setBounds(31, 148, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setIcon(new ImageIcon(ConfigurationPage.class.getResource("/Resources/confirmar.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 128)));
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setBackground(new Color(105, 105, 105));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				obj.setVisible(false);
			}
		});
		btnNewButton.setBounds(418, 213, 110, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblPort.setForeground(new Color(0, 0, 139));
		lblPort.setBounds(31, 193, 46, 14);
		contentPane.add(lblPort);
		
		textField_4 = new JTextField();
		textField_4.setForeground(new Color(0, 0, 255));
		textField_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		textField_4.setBackground(new Color(128, 128, 128));
		textField_4.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 128), new Color(0, 0, 0)));
		textField_4.setBounds(181, 190, 99, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Device Id");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_2.setForeground(new Color(0, 0, 139));
		lblNewLabel_2.setBackground(new Color(0, 0, 139));
		lblNewLabel_2.setBounds(309, 29, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		textField_5 = new JTextField();
		textField_5.setForeground(new Color(0, 0, 255));
		textField_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		textField_5.setBackground(new Color(128, 128, 128));
		textField_5.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 128), new Color(0, 0, 0)));
		textField_5.setBounds(384, 26, 99, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
	    choicebaud = new Choice();
	    choicebaud.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    choicebaud.setForeground(new Color(0, 0, 139));
	    choicebaud.setBackground(new Color(128, 128, 128));
		choicebaud.add("9600");
		choicebaud.add("19200");
		choicebaud.add("57600");
		choicebaud.add("115200");
		choicebaud.setBounds(181, 29, 99, 21);
		contentPane.add(choicebaud);
		
		choiceParity = new Choice();
		choiceParity.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		choiceParity.setForeground(new Color(0, 0, 139));
		choiceParity.setBackground(new Color(128, 128, 128));
		choiceParity.setBounds(181, 66, 99, 20);
		choiceParity.add("none");
		choiceParity.add("even");
		choiceParity.add("odd");
		contentPane.add(choiceParity);
		
		choiceData = new Choice();
		choiceData.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		choiceData.setBackground(new Color(128, 128, 128));
		choiceData.setForeground(new Color(0, 0, 139));
		choiceData.setBounds(180, 101, 100, 20);
		choiceData.add("5");
		choiceData.add("6");
		choiceData.add("7");
		choiceData.add("8");
		contentPane.add(choiceData);
		
		choiceStop = new Choice();
		choiceStop.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		choiceStop.setForeground(new Color(0, 0, 139));
		choiceStop.setBackground(new Color(128, 128, 128));
		choiceStop.setBounds(181, 148, 99, 20);
		choiceStop.add("1");
		choiceStop.add("2");
		contentPane.add(choiceStop);
	}
}
