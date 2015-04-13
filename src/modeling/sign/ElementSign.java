package modeling.sign;

public abstract class ElementSign implements SignDistinguisherInterface{

	protected String type;//是注释还是正文 interface AnalysisKey
	protected String key;//类型名 stateTrans
	
	
	public String getType() {
		return type;
	}

	public String getKey() {
		return key;
	}
	
}
