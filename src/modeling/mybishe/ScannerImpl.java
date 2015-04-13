package modeling.mybishe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import modeling.Extractor.Extractor;
import modeling.Extractor.JavaAnnotationExtractor;


public class ScannerImpl{



	private BufferedReader reader;
	private File file;


	public ScannerImpl(){
		
	}

//	public String scanFile(File file){
//
//		StringBuffer strBuffer = new StringBuffer();
//		
//
//
//		try {
//			reader = new BufferedReader(new FileReader(file));
//			String tempString = null;
//
//			if(recognizer == null)
//				while((tempString = reader.readLine()) != null){
//					strBuffer.append(tempString+"\r\n");
//				}
//
//			else
//				while((tempString = reader.readLine()) != null){
////					//				System.out.println("input:"+tempString);
////					interString = interpreter.interpretLanguage(tempString);
////					if(interString[0]!=null&&!interString[0].equals(""))
////						//					System.out.println("bingo");
////						res.append(interString[0]+"\r\n");
//				}
//
//			reader.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return strBuffer.toString();
//	}

	/**
	 * 读取一句话，若已到结尾则返回end
	 * @param file
	 */
	public String ScanSentence(File file){

		try {
			if(file.equals(this.file)){
				String temp;

				if((temp = reader.readLine()) != null)
					return temp;
				else{
					reader.close();
					return "end";
				}					
			}
			else{
				reader = new  BufferedReader(new FileReader(file));
				this.file = file;
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "start";
		
	}


	public static void main(String args[]){
				ScannerImpl s = new ScannerImpl();
				File file = new File("f:\\test.txt");
				String sentence;
				while(!(sentence = s.ScanSentence(file)).equals("end")){
					System.out.println(sentence);
				}
	}

}
