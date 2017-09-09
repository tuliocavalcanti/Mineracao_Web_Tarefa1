package util;

public class IndexedFileMapper {
	
	public static int getIndexedFileInt(boolean isUsingStemming, boolean isUsingStopwords) {
		if(isUsingStopwords) {
			//using Stopwords
			if(isUsingStemming) {
				/*analyzer with english stopwords and stemming*/
				return 0;
				
			}else {
				/*analyzer with english stopwords*/
				return 2;
			}
		}else {
			//not using Stopwords
			if(isUsingStemming) {
				/*analyzer with only stemming*/
				return 3;
			}else {
				//default analyzer
				return 1;
			}
		}
	}
}
