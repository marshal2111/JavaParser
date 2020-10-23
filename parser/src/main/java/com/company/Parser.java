package com.company.parser;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.io.IOException;

public class Parser {
	private String url;
	private String dir;
	ArrayList<String> parsedLinks;

	public Parser() {
		this.url = "";
		this.dir = "";
		this.parsedLinks = new ArrayList<>();
	}

	public void setAdress(String url) {
		this.url = url;
	}

	public void setPath(String path) {
		this.dir = path;
	}

	public void parseToFiles() throws IOException {
		Document doc = Jsoup.connect(this.url).get();  
		Element postEntry = doc.getElementsByClass("post-entry").first();
		Elements linkBlocks = postEntry.getElementsByTag("a");
		String link;
       	for (Element iter : linkBlocks) {
       		link = iter.attr("href");
       		if (!parsedLinks.contains(link)) {
       			parsedLinks.add(link);
       		}
       	}
       	String str;
       	for (int i = 0; i < parsedLinks.length() - 1; i++) {
       		str = parsePage(parsedLinks[i]);
       		//System.out.println(str);
       	}
	}

	private void parsePage(Document doc) {
	}
}