package util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFswitchTXT {

	public final static String DOC_DIRECTORY = System.getProperty("user.dir") + "\\Docs";
	public final static String TEXT_DIRECTORY = System.getProperty("user.dir") + "\\Texts";
	
	public static Path txtToPDF(Path path){
		Path p = path.subpath(1, path.getNameCount());
		String string = p.toString();
		string = string.substring(0, string.length()-3);
		return Paths.get(DOC_DIRECTORY +"/"+ string + "pdf");
	}
	
	public static Path PDFtoTxt(Path path){
		Path p = path.subpath(1, path.getNameCount());
		String string = p.toString();
		string = string.substring(0, string.length()-3);
		return Paths.get(TEXT_DIRECTORY +"/"+ string + "txt");
				
	}
	
}
