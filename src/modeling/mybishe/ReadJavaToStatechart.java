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
import modeling.Extractor.CodeType;
import modeling.Extractor.Extractor;
import modeling.Extractor.JavaAnnotationExtractor;

public class ReadJavaToStatechart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadJavaToStatechart hello = new ReadJavaToStatechart();

		hello.readFile(new File("F:/bishe_workspace/CRFBoard/src/wangy/board/questionexpert/LineChipper.java"));
	}

	ScannerImpl scanner = new ScannerImpl();
	Extractor javaAnoExtractor = new JavaAnnotationExtractor();
	JavaAnnotationAnalyst javaAnnotationAnalyst = new JavaAnnotationAnalyst();

	public void readFile(File file) {

		if (file.exists()) {
			String str;
			while (!(str = scanner.ScanSentence(file)).equals("end")) {
				// str = str+"\r\n";
				// System.out.println("produce:"+str);
				Iterator<RecognizedString> stateRes = javaAnoExtractor
						.interpretLanguage(str);
				while (stateRes.hasNext()) {
					RecognizedString rs = stateRes.next();
					if (rs.getKey().equals(CodeType.ANOTATION)) {
//						System.out.println(rs.getStr());
						javaAnnotationAnalyst.analysisState(rs.getStr());
					}
				}
				
				Iterator<RecognizedString> transitionRes = javaAnoExtractor
						.interpretLanguage(str);
				while (transitionRes.hasNext()) {
					RecognizedString rs = transitionRes.next();
					if (rs.getKey().equals(CodeType.ANOTATION)) {
//						System.out.println(rs.getStr());
						javaAnnotationAnalyst.analysisTransition(rs.getStr());
					}
				}
			}

			Statechart statechart = javaAnnotationAnalyst.getStatechart();

			System.out.println(statechart.getStates().size());
			for(State state : statechart.getStates()){
				System.out.println(state.getName()+state.isIs_init()+state.isIs_exception());
			}
			for(Transition transition : statechart.getTransitions()){
				System.out.println(transition.getMethod());
			}
			

		} else {
			System.out.println("file does not exists");
		}

	}

}
