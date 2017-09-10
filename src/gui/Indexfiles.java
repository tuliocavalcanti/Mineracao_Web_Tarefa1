package gui;

import facade.Facade;

public class Indexfiles {

	public static void main(String[] args) {
		System.out.println("Indexing Files...");
		Facade fachada = new Facade();
		fachada.indexFiles();
		
		System.out.println("Done");
	}
}
