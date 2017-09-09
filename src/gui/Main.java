package gui;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

import facade.Facade;

public class Main {

	public static void main(String[] args) {
		Facade fachada = new Facade();
		fachada.indexFiles();
		
		System.out.println("Done");
	}
}
