package CommunictionManager;


import Channels.MasterInstance;
import jsonbuilder.JsonUtil;
import jsonbuilder.Tags;
import jsonbuilder.Valor;
import jsonbuilder.tag;
import jsonbuilder.tag.E_CALIDAD;
import jsonbuilder.tag.E_CAUSA;
import jsonbuilder.tag.E_TYPE_VALIDITY;
import net.wimpi.modbus.msg.ModbusRequest;

public class RequestConsumerWrite implements RequestConsumer {
private JsonUtil jsonInstance= new JsonUtil();

	@Override
	public String GetSimpleTag(RequestProducer request) {
		// TODO Auto-generated method stub
		request.getCurrentMaster().ExecuteInstance(request.getActionPerforming().writesingle(request.getStartRquest(), request.getValueOfOperation()));
		String value=String.valueOf(request.getValueOfOperation());
		Valor valor= new Valor(E_CALIDAD.MANUAL, "", request.getActionPerforming().Gettypevalue(),E_TYPE_VALIDITY.VALID,value);
		tag nuevo= new tag(E_CAUSA.REQUIRED,"TagRequest: "+ request.getActionPerforming(),0, "Tagwrite",valor);
		return jsonInstance.Parsetojson(nuevo);
	}

	@Override
	public String GetAllTags(RequestProducer request) {
		// TODO Auto-generated method stub
		Tags TagsInstance=new Tags();
		MasterInstance run=request.getCurrentMaster();
		run.ExecuteInstance(request.getActionPerforming().writeOperation(request.getStartRquest(), request.getLenghtRequest(), request.getValueOfOperation()));
		for (int i=0; i <= request.getActionPerforming().ParsetoArrayString(run.GetChannelBond().getResponse()).size(); i++){
			String value= request.getActionPerforming().ParsetoArrayString(run.GetChannelBond().getResponse()).get(i);                // Action.GetValuebyposition(lenght-1,test.GetChannelBond().getResponse()); 
			Valor val=new Valor(E_CALIDAD.MANUAL,"",request.getActionPerforming().Gettypevalue(), E_TYPE_VALIDITY.VALID,value);
			tag current = new tag(E_CAUSA.REQUIRED,"TagRequest: "+ request.getActionPerforming() ,i,"Tag "+i,val);
			TagsInstance.addTag(current);
		}
		JsonUtil jsonInstance1= new JsonUtil();
		return jsonInstance1.ParseTagstoJson(TagsInstance);
	}

}
