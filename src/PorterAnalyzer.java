
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.AnalyzerWrapper;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.TypeTokenFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class PorterAnalyzer extends AnalyzerWrapper {

  private Analyzer baseAnalyzer;

  public PorterAnalyzer(Analyzer baseAnalyzer) {
	  super(Analyzer.PER_FIELD_REUSE_STRATEGY);
      this.baseAnalyzer = baseAnalyzer;
  }

  @Override
  public void close() {
      baseAnalyzer.close();
      super.close();
  }

  @Override
  protected Analyzer getWrappedAnalyzer(String fieldName)
  {
      return baseAnalyzer;
  }
  
  @Override
  protected TokenStreamComponents wrapComponents(String fieldName, TokenStreamComponents components)
  {
      TokenStream ts = components.getTokenStream();
      Set<String> filteredTypes = new HashSet<>();
      filteredTypes.add("<NUM>");
      TypeTokenFilter numberFilter = new TypeTokenFilter(ts, filteredTypes);

      PorterStemFilter porterStem = new PorterStemFilter(numberFilter);
      return new TokenStreamComponents(components.getTokenizer(), porterStem);
  }

  
  public static void main(String[] args) throws IOException
  {
	  //CharArraySet stopWords = new CharArraySet(0, true);
      //Analyzer analyzer = new StandardAnalyzer(stopWords);
      PorterAnalyzer analyzer = new PorterAnalyzer(new StandardAnalyzer());
      String text = "This is a testing example. It should tests the Porter stemmer version 111";

      TokenStream ts = analyzer.tokenStream("fieldName", new StringReader(text));
      ts.reset();
      
      while (ts.incrementToken()){
          CharTermAttribute ca = ts.getAttribute(CharTermAttribute.class);

          System.out.println(ca.toString());
      }
      analyzer.close();
  }

  
}