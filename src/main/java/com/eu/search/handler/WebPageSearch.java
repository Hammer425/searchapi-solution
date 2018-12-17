package com.eu.search.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.eu.search.exceptions.ErrorMessages;
import com.eu.search.model.HypermediaLink;
import com.eu.search.model.LinkGroup;
import com.eu.search.model.LinkType;
import com.eu.search.model.ResponseEntity;
import com.eu.search.model.SearchResponse;


@Component
public class WebPageSearch {

	public ResponseEntity<SearchResponse> fectchPageDetails(@NotNull String pageURI) {

		Connection connection = null;
		try {
			connection = Jsoup.connect(pageURI).timeout(5000);
		} catch (IllegalArgumentException iae) {

			return ResponseEntity.error(ErrorMessages.INVALID_URL_FORMAT);
		}

		Objects.requireNonNull(connection);

		Document document = null;
		try {
			document = connection.get();
		} catch (IllegalArgumentException e) {

			return ResponseEntity.error(ErrorMessages.INVALID_HTTP_URL);
		} catch (IOException e) {

		}

		Objects.requireNonNull(document);

		List<String> imageList = getPageImage(document);

		Map<LinkGroup, Set<HypermediaLink>> links = getHypermediaLinks(pageURI, document);

		SearchResponse searchResponse = new SearchResponse();
		searchResponse.setHypermediaLinks(links);
		searchResponse.setImages(imageList);

		return ResponseEntity.success(searchResponse);

	}

	private Map<LinkGroup, Set<HypermediaLink>> getHypermediaLinks(String submittedUrl, Document document) {

		Elements links = document.select("a[href]");
		Elements media = document.select("[src]");
		Elements imports = document.select("link[href]");

		Set<HypermediaLink> anchorTagList = collectHypermediaLinks(links, LinkType.LINK);

		Set<HypermediaLink> importList = collectHypermediaLinks(imports, LinkType.IMPORT);

		Set<HypermediaLink> mediaList = collectHypermediaLinks(media, LinkType.MEDIA);

		Set<HypermediaLink> allLinks = new HashSet<>(anchorTagList);
		allLinks.addAll(importList);
		allLinks.addAll(mediaList);

		Map<LinkGroup, Set<HypermediaLink>> groupByDomain = allLinks.stream()
				.collect(groupingBy(new ElementToLinkGroup(submittedUrl), Collectors.toSet()));

		return groupByDomain;

	}

	private Set<HypermediaLink> collectHypermediaLinks(Elements links, LinkType linkType) {

		return links.stream().map(new ElementToLink(linkType)).collect(Collectors.toSet());
	}

	private List<String> getPageImage(Document document) {

		List<String> imageList = new ArrayList<>();

		try {
			Elements imageElements = document.getElementsByTag("img");

			for (Element image : imageElements) {

				imageList.add(image.attr("src"));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return imageList;
	}

}
