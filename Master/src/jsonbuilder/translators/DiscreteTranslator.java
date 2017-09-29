package jsonbuilder.translators;

import jsonbuilder.tag;

public class DiscreteTranslator  implements translator{

	@Override
	public Object translate(tag totranslate) {
		// TODO Auto-generated method stub
		return Boolean.parseBoolean(totranslate.GetValor().getACT_VALUE());
	}

}
