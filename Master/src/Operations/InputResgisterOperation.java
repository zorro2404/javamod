package Operations;

import java.util.ArrayList;

import jsonbuilder.tag.E_TYPE_V_VALUE;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;

public class InputResgisterOperation implements ActionManager{

	public ModbusRequest readOperation(int Start, int count) {
		// TODO Auto-generated method stub
		return new ReadInputRegistersRequest(Start,count);
	}

	public ModbusRequest writeOperation(int Start, int count, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public String ResponseDetails(ModbusResponse ResponseInstance) {
		// TODO Auto-generated method
		String buffer="";
		ReadInputRegistersResponse Response=(ReadInputRegistersResponse) ResponseInstance;
		for(int i=0;i < Response.getRegisters().length;i++){
			buffer= buffer +" position :"+ i +" value: "+ String.valueOf(Response.getRegisterValue(i)+ " -- ");
		}

		return buffer;
	}

	public String toString() {
		return "InputOperation";
	}


	@Override
	public String GetValuebyposition(int position, ModbusResponse ResponseInstance) {
		// TODO Auto-generated method stub
		String buffer="";
		ReadInputRegistersResponse Response=(ReadInputRegistersResponse) ResponseInstance;
		buffer=String.valueOf(Response.getRegisterValue(position));
		return buffer;
	}

	@Override
	public E_TYPE_V_VALUE Gettypevalue() {
		// TODO Auto-generated method stub
		return  E_TYPE_V_VALUE.F_ANALOG ;
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
