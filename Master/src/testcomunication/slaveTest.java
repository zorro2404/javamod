package testcomunication;

import net.wimpi.modbus.net.*;
import net.wimpi.modbus.procimg.*;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusCoupler;

public class slaveTest {
	public static void main(String[] args) {
		 try {
			 
			 /* The important instances and variables */
			 ModbusTCPListener listener = null;
			 SimpleProcessImage spi = null;
			 int port =502;//Modbus.DEFAULT_PORT;
             System.out.println("ready to listen");
			   //1. Set port number from commandline parameter
			   if(args != null && args.length ==1) {
			     port = Integer.parseInt(args[0]);
			   }
			   
			 //2. Prepare a process image
			   spi = new SimpleProcessImage();
			   spi.addDigitalOut(new SimpleDigitalOut(true));
			   spi.addDigitalOut(new SimpleDigitalOut(true));
			   spi.addDigitalOut(new SimpleDigitalOut(true));
			   //
			   spi.addDigitalIn(new SimpleDigitalIn(false));
			   spi.addDigitalIn(new SimpleDigitalIn(true));
			   spi.addDigitalIn(new SimpleDigitalIn(true));
			   spi.addDigitalIn(new SimpleDigitalIn(true));
			   //  spi.addDigitalIn(new SimpleDigitalIn(true));
			   spi.addRegister(new SimpleRegister(251));
			   spi.addRegister(new SimpleRegister(246));
			   spi.addInputRegister(new SimpleInputRegister(45));
			   spi.addInputRegister(new SimpleInputRegister(56));

			   //3. Set the image on the coupler
			   ModbusCoupler.getReference().setProcessImage(spi);
			   ModbusCoupler.getReference().setMaster(true);
			   ModbusCoupler.getReference().setUnitID(12);
			   
			   
			 //4. Create a listener with 3 threads in pool
			   listener = new ModbusTCPListener(3);
			   listener.setPort(port);
			   System.out.println("starting to listen");
			   listener.start();
		     
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		  }//main
		
	}


