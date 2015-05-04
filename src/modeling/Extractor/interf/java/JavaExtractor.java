package modeling.Extractor.interf.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import modeling.Extractor.interf.Extractor;
import modeling.model.ExtractedString;

public abstract class JavaExtractor implements Extractor{
	
	Map<String,String> keyList = new HashMap<String,String>();
	
	public void setKey(String key,String value){
		keyList.put(key, value);
	}

	@Override
	public abstract Iterator<ExtractedString> interpretLanguage(String str);

}
