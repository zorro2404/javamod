package jsonbuilder.translators;

import jsonbuilder.tag;

public class AnalogTranslator implements translator {

	@Override
	public Object translate(tag totranslate) {
		// TODO Auto-generated method stub
		return Double.parseDouble(totranslate.GetValor().getACT_VALUE());
	}

}
