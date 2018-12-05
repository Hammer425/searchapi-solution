package com.eu.search.controller;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eu.search.model.PageContent;
import com.eu.search.service.SearchService;


@RestController
public class SearchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	SearchService searchService;

	@GetMapping(value = "/fetch", produces = "application/json")
	public ResponseEntity<PageContent> fecthDetails(
			@NotNull @RequestParam(value = "pageURI", required = true) final String pageURI) throws IOException {

		LOGGER.debug("Controller invoked with data");
		PageContent pageContent = searchService.fectchPageDetails(pageURI);
		if (pageContent == null) {
			LOGGER.info("PageContent not found");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(pageContent, HttpStatus.OK);

	}

}