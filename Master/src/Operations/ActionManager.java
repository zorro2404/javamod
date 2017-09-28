package Operations;

import java.util.ArrayList;

import jsonbuilder.tag.E_TYPE_V_VALUE;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;

public interface ActionManager {

	public abstract ModbusRequest readOperation(int Start,int count);
	
	public abstract ModbusRequest writeOperation(int Start,int count, Object value );
	
	public abstract String ResponseDetails(ModbusResponse ResponseInstance);
	
	public abstract String GetValuebyposition(int position,ModbusResponse ResponseInstance);
	
	public abstract ModbusRequest writesingle(int start,Object value );
	
	public abstract E_TYPE_V_VALUE Gettypevalue();
	
	public abstract String toString();
	
	public abstract ArrayList<String> ParsetoArrayString(ModbusResponse ResponseInstance);
}
