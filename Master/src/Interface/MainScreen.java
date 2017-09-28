package Interface;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Channels.Manager;
import Channels.MasterInstance;
import Channels.SerialChannel;
import Channels.TcpChannel;
import Operations.ActionManager;
import Operations.CoilsOperations;
import Operations.DiscreteOperations;
import Operations.InputResgisterOperation;
import Operations.RegistersOperations;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.procimg.SimpleRegister;
import net.wimpi.modbus.util.BitVector;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JCheckBox;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Canvas;
import java.awt.Scrollbar;
import javax.swing.SwingConstants;

public class MainScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private ConfigurationPageTcp ConTcp;
	private  ConfigurationPage ConSerial;
	private AutomaticConfiguration LimitConf;

	//variables para configuracion
	private MasterInstance m1=null;	


	// variables para operaciones 
	final CoilsOperations coils= new CoilsOperations();
	final DiscreteOperations Discrete=new DiscreteOperations();
	final InputResgisterOperation InputR=new InputResgisterOperation();
	final RegistersOperations Register= new RegistersOperations();
	private ActionManager CurrentResponse=null;


	// variables para multiple escritura
	private BitVector bits;
	private Vector<Register> RegisterWrite=null;
	/// modelo para multiple escritura  
	private DefaultListModel modelwrite=new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();

	}


	public final ModbusRequest chooseAction(Integer Option){

		switch (Option) {
		case 0:{
			CurrentResponse=coils;
			return coils.readOperation(Integer.parseInt(textField.getText()),Integer.parseInt(textField_1.getText()));
		}// WriteCoilRequest(0,true);
		case 1:{  

			return coils.writeOperation (Integer.parseInt(textField.getText()),Integer.parseInt(textField_1.getText()),this.bits);
		}
		case 2:{
			CurrentResponse=Register;
			return Register.readOperation(Integer.parseInt(textField.getText()),Integer.parseInt(textField_1.getText()));}
		case 3: 
		{			
			CurrentResponse=Register;
			return Register.writeOperation(Integer.parseInt(textField.getText()),Integer.parseInt(textField_1.getText()),this.RegisterWrite);
		}
		case 4:{
			CurrentResponse=Discrete;
			return Discrete.readOperation (Integer.parseInt(textField.getText()),Integer.parseInt(textField_1.getText()));
		}
		case 5:
		{  
			CurrentResponse=InputR;
			return InputR.readOperation(Integer.parseInt(textField.getText()),Integer.parseInt(textField_1.getText()));}
		case 6:{

			return coils.writesingle(Integer.parseInt(textField.getText()),Boolean.parseBoolean(textField_2.getText()));}
		case 7:{

			return Register.writesingle(Integer.parseInt(textField.getText()),Integer.parseInt(textField_2.getText()));

		}
		default:
			return null;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {


		final Manager core= new Manager();// genero manager para controlar los distintos canales

		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.DARK_GRAY);

		final JRadioButton NewRadioButton = new JRadioButton("Tcp");
		NewRadioButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 128), null));
		NewRadioButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		NewRadioButton.setForeground(new Color(0, 0, 139));
		NewRadioButton.setBackground(Color.DARK_GRAY);

		final JRadioButton NewRadioButton_1 = new JRadioButton("Serial");
		NewRadioButton_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 128), null));
		NewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		NewRadioButton_1.setBackground(Color.DARK_GRAY);
		NewRadioButton_1.setForeground(new Color(0, 0, 139));

		frame.setBounds(100, 100, 1182, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ButtonGroup grupo1=new ButtonGroup();
		grupo1.add(NewRadioButton);
		grupo1.add(NewRadioButton_1);

		JButton btnNewButton = new JButton("ConfigureSerial");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), null));
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setBackground(new Color(105, 105, 105));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConSerial= new ConfigurationPage();
				ConSerial.setVisible(true);

			}
		});

		JButton btnNewButton_1 = new JButton("ConfigureTcp");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewButton_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), null));
		btnNewButton_1.setForeground(new Color(0, 0, 128));
		btnNewButton_1.setBackground(new Color(105, 105, 105));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConTcp=new ConfigurationPageTcp();
				ConTcp.setVisible(true);
			}
		});





		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 128), null));
		scrollPane.setBackground(new Color(0, 0, 0));
		//scrollPane.setViewportView();

		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), null));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		lblNewLabel.setForeground(new Color(50, 205, 50));
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setOpaque(true);
		//scrollPane.setRowHeaderView(lblNewLabel);
		scrollPane.setViewportView(lblNewLabel);



		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 128), null));
		final JList list = new JList();
		list.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), null));
		list.setBackground(new Color(128, 128, 128));
		list.setForeground(new Color(0, 0, 128));
		DefaultListModel model=new DefaultListModel();
		model.addElement("ReadCoils");
		model.addElement("WriteCoils");
		model.addElement("ReadRegisters");
		model.addElement("WriteRegisters");
		model.addElement("ReadInputDiscretes");
		model.addElement("ReadInputRegisters");
		model.addElement("WriteSingleCoil");
		model.addElement("WritesingleRegister");
		list.setModel(model);
		scrollPane_1.setViewportView(list);


		JButton btnNewButton_2 = new JButton("Execute");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnNewButton_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), null));
		btnNewButton_2.setBackground(new Color(105, 105, 105));
		btnNewButton_2.setForeground(new Color(0, 0, 128));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ModbusRequest message = chooseAction(list.getSelectedIndex());
				String state="";
				String Init="";

				if (NewRadioButton.isSelected())

				{
					String adress=ConTcp.getAdress();
					int port=Integer.parseInt( ConTcp.getPort());
					core.addTCPChannel("secondconnection",adress,port,1);// 192.169.199.74, 502
					TcpChannel tcp2=(TcpChannel) core.getChannel("secondconnection");
					tcp2.addMasterInstance("second",12,adress,port);
					MasterInstance m2=tcp2.getInstance("second");
					System.out.println(m2);
					m2.ExecuteInstance(message);

					////control de null para operacion write
					if (CurrentResponse != null)
						state=CurrentResponse.ResponseDetails(tcp2.getResponse());
					else state="operation write succeed";

					String aux="<html>"+ "Connected using Tcp to "+ ConTcp.getAdress() + "<BR>" + tcp2.DebugRequest()+"<BR>"+ tcp2.DebugResponse()+ "<BR>"+ state +"</html>";
					lblNewLabel.setText(aux);
					System.out.println(ConTcp.getAdress());

				}

				else 
					if (NewRadioButton_1.isSelected()){
						core.addSerialChannel(1,"firstconnectionS",Integer.parseInt(ConSerial.GetBaud()),ConSerial.GetParity(),Integer.parseInt(ConSerial.GetDatabits()),Integer.parseInt( ConSerial.GetStopBits()),ConSerial.GetPort());
						SerialChannel Serial=(SerialChannel) core.getChannel("firstconnectionS");
						Serial.addMasterInstance("FirstInstance",Integer.parseInt(ConSerial.GetDevice()),null,0);
						System.out.println("estoy en serial ");
						MasterInstance m2=Serial.getInstance("FirstInstance");
						System.out.println(m2);
						m2.ExecuteInstance(message);

						//control de null para la operacion 
						if (CurrentResponse != null)
							state=CurrentResponse.ResponseDetails(Serial.getResponse());
						else state="operation write succeed";
						//core.addSerialChannel(name, device, baud, Parity, datab, stopb, com);
						lblNewLabel.setText("<html>"+ "Connected using Serial to slave "+ m2.getIdoutstation() + "<BR>" + Serial.DebugRequest()+"<BR>"+ Serial.DebugResponse()+ "<BR>"+ state +"</html>");
					}

			}
		});

		JLabel lblNewLabel_1 = new JLabel("Operations");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setForeground(new Color(0, 0, 139));

		JLabel lblStart = new JLabel("Start");
		lblStart.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblStart.setForeground(new Color(0, 0, 128));

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		textField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 205)));
		textField.setSelectedTextColor(Color.WHITE);
		textField.setForeground(new Color(0, 0, 205));
		textField.setBackground(new Color(105, 105, 105));
		textField.setColumns(10);

		JLabel lblCount = new JLabel("Lenght");
		lblCount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblCount.setForeground(new Color(0, 0, 128));

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		textField_1.setForeground(new Color(0, 0, 255));
		textField_1.setBackground(new Color(105, 105, 105));
		textField_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 255)));
		textField_1.setColumns(10);

		JLabel lblCoilstatus = new JLabel("Input");
		lblCoilstatus.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblCoilstatus.setForeground(new Color(0, 0, 139));

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		textField_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 255)));
		textField_2.setSelectedTextColor(new Color(0, 0, 255));
		textField_2.setForeground(new Color(0, 0, 255));
		textField_2.setCaretColor(Color.GRAY);
		textField_2.setBackground(new Color(105, 105, 105));
		textField_2.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Options");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_2.setForeground(new Color(0, 0, 128));

		JButton btnNewButton_3 = new JButton("add status");
		btnNewButton_3.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), null));
		btnNewButton_3.setForeground(new Color(0, 0, 139));
		btnNewButton_3.setBackground(new Color(105, 105, 105));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bits=new BitVector(Integer.parseInt(textField_1.getText()));
				for (int i=0 ;i < modelwrite.size();i++){
					bits.setBit(i,Boolean.parseBoolean((String) modelwrite.getElementAt(i)));
				}

			}
		});

		JButton btnNewButton_4 = new JButton("add Register");
		btnNewButton_4.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), null));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<Register> k=new Vector<Register>();
				RegisterWrite=k;

				for (int i=0;i < modelwrite.size();i++) {
					Register h=new SimpleRegister(Integer.parseInt((String) modelwrite.getElementAt(i)));
					RegisterWrite.addElement(h);
				}
			}
		});
		btnNewButton_4.setForeground(new Color(0, 0, 139));
		btnNewButton_4.setBackground(new Color(105, 105, 105));



		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), null));


		final JList list_2 = new JList();
		list_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 128), new Color(0, 0, 0)));
		list_2.setForeground(new Color(0, 0, 205));
		list_2.setBackground(new Color(105, 105, 105));
		scrollPane_2.setViewportView(list_2);



		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setIcon(new ImageIcon(MainScreen.class.getResource("/Resources/flecha-derecha-en-boton-circular.png")));
		btnNewButton_5.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 0), null));
		btnNewButton_5.setForeground(new Color(0, 0, 205));
		btnNewButton_5.setBackground(new Color(105, 105, 105));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelwrite.addElement(textField_2.getText());
				list_2.setModel(modelwrite);
			}
		});




		JButton btnNewButton_6 = new JButton(new ImageIcon(MainScreen.class.getResource("/Resources/basura-simbolo-de-interfaz.png")));
		btnNewButton_6.setSize(100, 100);
		btnNewButton_6.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 0, 205), new Color(0, 0, 0)));
		btnNewButton_6.setBackground(new Color(105, 105, 105));
		btnNewButton_6.setForeground(new Color(0, 0, 205));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(list_2.getSelectedIndex());
				modelwrite.remove(list_2.getSelectedIndex());
				list_2.setModel(modelwrite);


			}
		});



		JLabel lblNewLabel_3 = new JLabel("Multiple Options");
		lblNewLabel_3.setForeground(new Color(0, 0, 139));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));

		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.setBackground(new Color(105, 105, 105));
		btnNewButton_8.setIcon(new ImageIcon(MainScreen.class.getResource("/Resources/pausa.png")));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m1.disable();
				System.out.println("llegue al disable");
				lblNewLabel.setText(" Automatic poll has been stopped");}}
				);

		JButton btnNewButton_9 = new JButton("");
		btnNewButton_9.setIcon(new ImageIcon(MainScreen.class.getResource("/Resources/boton-de-reproduccion (1).png")));
		btnNewButton_9.setBackground(new Color(105, 105, 105));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m1.enable();
				lblNewLabel.setText("Automatic poll restaured");
			}
		});


		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.setBackground(new Color(105, 105, 105));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//make the array with the limits involved in the automatic operation
				ArrayList<String> Limits=new ArrayList<String>();
				Limits.add(LimitConf.limitDiscrete());
				Limits.add(LimitConf.limitRegister());
				Limits.add(LimitConf.limitInputRegister());
				Limits.add(LimitConf.limitCoil());

				if (NewRadioButton.isSelected()) {

					core.addTCPChannel("first",ConTcp.getAdress(),Integer.parseInt(ConTcp.getPort()),1);// 192.169.199.74, 502
					TcpChannel tcp1=(TcpChannel) core.getChannel("first");
					tcp1.SetLimitsQuery(Limits);
					tcp1.addMasterInstance("first",12,ConTcp.getAdress(),Integer.parseInt(ConTcp.getPort()));
					m1=tcp1.getInstance("first");}
				else {

					core.addSerialChannel(1,"firstconnectionS",Integer.parseInt(ConSerial.GetBaud()),ConSerial.GetParity(),Integer.parseInt(ConSerial.GetDatabits()),Integer.parseInt( ConSerial.GetStopBits()),ConSerial.GetPort());
					SerialChannel Serial=(SerialChannel) core.getChannel("firstconnectionS");
					Serial.SetLimitsQuery(Limits);
					System.out.println(Serial);
					Serial.addMasterInstance("F",Integer.parseInt(ConSerial.GetDevice()),null,0);
					m1=Serial.getInstance("F");
				}
				System.out.println(m1);
				//m1.SetLimitAutomatic(Integer.parseInt(textField_1.getText()));
				m1.start();
				lblNewLabel.setText("Automatic poll has been Initialized");
				m1.GetScreendebug();
			}
		});
		btnNewButton_7.setIcon(new ImageIcon(MainScreen.class.getResource("/Resources/potencia.png")));

		JLabel lblNewLabel_4 = new JLabel("Automatic Control");
		lblNewLabel_4.setForeground(new Color(0, 0, 139));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		JButton btnNewButton_10 = new JButton("");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimitConf=new AutomaticConfiguration();
				LimitConf.setVisible(true);

			}
		});
		btnNewButton_10.setIcon(new ImageIcon(MainScreen.class.getResource("/Resources/herramienta-filtro-forma-negra.png")));
		btnNewButton_10.setBackground(new Color(105, 105, 105));

		JButton btnNewButton_11 = new JButton("");
		btnNewButton_11.setBackground(new Color(105, 105, 105));
		btnNewButton_11.setIcon(new ImageIcon(MainScreen.class.getResource("/Resources/boton-cancelar (1).png")));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m1.destroy();
				m1.HideScreendebug();
			}
		});

		JLabel lblNewLabel_5 = new JLabel("Reboot automatic");
		lblNewLabel_5.setForeground(new Color(0, 0, 128));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));





		///............................................. responsive code application with group layout..........................................................

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(23)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(NewRadioButton, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
										.addGap(34))
								.addComponent(NewRadioButton_1, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(66)
										.addComponent(btnNewButton_10, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblNewLabel_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnNewButton_7, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnNewButton_9, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnNewButton_8, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
										.addGap(9))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
												.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
										.addGap(68)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
						.addGap(44)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
										.addGap(14))
								.addComponent(lblStart, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblCount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(50))
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(29)
										.addComponent(lblCoilstatus, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(18)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(31)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnNewButton_11, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
														.addGap(17)
														.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
										.addGap(40))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(14)
														.addComponent(btnNewButton_5, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnNewButton_6)
										.addGap(78)))
						.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
						.addGap(223))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(5)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 57, Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
														.addGap(17))
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(0, 0, Short.MAX_VALUE)
														.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
														.addGap(13)
														.addComponent(lblStart)
														.addGap(8)
														.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(15)
														.addComponent(lblCount)
														.addGap(8)
														.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(23))
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout.createSequentialGroup()
																		.addGap(9)
																		.addComponent(NewRadioButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
																.addGroup(groupLayout.createSequentialGroup()
																		.addGap(13)
																		.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(NewRadioButton_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
																.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
														.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
																.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(btnNewButton_8, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
																		.addComponent(btnNewButton_9, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(btnNewButton_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																.addComponent(btnNewButton_10, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(12)
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
																.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
														.addGap(11))
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(lblCoilstatus, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addComponent(btnNewButton_5))
														.addGap(37)))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnNewButton_4, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
										.addGap(23)
										.addComponent(lblNewLabel_5)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnNewButton_11, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGap(4))
				);
		frame.getContentPane().setLayout(groupLayout);







	}
}
