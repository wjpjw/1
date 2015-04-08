package modeling.mybishe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import model.State;
import model.Statechart;
import model.Transition;
import modeling.Recognizer.AnalysisType;
import modeling.Recognizer.JavaAnnotationRecognizer;
import modeling.Recognizer.Recognizer;

public class ReadJavaToStatechart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadJavaToStatechart hello = new ReadJavaToStatechart();
		
		hello.readFile(new File("f:\\test.java"));
	}
	
	ScannerImpl scanner = new ScannerImpl();
	Recognizer javaAnoRecognizer = new JavaAnnotationRecognizer();
	JavaAnnotationAnalyst javaAnnotationAnalyst = new JavaAnnotationAnalyst();
	
	public Statechart readFile(File file) {
		
			if (file.exists()) {				
				String str;
				while(!(str=scanner.ScanSentence(file)).equals("end")){
//					str = str+"\r\n";
//					System.out.println("produce:"+str);
					Iterator<RecognizedString> res = javaAnoRecognizer.interpretLanguage(str);
					while(res.hasNext()){
						RecognizedString rs = res.next();
						if(rs.getKey().equals(AnalysisType.ANOTATION)){
							javaAnnotationAnalyst.analysis(rs.getStr());
						}						
					}
				}
				
				Statechart s = javaAnnotationAnalyst.getStatechart();
				ArrayList<State> sIter =  s.getStates();
				ArrayList<Transition> tIter = s.getTransitions();
				
				return s;
				
				
			}else{
				System.out.println("file does not exists");
				return null;
			}
			

	}

	
}
