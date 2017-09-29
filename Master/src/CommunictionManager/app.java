package CommunictionManager;

import Channels.Manager;
import Channels.MasterInstance;
import Channels.SerialChannel;
import Channels.TcpChannel;
import Operations.ActionManager;
import Operations.DiscreteOperations;
import Operations.InputResgisterOperation;
import Operations.RegistersOperations;

public class app {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager core=new Manager();
		//declaracion de operaciones 
		InputResgisterOperation poll1=new InputResgisterOperation ();
		DiscreteOperations poll2=new DiscreteOperations();
		RegistersOperations poll3=new RegistersOperations();

		//init variables
		String ipadress="";
		int port=0;
		int idmaster=0;
		int idoutstation=0;
		String device="";
		int serialport=0;


		String path="C:\\Users\\Facu\\Desktop\\configTCP.json";

		//ConfigMaster MainMaster= new ConfigMaster();
		ConfigMaster config= ConfigMaster.fromFile(path);



		//determino tipo de conexion 
		if (config.GetOutstations().get(0).getType().name().equals("TCP")) {
			//caso de que sea una conexion tcp
			System.out.println("entre en tcp");
			ipadress=config.GetOutstations().get(0).ip;
			port=config.GetOutstations().get(0).getPort();		
			idmaster=config.GetOutstations().get(0).ids.get(0).idmaster;
			idoutstation=config.GetOutstations().get(0).ids.get(0).idoutstation;
		}
		else 
			//caso de que sea una conexion serial 
		{
			device=config.GetOutstations().get(0).getDevice(); 
			serialport=config.GetOutstations().get(0).getPort();
		}



		//conexion serial
		core.addSerialChannel(1, "serial", 19200,"none" ,8, 1, device);
		SerialChannel serial=(SerialChannel)core.getChannel("serial");
		serial.addMasterInstance("serial1", 1, null,0);
		MasterInstance m2=serial.getInstance("serial1");

		//conexion tcp
		core.addTCPChannel("secondconnection",ipadress,port,idmaster);      // 192.169.199.74, 502 second  192.168.7.42   pladema ip 192.168.7.213
		TcpChannel tcp2=(TcpChannel) core.getChannel("secondconnection");
		tcp2.addMasterInstance("second",idoutstation,ipadress,port);
		MasterInstance m1=tcp2.getInstance("second");

		
		RequestProducer request1 = new RequestProducer(0, 2, poll1, m1, null);
		// generar serial 
		// RequestProducer request2= new RequestProducer(1, 1, poll3, m2, 0);
		// read para serial 
		// RequestProducer request3=new RequestProducer(0,9, poll3, m2, null);
		// genero un consumidor para generar json ante solicitud
		RequestConsumer consumer= new RequestConsumerRead();
		System.out.println (consumer.GetAllTags(request1));
		//genero un consumidor para generar json ante solicitud ahora para serial
		//RequestConsumer consumerled = new RequestConsumerWrite();
		//System.out.println(consumer.GetAllTags(request3));

		// bond1=new BridgeManager(m2);
		// test poll 
		//System.out.println(bond1.GetJsonReadRequest(0, 2, poll1));
		//System.out.println("ahora el all");

		//System.out.println(bond1.GetAllJsonReadRequest(0, 2, poll1));

		// test command
		//System.out.println(bond1.GetJsonSingleWrite(1,poll3, 0));

		// System.out.println("ahora discretes all");

		//  System.out.println(bond1.GetJsonReadRequest(0, 4, poll2));
		// System.out.println(bond1.GetAllJsonReadRequest(0, 4, poll2));
	}


}
