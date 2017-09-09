package facade;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.TopDocs;

import lucene.Searcher;
import lucene.Writer;

public class Facade {
	
	private static final Path DOCS_DIR = Paths.get("Texts");
	private static final String INDEX_DIR = "indexedFiles";
	private Searcher searcher;
	private Writer writer;
	
	public Facade() {
		this.searcher = new Searcher(INDEX_DIR);
		this.writer = new Writer(DOCS_DIR, INDEX_DIR);
	}
	
	public void indexFiles() {
		this.writer.createIndexedBases();
	}
	
	public TopDocs searchFiles(String query, boolean isUsingStemming, boolean isUsingStopwords) throws IOException, ParseException {
		return this.searcher.search(query, isUsingStemming, isUsingStopwords);
	}
}
