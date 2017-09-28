package Channels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import Operations.ActionManager;
import Operations.CoilsOperations;
import Operations.DiscreteOperations;
import Operations.InputResgisterOperation;
import Operations.RegistersOperations;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.procimg.Register;

public abstract class ChannelInstance {
 protected HashMap<String,MasterInstance> MasterMap;
 protected String name;
 protected String Protocol;
 protected int idmaster;
 protected ModbusRequest Request;
 protected ModbusResponse Response;
 protected DateFormat dateformat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 private CoilsOperations coils=new CoilsOperations();
 private DiscreteOperations Discretes=new DiscreteOperations();
 private InputResgisterOperation Input=new InputResgisterOperation();
 private RegistersOperations Reope=new RegistersOperations();
 private ArrayList<String> limitsquery= new ArrayList<String>();

	
	
 public ChannelInstance(String name, String Protocol,int idmaster){
	 this.MasterMap=new HashMap<String,MasterInstance>();
     this.name=name;
     this.idmaster=idmaster;
     this.Protocol=Protocol;
     this.Request=null;
     this.Response=null;
	
 }
 
 public void addMasterInstance(String name,int idoutstation,String ipdirecction, int port){
	 
	 this.MasterMap.put(name, new MasterInstance(name,idoutstation,ipdirecction,port,this));
	 
 }

 
 public MasterInstance getInstance(String name){
	 return this.MasterMap.get(name);
 }
 
 public ModbusResponse getResponse(){
	 return this.Response;
 }
 
public HashMap<String, MasterInstance> getMasterMap() {
	return MasterMap;
}


public abstract int ExecuteAction(int device,ModbusRequest Request,int port);//ejecuta accion  para realizar poll

public abstract String Automaticpull (int device);


public String getName() {
	return name;
}


public String getProtocol() {
	return Protocol;
	
}




public String DebugResponse(){
 return "Response to: " +  this.name   + "  Protocol: " + this.Protocol + "   ---->  " + "massage response : " + this.Response.getHexMessage().toString() + "\n";
}
 

public String DebugRequest(){
	return "Resquest from: "+ this.name +"  Master: " + this.idmaster + "  Protocol: " +  this.Protocol  + "  ---->   " +"massage request : " + this.Request.getHexMessage().toString();
}



public ArrayList<ActionManager> getAutomaticOperations(){
	 ArrayList<ActionManager> action=new ArrayList<ActionManager>();
	 action.add(Discretes);
	 action.add(Reope);
	 action.add(Input);
	 action.add(coils);
	  return action;
}


public void SetLimitsQuery(ArrayList<String> query) {
	limitsquery=query;
}


public ArrayList<String> GetLimitsQuery(){
	return limitsquery;
}	


}
