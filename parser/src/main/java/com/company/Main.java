package com.company.parser;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException {

        Parser parser = new Parser();
        parser.setAdress("https://jsehelper.blogspot.com/2016/01/blog-post_9.html");
        parser.setPath("files");
        parser.parseToFiles();

        // String url = "https://jsehelper.blogspot.com/2016/05/java-8-1.html";
        // String path = "test2.txt";
    }
}
