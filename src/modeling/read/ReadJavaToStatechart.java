package modeling.read;

import java.io.File;
import java.util.Iterator;

import modeling.Analyst.JavaAnnotationAnalyst;
import modeling.Extractor.impl.java.JavaAnnotationExtractor;
import modeling.Extractor.interf.CodeType;
import modeling.Extractor.interf.Extractor;
import modeling.model.ExtractedString;
import modeling.model.State;
import modeling.model.Statechart;
import modeling.model.Transition;
import modeling.scanner.ScannerImpl;
import modeling.scanner.ScannerKey;


public class ReadJavaToStatechart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadJavaToStatechart hello = new ReadJavaToStatechart();

		hello.readFile(new File("F:/bishe_workspace/CRFBoard/src/wangy/board/questionexpert/LineChipper.java"));
	}

	ScannerImpl scanner = new ScannerImpl();
	Extractor javaAnoExtractor = new JavaAnnotationExtractor();
	JavaAnnotationAnalyst javaAnnotationAnalyst = new JavaAnnotationAnalyst();

	public Statechart readFile(File file) {

		if (file.exists()) {
			String str;
			while (!(str = scanner.ScanSentence(file)).equals(ScannerKey.END)) {
				// str = str+"\r\n";
				// System.out.println("produce:"+str);
				Iterator<ExtractedString> stateRes = javaAnoExtractor
						.interpretLanguage(str);
				while (stateRes.hasNext()) {
					ExtractedString rs = stateRes.next();
					if (rs.getKey().equals(CodeType.ANOTATION)) {
//						System.out.println(rs.getStr());
						javaAnnotationAnalyst.analysisState(rs.getStr());
					}
				}
				
				Iterator<ExtractedString> transitionRes = javaAnoExtractor
						.interpretLanguage(str);
				while (transitionRes.hasNext()) {
					ExtractedString rs = transitionRes.next();
					if (rs.getKey().equals(CodeType.ANOTATION)) {
//						System.out.println(rs.getStr());
						javaAnnotationAnalyst.analysisTransition(rs.getStr());
					}
				}
			}

			Statechart statechart = javaAnnotationAnalyst.getStatechart();
			return statechart;
//			System.out.println(statechart.getStates().size());
//			for(State state : statechart.getStates()){
//				System.out.println(state.getName()+state.isIs_init()+state.isIs_exception());
//			}
//			for(Transition transition : statechart.getTransitions()){
//				System.out.println(transition.getMethod());
//			}
			

		} else {
			System.out.println("file does not exists");
			return null;
		}

	}

}
