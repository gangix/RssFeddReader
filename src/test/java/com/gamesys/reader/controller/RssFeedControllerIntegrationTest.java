/**
 * 
 */
package com.gamesys.reader.controller;

import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gamesys.reader.model.Entry;
import com.gamesys.reader.service.EntryService;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

/**
 * @author olcay
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RssFeedControllerIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	@Autowired
	EntryService entryService;

	@Before
	public void generateData() throws Exception  {
		URL feedSource = new URL("https://twitrss.me/twitter_user_to_rss/?user=estonia_eu");
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(feedSource));
		entryService.buildAndSaveEntries(feed);
	}

	@Test
	public void testGetTop10RssFeed() {

		ResponseEntity<Entry[]> response = template.getForEntity("/api/entries", Entry[].class);

		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getStatusCode().value());
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(10, response.getBody().length);
	}

}
