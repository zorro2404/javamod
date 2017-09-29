package Operations;

import java.util.ArrayList;

import jsonbuilder.tag.E_TYPE_V_VALUE;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.BitVector;

public class RegistersOperations  implements ActionManager{

	public ModbusRequest readOperation(int Start, int count) {
		// TODO Auto-generated method stub
		return new ReadMultipleRegistersRequest(Start,count);
	}

	public ModbusRequest writeOperation(int Start, int count, Object value) {
		// TODO Auto-generated method stub

		return new WriteMultipleRegistersRequest(Start, (Register[]) value);}



	public String ResponseDetails(ModbusResponse ResponseInstance) {
		// TODO Auto-generated method stub
		String buffer="";
		ReadMultipleRegistersResponse Response=(ReadMultipleRegistersResponse) ResponseInstance;
		for (int i=0;i < Response.getRegisters().length;i++){
			buffer= buffer + "position: "+ i + " value: " + String.valueOf(Response.getRegisterValue(i) + " - ");
		}
		return buffer;
	}

	public String toString(){
		return "RegisterOperation";
	}

	@Override
	public String GetValuebyposition(int position, ModbusResponse ResponseInstance) {
		// TODO Auto-generated method stub
		String buffer="";
		ReadMultipleRegistersResponse Response=(ReadMultipleRegistersResponse) ResponseInstance;
		buffer=String.valueOf(Response.getRegisterValue(position)); 
		return buffer;
	}


	public ArrayList<String> ParsetoArrayString(ModbusResponse ResponseInstance) {
		ArrayList<String> partial=new ArrayList<String>();
		ReadMultipleRegistersResponse Response=(ReadMultipleRegistersResponse) ResponseInstance;
		for(int i=0;i<=Response.getRegisters().length;i++) {
			partial.add(String.valueOf(Response.getRegisterValue(i)));
		}
		return partial;
	}


	@Override
	public E_TYPE_V_VALUE Gettypevalue() {
		// TODO Auto-generated method stub
		return  E_TYPE_V_VALUE.F_ANALOG;
	}

	@Override
	public ModbusRequest writesingle(int start, Object value) {
		Register r = ModbusCoupler.getReference().getProcessImageFactory().createRegister();
		r.setValue((Integer)value);
		return new  WriteSingleRegisterRequest(start,r);
	}

}
