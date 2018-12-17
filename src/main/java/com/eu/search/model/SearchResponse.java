package com.eu.search.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchResponse {

	private Map<LinkGroup, Set<HypermediaLink>> hypermediaLinks;
	private List<String> images;

	public Map<LinkGroup, Set<HypermediaLink>> getHypermediaLinks() {
		return hypermediaLinks;
	}

	public void setHypermediaLinks(Map<LinkGroup, Set<HypermediaLink>> hypermediaLinks) {
		this.hypermediaLinks = hypermediaLinks;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
	
}
