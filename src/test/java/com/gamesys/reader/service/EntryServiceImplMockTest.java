package com.gamesys.reader.service;

import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gamesys.reader.model.Entry;
import com.gamesys.reader.repository.EntryRepository;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@RunWith(MockitoJUnitRunner.class)
public class EntryServiceImplMockTest {

	@InjectMocks
	EntryService entryService;
	@Mock
	EntryRepository entryRepository;


	@Test
	public void save() throws Exception {
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
		
		when(entryRepository.saveAll(list)).thenReturn(list);

		URL feedSource = new URL("https://twitrss.me/twitter_user_to_rss/?user=estonia_eu");
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(feedSource));
		Iterable<Entry> savedEntries = entryService.buildAndSaveEntries(feed);
		Assert.assertNotNull(savedEntries);
	}

}
