package com.eu.search.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eu.search.model.PageContent;
import com.eu.search.model.PageUrl;
import com.eu.search.service.SearchService;


@Service
public class SearchServiceImpl implements SearchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

	@Override
	public PageContent fectchPageDetails(String pageUri) {

		LOGGER.info("URI Request : " + pageUri);

		PageContent pageContent = null;
		List<String> urlList = new ArrayList<>();

		try {
			Document doc = Jsoup.connect(pageUri).get();
			Elements links = doc.select("a[href]");

			for (Element link : links) {

				urlList.add(link.attr("href"));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (!urlList.isEmpty()) {
			pageContent = new PageContent();
			List<PageUrl> pageUrlList = new ArrayList<>();

			urlList.stream().forEach(links -> {
				PageUrl pageUrl = new PageUrl();
				pageUrl.setLink(links);
				pageUrlList.add(pageUrl);
			});
			pageContent.setPageUrlList(pageUrlList);
		}

		return pageContent;

	}

}