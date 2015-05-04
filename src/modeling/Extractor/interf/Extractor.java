package modeling.Extractor.interf;

import java.util.Iterator;

import modeling.model.ExtractedString;


public interface Extractor {
	
	public Iterator<ExtractedString> interpretLanguage(String str);
	
}
