package Channels;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import Operations.ActionManager;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.Register;

public class TcpChannel  extends ChannelInstance{

	private String ip;
	private int port;
	private TCPMasterConnection connection;
	private ModbusTCPTransaction transaction;
	 

	public TcpChannel(String name, String Protocol,String address,int port,int idmaster) {
		super(name, Protocol,idmaster);
		this.ip=address;		
		this.port=port;
		this.connection=null;
		this.transaction=null;
	}
	
	
	public int ExecuteAction(int device,ModbusRequest request, int port){
		try {
			 /// init variable 
			
			 //ModbusRequest req=null;//ReadInputDiscretesRequest req = null; //the request
			 //ModbusResponse res=null;// init the response
			ModbusCoupler.getReference().setUnitID(this.idmaster);
			
			System.out.println(this.ip);
			InetAddress addr=InetAddress.getByName(this.ip);
			//int port = this.port;        //Modbus.DEFAULT_PORT; 
			connection = new TCPMasterConnection(addr);
			connection.setPort(port);
			System.out.println(port);
			System.out.println("antes de connect");
			connection.connect();
            System.out.println("despues de conectar");
           
			//3. Prepare the request
			//req = new ReadInputDiscretesRequest(ref, count);
            System.out.println(device);
            Request =request;
            System.out.println(Request);
            Request.setUnitID(device);
            //this.SelectOperation(startreference,operation,quantityread);     
           //System.out.println("Request = " + req.getHexMessage().toString());
          

			//4. Prepare the transaction
			transaction = new ModbusTCPTransaction(connection);
			transaction.setRequest(Request);	
			
			transaction.execute();
			Response = transaction.getResponse();
			
			//6. Close the connection
			connection.close();
			
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }//main
	   		
	  
		return 0;

	} 
	
	
	public void settimeout(int timeout ){
		this.connection.setTimeout(timeout);	
	}
	
	 
	
	public int getPort() {
		return port;
	}

	
	public String getIp() {
		return ip;
	}
	
	public void setport(int port){
		this.port=port;
	}


	@Override
	public String Automaticpull( int device) {
		String output="";
		Date date=new Date();
		try {

			ModbusCoupler.getReference().setUnitID(this.idmaster);

			System.out.println(this.ip);
			InetAddress addr=InetAddress.getByName(this.ip);
			int port = this.port;        //Modbus.DEFAULT_PORT; 
			connection = new TCPMasterConnection(addr);
			connection.setPort(port);
			System.out.println(port);
			System.out.println("antes de connect");
			connection.connect();
			System.out.println("despues de conectar");
			output=this.dateformat.format(date);

			//3. Prepare the request using a iteration ---- pull request 
			int i=0;
			for (ActionManager a:this.getAutomaticOperations()) {

				if (!GetLimitsQuery().get(i).equals("none")) {

					System.out.println(device);

					Request =a.readOperation(0,Integer.parseInt(GetLimitsQuery().get(i)));
					Request.setUnitID(device);



					//4. Prepare the transaction
					transaction = new ModbusTCPTransaction(connection);
					transaction.setRequest(Request);	

					transaction.execute();
					Response = transaction.getResponse();  
					output=output +"<html>"+"<BR>" +" Automatic Operation Response:  "+ a.toString()+"  " +a.ResponseDetails(Response)+"//"+ "<BR>"+"<BR>";
					i++;
				}
				else i++;
			}

			//6. Close the connection
			connection.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}//main

		output=output+"</html>";
		return output;

	}

	
	
		
}
