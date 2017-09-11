package gui;

import facade.Facade;

public class IndexFiles {
	public static void main(String[] args) {
		System.out.println("Indexing Files...");
		Facade fachada = new Facade();
		fachada.indexFiles();
		
		System.out.println("Done");
	}
}
