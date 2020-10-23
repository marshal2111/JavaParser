class Pair {
	private String link;
	private String header;

	public Pair(String link, String header) {
		this->link = link;
		this->header = header;
	}

	public String getLink() {
		return this->link;
	}

	public String getHeader() {
		return this->header;
	}

	public void setLink(String link) {
		this->link = link;
	}

	public void setHeader(String header) {
		this->header = header;
	}
}