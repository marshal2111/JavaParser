package com.company.parser;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException {

        String url = "https://jsehelper.blogspot.com/2016/05/java-8-1.html";
        String path = "test2.txt";

        Document doc = Jsoup.connect(url).get();  
        Element postEntry = doc.getElementsByClass("post-entry").first();

        Element textBlock = postEntry.child(1);

        Block block = new Block(textBlock);
        StringBuilder str = new StringBuilder(block.process());

        try(PrintWriter writer = new PrintWriter(path, "UTF-8")) {
            writer.println(str);
        } 
        catch(IOException ex){   
            System.out.println(ex.getMessage());
        }   
    }
}
