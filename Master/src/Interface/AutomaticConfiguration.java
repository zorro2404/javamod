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
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class AutomaticConfiguration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutomaticConfiguration frame = new AutomaticConfiguration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
	
	
	
	public String limitInputRegister() {
		return this.textField_1.getText();
	}
	
	public String limitCoil() {
		return this.textField_2.getText();
	}
	
	public String limitRegister() {
		return this.textField.getText();
	}
	
	public String limitDiscrete() {
		return this.textField_3.getText();
	}
	
	/**
	 * Create the frame.
	 */
	public AutomaticConfiguration() {
		final AutomaticConfiguration obj=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registers");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel.setBounds(30, 33, 71, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("InputRegisters");
		lblNewLabel_1.setForeground(new Color(0, 0, 139));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setBounds(30, 77, 95, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Coils");
		lblNewLabel_2.setForeground(new Color(0, 0, 139));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_2.setBounds(30, 117, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Discrete");
		lblNewLabel_3.setForeground(new Color(0, 0, 139));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_3.setBounds(30, 166, 71, 14);
		contentPane.add(lblNewLabel_3);
		
	 textField = new JTextField();
	 textField.setBackground(Color.LIGHT_GRAY);
		textField.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 139), new Color(0, 0, 0)));
		textField.setBounds(137, 30, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText("none");
		
		textField_1 = new JTextField();
		textField_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 139), new Color(0, 0, 0)));
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(137, 74, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText("none");
		
		textField_2 = new JTextField();
		textField_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 139), new Color(0, 0, 0)));
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(137, 114, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText("none");
		
		textField_3 = new JTextField();
		textField_3.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 139), new Color(0, 0, 0)));
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setBounds(137, 163, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText("none");
		
		JLabel lblNewLabel_4 = new JLabel("Limit Configuration");
		lblNewLabel_4.setForeground(new Color(0, 0, 205));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_4.setBounds(156, 5, 132, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 139), new Color(0, 0, 0)));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon(AutomaticConfiguration.class.getResource("/Resources/confirmar.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				obj.setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(105, 105, 105));
		btnNewButton.setBounds(300, 204, 95, 32);
		contentPane.add(btnNewButton);
	}
}
