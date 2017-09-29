package Operations;

import java.util.ArrayList;

import jsonbuilder.tag.E_TYPE_V_VALUE;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteCoilResponse;
import net.wimpi.modbus.msg.WriteMultipleCoilsRequest;
import net.wimpi.modbus.util.BitVector;

public class CoilsOperations implements ActionManager  {




	public ModbusRequest readOperation(int Start, int count) {

		return new ReadCoilsRequest(Start,count);
	}

	public ModbusRequest writeOperation(int Start, int count, Object value) {
		// TODO Auto-generated method stub
		return new WriteMultipleCoilsRequest(Start,(BitVector)value);

	}






	public String ResponseDetails(ModbusResponse ResponseInstance) {
		// TODO Auto-generated method stub
		String buffer="";
		ReadCoilsResponse Response=(ReadCoilsResponse) ResponseInstance;
		for(int i=0;i < Response.getCoils().size();i++){
			buffer= buffer +"position: "+ i +"  values: " + String.valueOf(Response.getCoilStatus(i))+ "--";	

		}

		return buffer;
	}

	public ArrayList<String> ParsetoArrayString(ModbusResponse ResponseInstance) {
		ArrayList<String> partial=new ArrayList<String>();
		ReadCoilsResponse Response=(ReadCoilsResponse) ResponseInstance;
		for(int i=0;i<= Response.getCoils().size();i++) {
			partial.add(String.valueOf(Response.getCoilStatus(i)));
		}
		return partial;
	}



	public String toString() {
		return "CoilOperation poll ";
	}


	@Override
	public String GetValuebyposition(int position,ModbusResponse ResponseInstance) {
		String buffer="";
		ReadCoilsResponse Response=(ReadCoilsResponse) ResponseInstance;
		buffer=String.valueOf(Response.getCoilStatus(position));
		return buffer;
	}

	@Override
	public E_TYPE_V_VALUE Gettypevalue() {
		// TODO Auto-generated method stub
		return  E_TYPE_V_VALUE.F_SINGLE_BIT;
	}

	@Override
	public ModbusRequest writesingle(int start, Object value) {
		return new WriteCoilRequest(start,(Boolean)value);
	}

}
