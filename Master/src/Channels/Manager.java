package Channels;

import java.util.HashMap;

public class Manager {
	private HashMap<String,ChannelInstance> channelMap;

	public Manager(){
		
		this.channelMap=new HashMap<String,ChannelInstance>();
	}
	
	
	public void addTCPChannel(String name,String ip,int port,int idmaster){
		
		this.channelMap.put(name,new TcpChannel(name,"TCP",ip,port,idmaster));
	}
	
	
	public void addSerialChannel(int idmaster,String name,int baud,String Parity,int datab,int stopb,String com){
	  this.channelMap.put(name,new SerialChannel(idmaster,name,"Serial",baud,Parity,datab,stopb,com));	
	}
	
	public ChannelInstance getChannel (String name){
	return 	this.channelMap.get(name);
	}
}
