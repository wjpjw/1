package modeling.Extractor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import modeling.mybishe.RecognizedString;


public abstract class JavaExtractor implements Extractor{
	
	Map<String,String> keyList = new HashMap<String,String>();
	
	public void setKey(String key,String value){
		keyList.put(key, value);
	}

	@Override
	public abstract Iterator<RecognizedString> interpretLanguage(String str);

}
