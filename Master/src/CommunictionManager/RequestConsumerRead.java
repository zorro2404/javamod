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


public class RequestConsumerRead implements RequestConsumer {
	private JsonUtil jsonInstance= new JsonUtil();

	@Override
	public String GetSimpleTag(RequestProducer request) {
		MasterInstance run=request.getCurrentMaster() ;
		run.ExecuteInstance(request.getActionPerforming().readOperation(request.getStartRquest(), request.getLenghtRequest()));
		String value=request.getActionPerforming().GetValuebyposition(request.getLenghtRequest()-1,run.GetChannelBond().getResponse()); 
		Valor val=new Valor(E_CALIDAD.CALCULATED,"",request.getActionPerforming().Gettypevalue(), E_TYPE_VALIDITY.VALID,value);
		tag nuevo = new tag(E_CAUSA.REQUIRED,"TagRequest",1,"Tag1",val);
		return jsonInstance.Parsetojson(nuevo);
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     

	@Override
	public String GetAllTags(RequestProducer request) {
		Tags TagsInstance=new Tags();
		MasterInstance run=request.getCurrentMaster();
		run.ExecuteInstance(request.getActionPerforming().readOperation(request.getStartRquest(),request.getLenghtRequest()));
		for (int i=0;i<request.getLenghtRequest();i++) {
			String value=request.getActionPerforming().GetValuebyposition(i,run.GetChannelBond().getResponse()); 
			Valor val=new Valor(E_CALIDAD.CALCULATED,"",request.getActionPerforming().Gettypevalue(), E_TYPE_VALIDITY.VALID,value);
			tag newtag = new tag(E_CAUSA.REQUIRED,"TagRequest: "+ request.getActionPerforming() ,i,"Tag:"+i,val);
			TagsInstance.addTag(newtag);
		}

		return jsonInstance.ParseTagstoJson(TagsInstance);
	}

}
