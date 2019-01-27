/**
 * 
 */
package com.gamesys.reader.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.gamesys.reader.model.Entry;
import com.gamesys.reader.service.EntryService;

/**
 * @author olcay
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(RssFeedController.class)
public class RssFeedControllerMockTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	EntryService entryService;

	@Test
	public void testGetTop10RssFeedMock() throws Exception {
		List<Entry> list = new ArrayList<>();
		Entry entry1 = new Entry("Olcay", "title", "comment", "link", new Date(), "description");
		Entry entry2 = new Entry("Olcay", "title", "comment", "link", new Date(), "description");
		Entry entry3 = new Entry("Olcay", "title", "comment", "link", new Date(), "description");
		Entry entry4 = new Entry("Olcay", "title", "comment", "link", new Date(), "description");
		Entry entry5 = new Entry("Olcay", "title", "comment", "link", new Date(), "description");
		Entry entry6 = new Entry("Olcay", "title", "comment", "link", new Date(), "description");
		Entry entry7 = new Entry("Olcay", "title", "comment", "link", new Date(), "description");
		Entry entry8 = new Entry("Olcay", "title", "comment", "link", new Date(), "description");
		Entry entry9 = new Entry("Olcay", "title", "comment", "link", new Date(), "description");
		Entry entry10 = new Entry("Olcay", "title", "comment", "link", new Date(), "description");
		list.add(entry1);
		list.add(entry2);
		list.add(entry3);
		list.add(entry4);
		list.add(entry5);
		list.add(entry6);
		list.add(entry7);
		list.add(entry8);
		list.add(entry9);
		list.add(entry10);

		when(entryService.getLastEntries()).thenReturn(list);
		RequestBuilder request = MockMvcRequestBuilders.get("/api/entries").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(jsonPath("$.size()", Matchers.is(10)));
	}
}
