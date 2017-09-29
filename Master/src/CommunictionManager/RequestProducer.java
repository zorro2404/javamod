package CommunictionManager;

import java.time.temporal.ValueRange;

import Channels.MasterInstance;
import Operations.ActionManager;

public class RequestProducer {

	private int StartRquest;
	private int LenghtRequest;
	private ActionManager ActionPerforming;
	private MasterInstance CurrentMaster;
	private Object ValueOfOperation;

	public RequestProducer(int start,int length,ActionManager action,MasterInstance instance,Object value) {
		// TODO Auto-generated constructor stub
		StartRquest=start;
		LenghtRequest=length;
		ActionPerforming=action;
		CurrentMaster=instance;
		ValueOfOperation=value;

	}
  
	public int getStartRquest() {
		return StartRquest;
	}

	public void setStartRquest(int startRquest) {
		StartRquest = startRquest;
	}

	public int getLenghtRequest() {
		return LenghtRequest;
	}

	public void setLenghtRequest(int lenghtRequest) {
		LenghtRequest = lenghtRequest;
	}

	public ActionManager getActionPerforming() {
		return ActionPerforming;
	}

	public void setActionPerforming(ActionManager actionPerforming) {
		ActionPerforming = actionPerforming;
	}

	public MasterInstance getCurrentMaster() {
		return CurrentMaster;
	}

	public void setCurrentMaster(MasterInstance currentMaster) {
		CurrentMaster = currentMaster;
	}

    public Object getValueOfOperation() {
    	
		return ValueOfOperation;
	}

    public void setValueOfOperation ( Object value) {
    	ValueOfOperation=value;
		
	}
}
