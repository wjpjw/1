package visualization;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import config.Config;

public class Visualization {
	private Visualization(){}
	private static Visualization instance=new Visualization();
	public static Visualization get_instance(){
		return instance;
	}
	public void visualize_statechart(){
	    Image image = new Image(Display.getCurrent(), Config.get_canvas_path()); 
	    GC gc = new GC(image); 
	    Rectangle bounds = image.getBounds(); 
	    gc.drawLine(0,0,bounds.width,bounds.height); 
	    gc.drawLine(0,bounds.height,bounds.width,0); 
	    saveImage(Config.get_statechart_path(), image.getImageData());
	    gc.dispose(); 
	    image.dispose(); 
	}
	
    public static ImageData[] readImage(String fileName) {  
        ImageLoader loader = new ImageLoader();  
        return loader.load(fileName);  
    }  
    //format: png
    public static void saveImage(String fileName, ImageData data) {  
        ImageLoader loader = new ImageLoader();  
        loader.data = new ImageData[1];  
        loader.data[0]=data;
        loader.save(fileName, SWT.IMAGE_PNG);  
    }  

	
}
