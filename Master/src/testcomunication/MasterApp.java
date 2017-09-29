package testcomunication;

import java.net.*;
import java.util.Vector;
import java.io.*;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.procimg.SimpleRegister;
import net.wimpi.modbus.util.*;
import net.wimpi.modbus.ModbusCoupler;

 
public class MasterApp {

	public static void main(String[] args) {
		
		try {
			 
			/* The important instances of the classes mentioned before */
			TCPMasterConnection con = null; //the connection
			ModbusTCPTransaction trans = null; //the transaction
			ReadInputRegistersRequest req=null;//ReadInputDiscretesRequest req = null; //the request
			//ReadInputDiscretesResponse res = null; //the response
		    ReadInputRegistersResponse res=null;
            System.out.println("antes de empezar");
			/* Variables for storing the parameters */
           InetAddress addr= InetAddress.getByName("192.168.0.20");
		   // InetAddress addr = InetAddress.getLocalHost(); //the slave's address
			int port =Modbus.DEFAULT_PORT;
			int ref = 0; //the reference; offset where to start reading from
			int count = 4; //the number of DI's to read
			int repeat = 1; //a loop for repeating the transaction
			String holasss="";
			
			// vector de registros
			Register h=new SimpleRegister(2);
			Vector<Register> g=new Vector<Register>();
			Register[] a={};
			g.add(h);
			g.toArray(a);
			
			//bitvectos para escribir multiples coils
			BitVector hola=new BitVector(3);
			hola.setBit(0,true);
			hola.setBit(1,true);
			hola.setBit(2,true);
	      	System.out.println("bit beto " +hola.getBit(2));
	      	
			//1. Setup the parameters
		/*	if (args.length < 3) {
			  System.exit(1);
			} else {
			  try {
			    String astr = args[0];
			    int idx = astr.indexOf(':');
			    if(idx > 0) {
			      port = Integer.parseInt(astr.substring(idx+1));
			      astr = astr.substring(0,idx);
			    }
			    addr = InetAddress.getByName(astr);
			    ref = Integer.decode(args[1]).intValue();
			    count = Integer.decode(args[2]).intValue();
			    if (args.length == 4) {
			      repeat = Integer.parseInt(args[3]);
			    }
			  } catch (Exception ex) {
			    ex.printStackTrace();
			    System.exit(1);
			  }
			} */
			
			//2. Open the connection
			con = new TCPMasterConnection(addr);
			con.setPort(port);
			System.out.println("antes de connect");
			con.connect();
            System.out.println("despues de conectar");
            System.out.println("el timeout es : " +con.getTimeout());
            con.setTimeout(15000);
            System.out.println("el timeout es despues del set: " +con.getTimeout());
			//3. Prepare the request
			//req = new ReadInputDiscretesRequest(ref, count);
            
            //req = getRequest(3,ref,count); //new  WriteMultipleRegistersRequest(0,a);
            //req.setUnitID(12);
            req=new ReadInputRegistersRequest(0,16);
            System.out.println("Request = " + req.getHexMessage().toString());
            
			//4. Prepare the transaction
			trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);
			
			
			//5. Execute the transaction repeat times
			//int k = 0;
			//do {
	      	 trans.execute();
			 res = (ReadInputRegistersResponse)trans.getResponse();
			 //for (int i=0;i<res.getCoils().size();i++){
		    	//	 System.out.println(res.getCoils().getBit(i));
		    		// System.out.println("viene el status");
		    		// System.out.println(res.getCoilStatus(i));
			// }
			 System.out.println("Response = " + res.getHexMessage().toString());
			 System.out.println(res.getRegister(0).getValue());
			//  k++;
			//} while (k < repeat);
			
			
			System.out.println("funciono antes de cerrar");
			

			 //6. Close the connection
			 con.close();
		      
		    } catch (Exception ex) {
		      ex.printStackTrace();
		        String buenas=ex.toString();
		       System.out.println("esto es la exepcion"+ buenas);
		    }
		  
	
	}

	
	private static ModbusRequest getRequest(int requestNumber, int ref, int count) {
		//Note: simple process image uses 0-based register addresses
		switch (requestNumber) {
		case 0:
			return new WriteCoilRequest(0,true);
		case 1:
			return new ReadCoilsRequest(0,3);
		case 2:
			return new ReadInputDiscretesRequest(0,4);
		case 3:
			return new ReadInputRegistersRequest(0,1);
		case 4:
			return new ReadMultipleRegistersRequest(0,1);
		case 5:
			Register r = ModbusCoupler.getReference().getProcessImageFactory().createRegister();
			r.setValue(420);
			return new WriteSingleRegisterRequest(0,r);
		case 6:
			return new ReadMultipleRegistersRequest(0,1);
		default:
			return null;
		}
	}
	
	
	
}
