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

	public String process() {
		Elements textBlocks = this.element.children();
		StringBuilder str = new StringBuilder("");
		for (Element iter : textBlocks) {
			if (iter.tag().toString().equals("h3")) {
				str.append(iter.text() + "\n");
			}
			if (iter.tag().toString().equals("ul")) {
				Elements markers = iter.getElementsByTag("li");
				for (Element marker : markers) {
					str.append("- " + marker.text() + "\n");;
				}
			}
			if (iter.tag().toString().equals("div")) {
				Block block = new Block(iter);
				str.append(block.process() + "\n");
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
}

