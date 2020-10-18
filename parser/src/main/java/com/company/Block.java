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

	}
}

