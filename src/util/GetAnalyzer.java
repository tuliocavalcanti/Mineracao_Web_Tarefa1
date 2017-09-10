package util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class GetAnalyzer {

	public static Analyzer stopWordsStemmingAnalyzer(){
		return new EnglishAnalyzer();
	}
	public static Analyzer stemmingAnalyzer(){
		CharArraySet stopWords = new CharArraySet(0, true); 
		return new EnglishAnalyzer(stopWords);
	}
	
	public static Analyzer stopWordsAnalyzer(){
		return new StandardAnalyzer();
	}
	
	public static Analyzer emptyAnalyzer(){
		CharArraySet stopWords = new CharArraySet(0, true); 
		return new StandardAnalyzer(stopWords);
	}
}
