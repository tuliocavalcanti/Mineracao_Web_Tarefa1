package util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFswitchTXT {

	public final static String DOC_DIRECTORY = System.getProperty("user.dir") + "\\Docs";
	public final static String TEXT_DIRECTORY = System.getProperty("user.dir") + "\\Texts";
	
	public static Path txtToPDF(Path path){
		int txts_folder_index = Paths.get(TEXT_DIRECTORY).getNameCount();
		Path p = path.subpath(txts_folder_index, path.getNameCount());
		return Paths.get(DOC_DIRECTORY +"/"+ p);
	}
	
	public static Path PDFtoTxt(Path path){
		int txts_folder_index = Paths.get(DOC_DIRECTORY).getNameCount();
		Path p = path.subpath(txts_folder_index, path.getNameCount());
		return Paths.get(TEXT_DIRECTORY +"/"+ p);
	}
	
}
