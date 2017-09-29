package testcomunication;
import java.net.*;
import gnu.io.*;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.*;

public class MasterSerial {

	
	
	public static void main(String[] args) {
	    try {
	      
	    	/* The important instances of the classes mentioned before */
	    	SerialConnection con = null; //the connection
	    	ModbusSerialTransaction trans = null; //the transaction
	    	ReadMultipleRegistersRequest req = null; //the request
	    	ReadMultipleRegistersResponse res = null; //the response
	    	
	    	/* Variables for storing the parameters */
	    	String portname= null; //the name of the serial port to be used
	    	int unitid = 0; //the unit identifier we will be talking to
	    	int ref = 0; //the reference, where to start reading from
	    	int count = 0; //the count of IR's to read
	    	int repeat = 1; //a loop for repeating the transaction
            
	    	
	    	//2. Set master identifier
	    	//ModbusCoupler.createModbusCoupler(null);
	    	ModbusCoupler.getReference().setUnitID(1);      

	    	//3. Setup serial parameters
	    	SerialParameters params = new SerialParameters();
	    	params.setPortName("COM7");
	    	params.setBaudRate(19200);
	    	params.setDatabits(8);
	    	params.setParity("None");
	    	params.setStopbits(1);
	    	params.setEncoding(Modbus.SERIAL_ENCODING_RTU);
	    	params.setEcho(false);
	    	
	    	
	    	
	    	//4. Open the connection
	    	con = new SerialConnection(params);
	    	con.open();

	    	//Register r = ModbusCoupler.getReference().getProcessImageFactory().createRegister();
	    	//r.setValue((Integer)0);
	    	
	    	//5. Prepare a request
	    	req = new ReadMultipleRegistersRequest(0,9);
	    	req.setUnitID(1);
	    	req.setHeadless();

	    	//6. Prepare a transaction
	    	trans = new ModbusSerialTransaction(con);
	    	trans.setRequest(req);
	    
	    	trans.execute();
	    	res = (ReadMultipleRegistersResponse) trans.getResponse();
	    	  
	    	 System.out.println("Response = " + res.getHexMessage().toString());
	    	//8. Close the connection
	    	con.close();
	    	
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	  }
}
