package com.gamesys.reader.service;

import java.util.List;

import com.gamesys.reader.model.Entry;
import com.rometools.rome.feed.synd.SyndFeed;

public interface EntryService {
	List<Entry> getLastEntries();
	
	Iterable<Entry> buildAndSaveEntries(SyndFeed feed);
}
