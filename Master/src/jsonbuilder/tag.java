package jsonbuilder;


public class tag {

	private E_CAUSA S_CAUSE;
	private String F_TAGNAME;
	private Integer RTID;
	private String F_TAGID;
	private Valor VALUE;
	//private Alarma ALARMA; ver la implementacion a futuro


	public tag(E_CAUSA s_CAUSE, String f_TAGNAME, Integer rTID, String f_TAGID, Valor vALUE) {
		S_CAUSE = s_CAUSE;
		F_TAGNAME = f_TAGNAME;
		RTID = rTID;
		VALUE = vALUE;
		//ALARMA = aLARMA;	
	}	

	public String toString() {

		return "[CAUSA=" + S_CAUSE + ", F_TAGNAME=" + F_TAGNAME + ", VALOR=" + VALUE + "]";

	}

	public Valor GetValor(){

		return this.VALUE;
	}



	public enum E_CAUSA {
		UNKNOWN, PERIODIC, SPONTANEOUS, REQUIRED
	}

	public enum E_CALIDAD{
		UNKNOWN, TELEMETERED, CALCULATED, ESTIMATED, MANUAL
	} 

	public enum E_TYPE_V_VALUE{

		F_OTHER, F_SINGLE_BIT, F_DOUBLE_BIT, F_DISCRETE, F_ANALOG, F_FIXEDPOINT, F_DATASET, F_TEXT,
	}

	public enum E_TYPE_VALIDITY {
		VALID, NOT_VALID, HELD, SUSPECT
	}

}



