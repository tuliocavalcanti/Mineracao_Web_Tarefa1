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

import util.PDFswitchTXT;



public class PDFtoTXT {

	
	public static void main(String args[]) throws IOException {
		
		tryGetFileFromDir(Paths.get(PDFswitchTXT.DOC_DIRECTORY));
		
    }
    
	private static void tryGetFileFromDir(Path path) throws IOException{

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
		
		int docs_folder_index = Paths.get(PDFswitchTXT.DOC_DIRECTORY).getNameCount();
		
		Path p = path.subpath(docs_folder_index, path.getNameCount());
		
		createFolderFromPath(p);
		
		write(p,text);
	}
	
	private static void write(Path path, String text){
		BufferedWriter writer = null;

		try{

			File file = new File(PDFswitchTXT.TEXT_DIRECTORY+path.toString().substring(0,path.toString().length()-3)+"txt");

			writer = new BufferedWriter(new FileWriter(file));

			writer.write(text);
			
			writer.close();
		} 
		catch (Exception e){

			e.printStackTrace();
		} 
	}
	
	
	private static void createFolderFromPath(Path path){
		if(path.getNameCount()>1){
			Path dir = path.subpath(0, path.getNameCount()-1);
			File file_dir = new File(PDFswitchTXT.TEXT_DIRECTORY+dir);
			if(!file_dir.exists()){
				try{
					file_dir.mkdir();
				} 
				catch(SecurityException se){ 
					System.out.println("Error creating Directory.");
				}
			}
		}
	}
	
    private static String getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        return new PDFTextStripper().getText(doc);
    }
}
