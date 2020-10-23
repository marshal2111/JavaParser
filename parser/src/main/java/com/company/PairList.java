package com.company.parser;

import java.util.ArrayList;

public class PairList extends ArrayList<Pair> {
	public boolean containsLink(String link) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getLink().equals(link)) 
				return true;
		}
		return false;
	}
}