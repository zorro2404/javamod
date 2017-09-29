package CommunictionManager;

import jsonbuilder.Tags;
import jsonbuilder.tag;

public interface RequestConsumer {

	public  String GetSimpleTag(RequestProducer request);
	
	public String GetAllTags(RequestProducer request);
	
}
