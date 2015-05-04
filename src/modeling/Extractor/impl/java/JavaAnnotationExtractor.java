package modeling.Extractor.impl.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modeling.Extractor.interf.CodeType;
import modeling.Extractor.interf.java.JavaExtractor;
import modeling.model.ExtractedString;



public class JavaAnnotationExtractor extends JavaExtractor{

	//记录是否处于注释中
	private boolean inAnotation = false;
	//记录是否处于“”中
	private boolean invalid = false;

	public JavaAnnotationExtractor(){
		this.initialize();
	}

	public void initialize(){
		this.setKey("@", "ano");
	}

	@Override
	public Iterator<ExtractedString> interpretLanguage(String str) {
		// TODO Auto-generated method stub
		//存放结果
		List<ExtractedString> res = new ArrayList<ExtractedString>();
		
//		StringBuffer match = new StringBuffer();
//		StringBuffer other = new StringBuffer();
		
		char temp[] = str.toCharArray();
		int l = temp.length;
		
		//记录开始位置
		int matchStart = 0;
		int otherStart = 0;

		for(int i = 0 ; i<l-1 ; i++){
			//判断进出"
			if(temp[i] == '"'&&inAnotation == false){ 
				if(invalid == false){
					invalid = true;
				}
				else 
					invalid = false;
			}
			//当遇到"//"的处理
//			if(temp[i] == '/'&&temp[i+1] == '/'&&invalid == false){
//				if(inAnotation == false){
//					res.add(new RecognizedString(str.substring(otherStart, i),otherStart, i,AnalysisKey.MAIN));
//					res.add(new RecognizedString(str.substring(i+2,l),i+2,l,AnalysisKey.ANOTATION));
//					otherStart = l;
//					break;
//				}
//			}
			//当遇到"/*"的处理
			else if(temp[i] == '/'&&temp[i+1] == '*'&&invalid == false){
				if(inAnotation == false){
					matchStart = i+2;
					res.add(new ExtractedString(str.substring(otherStart, i),otherStart, i,CodeType.MAIN));
					inAnotation = true;
				}
			}
			//当遇到"*/"的处理
			else if(temp[i] == '*'&&temp[i+1] == '/'&&invalid == false){
				if(inAnotation = true){
					inAnotation = false;
					otherStart = i+2;
					res.add(new ExtractedString(str.substring(matchStart, i),matchStart, i,CodeType.ANOTATION));
				}
			}
		}
		
		if(!inAnotation&&otherStart!=l)
			res.add(new ExtractedString(str.substring(otherStart, l),otherStart, l,CodeType.MAIN));
		if(inAnotation&&matchStart!=l)
			res.add(new ExtractedString(str.substring(matchStart, l),matchStart, l,CodeType.ANOTATION));
		
		return res.iterator();
	}
	
	public static void main(String arg[]){
		JavaAnnotationExtractor jar = new JavaAnnotationExtractor();
		String str = "/*@第一个*/for(int a = '0;'a<10;a++){asd asd;aefs;}"+System.getProperty("line.separator")+"/*nihao*/for(int a = '0;'a<10;a++){asd asd;aefs;}for(int a = '0;'a<10;a++){asd asd;aefs;}";
		System.out.println(str);
		Iterator<ExtractedString> res = jar.interpretLanguage(str);
		System.out.println("begin:");
		while(res.hasNext()){
			System.out.println(res.next().getStr());
		}
}

}
