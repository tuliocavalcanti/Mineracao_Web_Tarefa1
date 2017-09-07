package crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class SiteCrawlerRunnable implements Runnable{
	private String theme;
	private LinkedList<Link> queue; //maybe a TreeSet
	private String siteUrl;
	
	public SiteCrawlerRunnable(String siteUrl, String theme) {
		this.siteUrl = siteUrl;
		this.theme = theme;
		this.queue = new LinkedList<Link>();
	}

	@Override
	public void run() {
		getAllPageLinks(siteUrl);
		while(!this.queue.isEmpty()) {
			visit();
			try {
				Thread.sleep(4 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getAllPageLinks(String siteUrl) {
		try {
			Response response = Jsoup.connect(siteUrl)
					.userAgent("Mozilla")
					.referrer("http://www.google.com")
					.timeout(12000)
					.execute();
			if(response.contentType().startsWith("text")) {
				Document doc = response.parse();
				Elements links = doc.select(".list-identifier a");
				Elements titles = doc.select(".list-title.mathjax");
				String[] titlesArray = titles.text().replaceAll(" ", "_").replaceAll(":", "_").split("Title__");
				
				links.removeIf(s -> !s.absUrl("href").contains("pdf"));
				for (int i = 1; i < titlesArray.length; i++) { //skips the first item
					this.queue.add(new Link(links.get(i-1).absUrl("href"), titlesArray[i]));
				}
				//this.queue.forEach(s -> System.out.println(s.articleName + " : " + s.url));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void visit() {
		Link linkPDF = this.queue.poll();
		try {
			savePage(linkPDF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void savePage(Link linkPDF) throws IOException {
		InputStream input = null;
	    OutputStream output = null;
	    HttpURLConnection connection = null;
	    File file = null;
	    try {
	        URL url = new URL(linkPDF.url);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.connect();

	        // expect HTTP 200 OK, so we don't mistakenly save error report
	        // instead of the file
//	        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
//	            return "Server returned HTTP " + connection.getResponseCode()
//	                    + " " + connection.getResponseMessage();
//	        }

	        // this will be useful to display download percentage
	        // might be -1: server did not report the length
	        int fileLength = connection.getContentLength();

	        // download the file
	        input = connection.getInputStream();
	        file = new File("Docs/"+ this.theme+"/"+linkPDF.articleName+".pdf");
	        file.getParentFile().mkdirs();
	        output = new FileOutputStream(file);

	        byte data[] = new byte[4096];
	        int count;
	        while ((count = input.read(data)) != -1) {
	            output.write(data, 0, count);
	        }
	    } finally {
	        try {
	            if (output != null)
	                output.close();
	            if (input != null)
	                input.close();
	        } catch (IOException ignored) {
	        }

	        if (connection != null)
	            connection.disconnect();
	    }
	}
}
