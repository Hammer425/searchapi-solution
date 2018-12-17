package com.eu.search.handler;

import java.util.function.Function;

import org.jsoup.nodes.Element;

import com.eu.search.model.HypermediaLink;
import com.eu.search.model.LinkType;

public class ElementToLink implements Function<Element, HypermediaLink> {
	
private LinkType type;
	
	ElementToLink(LinkType type) {
        this.type = type;
    }

	@Override
	public HypermediaLink apply(Element element) {
		
		
		HypermediaLink hypermediaLink = new HypermediaLink();
        hypermediaLink.setType(type.getValue());
        switch (type) {
            case LINK:
                hypermediaLink.setLink(element.attr("abs:href"));
                break;
            case IMPORT:
                hypermediaLink.setLink(element.attr("abs:href"));
                break;
            case MEDIA:
                hypermediaLink.setLink(element.attr("abs:src"));
                break;
        }

        return hypermediaLink;
	}

}
