package modeling.read;

import java.io.File;
import java.util.Iterator;

import modeling.Analyst.JavaAnnotationAnalyst;
import modeling.Extractor.impl.java.JavaAnnotationExtractor;
import modeling.Extractor.interf.CodeType;
import modeling.Extractor.interf.Extractor;
import modeling.helper.TransitionAnoHelper;
import modeling.model.ExtractedString;
import modeling.model.Transition;
import modeling.model.TransitionAno;
import modeling.scanner.ScannerImpl;
import modeling.scanner.ScannerKey;

public class ReadOffset {
	private ScannerImpl scanner;
	private Extractor javaAnoExtractor;
	private JavaAnnotationAnalyst javaAnnotationAnalyst;
	private TransitionAnoHelper transAnoHelper;


	public ReadOffset(){
		scanner = new ScannerImpl();
		javaAnoExtractor = new JavaAnnotationExtractor();
		javaAnnotationAnalyst = new JavaAnnotationAnalyst();
		transAnoHelper = new TransitionAnoHelper();
	}
	
	public void readOffset(String fileText) {
		String lines[] = fileText.split(System.getProperty("line.separator"));
		
		for(int i = 0; i<lines.length;i++){
			String str = lines[i];
			Iterator<ExtractedString> transitionRes = javaAnoExtractor
					.interpretLanguage(str);
			int strLength = str.length();
			while (transitionRes.hasNext()) {
				ExtractedString rs = transitionRes.next();
				if (rs.getKey().equals(CodeType.ANOTATION)) {
					// System.out.println(rs.getStr());
					if (javaAnnotationAnalyst.isTransition(rs.getStr())) {
						Transition trans = javaAnnotationAnalyst
								.analysisTransition(rs.getStr());
						TransitionAno transAno = new TransitionAno();
						transAno.setMethodName(trans.getMethod());
						if (!transAnoHelper.contains(transAno)) {
//							System.out.println("没有：" + trans.getMethod() + transAnoHelper.getOffset());
							transAno.setOffset(transAnoHelper.getOffset());
							transAno.setLength(rs.getStr().length());
							transAnoHelper.add(transAno);
						}

					}
				}

			}
			transAnoHelper.setOffset(transAnoHelper.getOffset() + strLength + TransitionAno.newlinesLength);
		}
		
		
	}
	
	public void readOffset(File file) {
		if (file.exists()) {
			String str;
			while (!(str = scanner.ScanSentence(file)).equals(ScannerKey.END)) {
				if(str.equals(ScannerKey.START)){
					continue;
				}
//				System.out.println(str);
				Iterator<ExtractedString> transitionRes = javaAnoExtractor
						.interpretLanguage(str);
				int strLength = str.length();
				while (transitionRes.hasNext()) {
					ExtractedString rs = transitionRes.next();
					if (rs.getKey().equals(CodeType.ANOTATION)) {
						// System.out.println(rs.getStr());
						if (javaAnnotationAnalyst.isTransition(rs.getStr())) {
							Transition trans = javaAnnotationAnalyst
									.analysisTransition(rs.getStr());
							TransitionAno transAno = new TransitionAno();
							transAno.setMethodName(trans.getMethod());
							if (!transAnoHelper.contains(transAno)) {
//								System.out.println("没有：" + trans.getMethod() + transAnoHelper.getOffset());
								transAno.setOffset(transAnoHelper.getOffset());
								transAno.setLength(rs.getStr().length());
								transAnoHelper.add(transAno);
							}

						}
					}

				}
				transAnoHelper.setOffset(transAnoHelper.getOffset() + strLength + TransitionAno.newlinesLength);
			}//while			
		}// if
	}// readoffset

	
	public TransitionAnoHelper getTransAnoHelper() {
		return transAnoHelper;
	}


	public void setTransAnoHelper(TransitionAnoHelper transAnoHelper) {
		this.transAnoHelper = transAnoHelper;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadOffset test = new ReadOffset();
		test.readOffset(new File(
				"F:/bishe_workspace/CRFBoard/src/wangy/board/questionexpert/LineChipper.java"));
	}




	

	
}
