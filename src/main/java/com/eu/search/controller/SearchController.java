package com.eu.search.controller;


import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eu.search.handler.WebPageSearch;
import com.eu.search.model.ResponseEntity;
import com.eu.search.model.SearchResponse;


@RestController
public class SearchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

	
	@Autowired
    private WebPageSearch webPageSearch;
	
	@GetMapping(value = "/fetch", produces = "application/json")
    public ResponseEntity<SearchResponse> analysePage(@NotNull @RequestParam(value = "pageURI", required = true) final String pageURI) {
        return webPageSearch.fectchPageDetails(pageURI);
    }

}