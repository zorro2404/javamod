package CommunictionManager;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.stream.IntStream;

import Channels.ChannelInstance;
import Channels.MasterInstance;
import Operations.ActionManager;
import jsonbuilder.JsonUtil;
import jsonbuilder.Tags;
import jsonbuilder.Valor;
import jsonbuilder.tag;
import jsonbuilder.tag.E_CALIDAD;
import jsonbuilder.tag.E_CAUSA;
import jsonbuilder.tag.E_TYPE_VALIDITY;
import net.wimpi.modbus.msg.ModbusRequest;

public class BridgeManager{


	private MasterInstance CurrentInstance;


	public BridgeManager(MasterInstance Current) {
		this.CurrentInstance=Current;
	

	}

 // clase a ser eliminada falta hacer los write // terminado

	public String GetJsonReadRequest(int Start,int lenght,ActionManager Action) {
		ModbusRequest Resquest=Action.readOperation(Start, lenght);
		MasterInstance test=CurrentInstance;
		test.ExecuteInstance(Resquest);
		String value=Action.GetValuebyposition(lenght-1,test.GetChannelBond().getResponse()); 
		Valor val=new Valor(E_CALIDAD.CALCULATED,"",Action.Gettypevalue(), E_TYPE_VALIDITY.VALID,value);
		tag nuevo = new tag(E_CAUSA.REQUIRED,"TagRequest",1,"Tag1",val);
		JsonUtil jsonInstance= new JsonUtil();
		return jsonInstance.Parsetojson(nuevo);
	}	




	public String GetAllJsonReadRequest(int start,int lenght,ActionManager Action) {
		Tags TagsInstance=new Tags();
		JsonUtil jsonInstance= new JsonUtil();
		ModbusRequest Resquest=Action.readOperation(start, lenght);
		MasterInstance test=CurrentInstance;
		test.ExecuteInstance(Resquest);
		for (int i=0;i<lenght;i++) {
			String value=Action.GetValuebyposition(i,test.GetChannelBond().getResponse()); 
			Valor val=new Valor(E_CALIDAD.CALCULATED,"",Action.Gettypevalue(), E_TYPE_VALIDITY.VALID,value);
			tag newtag = new tag(E_CAUSA.REQUIRED,"TagRequest: "+ Action ,i,"Tag:"+i,val);
			TagsInstance.addTag(newtag);
		}

		return jsonInstance.ParseTagstoJson(TagsInstance);
	}




	public String GetJsonWriteRequest(int start,int lenght,ActionManager Action,Object Element) {
		Tags TagsInstance=new Tags();
		JsonUtil jsonInstance= new JsonUtil();
		ModbusRequest Request=Action.writeOperation(start, lenght, Element);
		MasterInstance test=CurrentInstance;
		test.ExecuteInstance(Request);
		for (int i=0; i <= Action.ParsetoArrayString(test.GetChannelBond().getResponse()).size(); i++){
			String value= Action.ParsetoArrayString(test.GetChannelBond().getResponse()).get(i);                // Action.GetValuebyposition(lenght-1,test.GetChannelBond().getResponse()); 
			Valor val=new Valor(E_CALIDAD.MANUAL,"",Action.Gettypevalue(), E_TYPE_VALIDITY.VALID,value);
			tag current = new tag(E_CAUSA.REQUIRED,"TagRequest: "+ Action ,i,"Tag "+i,val);
			TagsInstance.addTag(current);
		}
		JsonUtil jsonInstance1= new JsonUtil();
		return jsonInstance1.ParseTagstoJson(TagsInstance);
	}


	
	public String GetJsonSingleWrite(int start,ActionManager Action,Object val) {
		Tags TagsInstance=new Tags();
		JsonUtil jsonInstance= new JsonUtil();
		ModbusRequest Request =  Action.writesingle(start, val);
		CurrentInstance.ExecuteInstance(Request);
		String value=String.valueOf(val);
		Valor valor= new Valor(E_CALIDAD.MANUAL, "", Action.Gettypevalue(),E_TYPE_VALIDITY.VALID,value);
		tag nuevo= new tag(E_CAUSA.REQUIRED,"TagRequest: "+ Action,0, "Tagwrite",valor);
		return jsonInstance.Parsetojson(nuevo);
	}
	

	

	public void SetMasterInstance(MasterInstance m) {
		this.CurrentInstance=m;
	}
}
