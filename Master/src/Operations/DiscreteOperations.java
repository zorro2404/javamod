package Operations;

import java.util.ArrayList;

import jsonbuilder.tag.E_TYPE_V_VALUE;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesResponse;

public class DiscreteOperations implements ActionManager{

	public ModbusRequest readOperation(int Start, int count) {
		// TODO Auto-generated method stub
		return new ReadInputDiscretesRequest(Start,count);
	}

	public ModbusRequest writeOperation(int Start, int count, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public String ResponseDetails(ModbusResponse ResponseInstance) {
		// TODO Auto-generated method stub
		String buffer="";
		ReadInputDiscretesResponse Response= (ReadInputDiscretesResponse) ResponseInstance;
		for (int i=0;i < Response.getDiscretes().size();i++){
			buffer= buffer+ "position: "+ i +"  value: "+ String.valueOf(Response.getDiscreteStatus(i) + " -- ");
		}
		return buffer;
	}


	public String toString() {
		return "DiscreteOperation";
	}

	@Override
	public String GetValuebyposition(int position, ModbusResponse ResponseInstance) {
		// TODO Auto-generated method stub
		String buffer="";
		ReadInputDiscretesResponse Response= (ReadInputDiscretesResponse) ResponseInstance;
		buffer=String.valueOf(Response.getDiscreteStatus(position));
		return buffer;
	}


	public E_TYPE_V_VALUE Gettypevalue() {
		// TODO Auto-generated method stub
		return E_TYPE_V_VALUE.F_DISCRETE;
	}

	@Override
	public ArrayList<String> ParsetoArrayString(ModbusResponse ResponseInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModbusRequest writesingle(int start, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
}
