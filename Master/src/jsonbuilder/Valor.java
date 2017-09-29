package jsonbuilder;

import jsonbuilder.tag.E_CALIDAD;
import jsonbuilder.tag.E_TYPE_VALIDITY;
import jsonbuilder.tag.E_TYPE_V_VALUE;

public class Valor {
	private E_CALIDAD CALIDAD;
    private String TIMESTAMP;
    private E_TYPE_V_VALUE S_VALUE_TYPE;
    private E_TYPE_VALIDITY S_VALIDITY;
    private String ACT_VALUE;
    	
	
	public Valor( E_CALIDAD cALIDAD, String tIMESTAMP, E_TYPE_V_VALUE s_VALUE_TYPE, E_TYPE_VALIDITY s_VALIDITY,
	String aCT_VALUE) {
		CALIDAD = cALIDAD;
		TIMESTAMP = tIMESTAMP;
		S_VALUE_TYPE = s_VALUE_TYPE;
		S_VALIDITY = s_VALIDITY;
		ACT_VALUE = aCT_VALUE;	
	}

	  public E_CALIDAD getCALIDAD() {
			return CALIDAD;
		}


		public void setCALIDAD(E_CALIDAD cALIDAD) {
			CALIDAD = cALIDAD;
		}


		public String getTIMESTAMP() {
			return TIMESTAMP;
		}


		public void setTIMESTAMP(String tIMESTAMP) {
			TIMESTAMP = tIMESTAMP;
		}


		public E_TYPE_V_VALUE getS_VALUE_TYPE() {
			return S_VALUE_TYPE;
		}


		public void setS_VALUE_TYPE(E_TYPE_V_VALUE s_VALUE_TYPE) {
			S_VALUE_TYPE = s_VALUE_TYPE;
		}


		public E_TYPE_VALIDITY getS_VALIDITY() {
			return S_VALIDITY;
		}


		public void setS_VALIDITY(E_TYPE_VALIDITY s_VALIDITY) {
			S_VALIDITY = s_VALIDITY;
		}


		public String getACT_VALUE() {
			return ACT_VALUE;
		}


		public void setACT_VALUE(String aCT_VALUE) {
			ACT_VALUE = aCT_VALUE;
		}

		public String toString() {
			return "Valor [CALIDAD=" + CALIDAD + ", TIMESTAMP=" + TIMESTAMP + ", S_VALUE_TYPE=" + S_VALUE_TYPE
					+ ", S_VALIDITY=" + S_VALIDITY + ", ACT_VALUE=" + ACT_VALUE + "]";
		}
	
}
