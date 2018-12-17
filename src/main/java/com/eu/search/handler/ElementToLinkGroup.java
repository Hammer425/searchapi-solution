package com.eu.search.handler;

import java.util.function.Function;

import com.eu.search.model.HypermediaLink;
import com.eu.search.model.LinkGroup;
import com.eu.search.utils.URIUtils;

public class ElementToLinkGroup implements Function<HypermediaLink, LinkGroup> {

	private String submittedUrl;

	ElementToLinkGroup(String submittedUrl) {
		this.submittedUrl = submittedUrl;
	}

	@Override
	public LinkGroup apply(HypermediaLink hypermediaLink) {
		String url = hypermediaLink.getLink();

		
		if (URIUtils.isAbsoluteUrl(url) && !URIUtils.isSameDomain(submittedUrl, url)) {
			return LinkGroup.EXTERNAL;
		} else {
			return LinkGroup.INTERNAL;
		}
	}

}
