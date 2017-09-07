package pdfconverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;



public class PDFtoTXT {

	public final static String DOC_DIRECTORY = System.getProperty("user.dir") + "\\Docs";
	public final static String TEXT_DIRECTORY = "Texts\\";
	
	public static void main(String args[]) throws IOException {
		
		tryGetFileFromDir(Paths.get(DOC_DIRECTORY));
		
    }
    
	static void tryGetFileFromDir(Path path) throws IOException{

		if (Files.isDirectory(path)){

			Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
					tryGetFileFromDir(file);
					return FileVisitResult.CONTINUE;
				}
			});
		} 
		else{
			try{
				writeInTxtFile(path, getText(path.toFile()));
			} 
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}
    
	private static void writeInTxtFile(Path path,String text){
		
		//remove the "C:" from the path
		Path doc_dir = Paths.get(DOC_DIRECTORY).subpath(0, Paths.get(DOC_DIRECTORY).getNameCount());
		
		int docs_folder_index = 1;
		
		for(; docs_folder_index <path.getNameCount(); docs_folder_index++){
			
			if(path.subpath(0, docs_folder_index).equals(doc_dir)){
				
				break;
			}
		}

		Path p = path.subpath(docs_folder_index, path.getNameCount());

		if(p.getNameCount()>1){
			Path dir = p.subpath(0, p.getNameCount()-1);
			File file_dir = new File(TEXT_DIRECTORY+dir);
			if(!file_dir.exists()){
				try{
					file_dir.mkdir();
				} 
				catch(SecurityException se){ 
					System.out.println("Error creating Directory.");
				}
			}
		}

		BufferedWriter writer = null;

		try{

			File file = new File(TEXT_DIRECTORY+p.toString().substring(0,p.toString().length()-3)+"txt");

			writer = new BufferedWriter(new FileWriter(file));

			writer.write(text);
			
			writer.close();
		} 
		catch (Exception e){

			e.printStackTrace();
		} 
	}
	
    static String getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }
}
