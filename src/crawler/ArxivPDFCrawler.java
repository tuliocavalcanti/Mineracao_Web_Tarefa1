package crawler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArxivPDFCrawler {

	public static void main(String[] args) {
		System.out.println("Running...");
		List<String> baseUrls = new ArrayList<String>();
		baseUrls.add("https://arxiv.org/list/cs.AI/pastweek?show=47");
		baseUrls.add("https://arxiv.org/list/cs.IT/pastweek?show=68");
		baseUrls.add("https://arxiv.org/list/cs.CV/pastweek?show=129");
		
		Iterator<String> it = baseUrls.iterator();
		
		while(it.hasNext()) {
			String url = it.next();
			new Thread(new SiteCrawlerRunnable(url,url.substring(26, 28))).start();
		}
	}

}
