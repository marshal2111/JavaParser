package com.company.parser;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class Parser {
	private String url;
	private String dir;
	PairList parsedLinks;

	public Parser() {
		this.url = "";
		this.dir = "";
		this.parsedLinks = new PairList();
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
       		if ((!parsedLinks.containsLink(link)) && (!link.equals(""))) {
       			parsedLinks.add(new Pair(link, iter.text()));
       		}
       	}
       	String text;
       	for (int i = 0; i < parsedLinks.size() - 1; i++) {
       		text = parsePage(parsedLinks.get(i).getLink());
       		writeToFile(text, parsedLinks.get(i).getHeader());
       	}
	}

	private String parsePage(String link) throws IOException{
		Document doc = Jsoup.connect(link).get();
		Element postEntry = doc.getElementsByClass("post-entry").first();
        Element textBlock = postEntry.child(1);
        StringBuilder str = new StringBuilder(processBlock(textBlock));
        return str.toString();
	}

	public String processBlock(Element textBlock) throws IOException{
		Elements textBlocks = textBlock.children();
		StringBuilder str = new StringBuilder("");
		for (Element iter : textBlocks) {
			if (iter.tag().toString().equals("h3")) {
				str.append(iter.text() + "\n");
				Elements linkBlocks = iter.getElementsByTag("a");
        		String linkNext;
        		if (linkBlocks.size() != 0) {
        			for (Element linkIter : linkBlocks) {	
        				linkNext = linkIter.attr("href");
        				if (linkNext.contains(".html")) {
        					System.out.println(linkNext);
        					str.append(parsePage(linkNext));
        				}
        			}	
        		}
			}
			if (iter.tag().toString().equals("ul")) {
				Elements markers = iter.getElementsByTag("li");
				for (Element marker : markers) {
					str.append("- " + marker.text() + "\n");;
				}
			}
			if (iter.tag().toString().equals("div")) {
				str.append(processBlock(iter) + "\n");
			}
			if (iter.tag().toString().equals("span")) {
				if ((iter.children().size() != 0) && (iter.child(0).tag().toString().equals("br"))){
					str.append("\n");
				}
				str.append(iter.text());
			}
			if (iter.tag().toString().equals("br")) {
				str.append("\n");
			}
		}
		return str.toString();
	}

	private void writeToFile(String text, String name) {
		if (name.contains("/")) 
			name = name.replace("/", " ");
		try  {
			File file = new File(this.dir, name + "txt");
			if (!file.exists())
				file.createNewFile();
			try (FileWriter fileWriter = new FileWriter(this.dir + "/" + name + "txt")) {
				fileWriter.write(text);
				fileWriter.close();
			} 
			catch (IOException exW) {
				System.out.println(exW.getMessage() + " WHILE WRITING TO " + name + ".txt"); 
			}
		}
		catch (IOException exC) {
			System.out.println(exC.getMessage() + " WHILE CREATING FILE " + name + ".txt");
		}
	}	
}