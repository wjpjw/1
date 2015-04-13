package modeling.Extractor;

import java.util.Iterator;

import modeling.mybishe.RecognizedString;




public interface Extractor {
	
	public Iterator<RecognizedString> interpretLanguage(String str);
	
}
