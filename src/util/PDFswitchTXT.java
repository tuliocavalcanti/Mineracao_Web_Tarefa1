package util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFswitchTXT {

	public final static String DOC_DIRECTORY = System.getProperty("user.dir") + "\\Docs";
	public final static String TEXT_DIRECTORY = System.getProperty("user.dir") + "\\Texts";
	
	public static Path txtToPDF(Path path){
		Path p = path.subpath(path.getNameCount()-1, path.getNameCount());
		return Paths.get(DOC_DIRECTORY +"/"+ p);
	}
	
	public static Path PDFtoTxt(Path path){
		Path p = path.subpath(path.getNameCount()-1, path.getNameCount());
		return Paths.get(TEXT_DIRECTORY +"/"+ p);
	}
	
}
