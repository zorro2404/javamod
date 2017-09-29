package testcomunication;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.procimg.*;
import net.wimpi.modbus.util.SerialParameters;


public class slaveserial { 
	
	public static void main(String[] args) {
	    try {
	      
	    	/* The important instances and variables */
	    	ModbusSerialListener listener = null;
	    	SimpleProcessImage spi = null;
	    	
	    	
	    	System.setProperty("build.serial.gnu","true");
	    	
	    	//1. Prepare a process imag  	
          	   spi = new SimpleProcessImage();
			   spi.addDigitalOut(new SimpleDigitalOut(true));
			   spi.addDigitalOut(new SimpleDigitalOut(true));
			   spi.addDigitalOut(new SimpleDigitalOut(true));
			   spi.addDigitalIn(new SimpleDigitalIn(false));
			   spi.addDigitalIn(new SimpleDigitalIn(true));
			   spi.addDigitalIn(new SimpleDigitalIn(true));
			   spi.addDigitalIn(new SimpleDigitalIn(true));
			 //  spi.addDigitalIn(new SimpleDigitalIn(true));
			   spi.addRegister(new SimpleRegister(251));
			   spi.addRegister(new SimpleRegister(246));
			   spi.addInputRegister(new SimpleInputRegister(45));
			   spi.addInputRegister(new SimpleInputRegister(56));
          	
	        System.out.println("starting");
	    	
	    	//2. Create the coupler and set the slave identity
	    	ModbusCoupler.getReference().setProcessImage(spi);
	    	ModbusCoupler.getReference().setMaster(true);
	    	ModbusCoupler.getReference().setUnitID(2);
	    	
	    	//3. Set up serial parameters
	    	SerialParameters params = new SerialParameters();
	    	params.setPortName("COM1");
	    	params.setBaudRate(9600);
	    	params.setDatabits(8);
	    	params.setParity("None");
	    	params.setStopbits(1);
	    	params.setEncoding("ascii");
	    	params.setEcho(false);
	    	
	    	
	    	//4. Set up serial listener
	    	listener = new ModbusSerialListener(params);
	    	System.out.println("esperando request master");
	    	
	    	listener.setListening(true);  
	    	
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	  }

}
