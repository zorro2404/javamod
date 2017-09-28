 package Channels;

import Interface.debugAutomatic;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.procimg.Register;

public class  MasterInstance extends Thread {
	private int idoutstation;
	private String name;
	private String ipaddress;
	private int port;
	private ChannelInstance Channel;
	private boolean state;
	private String automaticout; 
	private debugAutomatic screendebug;
	private Integer LimitAutomatic=0;
	
	
	public MasterInstance(String name,int idout,String add,int port,ChannelInstance Channel){
		this.idoutstation=idout;
		this.name=name;
		this.ipaddress=add;
		this.Channel=Channel;
		this.state=true;
		this.automaticout="inicializado";
		this.screendebug=new debugAutomatic(this);
		this.port=port;
	}
	
	
	public int getIdoutstation() {
		return this.idoutstation;
	}
	
	public int port() {
		return port;		
	}
	
	public ChannelInstance GetChannelBond() {
		
		return this.Channel;
	}
	
	
	public int ExecuteInstance(ModbusRequest Request){
		    //((TcpChannel) this.Channel).setport(port);        // para agregar instancias dado el master para tcp , futuro poner port como parametro en ExecuteAction
	 return	this.Channel.ExecuteAction(this.idoutstation,Request,port);
	}
	
	
	public void SetLimitAutomatic(int value) {
		this.LimitAutomatic=value;
	}

	public void GetScreendebug() {
		this.screendebug.setVisible(true);
	}
	
	public void HideScreendebug() {
		screendebug.setVisible(false);
	}
	
	
	public void disable(){
		//this.state=false;
		this.suspend();;
	} 
	
	public void enable () {
		this.resume();
	}
	
	
	public void destroy(){
		this.stop();
	}

   public void run (){
	  while(state) {
		 
		  String out =Channel.Automaticpull(this.idoutstation);
		  System.out.println("este es el out "+out);
		  this.screendebug.SetScroolPane(this.screendebug.SetLabelScrenn(out));
		 
     		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
	  }
	   
   }
   
   
}
	
  
	

