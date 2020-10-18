package com.company.parser;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class Block {
	private Element element;

	public Block(Element element) {
		this.element = element;
	}

	public void setElement(Element element)
	{
		this.element = element;
	}

	public Element getElement()
	{
		return this.element;
	}

	public void process() {
		Elements textBlocks = this.element.children();
		for (Element iter : textBlocks) {
			if (iter.tag().toString().equals("h3")) {
				System.out.println(iter.text());
			}
			if (iter.tag().toString().equals("ul")) {
				Elements markers = iter.getElementsByTag("li");
				for (Element marker : markers) {
					System.out.println(marker.text());
				}
			}
			if (iter.tag().toString().equals("div")) {
				Block block = new Block(iter);
				block.process();
				System.out.println();
			}
			if (iter.tag().toString().equals("span")) {
				if ((iter.children().size() != 0) && (iter.child(0).tag().toString().equals("br"))){
					System.out.print("");
				}
				System.out.print(iter.text());
			}
		}
	}
}

