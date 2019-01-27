package com.gamesys.reader;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.gamesys.reader.service.EntryService;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Configuration
public class FeedReaderConfiguration {
	@Autowired
	private EntryService entryService;

	@Value("${url.rss.feed}")
	private String rssFeed;

	@Scheduled(fixedRate = 120_000)
	public void heartBeat() throws Exception {
		URL feedSource = new URL(rssFeed);
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(feedSource));
		entryService.buildAndSaveEntries(feed);
	}

}
