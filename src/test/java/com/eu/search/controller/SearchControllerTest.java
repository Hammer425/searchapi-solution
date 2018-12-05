package com.eu.search.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.eu.search.model.PageContent;
import com.eu.search.service.SearchService;



@RunWith(SpringJUnit4ClassRunner.class)
public class SearchControllerTest {
	
	//@Autowired
    private MockMvc mockMvc;
	@InjectMocks
    private SearchController searchController;
	private PageContent pageContent;
    
    @MockBean
    private SearchService searchService;
    
    
    @Before
    public void setUp() throws Exception {
    	
    	mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }
    
    @Test
    public void testFecthDetails() throws Exception {
    	
    	mockMvc.perform(MockMvcRequestBuilders.get("/fetch").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    	
    	Mockito.when(searchService.fectchPageDetails(Mockito.anyString()))
        .thenReturn(pageContent);
    	
	}

}
