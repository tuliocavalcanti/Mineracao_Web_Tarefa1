package lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import util.IndexedFileMapper;

public class Searcher {
	
	private String INDEX_DIR;
	
	public Searcher(String indexDir) {
		this.INDEX_DIR = indexDir;
	}
	
	public TopDocs search(String textQuery, boolean isUsingStemming, boolean isUsingStopwords) throws IOException, ParseException{

		IndexSearcher searcher = getIndexSearcher(isUsingStemming,isUsingStopwords);

		Analyzer analyzer = getAnalyzer(isUsingStemming,isUsingStopwords);

		QueryParser qp = new QueryParser("contents", analyzer);

		Query query = qp.parse(textQuery);

		TopDocs hits = searcher.search(query, 236);

		return hits;

	}

	private Analyzer getAnalyzer(boolean isUsingStemming, boolean isUsingStopwords){
		
		if(isUsingStopwords) {
			//using Stopwords
			if(isUsingStemming) {
				/*analyzer with english stopwords and stemming*/
				return new PorterAnalyzer(new EnglishAnalyzer());
			}else {
				/*analyzer with english stopwords*/
				return new EnglishAnalyzer();
			}
		}else {
			//not using Stopwords
			if(isUsingStemming) {
				/*analyzer with only stemming*/
				return new PorterAnalyzer(getEmptyAnalyzer());
			}else {
				//default analyzer
				return getEmptyAnalyzer();
			}
		}
	}

	private Analyzer getEmptyAnalyzer(){
		
		CharArraySet stopWords = new CharArraySet(0, true);
		
		return new StandardAnalyzer(stopWords);
	}


	private IndexSearcher getIndexSearcher(boolean isUsingStemming, boolean isUsingStopwords) throws IOException{

		Directory dir = FSDirectory.open(Paths.get(INDEX_DIR+"_"+IndexedFileMapper.getIndexedFileInt(isUsingStemming, isUsingStopwords)));

		IndexReader reader = DirectoryReader.open(dir);

		return new IndexSearcher(reader);
	}
}
