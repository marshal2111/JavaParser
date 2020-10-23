package com.company.parser;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;

public class Parser {
	private String url;
	private String dir;
	ArryList<String> parsedLinks;

	public Parser() {
		this->url = "";
		this->dir = "";
		this->parsedLinks = new ArryList<>();
	}

	public setAdress(String url) {
		this->url = url;
	}

	public setPath(String path) {
		this->dir = path;
	}

	public parseToFiles() {
		Document doc = Jsoup.connect(this->url).get();  
		Element postEntry = doc.getElementsByClass("post-entry").first();
		Element linkBlocks = postEntry.child(1).children();
		Strink link;
       	for (Element iter : linkBlocks) {
       		link = iter.html();
       		if (!parsedLinks.contains(link)) {
       			//parsePage(link, )
       			System.out.println(link);
       		}
       	}
	}

	private parsePage(Document doc, String path) {
		Element postEntry = doc.getElementsByClass("post-entry").first();

        Element dataBlock = postEntry.child(1);

        Block block = new Block()
        StringBuilder str = new StringBuilder(this->parseBlock(dataBlock));

        try(PrintWriter writer = new PrintWriter(path, "UTF-8")) {
            writer.println(str);
        } 
        catch(IOException ex){   
            System.out.println(ex.getMessage());
        }   
	}

	private parseBlock(Element datdBlock) {
		
	}
}