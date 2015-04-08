package modeling.Recognizer;

import java.util.Iterator;

import modeling.mybishe.RecognizedString;


public interface Recognizer {
	
	public Iterator<RecognizedString> interpretLanguage(String str);
	
}
