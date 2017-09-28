package Channels;

import java.net.*;
import java.util.Date;

import Operations.ActionManager;
import Operations.CoilsOperations;
import Operations.DiscreteOperations;
import Operations.InputResgisterOperation;
import Operations.RegistersOperations;

import java.io.*;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.util.*;
 
public class SerialChannel extends ChannelInstance {
	
	private SerialConnection conecction;
	private ModbusSerialTransaction transaction;
	private int baud;
	private String parity;
	private  int Databits;
	private int StopBits;
	private String com;
	
	
	
	
	

 public SerialChannel( int masterid,String name,String protocol, int baud, String Parity, int DataBits,int StopBits,String com){
	 super(name,protocol,masterid);
	 this.conecction=null;
	 this.transaction=null;
	 this.baud=baud;
	 this.parity=Parity;
	 this.Databits=DataBits;
	 this.StopBits=StopBits;
	 this.com=com;
}
 

@Override
public int ExecuteAction(int device, ModbusRequest request, int port ) {
    	// TODO Auto-generated method stub
	try {
	  
		//2. Set master identifier
		//ModbusCoupler.createModbusCoupler(null);
		ModbusCoupler.getReference().setUnitID(this.idmaster);      

		//3. Setup serial parameters
		SerialParameters params = new SerialParameters();
		System.out.println(com);
		params.setPortName(com);  //com                         ///params.setPortName(portname);
		params.setBaudRate(this.baud);
		params.setDatabits(this.Databits);
		params.setParity(this.parity);
		params.setStopbits(this.StopBits);
		params.setEncoding(Modbus.SERIAL_ENCODING_RTU);
		params.setEcho(false);
		
		System.out.println("el divice es"+ device);
		//4. Open the connection
		conecction = new SerialConnection(params);
		conecction.open();

		//5. Prepare a request
		Request = request;//this.SelectOperation(start, operation, quantityread);
		Request.setUnitID(device);
		Request.setHeadless();

		//6. Prepare a transaction
		transaction = new ModbusSerialTransaction(conecction);
		transaction.setRequest(Request);
		System.out.println("antes del conect");
		transaction.execute();
		Response = transaction.getResponse();
		
		//8. Close the connection
		conecction.close();
		
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	 
	
	return 0;
}  
	
 

public String Automaticpull (int device) {
	String output="";
	Date date=new Date();
	// TODO Auto-generated method stub
	try {

		//2. Set master identifier
		//ModbusCoupler.createModbusCoupler(null);
		ModbusCoupler.getReference().setUnitID(this.idmaster);      

		//3. Setup serial parameters
		SerialParameters params = new SerialParameters();
		params.setPortName(com);                           ///params.setPortName(portname);
		params.setBaudRate(this.baud);
		params.setDatabits(this.Databits);
		params.setParity(this.parity);
		params.setStopbits(this.StopBits);
		params.setEncoding(Modbus.SERIAL_ENCODING_RTU);
		params.setEcho(false);




		//4. Open the connection
		conecction = new SerialConnection(params);
		conecction.open();
		System.out.println("despues de conectar");
		output=this.dateformat.format(date);


		//5. Prepare a request
		int i=0;
		for (ActionManager a:this.getAutomaticOperations()) {
			if (!GetLimitsQuery().get(i).equals("none")) {
			Request = a.readOperation(0,Integer.parseInt(GetLimitsQuery().get(i))); //this.SelectOperation(start, operation, quantityread);
			Request.setUnitID(device);
			Request.setHeadless();
            System.out.println("troden");

			//6. Prepare a transaction
			transaction = new ModbusSerialTransaction(conecction);
			transaction.setRequest(Request);
			transaction.execute();

			Response = transaction.getResponse();  
			output=output +"<html>"+"<BR>" +" Automatic Operation Response:  "+ a.toString()+"  " +a.ResponseDetails(Response)+"//"+ "<BR>"+"<BR>";
	       i++;
			}
			else i++;
		}
		


		//8. Close the connection
		conecction.close();

	} catch (Exception ex) {
		ex.printStackTrace();
	}

	output=output+"</html>";
	return output;
}



 
 
}
