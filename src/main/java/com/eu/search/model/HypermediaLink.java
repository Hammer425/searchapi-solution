package com.eu.search.model;

public class HypermediaLink {
	
	private String link;
    private String type;
    
    
    
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "HypermediaLink [link=" + link + ", type=" + type + "]";
	}

}
