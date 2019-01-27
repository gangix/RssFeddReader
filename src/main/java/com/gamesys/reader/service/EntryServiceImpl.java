package com.gamesys.reader.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamesys.reader.model.Entry;
import com.gamesys.reader.repository.EntryRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

@Service
public class EntryServiceImpl implements EntryService {

	private static final String[] BAD_WORDS = { "fee", "nee", "cruul", "leent" };
	
	@Autowired
	EntryRepository entryRepository;

	@Override
	public List<Entry> getLastEntries() {
		return entryRepository.findTop10ByOrderByTransactionDateTimeDescPublishDateTimeDesc();
	}

	@Override
	public Iterable<Entry> buildAndSaveEntries(SyndFeed feed) {
		String description = feed.getDescription();
		List<Entry> entryList = new ArrayList<>();
		List<SyndEntry> entries = feed.getEntries();
		for (SyndEntry syndEntry : entries) {
			String comments = syndEntry.getComments();
			boolean existsBadWord = existsBadWord(comments);
			if(existsBadWord) {
				comments ="";
			}
			String title = syndEntry.getTitle();
			String author = syndEntry.getAuthor();
			String link = syndEntry.getLink();
			Date dateTime = syndEntry.getPublishedDate();
			
			Entry entry = new Entry(author, title, comments, link, dateTime, description);
			entryList.add(entry);
		}
		return entryRepository.saveAll(entryList);
	}
	
	private boolean existsBadWord(String comment) {
		if(comment == null) {
			return false;
		}
		return Arrays.stream(BAD_WORDS).parallel().anyMatch(comment::contains);
	}

}
