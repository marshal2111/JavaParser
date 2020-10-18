package com.company.parser;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.IOException; 

public class Main
{
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://jsehelper.blogspot.com/2016/01/blog-post_9.html").get();  
       	Element postEntry = doc.getElementsByClass("post-entry").first();

       	Element textBlock = postEntry.child(1);

        Block block = new Block(textBlock);
        StringBuilder str = new StringBuilder(block.process());

        System.out.println(str);
    }
}
